package edu.potsdam.cs.hpc.jtrace.common;

import edu.potsdam.cs.hpc.jtrace.common.color.Color;

public class GlobalSettings
{
    public final Color background;
    public final Color ambientLight;
    public final boolean quickColor;

    public GlobalSettings(Color background, Color ambientLight,
            boolean quickColor)
    {
        this.background = background;
        this.ambientLight = ambientLight;
        this.quickColor = quickColor;
    }
    
    @Override
    public String toString()
    {
        return String.format("GlobalSettings:\n"
                + "\tBackground Color: %s\n"
                + "\tAmbient Light: %s\n"
                + "\tQuick Color: %b\n", background, ambientLight, quickColor);
    }
}