package edu.potsdam.cs.hpc.jtrace.common.sdl;

import edu.potsdam.cs.hpc.jtrace.common.Vec3;

class Vec3Type
{
    Vec3 vec;

    Vec3Type (Vec3 v)
    {
        vec = v;
    }

    Vec3Type (double x, double y, double z)
    {
        vec = new Vec3(x, y, z);
    }
}
