package edu.potsdam.cs.hpc.jtrace.builder;

import edu.potsdam.cs.hpc.jtrace.Material;
import edu.potsdam.cs.hpc.jtrace.Texture;

/**
 * @author jlavieri
 * @version 2015-03-15
 * @since 2015-03-12
 */
public class MaterialBuilder
{
    private final GeomBuilder gb;
    
    public Texture texture = Texture.DEFAULT;

    public MaterialBuilder(GeomBuilder gb)
    {
        this.gb = gb;
    }

    public TextureBuilder texture()
    {
        return new TextureBuilder(this);
    }

    public GeomBuilder end()
    {
        gb.material = new Material(texture);
        return gb;
    }

}
