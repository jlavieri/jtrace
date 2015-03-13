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
        System.out.println("Tracing...");
        new Renderer(testScene, renderSettings).renderToFile();
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
            .lookAt(Vec3.O)
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
                .position(Vec3.O)
                .radius(1)
            .end()
            .material()
                .texture()
                    .pigment(Color.SKY_BLUE)
                .end()
            .end()
        .end();
        return sb.getScene();
    }

}