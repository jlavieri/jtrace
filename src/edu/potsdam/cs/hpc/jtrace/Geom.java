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
    
    public Geom (Primitive primitive, Material material)
    {
        this.primitive = primitive;
        this.material = material;
    }
    
    @Override
    public String toString()
    {
        return String.format("Geom(%s, %s)", primitive, material);
    }
}