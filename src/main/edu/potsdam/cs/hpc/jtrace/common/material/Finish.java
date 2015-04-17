package edu.potsdam.cs.hpc.jtrace.common.material;

public class Finish
{
    public final double diffuse, reflection;

    public Finish (double diffuse, double reflection)
    {
        this.diffuse = diffuse;
        this.reflection = reflection;
    }
}
