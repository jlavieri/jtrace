package edu.potsdam.cs.hpc.jtrace.common;

import edu.potsdam.cs.hpc.jtrace.common.material.Material;
import edu.potsdam.cs.hpc.jtrace.common.primitive.Primitive;

public class Geom
{
    public final Primitive primitive;
    public final Material material;
    public final Color quickColor;
    
    public Geom (Primitive primitive, Material material, Color quickColor)
    {
        this.primitive = primitive;
        this.material = material;
        this.quickColor = quickColor;
    }
    
    @Override
    public String toString()
    {
        return String.format("Geom(%s, %s)", primitive, material);
    }
}