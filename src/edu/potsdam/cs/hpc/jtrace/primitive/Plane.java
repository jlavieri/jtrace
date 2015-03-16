package edu.potsdam.cs.hpc.jtrace.primitive;

import edu.potsdam.cs.hpc.jtrace.Primitive;
import edu.potsdam.cs.hpc.jtrace.Ray;
import edu.potsdam.cs.hpc.jtrace.Vec3;

/**
 * @author lavierijp
 * @version 2015-03-14
 * @since 2012-06-23
 */
public class Plane extends Primitive
{
    Vec3 position, normal;

    public Plane(Vec3 pos, Vec3 nrm)
    {
        this.position = pos;
        this.normal = nrm;
    }

    @Override
    public double intersect(Ray ray)
    {
        double d = ray.dir.dot(normal);
        if (d == 0)
            return -1d;
        return position.sub(ray.pos).dot(normal) / d;
    }

    @Override
    public double [] intersecta(Ray ray)
    {
        return new double[0]; // Planes never cast a shadow.
    }

    @Override
    public Vec3 normalOf(Vec3 point)
    {
        return normal;
    }
}