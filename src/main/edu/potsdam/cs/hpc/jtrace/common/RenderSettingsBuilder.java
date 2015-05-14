package edu.potsdam.cs.hpc.jtrace.common;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RenderSettingsBuilder
{
    private Dimension dimension;
    private File inputFile;
    private File outputFile;
    
    public RenderSettingsBuilder(String [] args)
    {
        if (args.length > 0 && Files.exists(Paths.get(args[0]))) {
            Path srsPath = Paths.get(args[0]);
            if (Files.exists(srsPath)) {
                try (Scanner srsScanner = new Scanner(srsPath)) {
                    List<String> argList = new ArrayList<>();
                    while(srsScanner.hasNext())
                        argList.add(srsScanner.next());
                    args = argList.toArray(new String[0]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        int width = 480;
        int height = 320;
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
            case "-?":
            case "--help":
            case "?":
            case "help":
                printHelp();
                break;
            case "-w":
                width = Integer.parseInt(args[++i]);
                break;
            case "-h":
                height = Integer.parseInt(args[++i]);
                break;
            case "-i":
            case "--input":
                inputFile = new File(args[++i]);
                break;
            case "-o":
            case "--output":
                outputFile = new File(args[++i]);
                break;
            default:
                throw new IllegalArgumentException("Unknown argument: " + args[i]);
            }
        }
        if (inputFile == null)
            throw new IllegalArgumentException("No input file specified. Use -i <file-path>.");
        if (outputFile == null)
            throw new IllegalArgumentException("No output file specified. Use -o <file-path>.");
        dimension = new Dimension(width, height);
    }

    private static void printHelp()
    {
        System.out.print("Usage: JTrace [[OPTION_FILE] | [OPTION]...]\n"
             + "Raytraces a scene to an output image.\n"
             + "\n"
             + "You can specify an option file that may contain any options\n"
             + "you could place on the command line.\n"
             + "\n"
             + "Mandatory options:\n"
             + "  -i, --input  [FILE-PATH] The input file.\n"
             + "  -o, --output [FILE-PATH] The output file.\n"
             + "\n"
             + "Optional options:\n"
             + "  -w, --width  [SIZE] The width of the output image. Default is 480 px.\n"
             + "  -h, --height [SIZE] The height of the output image. Default is 320 px.\n"
             + "  -?, --help          Prints this help message."
             + "\n"
             + "For example, JTrace -w 480 -h 320 -i MyScene.java -o MyPicture.png\n");
    }

    public RenderSettings build()
    {
        return new RenderSettings(dimension, inputFile, outputFile);
    }

}
