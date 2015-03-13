package edu.potsdam.cs.hpc.jtrace;

/**
 * @author jlavieri
 * @version 2015-03-12
 * @since 2012-06-22
 */
public class PointLight extends Light
{
    public PointLight(Vec3 position, Color color)
    {
        this.position = position;
        this.color = color;
    }
}