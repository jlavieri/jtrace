package edu.potsdam.cs.hpc.jtrace.serial;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.potsdam.cs.hpc.jtrace.common.RenderSettings;
import edu.potsdam.cs.hpc.jtrace.common.RenderSettingsBuilder;
import edu.potsdam.cs.hpc.jtrace.common.Renderer;

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
        BufferedImage image = new Renderer(renderSettings).render();
        ImageIO.write(image, "png", renderSettings.outputFile);
    }
}