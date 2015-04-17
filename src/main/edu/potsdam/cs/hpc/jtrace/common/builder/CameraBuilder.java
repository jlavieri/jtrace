package edu.potsdam.cs.hpc.jtrace.common.builder;

import edu.potsdam.cs.hpc.jtrace.common.Vec3;
import edu.potsdam.cs.hpc.jtrace.common.camera.Camera;
import edu.potsdam.cs.hpc.jtrace.common.camera.PerspectiveCamera;

public class CameraBuilder
{
    private static final Projection DEFAULT_PROJECTION = Projection.PERSPECTIVE;
    private static final Vec3 DEFAULT_POSITION = Vec3.O;
    private static final Vec3 DEFAULT_LOOK_AT = Vec3.Z;
    private static final Vec3 DEFAULT_UP = Vec3.Y;
    private static final double DEFAULT_FOV = 30.0; // Degrees.

    static final Camera DEFAULT = new PerspectiveCamera(DEFAULT_POSITION,
            DEFAULT_LOOK_AT, DEFAULT_UP, DEFAULT_FOV);

    private final SceneBuilder sb;

    private Projection projection = DEFAULT_PROJECTION;
    private Vec3 position = DEFAULT_POSITION;
    private Vec3 lookAt = DEFAULT_LOOK_AT;
    private Vec3 up = DEFAULT_UP;
    private double fov = DEFAULT_FOV;

    public CameraBuilder(SceneBuilder sb)
    {
        this.sb = sb;
    }

    public CameraBuilder perspective()
    {
        projection = Projection.PERSPECTIVE;
        return this;
    }

    private enum Projection {
        PERSPECTIVE
    }

    public CameraBuilder position(double x, double y, double z)
    {
        position = new Vec3(x, y, z);
        return this;
    }

    public CameraBuilder lookAt(double x, double y, double z)
    {
        lookAt = new Vec3(x, y, z);
        return this;
    }

    public CameraBuilder lookAt(Vec3 lookAt)
    {
        this.lookAt = lookAt;
        return this;
    }

    public SceneBuilder end()
    {
        Camera c;
        switch (projection) {
        case PERSPECTIVE:
        default:
            c = new PerspectiveCamera(position, lookAt, up, fov);
        }
        sb.camera = c;
        return sb;
    }

}
