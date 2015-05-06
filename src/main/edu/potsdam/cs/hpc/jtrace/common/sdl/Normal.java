package edu.potsdam.cs.hpc.jtrace.common.sdl;

import edu.potsdam.cs.hpc.jtrace.common.Vec3;


final class Normal extends Vec3Type
{
    Normal (Vec3 normal)
    {
        super(normal);
    }

    Normal (double x, double y, double z)
    {
        super(x, y, z);
    }
}
