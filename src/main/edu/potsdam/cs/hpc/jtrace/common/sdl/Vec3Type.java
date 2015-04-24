package edu.potsdam.cs.hpc.jtrace.common.sdl;

import edu.potsdam.cs.hpc.jtrace.common.Vec3;

class Vec3Type
{
    Vec3 v;

    Vec3Type ()
    {
        this.v = Vec3.O;
    }

    Vec3Type (Vec3 v)
    {
        this.v = v;
    }

    Vec3Type (double x, double y, double z)
    {
        v = new Vec3(x, y, z);
    }
}
