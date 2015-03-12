package edu.potsdam.cs.hpc.jtrace;



/**
 * @author  lavierijp
 * @version 2012-06-23
 * @since   2012-03-15
 */
class Ray
{
  final Vec3 pos, dir;

  Ray (Vec3 pos, Vec3 dir)
  {
    this.pos = pos;
    this.dir = dir;
  }

  Vec3 pos (double t)
  {
    return pos.add(dir.mul(t));
  }

  @Override
  public String toString ()
  {
    return pos + ", " + dir;
  }
}