package edu.potsdam.cs.hpc.jtrace.common;

import edu.potsdam.cs.hpc.jtrace.common.color.Color;
import edu.potsdam.cs.hpc.jtrace.common.material.Texture;

public class SkySphere
{
    public final Texture texture;
    
    public SkySphere (Texture texture)
    {
        this.texture = texture;
    }
    
    @Override
    public String toString ()
    {
        return String.format("SkySphere(%s)", texture);
    }

    public Color getRadiance (Ray ray)
    {
        Vec3 d = ray.direction;
        double theta = Math.signum(d.y) * Math.acos(d.y) / Math.PI;
        double phi = Math.atan2(d.z, d.x) / Math.PI;
        return texture.pigment.getColor(new Vec3(phi, theta, 0));
    }
}
