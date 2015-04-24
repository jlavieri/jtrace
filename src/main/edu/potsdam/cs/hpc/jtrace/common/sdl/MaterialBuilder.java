package edu.potsdam.cs.hpc.jtrace.common.sdl;

import edu.potsdam.cs.hpc.jtrace.common.material.Material;
import edu.potsdam.cs.hpc.jtrace.common.material.Texture;

public class MaterialBuilder
{
    private static final Texture DEFAULT_TEXTURE = TextureBuilder.DEFAULT;

    static final Material DEFAULT = new Material(DEFAULT_TEXTURE);

    private Texture texture = DEFAULT_TEXTURE;

    void setTexture (TextureBuilder texture)
    {
        this.texture = texture.eval();
    }

    Material eval ()
    {
        return new Material(texture);
    }
}
