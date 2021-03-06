package edu.potsdam.cs.hpc.jtrace.common.pattern;

import edu.potsdam.cs.hpc.jtrace.common.Vec3;

public interface MapPattern extends Pattern
{
    public double evaluate (Vec3 v);
}
