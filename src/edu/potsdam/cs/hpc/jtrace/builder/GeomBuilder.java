package edu.potsdam.cs.hpc.jtrace.builder;

import edu.potsdam.cs.hpc.jtrace.Geom;
import edu.potsdam.cs.hpc.jtrace.Material;
import edu.potsdam.cs.hpc.jtrace.Primitive;

/**
 * @author  jlavieri
 * @version 2015-03-12
 * @since   2015-03-12
 */
public class GeomBuilder
{
    final SceneBuilder sb;
    Primitive primitive;
    Material material;
    
    public GeomBuilder(SceneBuilder sb)
    {
        this.sb = sb;
    }

    public SphereBuilder sphere()
    {
        return new SphereBuilder(this);
    }

    public MaterialBuilder material()
    {
        return new MaterialBuilder(this);
    }

    public SceneBuilder end()
    {
        sb.geoms.add(new Geom(primitive, material));
        return sb;
    }

}
