package edu.potsdam.cs.hpc.jtrace.camera;

import java.awt.Dimension;

import edu.potsdam.cs.hpc.jtrace.Ray;

/**
 * @author lavierijp
 * @version 2015-03-11
 * @since 2012-06-23
 */
public interface Camera
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