package edu.potsdam.cs.hpc.jtrace;

/**
 * @author lavierijp
 * @version 2015-03-12
 * @since 2012-03-15
 */
public class Ray
{
    public final Vec3 pos, dir;

    public Ray(Vec3 pos, Vec3 dir)
    {
        this.pos = pos;
        this.dir = dir;
    }

    /**
     * Gets the point on this ray a given distance away from where it starts.
     * 
     * @param t
     *            The scalar distance away from the start.
     * @return A new position that is distance t in this ray's direction.
     */
    public Vec3 position(double t)
    {
        return pos.add(dir.mul(t));
    }

    @Override
    public String toString()
    {
        return String.format("Ray(%s, %s)", pos, dir);
    }
}