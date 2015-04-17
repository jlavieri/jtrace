package edu.potsdam.cs.hpc.jtrace.common.builder;

import edu.potsdam.cs.hpc.jtrace.common.color.Color;
import edu.potsdam.cs.hpc.jtrace.common.color.ColorList;
import edu.potsdam.cs.hpc.jtrace.common.color.ColorMap;
import edu.potsdam.cs.hpc.jtrace.common.color.IColor;
import edu.potsdam.cs.hpc.jtrace.common.material.pigment.ListPatternPigment;
import edu.potsdam.cs.hpc.jtrace.common.material.pigment.MapPatternPigment;
import edu.potsdam.cs.hpc.jtrace.common.material.pigment.Pigment;
import edu.potsdam.cs.hpc.jtrace.common.material.pigment.SolidColorPigment;
import edu.potsdam.cs.hpc.jtrace.common.pattern.ListPattern;
import edu.potsdam.cs.hpc.jtrace.common.pattern.MapPattern;
import edu.potsdam.cs.hpc.jtrace.common.pattern.Pattern;

public class PigmentBuilder
{
    private static final Color DEFAULT_COLOR = Color.BLACK;

    static final Pigment DEFAULT = new SolidColorPigment(DEFAULT_COLOR);

    private final TextureBuilder tb;

    private IColor color = DEFAULT_COLOR;
    private Pattern pattern;

    public PigmentBuilder (TextureBuilder tb)
    {
        this.tb = tb;
    }

    public PigmentBuilder color (IColor color)
    {
        this.color = color;
        return this;
    }

    public PigmentBuilder pattern (Pattern pattern)
    {
        this.pattern = pattern;
        return this;
    }
    
    public PigmentBuilder checker ()
    {
        pattern = PatternBuilder.CHECKER;
        return this;
    }
    
    public PigmentBuilder colors (Color ... colors)
    {
       ColorList colorList = new ColorList();
       for (Color color : colors)
           colorList.add(color);
       color = colorList;
       return this;
    }

    public TextureBuilder end ()
    {
        if (color != null && pattern != null) {
            if (color instanceof ColorMap && pattern instanceof MapPattern)
                tb.pigment = new MapPatternPigment((ColorMap) color,
                        (MapPattern) pattern);
            else if (color instanceof ColorList
                    && pattern instanceof ListPattern)
                tb.pigment = new ListPatternPigment((ColorList) color,
                        (ListPattern) pattern);
        } else if (color instanceof Color)
            tb.pigment = new SolidColorPigment((Color) color);
        return tb;
    }
}
