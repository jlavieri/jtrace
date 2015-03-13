package edu.potsdam.cs.hpc.jtrace.test;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.potsdam.cs.hpc.jtrace.Vec3;

public class Vec3Test
{

    @Test
    public void testDirectionTo()
    {
        double radTwo = 1.0 / Math.sqrt(2.0);
        assertEquals(new Vec3(radTwo, radTwo, 0), Vec3.O.directionTo(new Vec3(1,1,0)));
    }

    @Test
    public void mulTest()
    {
        Vec3 p = new Vec3(1, 2, 3);
        assertEquals(new Vec3(2, 4, 6), p.mul(2.0));
        assertEquals(new Vec3(1, 2, 3), p);
    }
}
