package edu.potsdam.cs.hpc.jtrace.common.pattern;

import edu.potsdam.cs.hpc.jtrace.common.Vec3;

/**
 * 1D sawtooth gradient isovalued in xz-plane.
 */
public class GradientPattern implements MapPattern
{
    @Override
    public double evaluate (Vec3 v)
    {
        return Math.abs(v.y % 1.0d);
    }
}
