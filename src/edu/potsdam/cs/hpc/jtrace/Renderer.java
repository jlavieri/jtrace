package edu.potsdam.cs.hpc.jtrace;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import javax.imageio.ImageIO;



/**
 * @author  lavierijp
 * @version 2012-06-23
 * @since   2012-03-15
 */
class Renderer
{
  private static final int MAX_DEPTH = 5;
  private static final double ADC_BAILOUT = 1D / 255D;
  private static final double MAX_DIS = 1E10D;
  private static final boolean QUICK_COLOUR = false;

  private final Scene s;
  private final Dimension d;

  private int pixelCount, rayCount, shadowRayCount;

  Renderer (Scene s)
  {
    this.s = s;
    d = s.d;
  }

  private Colour [][] render ()
  {
    Colour [][] image = new Colour[d.height][d.width];
    for (int y = 0; y < d.height; y++)
      for (int x = 0; x < d.width; x++) {
        pixelCount++;
        image[y][x] = trace(s.c.getRay(x, y), 0);
      }
    printStatistics();
    return image;
  }

  private Colour trace (Ray ray, int depth)
  {
    rayCount++;
    double dis = MAX_DIS;
    Renderable obj = null;
    for (Renderable r : s.objects) {
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
      Colour c = new Colour();
      for (Light l : s.lights) {
        double its = 1d;
        double disl = l.pos.dis(p);
        loi:
          for (Renderable r : s.objects) {
            double [] disa =
                r.intersecta(new Ray(l.pos, p.dir(l.pos)));
            shadowRayCount++;
            // MAYBE-DO Validate intersections.
            for (int i = 0; i < disa.length; i += 2)
              if (disa[i] < disl)
                if (disa[i + 1] < disl || Maths.equ(disa[i + 1], disl)) {
                  its *= r.tex.pig.clr.t;
                  if (its == 0d)
                    break loi; // obj is in full shadow.
                } // else obj is inside r!
        }
        c.sadd(s.al).sadd(obj.tex.pig.clr.mul(its));
      }
      return c;
    }
    return s.bg;
  }

  void render (String s)
  {
    Colour [][] image = render();
    BufferedImage bi = new BufferedImage(d.width, d.height,
                                         BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < d.height; i++)
      for (int j = 0; j < d.width; j++)
        bi.setRGB(j, i, image[i][j].toInt());
    try {
      ImageIO.write(bi, "png", new File(s));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void printStatistics ()
  {
    PrintStream o = System.out;
    o.println("Render Statistics");
    o.println("Pixels: " + pixelCount);
    o.println("Rays: " + rayCount);
    o.println("Shadow Rays: " + shadowRayCount);
  }

  void debug (Object o)
  {
    System.out.println(o);
  }
}