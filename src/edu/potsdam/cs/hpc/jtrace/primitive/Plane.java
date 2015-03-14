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
    Vec3 pos, nrm;

    public Plane(Vec3 pos, Vec3 nrm)
    {
        this.pos = pos;
        this.nrm = nrm;
    }

    @Override
    public double intersect(Ray ray)
    {
        double d = ray.dir.dot(nrm);
        if (d == 0)
            return -1d;
        return pos.sub(ray.pos).dot(nrm) / d;
    }

    @Override
    public double [] intersecta(Ray ray)
    {
        return new double[0]; // Planes never cast a shadow.
    }
}