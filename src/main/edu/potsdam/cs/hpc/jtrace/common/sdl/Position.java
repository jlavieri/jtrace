package edu.potsdam.cs.hpc.jtrace.common.sdl;

import edu.potsdam.cs.hpc.jtrace.common.Vec3;


final class Position extends Vec3Type
{
    Position ()
    {
        super();
    }

    Position (Vec3 v)
    {
        super(v);
    }

    Position (double x, double y, double z)
    {
        super(x, y, z);
    }
}
