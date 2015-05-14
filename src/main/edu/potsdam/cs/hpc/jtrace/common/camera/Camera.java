package edu.potsdam.cs.hpc.jtrace.common.camera;

import java.awt.Dimension;
import java.io.Serializable;

import edu.potsdam.cs.hpc.jtrace.common.Ray;

/**
 * @author lavierijp
 * @since 2012-06-23
 */
public interface Camera extends Serializable
{
    /**
     * Sets the dimension of the output buffer. This needs to be called before
     * any tracing can begin. This will perform all of the necessary
     * initialization that requires knowledge of the output dimension.
     * @param d The dimension of the output.
     */
    void initialize(Dimension d);
    
    Ray getRay(int x, int y);
}