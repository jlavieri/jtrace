package edu.potsdam.cs.hpc.jtrace.builder;

import java.awt.Dimension;
import java.io.File;

import edu.potsdam.cs.hpc.jtrace.RenderSettings;

/**
 * @author  jlavieri
 * @version 2015-03-12
 * @since   2015-03-12
 */
public class RenderSettingsBuilder
{
    private Dimension dimension;
    private File outputFile;
    
    public RenderSettingsBuilder(String [] args)
    {
        int width = 480;
        int height = 320;
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
            case "-w":
                width = Integer.parseInt(args[++i]);
                break;
            case "-h":
                height = Integer.parseInt(args[++i]);
                break;
            case "-o":
                outputFile = new File(args[++i]);
                break;
            default:
                throw new IllegalArgumentException("Unknown argument: " + args[i]);
            }
        }
        if (outputFile == null)
            throw new IllegalArgumentException("Need to specified an output file! Use -o <file-path>.");
        dimension = new Dimension(width, height);
    }

    public RenderSettings build()
    {
        return new RenderSettings(dimension, outputFile);
    }

}
