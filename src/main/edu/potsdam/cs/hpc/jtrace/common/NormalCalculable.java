package edu.potsdam.cs.hpc.jtrace.common;

public interface NormalCalculable
{
    /**
     * Calculates the normal of some surface or object at the given point
     * in absolute coordinates.
     * @param The point on the surface to calculate the normal.
     * @return The normed normal vector.
     */
    public Vec3 normalOf(Vec3 point);
}
