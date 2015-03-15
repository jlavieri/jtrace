package edu.potsdam.cs.hpc.jtrace;

/**
 * @author jlavieri
 * @version 2015-03-14
 * @since 2015-03-12
 */
public class GlobalSettings
{
    final Color background;
    final Color ambientLight;
    final boolean quickColor;

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
