package edu.potsdam.cs.hpc.jtrace.common.builder;

import edu.potsdam.cs.hpc.jtrace.common.Vec3;
import edu.potsdam.cs.hpc.jtrace.common.primitive.Plane;

public class PlaneBuilder
{
    private static final Vec3 DEFAULT_POSITION = Vec3.O;
    private static final Vec3 DEFAULT_NORMAL = Vec3.Y;

    private final GeomBuilder gb;

    private Vec3 position = DEFAULT_POSITION;
    private Vec3 normal = DEFAULT_NORMAL;

    public PlaneBuilder(GeomBuilder gb)
    {
        this.gb = gb;
    }

    public PlaneBuilder position(Vec3 position)
    {
        this.position = position;
        return this;
    }

    public PlaneBuilder normal(Vec3 normal)
    {
        this.normal = normal;
        return this;
    }

    public GeomBuilder end()
    {
        gb.primitive = new Plane(position, normal);
        return gb;
    }

}
