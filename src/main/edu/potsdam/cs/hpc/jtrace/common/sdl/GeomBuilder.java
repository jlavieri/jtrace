package edu.potsdam.cs.hpc.jtrace.common.sdl;

import edu.potsdam.cs.hpc.jtrace.common.Geom;
import edu.potsdam.cs.hpc.jtrace.common.color.Color;
import edu.potsdam.cs.hpc.jtrace.common.material.Material;
import edu.potsdam.cs.hpc.jtrace.common.primitive.Primitive;

public final class GeomBuilder extends SceneScopeBuilder
{
    private static final Material DEFAULT_MATERIAL = MaterialBuilder.DEFAULT;
    private static final Color DEFAULT_QUICK_COLOR = Color.BLACK;

    private Primitive primitive; // Primitive is required for geom.
    private Material material = DEFAULT_MATERIAL;
    private Color quickColor; // Default logic in GeomBuilder.end().

    public GeomBuilder (SceneBuilder sb)
    {
        super(sb);
    }

    void setQuickColor (QuickColor quickColor)
    {
        this.quickColor = quickColor.color;
    }

    void setPrimitive (PrimitiveBuilder primitive)
    {
        this.primitive = primitive.eval();
    }

    void setMaterial (MaterialBuilder material)
    {
        this.material = material.eval();
    }

    @Override
    public void apply ()
    {
        if (primitive == null)
            throw new IllegalStateException("Geom has no primitive set.");

        Color quick;
        if (quickColor != null)
            quick = quickColor;
        else
            quick = DEFAULT_QUICK_COLOR;

        sb.addGeom(new Geom(primitive, material, quick));
    }

}
