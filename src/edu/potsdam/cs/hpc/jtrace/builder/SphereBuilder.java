package edu.potsdam.cs.hpc.jtrace.builder;

import edu.potsdam.cs.hpc.jtrace.Sphere;
import edu.potsdam.cs.hpc.jtrace.Vec3;

/**
 * @author  jlavieri
 * @version 2015-03-12
 * @since   2015-03-12
 */
public class SphereBuilder
{
    private final GeomBuilder gb;
    private Vec3 position;
    private double radius;
    
    public SphereBuilder(GeomBuilder gb)
    {
        this.gb = gb;
    }
    
    public SphereBuilder position(double x, double y, double z)
    {
        position = new Vec3(x, y, z);
        return this;
    }

    public SphereBuilder radius(double r)
    {
        radius = r;
        return this;
    }

    public GeomBuilder end()
    {
        gb.primitive = new Sphere(position, radius);
        return gb;
    }

}
