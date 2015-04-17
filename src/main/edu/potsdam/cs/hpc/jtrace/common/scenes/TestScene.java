package edu.potsdam.cs.hpc.jtrace.common.scenes;

import edu.potsdam.cs.hpc.jtrace.common.Color;
import edu.potsdam.cs.hpc.jtrace.common.Vec3;
import edu.potsdam.cs.hpc.jtrace.common.builder.SceneBuilder;

public class TestScene implements SceneDescription
{

    @Override
    public void getSceneDescription(SceneBuilder sb)
    {
        sb
        .globalSettings()
            .backGround(Color.BLACK)
            .ambientLight(Color.GREY10)
            .quickColor(false)
        .end()
        
        .camera()
            .perspective()
            .position(2, 1.5, 2)
            .lookAt(Vec3.O)
        .end()
        
        .light()
            .point()
            .position(20, 40, -20)
            .color(Color.SKY_AQUA)
        .end()
        
        .light()
            .point()
            .position(-20, 40, -20)
            .color(Color.VIOLET)
        .end()
        /*
        .geom()
            .plane()
                .position(Vec3.O)
                .normal(Vec3.Y)
            .end()
            .material().texture().pigment(Color.GREY).end().end()
        .end()*/
        
        .geom()
            .sphere()
                .position(Vec3.O)
                .radius(0.3)
            .end()
            .quickColor(Color.WHITE)
            .material()
                .texture()
                    .pigment(Color.WHITE)
                    .finish()
                        .diffuse(0.5)
                    .end()
                .end()
            .end()
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
    }

}
