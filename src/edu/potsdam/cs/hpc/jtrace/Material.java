package edu.potsdam.cs.hpc.jtrace;

/**
 * @author jlavieri
 * @version 2015-03-15
 * @since 2015-03-12
 */
public class Material
{
    public static final Material DEFAULT = new Material(Texture.DEFAULT);

    public final Texture texture;

    public Material(Texture texture)
    {
        this.texture = texture;
    }
}
