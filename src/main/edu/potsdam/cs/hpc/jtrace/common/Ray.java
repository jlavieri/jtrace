package edu.potsdam.cs.hpc.jtrace.common;

/**
 * @author lavierijp
 * @version 2015-03-12
 * @since 2012-03-15
 */
public class Ray
{
    // Eye and offset.
    public final Vec3 position, direction;

    /**
     * Constructs a ray given an eye position and direction. The direction
     * should have length equal to one.
     * 
     * @param position
     *            The eye position.
     * @param direction
     *            The normed direction vector.
     */
    public Ray (Vec3 position, Vec3 direction)
    {
        this.position = position;
        this.direction = direction;
    }

    /**
     * Gets the point on this ray a given distance away from where it starts.
     * 
     * @param t
     *            The scalar distance away from the eye.
     * @return A new position that is distance t in this ray's direction.
     */
    public Vec3 position (double t)
    {
        return position.add(direction.mul(t));
    }

    @Override
    public String toString ()
    {
        return String.format("Ray(%s, %s)", position, direction);
    }
}
