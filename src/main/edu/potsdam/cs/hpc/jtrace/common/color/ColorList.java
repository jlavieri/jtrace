package edu.potsdam.cs.hpc.jtrace.common.color;

import java.util.ArrayList;
import java.util.List;

public class ColorList implements IColor
{
    private final List<Color> list = new ArrayList<Color>(2);
    
    public void add (Color color)
    {
        list.add(color);
    }
    
    public Color get (int i)
    {
        return list.get(i);
    }
}
