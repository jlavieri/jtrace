package edu.potsdam.cs.hpc.jtrace.common;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintStream;

import javax.imageio.ImageIO;

import edu.potsdam.cs.hpc.jtrace.common.color.Color;
import edu.potsdam.cs.hpc.jtrace.common.light.Light;
import edu.potsdam.cs.hpc.jtrace.common.scenes.Scenes;

class Renderer
{
    // TODO move these to render settings or global settings
    private static final int MAX_DEPTH = 5;
    private static final double ADC_BAILOUT = 1D / 255D;
    private static final double MAX_DIS = 1E10D;

    private final Scene scene;
    private final RenderSettings renderSettings;

    // Extract these from renderSettings for sugar.
    private final int width, height;

    // Statistics
    private int eyeRayCount, reflectionRayCount, refractionRayCount,
            shadowRayCount, geomHitCount;
    private long time;

    public Renderer (RenderSettings renderSettings)
    {
        this.scene = Scenes.getSceneFromFile(renderSettings.inputFile);
        this.renderSettings = renderSettings;
        width = renderSettings.dimension.width;
        height = renderSettings.dimension.height;
        scene.camera.initialize(renderSettings.dimension);
    }

    /**
     * Traces a ray recursively.
     * 
     * @param ray
     *            The ray to trace.
     * @param depth
     *            The recursion depth. Casts start at 0.
     * @return The radiance in the scene.
     */
    private Color trace (Ray ray, int depth)
    {
        if (depth > MAX_DEPTH)
            return null;

        // Calculate the closest intersection of the ray with scene geoms.
        Geom geom = null;
        double smallestDistance = MAX_DIS;

        // TODO scene.geoms needs to be a filtered set of geoms by visbounding
        for (Geom gitr : scene.geoms) {
            double distance = gitr.primitive.intersect(ray);
            if (distance < 0d)
                continue;
            if (distance < smallestDistance) {
                smallestDistance = distance;
                geom = gitr;
            }
        }

        // If the ray hits nothing return the background color.
        if (geom == null)
            return scene.globalSettings.background;

        geomHitCount++;

        // If quick color is on then simply return the basic color of the geom.
        if (scene.globalSettings.quickColor)
            return geom.quickColor;

        // This is the intersection point of the geom with the ray.
        Vec3 intersectionPoint = ray.position(smallestDistance);
        Vec3 intersectionNorm = geom.primitive.normalOf(intersectionPoint);

        Color pigment = geom.material.texture.pigment
                .getColor(intersectionPoint);
        Color radiance = pigment.mult(scene.globalSettings.ambientLight);

        // Calculate lighting
        for (Light light : scene.lights) {

            // Shadow
            double shade = 1.0d;
            Vec3 pointToLight = intersectionPoint.directionTo(light.position);
            Ray shadowRay = new Ray(intersectionPoint, pointToLight);
            shadowRayCount++;
            for (Geom gitr : scene.geoms)
                if (gitr != geom) {
                    double distanceFromGeomToGitr = gitr.primitive
                            .intersect(shadowRay);
                    double distanceFromGeomToLight = intersectionPoint
                            .distanceTo(light.position);
                    if (distanceFromGeomToGitr > 0
                            && distanceFromGeomToGitr < distanceFromGeomToLight) {
                        shade = 0.0d;
                        break;
                    }
                }

            // Diffuse Lighting
            if (geom.material.texture.finish.diffuse > 0) {
                double dot = intersectionNorm.dot(pointToLight);
                if (dot > 0) {
                    double diffuse = dot * geom.material.texture.finish.diffuse
                            * shade;
                    radiance.addeq(pigment.mult(diffuse).multeq(light.color));
                }
            }
        }

        // Reflection
        if (geom.material.texture.finish.reflection > 0.0d && depth < MAX_DEPTH) {
            Vec3 reflectionDirection = intersectionNorm
                    .mul(2.0d * ray.direction.dot(intersectionNorm))
                    .directionTo(ray.direction);
            Ray reflectionRay = new Ray(intersectionPoint, reflectionDirection);
            reflectionRayCount++;
            Color reflectionColor = trace(reflectionRay, depth + 1);
            radiance.addeq(reflectionColor
                    .mult(geom.material.texture.finish.reflection)
                    .multeq(pigment));
        }

        return radiance;
    }

    void render ()
    {
        long start = System.currentTimeMillis();

        BufferedImage bi = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++) {
                eyeRayCount++;
                bi.setRGB(x, y, trace(scene.camera.getRay(x, y), 0).toInt());
            }
        try {
            ImageIO.write(bi, "png", renderSettings.outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        long stop = System.currentTimeMillis();
        time = stop - start;
        printStatistics();
    }

    private void printStatistics ()
    {
        PrintStream o = System.out;
        o.println("Render Statistics:");
        o.printf("\tTime: %d ms\n", time);
        o.printf("\tEye Rays: %d\n", eyeRayCount);
        o.printf("\tRecflection Rays: %d\n", reflectionRayCount);
        o.printf("\tRefracted Rays: %d\n", refractionRayCount);
        o.printf("\tShadow Rays: %d\n", shadowRayCount);
        o.printf("\tScene Object Ray Hits: %d\n", geomHitCount);
    }

    void debug (Object o)
    {
        System.out.println(o);
    }
}
