package edu.potsdam.cs.hpc.jtrace;

/**
 * @author jlavieri
 * @version 2015-03-15
 * @since 2012-03-15
 */
public class Texture
{
    public static final Texture DEFAULT = new Texture(Pigment.DEFAULT);

    public final Pigment pigment;

    public Texture(Pigment pigment)
    {
        this.pigment = pigment;
    }
}
