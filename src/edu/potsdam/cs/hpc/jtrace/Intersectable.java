package edu.potsdam.cs.hpc.jtrace;

public interface Intersectable
{
    /**
     * Returns the closest intersection of this with the given ray.
     * 
     * @param ray
     *            The ray.
     * @return The closest (towards the position of the ray) intersection of the
     *         ray with this Intersectable.
     */
    abstract double intersect(Ray ray);

    abstract double [] intersecta(Ray ray);
}
