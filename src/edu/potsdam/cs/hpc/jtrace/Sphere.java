package edu.potsdam.cs.hpc.jtrace;

/**
 * @author lavierijp
 * @version 2015-03-12
 * @since 2012-03-15
 */
public class Sphere extends Primitive
{
    private final Vec3 position;
    private final double radius;

    public Sphere(Vec3 position, double radius)
    {
        this.position = position;
        this.radius = radius;
    }

    @Override
    public double intersect(Ray r)
    {
        Vec3 c = position.sub(r.pos);
        double b = r.dir.dot(c);
        double dsc = b * b - c.dot(c) + radius * radius;
        if (dsc < 0)
            return -1;
        if (dsc == 0)
            return b;
        return b - Math.sqrt(dsc);
    }

    @Override
    public double [] intersecta(Ray r)
    {
        Vec3 c = position.sub(r.pos);
        double b = r.dir.dot(c);
        double dsc = b * b - c.dot(c) + radius * radius;
        if (dsc <= 0)
            return new double[0];
        double sdsc = Math.sqrt(dsc);
        double [] ret = { b - sdsc, b + sdsc };
        return ret;
    }
}