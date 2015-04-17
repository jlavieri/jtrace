package edu.potsdam.cs.hpc.jtrace.common;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.potsdam.cs.hpc.jtrace.common.pattern.CheckerPattern;
import edu.potsdam.cs.hpc.jtrace.common.pattern.ListPattern;

public class PatternTest
{
    @Test
    public void checkerBoardTest ()
    {
        ListPattern p = new CheckerPattern();
        assertEquals(0, p.evaluate(new Vec3(-0.3, 0.3, 0.0)));
        assertEquals(1, p.evaluate(new Vec3(0.3, 0.3, 0.0)));
        assertEquals(0, p.evaluate(new Vec3(1.3, 0.3, 0.0)));
    }
}
