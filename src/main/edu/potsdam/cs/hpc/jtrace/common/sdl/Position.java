package edu.potsdam.cs.hpc.jtrace.common.sdl;

import edu.potsdam.cs.hpc.jtrace.common.Vec3;


final class Position extends Vec3Type
{
    Position (Vec3 position)
    {
        super(position);
    }

    Position (double x, double y, double z)
    {
        super(x, y, z);
    }
}
