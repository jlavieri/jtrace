package edu.potsdam.cs.hpc.jtrace.common.color;

import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;

public class ColorMap implements IColor
{
    private final NavigableMap<Double, Color> map = new TreeMap<>();

    public void put (Double d, Color c)
    {
        if (d < 0.0d || d > 1.0d)
            throw new IllegalArgumentException(
                    "Color map key out of [0,1] domain: " + d);
        map.put(d, c);
    }

    public Color get (double d)
    {
        Entry<Double, Color> b = map.ceilingEntry(d);
        Entry<Double, Color> a = map.floorEntry(d);
        if (a.getKey() == b.getKey())
            if (d > 0.0d)
                a = map.lowerEntry(d);
            else
                b = map.higherEntry(d);
        return a.getValue().lerp(b.getValue(), (d - a.getKey()) /
                                 (b.getKey() - a.getKey()));
    }
}
