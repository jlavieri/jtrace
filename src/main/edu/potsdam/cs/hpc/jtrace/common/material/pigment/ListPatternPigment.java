package edu.potsdam.cs.hpc.jtrace.common.material.pigment;

import edu.potsdam.cs.hpc.jtrace.common.Vec3;
import edu.potsdam.cs.hpc.jtrace.common.color.Color;
import edu.potsdam.cs.hpc.jtrace.common.color.ColorList;
import edu.potsdam.cs.hpc.jtrace.common.pattern.ListPattern;

public class ListPatternPigment implements Pigment
{
    private final ColorList colorList;
    private final ListPattern pattern;

    public ListPatternPigment(ColorList colorList, ListPattern pattern)
    {
        this.colorList = colorList;
        this.pattern = pattern;
    }
    
    @Override
    public Color getColor(Vec3 v)
    {
        return colorList.get(pattern.evaluate(v));
    }
}