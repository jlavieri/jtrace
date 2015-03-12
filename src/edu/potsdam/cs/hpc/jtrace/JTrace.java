package edu.potsdam.cs.hpc.jtrace;

/**
 * JTrace.
 *
 * @author  lavierijp
 * @version 0.0.0.1 2012-06-23
 * @since   0.0.0.0 2012-03-15
 */
public class JTrace
{
  public static void main (String [] args)
  {
    long start = System.currentTimeMillis();
    System.out.println("Rendering");
    new Renderer(Scene.S).render("D:\\jtrace.png");
    //System.out.println(new Colour(0.5d, 0.5d, 1d).toInt());
    System.out.println("Done in " + (System.currentTimeMillis() - start) +
                       " ms.");
  }
}