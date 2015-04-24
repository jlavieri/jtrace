package edu.potsdam.cs.hpc.jtrace.common.sdl;

import edu.potsdam.cs.hpc.jtrace.common.Vec3;
import edu.potsdam.cs.hpc.jtrace.common.camera.Camera;
import edu.potsdam.cs.hpc.jtrace.common.camera.PerspectiveCamera;
import edu.potsdam.cs.hpc.jtrace.common.sdl.SceneDescription.Projection;

final class CameraBuilder extends SceneScopeBuilder
{
    private static final Projection DEFAULT_PROJECTION = Projection.perspective;
    private static final Vec3 DEFAULT_POSITION = Vec3.O;
    private static final Vec3 DEFAULT_LOOK_AT = Vec3.Z;
    private static final Vec3 DEFAULT_UP = Vec3.Y;
    private static final double DEFAULT_FOV = 30.0; // Degrees.

    static final Camera DEFAULT = new PerspectiveCamera(DEFAULT_POSITION,
            DEFAULT_LOOK_AT, DEFAULT_UP, DEFAULT_FOV);

    private Projection projection = DEFAULT_PROJECTION;
    private Vec3 position = DEFAULT_POSITION;
    private Vec3 lookAt = DEFAULT_LOOK_AT;
    private Vec3 up = DEFAULT_UP;
    private double fov = DEFAULT_FOV;

    CameraBuilder(SceneBuilder sb)
    {
        super(sb);
    }

    void setProjection(Projection projection)
    {
        this.projection = projection;
    }

    void setPosition(Position position)
    {
        this.position = position.vec;
    }

    void setLookAt(LookAt lookAt)
    {
        this.lookAt = lookAt.vec;
    }

    @Override
    void apply ()
    {
        Camera c;
        switch (projection) {
        case perspective:
        default:
            c = new PerspectiveCamera(position, lookAt, up, fov);
        }
        sb.camera = c;
    }

}
