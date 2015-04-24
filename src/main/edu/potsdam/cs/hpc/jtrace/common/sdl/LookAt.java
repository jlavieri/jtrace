package edu.potsdam.cs.hpc.jtrace.common.sdl;

import edu.potsdam.cs.hpc.jtrace.common.Vec3;

final class LookAt extends Vec3Type
{
    LookAt (Vec3 lookAt)
    {
        super(lookAt);
    }
    
    LookAt (double x, double y, double z)
    {
        super(x, y, z);
    }
}
