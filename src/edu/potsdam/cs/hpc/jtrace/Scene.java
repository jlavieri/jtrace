package edu.potsdam.cs.hpc.jtrace;

import java.awt.Dimension;



/**
 * @author  lavierijp
 * @version 2012-06-23
 * @since   2012-03-15
 */
class Scene
{
  static final Scene S = new Scene();

  Dimension d = new Dimension(640, 480);
  Colour bg = new Colour(0.0d, 0.0d, 0.0d);
  Colour al = new Colour(0.1d);

  Camera c = new PerspectCamera(d, new Vec3(5d, 2.5d, 5d), Vec3.O);

  Light [] lights = {
    new PointLight(new Vec3( 20d, 40d, -20d)),
    new PointLight(new Vec3(-20d, 40d, -20d))
  };
  {
    lights[0].clr.set(1d, 1d, 1d);
  }

  Renderable [] objects = {
    new Sphere(new Vec3(0d, 1d, 0d), 1d),
    new Plane(Vec3.O, Vec3.Y)
  };
  {
    objects[0].tex.pig.clr.set(0d, 0d, 1d, 0d, 0.0d);
    objects[1].tex.pig.clr.set(0.5, 0.5, 0.5);
  }

  private Scene () {}
}