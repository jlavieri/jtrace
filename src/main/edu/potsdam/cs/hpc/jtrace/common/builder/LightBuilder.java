package edu.potsdam.cs.hpc.jtrace.common.builder;

import edu.potsdam.cs.hpc.jtrace.common.Color;
import edu.potsdam.cs.hpc.jtrace.common.Vec3;
import edu.potsdam.cs.hpc.jtrace.common.light.Light;
import edu.potsdam.cs.hpc.jtrace.common.light.PointLight;

public class LightBuilder
{
    private static final LightType DEFAULT_TYPE = LightType.POINT;
    private static final Vec3 DEFAULT_POSITION = Vec3.O;
    private static final Color DEFAULT_COLOR = Color.WHITE;

    private final SceneBuilder sb;

    private LightType type = DEFAULT_TYPE;
    private Vec3 position = DEFAULT_POSITION;
    private Color color = DEFAULT_COLOR;

    private enum LightType
    {
        POINT
    }
    
    public LightBuilder(SceneBuilder sb)
    {
        this.sb = sb;
    }

    public LightBuilder point()
    {
        type = LightType.POINT;
        return this;
    }

    public LightBuilder position(double x, double y, double z)
    {
        position = new Vec3(x, y, z);
        return this;
    }

    public LightBuilder color(Color color)
    {
        this.color = color;
        return this;
    }

    public SceneBuilder end()
    {
        Light light;
        switch (type) {
        case POINT:
        default:
            light = new PointLight(position, color);
        }
        sb.lights.add(light);
        return sb;
    }

}
