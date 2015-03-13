package edu.potsdam.cs.hpc.jtrace;


/**
 * @author lavierijp
 * @version 2015-03-12
 * @since 2012-03-15
 */
public class Scene
{
    final GlobalSettings globalSettings;
    final Camera camera;
    final Light[] lights;
    final Geom[] objects;
    
    public Scene (GlobalSettings globalSettings, Camera camera, Light[] lights,
            Geom[] objects)
    {
        this.globalSettings = globalSettings;
        this.camera = camera;
        this.lights = lights;
        this.objects = objects;
    }
}