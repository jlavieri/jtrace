package edu.potsdam.cs.hpc.jtrace.common.material;

import java.io.Serializable;

import edu.potsdam.cs.hpc.jtrace.common.material.pigment.Pigment;

public class Texture implements Serializable
{
    private static final long serialVersionUID = -7262587414013370579L;
    
    public final Pigment pigment;
    public final Finish finish;

    public Texture(Pigment pigment, Finish finish)
    {
        this.pigment = pigment;
        this.finish = finish;
    }
}
