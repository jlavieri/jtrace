package edu.potsdam.cs.hpc.jtrace.common.sdl;

import java.util.ArrayList;
import java.util.List;

import edu.potsdam.cs.hpc.jtrace.common.Geom;
import edu.potsdam.cs.hpc.jtrace.common.GlobalSettings;
import edu.potsdam.cs.hpc.jtrace.common.Scene;
import edu.potsdam.cs.hpc.jtrace.common.SkySphere;
import edu.potsdam.cs.hpc.jtrace.common.camera.Camera;
import edu.potsdam.cs.hpc.jtrace.common.light.Light;

public class SceneBuilder
{
    private static final GlobalSettings DEFAULT_GLOBAL_SETTINGS = GlobalSettingsBuilder.DEFAULT;
    private static final Camera DEFAULT_CAMERA = CameraBuilder.DEFAULT;

    private GlobalSettings globalSettings = DEFAULT_GLOBAL_SETTINGS;
    private Camera camera = DEFAULT_CAMERA;
    private SkySphere skySphere = null;
    private List<Light> lights = new ArrayList<Light>();
    private List<Geom> geoms = new ArrayList<Geom>();

    void setGlobalSettings (GlobalSettings globalSettings)
    {
        this.globalSettings = globalSettings;
    }

    void setCamera (Camera camera)
    {
        this.camera = camera;
    }

    void setSkySphere (SkySphere skySphere)
    {
        this.skySphere = skySphere;
    }

    void addLight (Light light)
    {
        lights.add(light);
    }

    void addGeom (Geom geom)
    {
        geoms.add(geom);
    }

    public Scene getScene ()
    {
        Light [] lights = this.lights.toArray(new Light[] {});
        Geom [] geoms = this.geoms.toArray(new Geom[] {});
        return new Scene(globalSettings, camera, skySphere, lights, geoms);
    }
}
