package edu.potsdam.cs.hpc.jtrace.common;

import edu.potsdam.cs.hpc.jtrace.common.sdl.RenderSettingsBuilder;

/**
 * JTrace.
 *
 * @author lavierijp
 * @version 2015-03-20
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