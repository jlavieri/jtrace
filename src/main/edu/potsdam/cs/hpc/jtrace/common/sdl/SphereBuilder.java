package edu.potsdam.cs.hpc.jtrace.common.sdl;

import edu.potsdam.cs.hpc.jtrace.common.Vec3;
import edu.potsdam.cs.hpc.jtrace.common.primitive.Primitive;
import edu.potsdam.cs.hpc.jtrace.common.primitive.Sphere;

public class SphereBuilder extends PrimitiveBuilder
{
    private static final Vec3 DEFAULT_POSITION = Vec3.O;
    private static final double DEFAULT_RADIUS = 1.0;

    private Vec3 position = DEFAULT_POSITION;
    private double radius = DEFAULT_RADIUS;

    void setPosition (Position position)
    {
        this.position = position.vec;
    }

    void setRadius (Radius radius)
    {
        this.radius = radius.d;
    }

    @Override
    Primitive eval ()
    {
        return new Sphere(position, radius);
    }

}
