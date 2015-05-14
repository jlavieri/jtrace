package edu.potsdam.cs.hpc.jtrace.common.material;

import java.io.Serializable;

public class Finish implements Serializable
{
    private static final long serialVersionUID = 3179514217720211193L;
    
    public final double diffuse, reflection, specular;

    public Finish (double diffuse, double reflection, double specular)
    {
        this.diffuse = diffuse;
        this.reflection = reflection;
        this.specular = specular;
    }
}
