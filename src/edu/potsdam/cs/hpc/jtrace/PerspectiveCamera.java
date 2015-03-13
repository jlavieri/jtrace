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
        up, // This is up in the camera orientation sense. This is usually y-hat.
        planeLeft, // This is the left of the projection plane.
        planeUp, // This is the up of the projection plane.
        projectionPlaneOrigin; // This is the position of the center of the projection plane.
    
    // Projection depth. It's like zoom.
    final double projectionDepth;
    
    // Field of view.
    final double fov;
    
    // These fields must be initialized before rendering by calling initialize()!
    double sceneWidth, sceneHeight,
        projectionWidth, projectionHeight,
        projectionSceneWidthRatio, projectionSceneHeightRatio,
        halfProjectionWidth, halfProjectionHeight;

    public PerspectiveCamera(Vec3 position, Vec3 lookAt, Vec3 up, double fov)
    {
        this.position = position;
        this.direction = position.directionTo(lookAt);
        this.up = up;
        this.projectionDepth = 1d; // XXX Does this need to parameterized?
        this.fov = fov;
        planeLeft = direction.cross(up);
        planeUp = direction.cross(planeLeft);
        projectionPlaneOrigin = position.add(direction.mul(projectionDepth));
    }
    
    @Override
    public void initialize(Dimension d)
    {
        sceneWidth = d.width;
        sceneHeight = d.height;
        projectionWidth = 2.0d * Math.tan(Math.toRadians(fov));
        projectionHeight = projectionWidth * sceneHeight / sceneWidth;
        projectionSceneWidthRatio = projectionWidth / sceneWidth;
        projectionSceneHeightRatio = projectionHeight / sceneHeight;
        halfProjectionWidth = projectionWidth / 2.0d;
        halfProjectionHeight = projectionHeight / 2.0d;
    }
    
    private double projectionPlanePositionX(int x)
    {
        return halfProjectionWidth - (double)x * projectionSceneWidthRatio;
       // return p / 2.0d - v * p / s;
    }
    
    private double projectionPlanePositionY(double y)
    {
        return halfProjectionHeight - (double)y * projectionSceneHeightRatio;
    }

    @Override
    public Ray getRay(int x, int y)
    {
        return new Ray(position, position.directionTo(
                projectionPlaneOrigin
                .add(planeLeft.mul(projectionPlanePositionX(x)))
                .sadd(planeUp.mul(projectionPlanePositionY(y)))));
    }
    
    @Override
    public String toString()
    {
        return String.format("PerspectiveCamera(\n"
                + "\tpos:%s,\n"
                + "\tdir:%s,\n"
                + "\tup:%s,\n"
                + "\tfov:%s)",
                             position, direction, up, fov);
    }
}