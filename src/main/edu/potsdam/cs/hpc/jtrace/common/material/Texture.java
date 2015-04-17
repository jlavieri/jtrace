package edu.potsdam.cs.hpc.jtrace.common.material;

public class Texture
{
    public final Pigment pigment;
    public final Finish finish;

    public Texture(Pigment pigment, Finish finish)
    {
        this.pigment = pigment;
        this.finish = finish;
    }
}
