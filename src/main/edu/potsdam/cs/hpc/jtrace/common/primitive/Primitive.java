package edu.potsdam.cs.hpc.jtrace.common.primitive;

import java.io.Serializable;

import edu.potsdam.cs.hpc.jtrace.common.Intersectable;
import edu.potsdam.cs.hpc.jtrace.common.NormalCalculable;

/**
 * @author jlavieri
 * @since 2015-03-12
 */
public abstract class Primitive implements Intersectable, NormalCalculable,
        Serializable
{
    private static final long serialVersionUID = 1L;
}
