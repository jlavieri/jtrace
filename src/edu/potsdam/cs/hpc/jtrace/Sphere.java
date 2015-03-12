package edu.potsdam.cs.hpc.jtrace;



/**
 * @author  lavierijp
 * @version 2012-06-23
 * @since   2012-03-15
 */
class Sphere
  extends Renderable
{
  private final Vec3 pos;
  private final double rad;

  Sphere (Vec3 pos, double rad)
  {
    this.pos = pos;
    this.rad = rad;
  }

  @Override
  public double intersect (Ray r)
  {
    Vec3 c = pos.sub(r.pos);
    double b = r.dir.dot(c);
    double dsc = b * b - c.dot(c) + rad * rad;
    if (dsc < 0)
      return -1;
    if (dsc == 0)
      return b;
    return b - Math.sqrt(dsc);
  }

  @Override
  double [] intersecta (Ray r)
  {
    Vec3 c = pos.sub(r.pos);
    double b = r.dir.dot(c);
    double dsc = b * b - c.dot(c) + rad * rad;
    if (dsc <= 0)
      return new double[0];
    double sdsc = Math.sqrt(dsc);
    double [] ret = {b - sdsc, b + sdsc};
    return ret;
  }
}