package edu.potsdam.cs.hpc.jtrace.common.sdl;

import edu.potsdam.cs.hpc.jtrace.common.Vec3;
import edu.potsdam.cs.hpc.jtrace.common.primitive.Plane;
import edu.potsdam.cs.hpc.jtrace.common.primitive.Primitive;

public class PlaneBuilder extends PrimitiveBuilder
{
    private static final Vec3 DEFAULT_POSITION = Vec3.O;
    private static final Vec3 DEFAULT_NORMAL = Vec3.Y;

    private Vec3 position = DEFAULT_POSITION;
    private Vec3 normal = DEFAULT_NORMAL;

    void setPosition(Position position)
    {
        this.position = position.vec;
    }

    void setNormal(Normal normal)
    {
        this.normal = normal.vec;
    }

    @Override
    Primitive eval ()
    {
        return new Plane(position, normal);
    }

}
