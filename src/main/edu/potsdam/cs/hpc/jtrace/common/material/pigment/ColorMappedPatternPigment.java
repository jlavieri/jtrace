package edu.potsdam.cs.hpc.jtrace.common.material.pigment;

import edu.potsdam.cs.hpc.jtrace.common.Vec3;
import edu.potsdam.cs.hpc.jtrace.common.color.Color;
import edu.potsdam.cs.hpc.jtrace.common.color.ColorMap;
import edu.potsdam.cs.hpc.jtrace.common.pattern.Pattern;

public class ColorMappedPatternPigment implements Pigment
{
    private final ColorMap colorMap;
    private final Pattern pattern;

    public ColorMappedPatternPigment(ColorMap colorMap, Pattern pattern)
    {
        this.colorMap = colorMap;
        this.pattern = pattern;
    }
    
    @Override
    public Color getColor(Vec3 v)
    {
        return colorMap.get(pattern.evaluate(v));
    }
}