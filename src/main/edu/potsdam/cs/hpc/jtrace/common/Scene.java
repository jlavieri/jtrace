package edu.potsdam.cs.hpc.jtrace.common;

import edu.potsdam.cs.hpc.jtrace.common.camera.Camera;
import edu.potsdam.cs.hpc.jtrace.common.light.Light;

public class Scene
{
    public final GlobalSettings globalSettings;
    public final Camera camera;
    public final SkySphere skySphere;
    public final Light [] lights;
    public final Geom [] geoms;

    public Scene (GlobalSettings globalSettings, Camera camera,
            SkySphere skySphere, Light [] lights, Geom [] geoms)
    {
        this.globalSettings = globalSettings;
        this.camera = camera;
        this.skySphere = skySphere;
        this.lights = lights;
        this.geoms = geoms;
    }
    
    @Override
    public String toString ()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Scene:\n").append(globalSettings.toString())
        .append(camera).append(skySphere).append('\n')
        .append("Lights:\n");
        for (Light light : lights)
            sb.append('\t').append(light).append('\n');
        sb.append("Geoms:\n");
        for (Geom geom : geoms)
            sb.append('\t').append(geom).append('\n');
        return sb.toString();
    }
}
