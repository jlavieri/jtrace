package edu.potsdam.cs.hpc.jtrace.common.sdl;

import edu.potsdam.cs.hpc.jtrace.common.material.Finish;

public class FinishBuilder
{
    private static final double DEFAULT_DIFFUSE = 0.6d;
    private static final double DEFAULT_REFLECTION = 0.0d;
    private static final double DEFAULT_SPECULAR = 0.0d;

    static final Finish DEFAULT = new Finish(DEFAULT_DIFFUSE,
            DEFAULT_REFLECTION, DEFAULT_SPECULAR);

    private final TextureBuilder tb;

    private double diffuse = DEFAULT_DIFFUSE;
    private double reflection = DEFAULT_REFLECTION;
    private double specular = DEFAULT_SPECULAR;

    public FinishBuilder (TextureBuilder tb)
    {
        this.tb = tb;
    }

    public FinishBuilder diffuse (double diffuse)
    {
        this.diffuse = diffuse;
        return this;
    }

    public FinishBuilder reflection (double reflection)
    {
        this.reflection = reflection;
        return this;
    }

    public FinishBuilder specular (double specular)
    {
        this.specular = specular;
        return this;
    }

    public TextureBuilder end ()
    {
        tb.finish = new Finish(diffuse, reflection, specular);
        return tb;
    }

}
