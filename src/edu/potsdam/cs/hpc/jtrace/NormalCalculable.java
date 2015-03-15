package edu.potsdam.cs.hpc.jtrace;

public interface NormalCalculable
{
    /**
     * Calculates the normal of some surface or object at the given point
     * in absolute coordinates.
     * @param The point on the surface to calculate the normal.
     * @return The normal normal vector.
     */
    public Vec3 normalOf(Vec3 point);
}
