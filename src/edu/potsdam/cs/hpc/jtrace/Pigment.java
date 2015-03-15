package edu.potsdam.cs.hpc.jtrace;

/**
 * @author jlavieri
 * @version 2015-03-15
 * @since 2012-03-15
 */
public class Pigment
{
    public static final Pigment DEFAULT = new Pigment(Color.BLACK);

    public final Color color;

    public Pigment(Color color)
    {
        this.color = color;
    }
}