package edu.potsdam.cs.hpc.jtrace;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintStream;

import javax.imageio.ImageIO;

/**
 * @author lavierijp
 * @version 2012-06-23
 * @since 2012-03-15
 */
class Renderer
{
    // TODO move these to render settings or global settings
    private static final int MAX_DEPTH = 5;
    private static final double ADC_BAILOUT = 1D / 255D;
    private static final double MAX_DIS = 1E10D;
    private static final boolean QUICK_COLOUR = false;

    private final Scene scene;
    private final RenderSettings renderSettings;
    
    //Extract these from renderSettings for sugar.
    private final int width, height;

    private int pixelCount, rayCount, shadowRayCount;

    public Renderer(Scene scene, RenderSettings renderSettings)
    {
        this.scene = scene;
        this.renderSettings = renderSettings;
        width = renderSettings.dimension.width;
        height = renderSettings.dimension.height;
    }

    private Color [][] render()
    {
        Color [][] image = new Color[height][width];
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++) {
                pixelCount++;
                image[y][x] = trace(scene.c.getRay(x, y), 0);
            }
        printStatistics();
        return image;
    }

    private Color trace(Ray ray, int depth)
    {
        rayCount++;
        double dis = MAX_DIS;
        Geom obj = null;
        for (Geom r : scene.objects) {
            double d = r.intersect(ray);
            if (d < 0d)
                continue;
            if (d < dis) {
                dis = d;
                obj = r;
            }
        }
        if (obj != null) {
            if (QUICK_COLOUR)
                return obj.tex.pig.clr;
            Vec3 p = ray.pos(dis);
            Color c = new Color();
            for (Light l : scene.lights) {
                double its = 1d;
                double disl = l.pos.dis(p);
                loi: for (Geom r : scene.objects) {
                    double [] disa = r.intersecta(new Ray(l.pos, p
                            .direction(l.pos)));
                    shadowRayCount++;
                    // MAYBE-DO Validate intersections.
                    for (int i = 0; i < disa.length; i += 2)
                        if (disa[i] < disl)
                            if (disa[i + 1] < disl
                                    || Maths.equ(disa[i + 1], disl)) {
                                its *= r.tex.pig.clr.t;
                                if (its == 0d)
                                    break loi; // obj is in full shadow.
                            } // else obj is inside r!
                }
                c.sadd(scene.al).sadd(obj.tex.pig.clr.mul(its));
            }
            return c;
        }
        return scene.bg;
    }

    void renderToFile()
    {
        Color [][] image = render();
        
        BufferedImage bi = new BufferedImage(width, height,
                                             BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                bi.setRGB(j, i, image[i][j].toInt());
        try {
            ImageIO.write(bi, "png", renderSettings.outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printStatistics()
    {
        PrintStream o = System.out;
        o.println("Render Statistics:");
        o.println("\tPixels: " + pixelCount);
        o.println("\tRays: " + rayCount);
        o.println("\tShadow Rays: " + shadowRayCount);
    }

    void debug(Object o)
    {
        System.out.println(o);
    }
}