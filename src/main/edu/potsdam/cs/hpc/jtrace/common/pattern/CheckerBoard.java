package edu.potsdam.cs.hpc.jtrace.common.pattern;

import edu.potsdam.cs.hpc.jtrace.common.Vec3;

public class CheckerBoard implements Pattern
{
    @Override
    public double evaluate (Vec3 v)
    {
        return (double)((int)v.x % 2 & (int)v.y % 2 & (int)v.z % 2);
    }
}
