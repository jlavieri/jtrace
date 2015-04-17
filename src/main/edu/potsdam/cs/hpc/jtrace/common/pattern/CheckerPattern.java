package edu.potsdam.cs.hpc.jtrace.common.pattern;

import edu.potsdam.cs.hpc.jtrace.common.Vec3;

public class CheckerPattern implements ListPattern
{
    @Override
    public int evaluate (Vec3 v)
    {
        return (mod(v.x) + mod(v.y) + mod(v.z)) % 2;
    }
    
    private static int mod (double x)
    {
        return ((int)Math.round(x + 0.5000000000000004d) % 2 + 2) % 2;
    }
}
