package edu.potsdam.cs.hpc.jtrace.common.sdl;

import edu.potsdam.cs.hpc.jtrace.common.Geom;
import edu.potsdam.cs.hpc.jtrace.common.color.Color;
import edu.potsdam.cs.hpc.jtrace.common.material.Material;
import edu.potsdam.cs.hpc.jtrace.common.primitive.Primitive;

public class GeomBuilder
{
    private static final Material DEFAULT_MATERIAL = MaterialBuilder.DEFAULT;
    private static final Color DEFAULT_QUICK_COLOR = Color.BLACK;

    private final SceneBuilder sb;

    Primitive primitive; // Primitive is required for geom.
    Material material = DEFAULT_MATERIAL;
    private Color quickColor; // Default logic in GeomBuilder.end().

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
        if (primitive == null)
            throw new IllegalStateException("Geom has no primitive set.");
        
        Color quick;
        if (quickColor != null)
            quick = quickColor;
        else
            quick = DEFAULT_QUICK_COLOR;
        
        sb.geoms.add(new Geom(primitive, material, quick));
        return sb;
    }

}
