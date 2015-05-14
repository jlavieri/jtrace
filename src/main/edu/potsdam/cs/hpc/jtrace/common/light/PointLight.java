package edu.potsdam.cs.hpc.jtrace.common.light;

import edu.potsdam.cs.hpc.jtrace.common.Vec3;
import edu.potsdam.cs.hpc.jtrace.common.color.Color;

/**
 * @author jlavieri
 * @since 2012-06-22
 */
public class PointLight extends Light
{
    private static final long serialVersionUID = 5556597930609951506L;

    public PointLight(Vec3 position, Color color)
    {
        this.position = position;
        this.color = color;
    }
}