package edu.potsdam.cs.hpc.jtrace.common.builder;

import edu.potsdam.cs.hpc.jtrace.common.Color;
import edu.potsdam.cs.hpc.jtrace.common.GlobalSettings;

public class GlobalSettingsBuilder
{
    private static final Color DEFAULT_BACKGROUND = Color.BLACK;
    private static final Color DEFAULT_AMBIENT_LIGHT = Color.BLACK;
    private static final boolean DEFAULT_QUICK_COLOR = false;

    static final GlobalSettings DEFAULT = new GlobalSettings(
            DEFAULT_BACKGROUND, DEFAULT_AMBIENT_LIGHT, DEFAULT_QUICK_COLOR);

    private final SceneBuilder sb;

    private Color background = Color.BLACK;
    private Color ambientLight = Color.BLACK;
    private boolean useQuickColor = false;

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

    public GlobalSettingsBuilder quickColor(boolean useQuickColor)
    {
        this.useQuickColor = useQuickColor;
        return this;
    }

    public SceneBuilder end()
    {
        sb.globalSettings = new GlobalSettings(background, ambientLight,
                useQuickColor);
        return sb;
    }
}
