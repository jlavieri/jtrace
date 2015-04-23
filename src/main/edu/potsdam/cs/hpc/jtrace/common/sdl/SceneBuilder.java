package edu.potsdam.cs.hpc.jtrace.common.sdl;

import java.util.ArrayList;
import java.util.List;

import edu.potsdam.cs.hpc.jtrace.common.Geom;
import edu.potsdam.cs.hpc.jtrace.common.GlobalSettings;
import edu.potsdam.cs.hpc.jtrace.common.Scene;
import edu.potsdam.cs.hpc.jtrace.common.camera.Camera;
import edu.potsdam.cs.hpc.jtrace.common.light.Light;

public class SceneBuilder
{
    private static final GlobalSettings DEFAULT_GLOBAL_SETTINGS = GlobalSettingsBuilder.DEFAULT;
    private static final Camera DEFAULT_CAMERA = CameraBuilder.DEFAULT;

    GlobalSettings globalSettings = DEFAULT_GLOBAL_SETTINGS;
    Camera camera = DEFAULT_CAMERA;
    List<Light> lights = new ArrayList<Light>();
    List<Geom> geoms = new ArrayList<Geom>();

    public GlobalSettingsBuilder globalSettings()
    {
        return new GlobalSettingsBuilder(this);
    }

    public CameraBuilder camera()
    {
        return new CameraBuilder(this);
    }

    public LightBuilder light()
    {
        return new LightBuilder(this);
    }

    public GeomBuilder geom()
    {
        return new GeomBuilder(this);
    }

    public Scene getScene()
    {
        Light [] lights = this.lights.toArray(new Light[] {});
        Geom [] geoms = this.geoms.toArray(new Geom[] {});
        return new Scene(globalSettings, camera, lights, geoms);
    }
}
