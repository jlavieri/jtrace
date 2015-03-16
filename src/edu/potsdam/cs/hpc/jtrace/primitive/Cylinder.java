package edu.potsdam.cs.hpc.jtrace.primitive;

import edu.potsdam.cs.hpc.jtrace.Primitive;
import edu.potsdam.cs.hpc.jtrace.Ray;
import edu.potsdam.cs.hpc.jtrace.Vec3;

/**
 * @author lavierijp
 * @version 2015-03-12
 * @since 2012-03-15
 */
public class Cylinder extends Primitive
{
    private final Vec3 position1, position2;
    private final double radius;

    public Cylinder(Vec3 position1, Vec3 position2, double radius)
    {
        this.position1 = position1;
        this.position2 = position2;
        this.radius = radius;
    }

    @Override
    public double intersect(Ray r)
    {
        // TODO
        return 0;
    }

    @Override
    public double [] intersecta(Ray r)
    {
        // TODO
        return new double[0];
    }
    
    @Override
    public String toString()
    {
        return String.format("Cylinder(%s, %s, %s)", position1, position2, radius);
    }

    @Override
    public Vec3 normalOf(Vec3 point)
    {
        // TODO Auto-generated method stub
        return null;
    }
}