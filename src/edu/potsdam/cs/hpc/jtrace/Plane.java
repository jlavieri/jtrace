package edu.potsdam.cs.hpc.jtrace;


/**
 * @author  lavierijp
 * @version 2012-06-23
 * @since   2012-06-23
 */
class Plane
  extends Renderable
{
  Vec3 pos, nrm;

  Plane (Vec3 pos, Vec3 nrm)
  {
    this.pos = pos;
    this.nrm = nrm;
  }

  @Override
  double intersect (Ray ray)
  {
    double d = ray.dir.dot(nrm);
    if (d == 0)
      return -1d;
    return pos.sub(ray.pos).dot(nrm) / d;
  }

  @Override
  double [] intersecta (Ray ray)
  {
    return new double[0]; // Planes never cast a shadow.
  }
}