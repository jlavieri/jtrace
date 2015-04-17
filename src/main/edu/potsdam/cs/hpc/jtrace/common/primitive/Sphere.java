package edu.potsdam.cs.hpc.jtrace.common.primitive;

import edu.potsdam.cs.hpc.jtrace.common.Ray;
import edu.potsdam.cs.hpc.jtrace.common.Vec3;

public class Sphere extends Primitive
{
    private final Vec3 position;
    private final double radius;

    public Sphere (Vec3 position, double radius)
    {
        this.position = position;
        this.radius = radius;
    }

    @Override
    public double intersect (Ray ray)
    {
        Vec3 c = position.sub(ray.position);
        double b = ray.direction.dot(c);
        double dsc = b * b - c.dot(c) + radius * radius;
        if (dsc < 0)
            return -1;
        if (dsc == 0)
            return b;
        return b - Math.sqrt(dsc);
    }

    @Override
    public Vec3 normalOf (Vec3 point)
    {
        return position.directionTo(point);
    }

    @Override
    public String toString ()
    {
        return String.format("Sphere(%s, %s)", position, radius);
    }
}
