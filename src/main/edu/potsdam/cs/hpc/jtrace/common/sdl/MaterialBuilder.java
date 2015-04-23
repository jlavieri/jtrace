package edu.potsdam.cs.hpc.jtrace.common.sdl;

import edu.potsdam.cs.hpc.jtrace.common.material.Material;
import edu.potsdam.cs.hpc.jtrace.common.material.Texture;

public class MaterialBuilder
{
    private static final Texture DEFAULT_TEXTURE = TextureBuilder.DEFAULT;

    static final Material DEFAULT = new Material(DEFAULT_TEXTURE);

    private final GeomBuilder gb;

    Texture texture = DEFAULT_TEXTURE;

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