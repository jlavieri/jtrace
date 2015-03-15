package edu.potsdam.cs.hpc.jtrace;

/**
 * @author jlavieri
 * @version 2015-03-15
 * @since 2015-03-15
 */
public class Finish
{
    public static final double DEFAULT_DIFFUSE = 0.6;

    public static final Finish DEFAULT = new Finish(DEFAULT_DIFFUSE);

    final double diffuse;

    public Finish(double diffuse)
    {
        this.diffuse = diffuse;
    }
}
