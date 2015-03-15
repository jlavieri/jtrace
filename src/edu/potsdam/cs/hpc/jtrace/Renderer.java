package edu.potsdam.cs.hpc.jtrace;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintStream;

import javax.imageio.ImageIO;

/**
 * @author lavierijp
 * @version 2015-03-13
 * @since 2012-03-15
 */
class Renderer
{
    // TODO move these to render settings or global settings
    private static final int MAX_DEPTH = 5;
    private static final double ADC_BAILOUT = 1D / 255D;
    private static final double MAX_DIS = 1E10D;
    private static final boolean QUICK_COLOUR = true;

    private final Scene scene;
    private final RenderSettings renderSettings;

    // Extract these from renderSettings for sugar.
    private final int width, height;

    // Statistics
    private int eyeRayCount, reflectionRayCount, refractionRayCount,
            shadowRayCount;
    private long time;

    public Renderer(Scene scene, RenderSettings renderSettings)
    {
        this.scene = scene;
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
    private Color trace(Ray ray, int depth)
    {
        // Calculate the closest intersection of the ray with scene geoms.
        Geom closestGeom = null;
        double smallestDistance = MAX_DIS;

        // TODO scene.geoms needs to be a filtered set of geoms by visbounding
        for (Geom geom : scene.geoms) {
            double distance = geom.primitive.intersect(ray);
            if (distance < 0d)
                continue;
            if (distance < smallestDistance) {
                smallestDistance = distance;
                closestGeom = geom;
            }
        }

        // If the ray hits nothing return the background color.
        if (closestGeom == null)
            return scene.globalSettings.background;

        // If quick color is on then simply return the basic color of the geom.
        if (QUICK_COLOUR)
            return closestGeom.material.texture.pigment.color;
        
        // Otherwise we really do need to find the radiance of this ray.
        Color radiance = new Color(); // Radiance will start out black.
        
        // This is the position on the geom that the ray hits.
        Vec3 position = ray.position(smallestDistance);
        
        
        for (Light light : scene.lights) {
            
        }
        
        for (Light light : scene.lights) {
            double its = 1d;
            double distanceToLight = light.position.distanceTo(position);
            loi: for (Geom g : scene.geoms) {
                double [] disa = g.primitive.intersecta(new Ray(light.position,
                        position.directionTo(light.position)));
                shadowRayCount++;
                // MAYBE-DO Validate intersections.
                for (int i = 0; i < disa.length; i += 2)
                    if (disa[i] < distanceToLight)
                        if (disa[i + 1] < distanceToLight
                                || Maths.equ(disa[i + 1], distanceToLight)) {
                            its *= g.material.texture.pigment.color.t;
                            if (its == 0d)
                                break loi; // geom is in full shadow.
                        } // else geom is inside g!
            }
            radiance.sadd(scene.globalSettings.ambientLight)
                    .sadd(closestGeom.material.texture.pigment.color.mul(its));
        }
        return radiance;
    }

    void render()
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

    private void printStatistics()
    {
        PrintStream o = System.out;
        o.println("Render Statistics:");
        o.printf("\tTime: %d ms\n", time);
        o.printf("\tEye Rays: %d\n", eyeRayCount);
        o.printf("\tRecflection Rays: %d\n", reflectionRayCount);
        o.printf("\tRefracted Rays: %d\n", refractionRayCount);
        o.printf("\tShadow Rays: %d\n", shadowRayCount);
    }

    void debug(Object o)
    {
        System.out.println(o);
    }
}