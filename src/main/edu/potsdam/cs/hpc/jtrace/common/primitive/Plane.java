package edu.potsdam.cs.hpc.jtrace.common.primitive;

import edu.potsdam.cs.hpc.jtrace.common.Ray;
import edu.potsdam.cs.hpc.jtrace.common.Vec3;

public class Plane extends Primitive
{
    private static final long serialVersionUID = -4923358428174984535L;
    
    Vec3 position, normal;

    public Plane(Vec3 position, Vec3 normal)
    {
        this.position = position;
        this.normal = normal;
    }

    @Override
    public double intersect(Ray ray)
    {
        double d = ray.direction.dot(normal);
        if (d == 0)
            return -1d;
        return position.sub(ray.position).dot(normal) / d;
    }

    @Override
    public Vec3 normalOf(Vec3 point)
    {
        return normal;
    }
    
    @Override
    public String toString ()
    {
        return String.format("Plane(%s, %s)", position, normal);
    }
}