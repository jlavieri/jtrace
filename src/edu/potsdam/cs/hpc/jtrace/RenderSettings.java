package edu.potsdam.cs.hpc.jtrace;

import java.awt.Dimension;
import java.io.File;

public class RenderSettings
{
    public final Dimension dimension;
    public final File inputFile, outputFile;

    public RenderSettings(Dimension dimension, File inputFile, File outputFile)
    {
        this.dimension = dimension;
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }
}
