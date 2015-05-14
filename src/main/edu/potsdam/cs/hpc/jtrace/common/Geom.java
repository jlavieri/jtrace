package edu.potsdam.cs.hpc.jtrace.common;

import java.io.Serializable;

import edu.potsdam.cs.hpc.jtrace.common.color.Color;
import edu.potsdam.cs.hpc.jtrace.common.material.Material;
import edu.potsdam.cs.hpc.jtrace.common.primitive.Primitive;

public class Geom implements Serializable
{
    private static final long serialVersionUID = -9013945230100223086L;
    
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