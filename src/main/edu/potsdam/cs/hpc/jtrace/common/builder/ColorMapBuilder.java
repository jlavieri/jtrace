package edu.potsdam.cs.hpc.jtrace.common.builder;

import edu.potsdam.cs.hpc.jtrace.common.color.Color;
import edu.potsdam.cs.hpc.jtrace.common.color.ColorMap;

public class ColorMapBuilder
{

    public static final ColorMap DEFAULT = new ColorMap();
    {
        DEFAULT.put(0.0d, Color.BLACK);
        DEFAULT.put(1.0d, Color.BLACK);
    }

}
