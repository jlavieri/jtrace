package edu.potsdam.cs.hpc.jtrace.builder;

import edu.potsdam.cs.hpc.jtrace.Color;
import edu.potsdam.cs.hpc.jtrace.GlobalSettings;

/**
 * @author jlavieri
 * @version 2015-03-14
 * @since 2015-03-12
 */
public class GlobalSettingsBuilder
{
    private final SceneBuilder sb;
    private Color background = Color.BLACK;
    private Color ambientLight = Color.BLACK;
    private boolean quickColor = false;

    public GlobalSettingsBuilder(SceneBuilder sb)
    {
        this.sb = sb;
    }

    public GlobalSettingsBuilder backGround(Color background)
    {
        this.background = background;
        return this;
    }

    public GlobalSettingsBuilder ambientLight(Color ambientLight)
    {
        this.ambientLight = ambientLight;
        return this;
    }

    public GlobalSettingsBuilder quickColor(boolean b)
    {
        this.quickColor = true;
        return this;
    }

    public SceneBuilder end()
    {
        sb.globalSettings = new GlobalSettings(background, ambientLight,
                quickColor);
        return sb;
    }
}
