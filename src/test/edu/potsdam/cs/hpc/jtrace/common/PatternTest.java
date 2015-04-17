package edu.potsdam.cs.hpc.jtrace.common;

import org.junit.Test;

import edu.potsdam.cs.hpc.jtrace.common.pattern.CheckerPattern;
import edu.potsdam.cs.hpc.jtrace.common.pattern.ListPattern;

public class PatternTest
{
    @Test
    public void checkerBoardTest ()
    {
        ListPattern p = new CheckerPattern();
        for (double i = 0; i < 4; i += 0.1)
            for (double j = 0; j < 4; j += 0.1)
                for (double k = 0; k < 4; k += 0.1) {
                    Vec3 v = new Vec3(i, j, k);
                    double d = p.evaluate(v);
                    System.out.printf("%s: %s\n", v, d);
                }
    }
}
