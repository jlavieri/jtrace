package edu.potsdam.cs.hpc.jtrace.common.light;

import java.io.Serializable;

import edu.potsdam.cs.hpc.jtrace.common.Vec3;
import edu.potsdam.cs.hpc.jtrace.common.color.Color;

/**
 * @author jlavieri
 * @since 2012-06-22
 */
public abstract class Light implements Serializable
{
    private static final long serialVersionUID = 1L;
    public Vec3 position;
    public Color color;
}