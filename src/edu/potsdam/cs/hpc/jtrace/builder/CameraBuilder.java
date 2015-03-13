package edu.potsdam.cs.hpc.jtrace.builder;

import edu.potsdam.cs.hpc.jtrace.Camera;
import edu.potsdam.cs.hpc.jtrace.PerspectiveCamera;
import edu.potsdam.cs.hpc.jtrace.Vec3;

/**
 * @author  jlavieri
 * @version 2015-03-12
 * @since   2015-03-12
 */
public class CameraBuilder
{
    private final SceneBuilder sb;
    
    private Projection projection = Projection.PERSPECTIVE;
    private Vec3 position = Vec3.NZ;
    private Vec3 lookAt = Vec3.O;
    private Vec3 up = Vec3.Y;
    private double fov = 30.0; // Degrees! FOV is in degrees, not radians.

    public CameraBuilder (SceneBuilder sb)
    {
        this.sb = sb;
    }

    public CameraBuilder perspective()
    {
        projection = Projection.PERSPECTIVE;
        return this;
    }
    
    private enum Projection
    {
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
