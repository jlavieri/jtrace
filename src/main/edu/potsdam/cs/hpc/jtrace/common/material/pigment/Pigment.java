package edu.potsdam.cs.hpc.jtrace.common.material.pigment;

import java.io.Serializable;

import edu.potsdam.cs.hpc.jtrace.common.Vec3;
import edu.potsdam.cs.hpc.jtrace.common.color.Color;

public interface Pigment extends Serializable
{
    public Color getColor(Vec3 v);
}
