package edu.potsdam.cs.hpc.jtrace.common;

import static org.junit.Assert.*;

import org.junit.Test;

public class RayTest
{
    @Test
    public void constructorTest ()
    {
        Ray r = new Ray(Vec3.X, Vec3.Y);
        assertNotNull(r);
        assertEquals(Vec3.X, r.position);
        assertEquals(Vec3.Y, r.direction);
    }

    @Test
    public void positionTest ()
    {
        Ray r = new Ray(Vec3.O, Vec3.X);
        assertEquals(new Vec3(20, 0, 0), r.position(20));
        r = new Ray(new Vec3(0.2, 0.4, 0.6), new Vec3(0.1, 0.2, 0.3));
        assertEquals(new Vec3(1.2, 2.4, 3.6), r.position(10));
    }
}
