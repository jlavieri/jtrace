package edu.potsdam.cs.hpc.jtrace.common;

public interface Intersectable
{
    /**
     * Returns the closest intersection of this with the given ray.
     * 
     * @param ray
     *            The ray.
     * @return The minimum distance of intersection from the ray's position to
     *         the intersectable. -1 if no intersection exists.
     */
    public abstract double intersect (Ray ray);
}
