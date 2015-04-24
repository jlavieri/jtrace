package edu.potsdam.cs.hpc.jtrace.common.sdl;

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

    private IColor color = DEFAULT_COLOR;
    private Pattern pattern = null;

    PigmentBuilder () {}
    
    void setColor (IColor color)
    {
        this.color = color;
    }

    void setPattern (PatternType pattern)
    {
        this.pattern = pattern.pattern;
    }

    Pigment eval ()
    {
        if (color != null && pattern != null) {
            if (color instanceof ColorMap && pattern instanceof MapPattern)
                return new MapPatternPigment((ColorMap) color,
                                             (MapPattern) pattern);
            else if (color instanceof ColorList
                    && pattern instanceof ListPattern)
                return new ListPatternPigment((ColorList) color,
                        (ListPattern) pattern);
        } else if (color instanceof Color)
            return new SolidColorPigment((Color) color);
        return DEFAULT;
    }
}
