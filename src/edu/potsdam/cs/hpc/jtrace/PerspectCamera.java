package edu.potsdam.cs.hpc.jtrace;

import java.awt.Dimension;

/**
 * @author  lavierijp
 * @version 2012-06-23
 * @since   2012-06-23
 */
class PerspectCamera
  implements Camera
{
  Vec3 pos, dir, up, left, cpo;
  double sw, sh, d, w, h;

  PerspectCamera (Dimension dim, Vec3 pos, Vec3 lkp)
  {
    this.pos = pos;
    this.dir = lkp.dir(pos);
    this.up = Vec3.Y;
    this.sw = dim.width;
    this.sh = dim.height;
    this.d = 1d;
    this.w = 2d * Math.tan(Math.toRadians(30d));
    this.h = w * sw / sh;
    left = dir.crs(up);
    cpo = pos.add(dir.mul(d));
  }

  PerspectCamera (Dimension dim, Vec3 pos, Vec3 dir, Vec3 up, double d, double w, double h)
  {
    this.pos = pos;
    this.dir = dir;
    this.up = up;
    this.sw = dim.width;
    this.sh = dim.height;
    this.d = d;
    this.w = w;
    this.h = h;
    left = dir.crs(up);
    cpo = pos.add(dir.mul(d));
  }

  private static double planarPos(double s, double d, double sd)
  {
    return d / 2d - s * d / sd;
  }

  @Override
  public Ray getRay (int x, int y)
  {
    return new Ray(pos, cpo.add(left.mul(planarPos(x, w, sw)))
                            .sadd(up.mul(planarPos(y, h, sh))).dir(pos));
  }
}