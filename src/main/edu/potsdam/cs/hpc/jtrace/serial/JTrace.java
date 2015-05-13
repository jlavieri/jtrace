package edu.potsdam.cs.hpc.jtrace.serial;

import edu.potsdam.cs.hpc.jtrace.common.RenderSettings;
import edu.potsdam.cs.hpc.jtrace.common.Renderer;
import edu.potsdam.cs.hpc.jtrace.common.sdl.RenderSettingsBuilder;

/**
 * JTrace serial version.
 *
 * @author lavierijp
 * @version 2015-05-12
 * @since 2012-03-15
 */
public class JTrace
{
    public static void main(String [] args)
    {
        RenderSettings renderSettings = new RenderSettingsBuilder(args).build();
        System.out.println("Tracing...");
        new Renderer(renderSettings).render();
    }
}