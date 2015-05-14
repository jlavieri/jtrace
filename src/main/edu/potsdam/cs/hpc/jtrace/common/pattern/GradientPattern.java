package edu.potsdam.cs.hpc.jtrace.common.pattern;

import edu.potsdam.cs.hpc.jtrace.common.Vec3;

/**
 * 1D sawtooth gradient isovalued in xz-plane.
 */
public class GradientPattern implements MapPattern
{
    private static final long serialVersionUID = -4277246673032561303L;

    @Override
    public double evaluate (Vec3 v)
    {
        return Math.abs(v.y % 1.0d);
    }
}
