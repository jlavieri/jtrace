package edu.potsdam.cs.hpc.jtrace;

import java.awt.Dimension;

/**
 * @author lavierijp
 * @version 2012-06-23
 * @since 2012-06-23
 */
class OrthoCamera implements Camera
{
    Vec3 pos, dir, up, left;
    double w, h, sw, sh;

    OrthoCamera(Dimension d, Vec3 pos, Vec3 dir)
    {
        this(d, pos, dir, Vec3.Y, d.width, d.height);
    }

    OrthoCamera(Dimension d, Vec3 pos, Vec3 dir, Vec3 up, double w, double h)
    {
        this.pos = pos;
        this.dir = dir;
        this.up = up;
        this.w = w;
        this.h = h;
        sw = d.width;
        sh = d.height;
        left = dir.cross(up);
    }

    private static double planarPos(double s, double d, double sd)
    {
        return d / 2d - s * d / sd;
    }

    @Override
    public Ray getRay(int x, int y)
    {
        return new Ray(pos.add(left.mul(planarPos(x, w, sw)))
                .sadd(up.mul(planarPos(y, h, sh))), dir);
    }

    @Override
    public void initialize(Dimension d)
    {
        // TODO Auto-generated method stub

    }
}