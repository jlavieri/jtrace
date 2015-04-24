package edu.potsdam.cs.hpc.jtrace.common.sdl;

import edu.potsdam.cs.hpc.jtrace.common.GlobalSettings;
import edu.potsdam.cs.hpc.jtrace.common.color.Color;

final class GlobalSettingsBuilder extends SceneScopeBuilder
{
    private static final Color DEFAULT_BACKGROUND = Color.BLACK;
    private static final Color DEFAULT_AMBIENT_LIGHT = Color.BLACK;
    private static final boolean DEFAULT_QUICK_COLOR = false;

    static final GlobalSettings DEFAULT = new GlobalSettings(
            DEFAULT_BACKGROUND, DEFAULT_AMBIENT_LIGHT, DEFAULT_QUICK_COLOR);

    private Color background = Color.BLACK;
    private Color ambientLight = Color.BLACK;
    private boolean useQuickColor = false;

    GlobalSettingsBuilder(SceneBuilder sb)
    {
        super(sb);
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

    @Override
    void apply()
    {
        sb.globalSettings = new GlobalSettings(background, ambientLight,
                useQuickColor);
    }
}
