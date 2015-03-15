package edu.potsdam.cs.hpc.jtrace.builder;

import edu.potsdam.cs.hpc.jtrace.Color;
import edu.potsdam.cs.hpc.jtrace.Geom;
import edu.potsdam.cs.hpc.jtrace.Material;
import edu.potsdam.cs.hpc.jtrace.Primitive;

/**
 * @author jlavieri
 * @version 2015-03-15
 * @since 2015-03-12
 */
public class GeomBuilder
{
    final SceneBuilder sb;
    Primitive primitive;
    Material material;
    Color quickColor;

    public GeomBuilder(SceneBuilder sb)
    {
        this.sb = sb;
    }

    public PlaneBuilder plane()
    {
        return new PlaneBuilder(this);
    }

    public SphereBuilder sphere()
    {
        return new SphereBuilder(this);
    }

    public MaterialBuilder material()
    {
        return new MaterialBuilder(this);
    }

    public GeomBuilder quickColor(Color quickColor)
    {
        this.quickColor = quickColor;
        return this;
    }

    public SceneBuilder end()
    {
        Material mat;
        if (material != null)
            mat = material;
        else
            mat = Material.DEFAULT;
        
        Color quick;
        if (quickColor != null)
            quick = quickColor;
        else if (material.texture.pigment.color != null)
            quick = material.texture.pigment.color;
        else
            quick = Color.BLACK;
        
        sb.geoms.add(new Geom(primitive, mat, quick));
        return sb;
    }

}
