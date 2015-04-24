package edu.potsdam.cs.hpc.jtrace.common.sdl;

import edu.potsdam.cs.hpc.jtrace.common.GlobalSettings;
import edu.potsdam.cs.hpc.jtrace.common.color.Color;

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

    GlobalSettingsBuilder(SceneBuilder sb)
    {
        this.sb = sb;
    }

    void setBackground(BackgroundColor background)
    {
        this.background = background.color;
    }

    void setAmbientLight(AmbientLightColor ambientLight)
    {
        this.ambientLight = ambientLight.color;
    }

    void useQuickColor(UseQuickColor tok)
    {
        this.useQuickColor = tok.bool;
    }

    void apply()
    {
        sb.globalSettings = new GlobalSettings(background, ambientLight,
                useQuickColor);
    }
}
