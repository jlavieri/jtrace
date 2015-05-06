package edu.potsdam.cs.hpc.jtrace.common.sdl;

import edu.potsdam.cs.hpc.jtrace.common.material.Finish;

public class FinishBuilder
{
    private static final double DEFAULT_DIFFUSE = 0.6d;
    private static final double DEFAULT_REFLECTION = 0.0d;
    private static final double DEFAULT_SPECULAR = 0.0d;

    static final Finish DEFAULT = new Finish(DEFAULT_DIFFUSE,
            DEFAULT_REFLECTION, DEFAULT_SPECULAR);

    private double diffuse = DEFAULT_DIFFUSE;
    private double reflection = DEFAULT_REFLECTION;
    private double specular = DEFAULT_SPECULAR;

    void setDiffuse (Diffuse diffuse)
    {
        this.diffuse = diffuse.d;
    }
    
    void setReflection(Reflection reflection)
    {
        this.reflection = reflection.d;
    }
    
    void setSpecular(Specular specular)
    {
        this.specular = specular.d;
    }

    Finish eval ()
    {
        return new Finish(diffuse, reflection, specular);
    }
}
