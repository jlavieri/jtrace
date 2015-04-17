package edu.potsdam.cs.hpc.jtrace.common.material;

public class Finish
{
    public final double diffuse, reflection, specular;

    public Finish (double diffuse, double reflection, double specular)
    {
        this.diffuse = diffuse;
        this.reflection = reflection;
        this.specular = specular;
    }
}
