package edu.potsdam.cs.hpc.jtrace.builder;

import edu.potsdam.cs.hpc.jtrace.Color;
import edu.potsdam.cs.hpc.jtrace.Vec3;
import edu.potsdam.cs.hpc.jtrace.light.Light;
import edu.potsdam.cs.hpc.jtrace.light.PointLight;

public class LightBuilder
{
    private final SceneBuilder sb;
    private LightType type = LightType.POINT;
    private Vec3 position = Vec3.O;
    private Color color = Color.WHITE;

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
