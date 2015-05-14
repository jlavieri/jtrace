package edu.potsdam.cs.hpc.jtrace.serial;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.potsdam.cs.hpc.jtrace.common.RenderConfiguration;
import edu.potsdam.cs.hpc.jtrace.common.RenderSettings;
import edu.potsdam.cs.hpc.jtrace.common.RenderSettingsBuilder;
import edu.potsdam.cs.hpc.jtrace.common.Renderer;
import edu.potsdam.cs.hpc.jtrace.common.Scene;
import edu.potsdam.cs.hpc.jtrace.common.sdl.Scenes;

/**
 * JTrace serial version.
 *
 * @author lavierijp
 * @since 2012-03-15
 */
public class JTrace
{
    public static void main(String [] args) throws IOException
    {
        RenderSettings renderSettings = new RenderSettingsBuilder(args).build();
        System.out.println("Tracing...");
        Scene scene = Scenes.getSceneFromFile(renderSettings.inputFile);
        RenderConfiguration renderConfig = new RenderConfiguration(renderSettings.dimension);
        BufferedImage image = new Renderer(scene, renderConfig).render();
        ImageIO.write(image, "png", renderSettings.outputFile);
    }
}