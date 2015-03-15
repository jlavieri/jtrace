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
        new Renderer(testScene, renderSettings).render();
    }

    private static Scene buildTestScene()
    {
        SceneBuilder sb = new SceneBuilder();
        sb
        .globalSettings()
            .backGround(Color.BLACK)
            .ambientLight(Color.GREY10)
            .quickColor(true)
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
            .plane()
                .position(Vec3.O)
                .normal(Vec3.Y)
            .end()
            .material().texture().pigment(Color.GREY).end().end()
        .end()
        
        .geom()
            .sphere()
                .position(Vec3.O)
                .radius(0.2)
            .end()
            .material().texture().pigment(Color.WHITE).end().end()
        .end()
        
        .geom().sphere().position(Vec3.X).radius(0.1).end()
        .material().texture().pigment(Color.RED).end().end().end()
        
        .geom().sphere().position(Vec3.Y).radius(0.1).end()
        .material().texture().pigment(Color.GREEN).end().end().end()
        
        .geom().sphere().position(Vec3.Z).radius(0.1).end()
        .material().texture().pigment(Color.BLUE).end().end().end()
        
        .geom().sphere().position(Vec3.NX).radius(0.1).end()
        .material().texture().pigment(Color.CYAN).end().end().end()
        
        .geom().sphere().position(Vec3.NY).radius(0.1).end()
        .material().texture().pigment(Color.MAGENTA).end().end().end()
        
        .geom().sphere().position(Vec3.NZ).radius(0.1).end()
        .material().texture().pigment(Color.YELLOW).end().end().end();
        return sb.getScene();
    }

}