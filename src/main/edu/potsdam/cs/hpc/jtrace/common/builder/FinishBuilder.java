package edu.potsdam.cs.hpc.jtrace.common.builder;

import edu.potsdam.cs.hpc.jtrace.common.material.Finish;

public class FinishBuilder
{
    private static final double DEFAULT_DIFFUSE = 0.6;

    static final Finish DEFAULT = new Finish(DEFAULT_DIFFUSE);

    private final TextureBuilder tb;

    private double diffuse = DEFAULT_DIFFUSE;

    public FinishBuilder(TextureBuilder tb)
    {
        this.tb = tb;
    }

    public FinishBuilder diffuse(double diffuse)
    {
        this.diffuse = diffuse;
        return this;
    }

    public TextureBuilder end()
    {
        tb.finish = new Finish(diffuse);
        return tb;
    }

}
