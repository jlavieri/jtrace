package edu.potsdam.cs.hpc.jtrace.common.sdl;

import edu.potsdam.cs.hpc.jtrace.common.Vec3;
import edu.potsdam.cs.hpc.jtrace.common.color.Color;
import edu.potsdam.cs.hpc.jtrace.common.light.Light;
import edu.potsdam.cs.hpc.jtrace.common.light.PointLight;
import edu.potsdam.cs.hpc.jtrace.common.sdl.SceneDescription.LightType;

final class LightBuilder extends SceneScopeBuilder
{
    private static final LightType DEFAULT_TYPE = LightType.point;
    private static final Vec3 DEFAULT_POSITION = Vec3.O;
    private static final Color DEFAULT_COLOR = Color.WHITE;

    private LightType type = DEFAULT_TYPE;
    private Vec3 position = DEFAULT_POSITION;
    private Color color = DEFAULT_COLOR;
    
    public LightBuilder(SceneBuilder sb)
    {
        super(sb);
    }

    void setType (LightType type)
    {
        this.type = type;
    }

    void setPosition (Position position)
    {
        this.position = position.vec;
    }

    void setColor (ColorType color)
    {
        this.color = color.color;
    }

    @Override
    void apply ()
    {
        Light light;
        switch (type) {
        case point:
        default:
            light = new PointLight(position, color);
        }
        sb.addLight(light);
    }
}
