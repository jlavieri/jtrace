package edu.potsdam.cs.hpc.jtrace.common.material.pigment;

import edu.potsdam.cs.hpc.jtrace.common.Vec3;
import edu.potsdam.cs.hpc.jtrace.common.color.Color;

public interface Pigment
{
    public Color getColor(Vec3 v);
}
