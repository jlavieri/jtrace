package edu.potsdam.cs.hpc.jtrace.common.builder;

import edu.potsdam.cs.hpc.jtrace.common.material.Finish;

public class FinishBuilder
{
    private static final double DEFAULT_DIFFUSE = 0.6d;
    private static final double DEFAULT_REFLECTION = 0.0d;

    static final Finish DEFAULT = new Finish(DEFAULT_DIFFUSE, DEFAULT_REFLECTION);

    private final TextureBuilder tb;

    private double diffuse = DEFAULT_DIFFUSE;
    private double reflection = DEFAULT_REFLECTION;

    public FinishBuilder(TextureBuilder tb)
    {
        this.tb = tb;
    }

    public FinishBuilder diffuse(double diffuse)
    {
        this.diffuse = diffuse;
        return this;
    }
    
    public FinishBuilder reflection(double reflection)
    {
        this.reflection = reflection;
        return this;
    }

    public TextureBuilder end()
    {
        tb.finish = new Finish(diffuse, reflection);
        return tb;
    }

}
