package edu.potsdam.cs.hpc.jtrace;

import java.awt.Dimension;

/**
 * @author lavierijp
 * @version 2015-03-12
 * @since 2012-06-23
 */
public class PerspectiveCamera implements Camera
{
    final Vec3
        position, // This is the "position" of the camera.
        direction, // This is the direction the camera points.
        up, // This is up.
        left, // This is left. (Normally computed from direction and up).
        cpo; // This is the position of the center of the projection plane.
    
    // Projection depth.
    final double projectionDepth;
    
    // Projection width and height. Initialized before tracing.
    double projectionWidth, projectionHeight;
    
    // Field of view.
    final double fov;
    
    // The output buffer dimension. Must be initialized before tracing.
    double sceneWidth, sceneHeight;

    public PerspectiveCamera(Vec3 position, Vec3 lookAt, Vec3 up, double fov)
    {
        this.position = position;
        this.direction = position.direction(lookAt);
        this.up = up;
        this.projectionDepth = 1d; // XXX Does this need to parameterized?
        this.fov = fov;
        left = direction.crs(up);
        cpo = position.add(direction.mul(projectionDepth));
    }
    
    @Override
    public void initialize(Dimension d)
    {
        sceneWidth = d.width;
        sceneHeight = d.height;
        projectionWidth = 2.0d * Math.tan(Math.toRadians(fov));
        projectionHeight = projectionWidth * sceneWidth / sceneHeight;
    }
    
    /**
     * @param v Integral scene coordinate.
     * @param p Projection dimension.
     * @param s Scene dimension.
     * @return TODO
     */
    private static double planarPos(int v, double p, double s)
    {
        return p / 2.0d - v * p / s;
    }

    @Override
    public Ray getRay(int x, int y)
    {
        return new Ray(position, position.direction(
                cpo
                .add(left.mul(planarPos(x, projectionWidth, sceneWidth)))
                .sadd(up.mul(planarPos(y, projectionHeight, sceneHeight)))));
    }
}