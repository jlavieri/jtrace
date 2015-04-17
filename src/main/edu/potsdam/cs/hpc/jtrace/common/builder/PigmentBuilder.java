package edu.potsdam.cs.hpc.jtrace.common.builder;

import edu.potsdam.cs.hpc.jtrace.common.color.Color;
import edu.potsdam.cs.hpc.jtrace.common.color.ColorMap;
import edu.potsdam.cs.hpc.jtrace.common.material.pigment.ColorMappedPatternPigment;
import edu.potsdam.cs.hpc.jtrace.common.material.pigment.Pigment;
import edu.potsdam.cs.hpc.jtrace.common.material.pigment.SolidColorPigment;
import edu.potsdam.cs.hpc.jtrace.common.pattern.Pattern;

public class PigmentBuilder
{
    private static final Color DEFAULT_COLOR = Color.BLACK;

    static final Pigment DEFAULT = new SolidColorPigment(DEFAULT_COLOR);

    private final TextureBuilder tb;

    private Color color = DEFAULT_COLOR;
    private ColorMap colorMap;
    private Pattern pattern;

    public PigmentBuilder (TextureBuilder tb)
    {
        this.tb = tb;
    }

    public PigmentBuilder colorMap (ColorMap colorMap)
    {
        this.colorMap = colorMap;
        return this;
    }

    public PigmentBuilder color (Color color)
    {
        this.color = color;
        return this;
    }

    public PigmentBuilder pattern (Pattern pattern)
    {
        this.pattern = pattern;
        return this;
    }

    public TextureBuilder end ()
    {
        if (colorMap != null && pattern != null)
            tb.pigment = new ColorMappedPatternPigment(colorMap, pattern);
        else
            tb.pigment = new SolidColorPigment(color);
        return tb;
    }
}
