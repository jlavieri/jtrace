package edu.potsdam.cs.hpc.jtrace.common.builder;

import edu.potsdam.cs.hpc.jtrace.common.Vec3;
import edu.potsdam.cs.hpc.jtrace.common.primitive.Sphere;

public class SphereBuilder
{
    private static final Vec3 DEFAULT_POSITION = Vec3.O;
    private static final double DEFAULT_RADIUS = 1.0;

    private final GeomBuilder gb;

    private Vec3 position = DEFAULT_POSITION;
    private double radius = DEFAULT_RADIUS;
    
    public SphereBuilder(GeomBuilder gb)
    {
        this.gb = gb;
    }
    
    public SphereBuilder position(double x, double y, double z)
    {
        position = new Vec3(x, y, z);
        return this;
    }
    
    public SphereBuilder position(Vec3 position)
    {
        this.position = position;
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
