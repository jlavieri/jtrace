package edu.potsdam.cs.hpc.jtrace;

import edu.potsdam.cs.hpc.jtrace.material.Material;
import edu.potsdam.cs.hpc.jtrace.primitive.Primitive;

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