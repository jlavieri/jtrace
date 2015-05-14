package edu.potsdam.cs.hpc.jtrace.common;

import java.awt.image.BufferedImage;
import java.io.PrintStream;

import edu.potsdam.cs.hpc.jtrace.common.color.Color;
import edu.potsdam.cs.hpc.jtrace.common.light.Light;
import edu.potsdam.cs.hpc.jtrace.common.sdl.Scenes;

public class Renderer
{
    // TODO move these to render settings or global settings
    private static final int MAX_DEPTH = 8;
    private static final double ADC_BAILOUT = 1D / 255D;
    private static final double MAX_DIS = 1E10D;
    
    private static final double SPECULAR_POWER = 21.0d;
    
    private static final Color[] DEPTH_COLOR = {Color.RED, Color.ORANGE,
        Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA, Color.BLACK};

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
        this(Scenes.getSceneFromFile(renderSettings.inputFile), renderSettings);
    }
    
    public Renderer (Scene scene, RenderSettings renderSettings)
    {
        this.scene = scene;
        this.renderSettings = renderSettings;
        width = renderSettings.dimension.width;
        height = renderSettings.dimension.height;
        scene.camera.initialize(renderSettings.dimension);
    }

    private Color trace(Ray ray)
    {
        return trace(ray, 0, null);
    }

    /**
     * Traces a ray recursively.
     * 
     * @param ray
     *            The ray to trace.
     * @param depth
     *            The recursion depth. Casts start at 0.
     * 
     * @return The radiance in the scene.
     */
    private Color trace (Ray ray, int depth, Geom iGeom)
    {
        if (depth > MAX_DEPTH)
            return null;

        // Calculate the closest intersection of the ray with scene geoms.
        Geom geom = null;
        double smallestDistance = MAX_DIS;

        // TODO scene.geoms needs to be a filtered set of geoms by visbounding
        for (Geom gitr : scene.geoms) {
            if (gitr == iGeom)
                continue;
            double distance = gitr.primitive.intersect(ray);
            if (distance < 0d)
                continue;
            if (distance < smallestDistance) {
                smallestDistance = distance;
                geom = gitr;
            }
        }

        // If the ray hits nothing return the background color or the skysphere.
        if (geom == null)
            if (scene.skySphere == null)
                return scene.globalSettings.background;
            else
                return scene.skySphere.getRadiance(ray);

        geomHitCount++;

        // If quick color is on then simply return the basic color of the geom.
        if (scene.globalSettings.quickColor)
            return geom.quickColor;

        // This is the intersection point of the geom with the ray.
        Vec3 intersectionPoint = ray.position(smallestDistance);
        Vec3 intersectionNormal = geom.primitive.normalOf(intersectionPoint);

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

            double normDotLight = intersectionNormal.dot(pointToLight);

            // Diffuse Lighting
            if (geom.material.texture.finish.diffuse > 0.0d) {
                if (normDotLight > 0) {
                    double diffuse = normDotLight
                            * geom.material.texture.finish.diffuse * shade;
                    radiance.addeq(pigment.mult(diffuse).multeq(light.color));
                }
            }

            // Specular Lighting
            if (geom.material.texture.finish.specular > 0.0d) {
                double rayReflection = ray.direction.dot(pointToLight
                        .reflect(intersectionNormal, normDotLight));
                if (rayReflection > 0) {
                    double specular = Math.pow(rayReflection, SPECULAR_POWER)
                            * geom.material.texture.finish.specular * shade;
                    radiance.addeq(light.color.mult(specular));
                }
            }
        }

        // Reflection
        if (geom.material.texture.finish.reflection > 0.0d && depth < MAX_DEPTH) {
            Vec3 reflectionDirection = ray.direction
                    .reflect(intersectionNormal,
                             ray.direction.dot(intersectionNormal));
            Ray reflectionRay = new Ray(intersectionPoint, reflectionDirection);
            reflectionRayCount++;
            Color reflectionColor = trace(reflectionRay, depth + 1, geom);
            radiance.addeq(reflectionColor
                    .mult(geom.material.texture.finish.reflection)
                    .multeq(pigment));
        }

        return radiance;
    }
    
    /**
     * Traces a ray recursively.
     * 
     * @param ray
     *            The ray to trace.
     * @param depth
     *            The recursion depth. Casts start at 0.
     * @return The max depth reached in the scene.
     */
    private int traceDepth (Ray ray, int depth)
    {
        int maxDepth = DEPTH_COLOR.length - 1;

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

        // If the ray hits nothing return the current depth.
        if (geom == null)
            return depth;

        geomHitCount++;

        // This is the intersection point of the geom with the ray.
        Vec3 intersectionPoint = ray.position(smallestDistance);
        Vec3 intersectionNormal = geom.primitive.normalOf(intersectionPoint);

        // Reflection
        if (geom.material.texture.finish.reflection > 0.0d && depth < maxDepth) {
            Vec3 reflectionDirection = ray.direction.
                    sub(intersectionNormal
                        .mul(2.0d * ray.direction
                             .dot(intersectionNormal)))
                    ;
            Ray reflectionRay = new Ray(intersectionPoint, reflectionDirection);
            reflectionRayCount++;
            int recDepth = traceDepth(reflectionRay, depth + 1);
            return recDepth > maxDepth ? maxDepth : recDepth;
        }

        return depth;
    }
    
    private Color traceIntersection (Ray ray, int depth)
    {
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

        // If the ray hits nothing return black.
        if (geom == null)
            return Color.BLACK;

        geomHitCount++;

        // This is the intersection point of the geom with the ray.
        Vec3 iP = ray.position(smallestDistance);
        Vec3 iN = geom.primitive.normalOf(iP);
        //return new Color(Math.abs(iN.x), Math.abs(iN.y), Math.abs(iN.z));
        return new Color(Math.abs(iP.x), Math.abs(iP.y), Math.abs(iP.z));
        //return new Color(iP.x, iP.y, iP.z);
    }

    private Color traceReflection (Ray ray)
    {
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

        // If the ray hits nothing return the background color or the skysphere.
        if (geom == null)
            return Color.BLACK;

        geomHitCount++;

        // This is the intersection point of the geom with the ray.
        Vec3 intersectionPoint = ray.position(smallestDistance);
        Vec3 intersectionNormal = geom.primitive.normalOf(intersectionPoint);

        // Reflection
        if (geom.material.texture.finish.reflection > 0.0d) {
            Vec3 rD = ray.direction
                    .reflect(intersectionNormal,
                             ray.direction.dot(intersectionNormal)).abs();
            reflectionRayCount++;
            return new Color(rD.x, rD.y, rD.z);
        }

        return Color.WHITE;
    }

    public BufferedImage render ()
    {
        long start = System.currentTimeMillis();

        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++) {
                eyeRayCount++;
                image.setRGB(x, y,
             trace(scene.camera.getRay(x, y)).toInt()
             //DEPTH_COLOR[traceDepth(scene.camera.getRay(x, y), 0)].toInt()
             //traceIntersection(scene.camera.getRay(x, y), 0).toInt()
             //traceReflection(scene.camera.getRay(x, y)).toInt()
                        );
            }

        long stop = System.currentTimeMillis();
        time = stop - start;
        printStatistics();
        return image;
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
