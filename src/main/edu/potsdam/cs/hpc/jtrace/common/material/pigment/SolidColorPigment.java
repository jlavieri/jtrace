package edu.potsdam.cs.hpc.jtrace.common.material.pigment;

import edu.potsdam.cs.hpc.jtrace.common.Vec3;
import edu.potsdam.cs.hpc.jtrace.common.color.Color;

public class SolidColorPigment implements Pigment
{
    private final Color color;

    public SolidColorPigment (Color color)
    {
        this.color = color;
    }
    
    @Override
    public Color getColor (Vec3 v)
    {
        return color;
    }

}
