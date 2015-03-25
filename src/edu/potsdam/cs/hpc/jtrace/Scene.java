package edu.potsdam.cs.hpc.jtrace;

import edu.potsdam.cs.hpc.jtrace.camera.Camera;
import edu.potsdam.cs.hpc.jtrace.light.Light;

public class Scene
{
    public final GlobalSettings globalSettings;
    public final Camera camera;
    public final Light[] lights;
    public final Geom[] geoms;
    
    public Scene (GlobalSettings globalSettings, Camera camera, Light[] lights,
            Geom[] geoms)
    {
        this.globalSettings = globalSettings;
        this.camera = camera;
        this.lights = lights;
        this.geoms = geoms;
    }
}