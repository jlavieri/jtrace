package edu.potsdam.cs.hpc.jtrace.common;

import edu.potsdam.cs.hpc.jtrace.common.material.Texture;

public class SkySphere
{
    public final Texture texture;
    
    public SkySphere (Texture texture)
    {
        this.texture = texture;
    }
    
    @Override
    public String toString ()
    {
        return String.format("SkySphere(%s)", texture);
    }
}
