package edu.potsdam.cs.hpc.jtrace;

/**
 * @author lavierijp
 * @version 2015-03-12
 * @since 2012-03-15
 */
public class Geom
{
    final Primitive primitive;
    final Material material;
    final Color quickColor;
    
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