package edu.potsdam.cs.hpc.jtrace;

import edu.potsdam.cs.hpc.jtrace.builder.RenderSettingsBuilder;
import edu.potsdam.cs.hpc.jtrace.builder.SceneBuilder;

/**
 * JTrace.
 *
 * @author lavierijp
 * @version 2015-03-12
 * @since 2012-03-15
 */
public class JTrace
{
    public static void main(String [] args)
    {
        Scene testScene = buildTestScene();
        RenderSettings renderSettings = new RenderSettingsBuilder(args).build();
        
        long start = System.currentTimeMillis();
        System.out.println("Tracing.");
        
        new Renderer(testScene, renderSettings).renderToFile();
        
        long stop = System.currentTimeMillis();
        long delta = stop - start;
        System.out.printf("Trace took %d ms.", delta);
    }

    private static Scene buildTestScene()
    {
        SceneBuilder sb = new SceneBuilder();
        sb
        .globalSettings()
            .backGround(Color.BLACK)
            .ambientLight(Color.GREY10)
        .end()
        .camera()
            .perspective()
            .position(5, 2.5, 5)
            .lookAt(0, 0, 0)
        .end()
        .light()
            .point()
            .position(20, 40, -20)
            .color(Color.WHITE)
        .end()
        .light()
            .point()
            .position(-20, 40, -20)
            .color(Color.WHITE)
        .end()
        .geom()
            .sphere()
                .position(0, 1, 0)
                .radius(1)
            .end()
            .material()
                .texture()
                    .pigment(Color.BLUE)
                .end()
            .end()
        .end();
        return sb.getScene();
    }

}