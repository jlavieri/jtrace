package edu.potsdam.cs.hpc.jtrace.common.pattern;

import edu.potsdam.cs.hpc.jtrace.common.Vec3;

public class CheckerPattern implements ListPattern
{
    @Override
    public int evaluate (Vec3 v)
    {
        return (int)v.x % 2 & (int)v.y % 2 & (int)v.z % 2;
    }
}
