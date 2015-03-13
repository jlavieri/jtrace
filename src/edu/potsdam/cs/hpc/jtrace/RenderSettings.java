package edu.potsdam.cs.hpc.jtrace;

import java.awt.Dimension;
import java.io.File;

/**
 * @author  jlavieri
 * @version 2015-03-12
 * @since   2015-03-12
 */
public class RenderSettings
{
    final Dimension dimension;
    final File outputFile;
    
    public RenderSettings (Dimension dimension, File outputFile)
    {
        this.dimension = dimension;
        this.outputFile = outputFile;
    }
}
