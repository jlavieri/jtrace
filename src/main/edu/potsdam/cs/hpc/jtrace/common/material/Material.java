package edu.potsdam.cs.hpc.jtrace.common.material;

import java.io.Serializable;

public class Material implements Serializable
{
    private static final long serialVersionUID = -2870562735275308990L;
    
    public final Texture texture;

    public Material(Texture texture)
    {
        this.texture = texture;
    }
}
