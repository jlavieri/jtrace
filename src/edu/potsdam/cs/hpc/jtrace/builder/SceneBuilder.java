package edu.potsdam.cs.hpc.jtrace.builder;

import java.util.ArrayList;
import java.util.List;

import edu.potsdam.cs.hpc.jtrace.Camera;
import edu.potsdam.cs.hpc.jtrace.Geom;
import edu.potsdam.cs.hpc.jtrace.GlobalSettings;
import edu.potsdam.cs.hpc.jtrace.Light;
import edu.potsdam.cs.hpc.jtrace.Scene;


/**
 * @author  jlavieri
 * @version 2015-03-12
 * @since   2015-03-12
 */
public class SceneBuilder
{

    GlobalSettings globalSettings;
    Camera camera;
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
        Light[] lights = this.lights.toArray(new Light[]{});
        Geom[] geoms = this.geoms.toArray(new Geom[]{});
        return new Scene(globalSettings, camera, lights, geoms);
    }
}
