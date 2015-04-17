package edu.potsdam.cs.hpc.jtrace.common.material.pigment;

import edu.potsdam.cs.hpc.jtrace.common.Vec3;
import edu.potsdam.cs.hpc.jtrace.common.color.Color;
import edu.potsdam.cs.hpc.jtrace.common.color.ColorMap;
import edu.potsdam.cs.hpc.jtrace.common.pattern.MapPattern;

public class MapPatternPigment implements Pigment
{
    private final ColorMap colorMap;
    private final MapPattern pattern;

    public MapPatternPigment(ColorMap colorMap, MapPattern pattern)
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