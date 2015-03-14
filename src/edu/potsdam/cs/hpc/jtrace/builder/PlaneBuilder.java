package edu.potsdam.cs.hpc.jtrace.builder;

import edu.potsdam.cs.hpc.jtrace.Vec3;
import edu.potsdam.cs.hpc.jtrace.primitive.Plane;

/**
 * @author  jlavieri
 * @version 2015-03-14
 * @since   2015-03-14
 */
public class PlaneBuilder
{
    private final GeomBuilder gb;
    private Vec3 position, normal;

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
