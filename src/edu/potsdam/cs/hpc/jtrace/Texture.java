package edu.potsdam.cs.hpc.jtrace;

/**
 * @author jlavieri
 * @version 2015-03-15
 * @since 2012-03-15
 */
public class Texture
{
    public static final Texture DEFAULT = new Texture(Pigment.DEFAULT,
            Finish.DEFAULT);

    public final Pigment pigment;
    public final Finish finish;

    public Texture(Pigment pigment, Finish finish)
    {
        this.pigment = pigment;
        this.finish = finish;
    }
}
