package edu.potsdam.cs.hpc.jtrace.common.sdl;

import static edu.potsdam.cs.hpc.jtrace.common.Vec3.*;
import static edu.potsdam.cs.hpc.jtrace.common.color.Color.*;
import static edu.potsdam.cs.hpc.jtrace.common.sdl.LightType.*;
import static edu.potsdam.cs.hpc.jtrace.common.sdl.PatternType.*;
import static edu.potsdam.cs.hpc.jtrace.common.sdl.Projection.*;
import edu.potsdam.cs.hpc.jtrace.common.Vec3;

public class TestScene extends SceneDescription
{

    @Override
    public void describeScene()
    {
        globalSettings (
            background(BLACK),
            ambientLight(GREY10),
            useQuickColor(false)
        ).apply();
        
        camera (
            perspective,
            position(2, 1.5, 2),
            lookAt(O)
        ).apply();
        
        light(
            point,
            position(20, 40, -20),
            color(SKY_AQUA)
        ).apply();
        
        light(
            point,
            position(-20, 40, -20),
            color(VIOLET)
        ).apply();
        
        skySphere(
            texture (
                pigment (
                    checker,
                    colorList(CHARTREUSE, VIOLET)
                )
            )
        ).apply();
        
        geom(
            sphere(
                position(O),
                radius(0.3)
            ),
            quickColor(WHITE),
            material(
                texture(
                    pigment(WHITE),
                    finish(
                        diffuse(0.5)
                    )
                )
            )
        ).apply();
        
        geom(sphere(position(Vec3.X), radius(0.1)),
             material(texture(pigment(RED)))).apply();
        
        geom(sphere(position(Vec3.Y), radius(0.1)),
             material(texture(pigment(GREEN)))).apply();
        
        geom(sphere(position(Vec3.Z), radius(0.1)),
             material(texture(pigment(BLUE)))).apply();
        
        geom(sphere(position(Vec3.NX), radius(0.1)),
             material(texture(pigment(CYAN)))).apply();
        
        geom(sphere(position(Vec3.NY), radius(0.1)),
             material(texture(pigment(MAGENTA)))).apply();
        
        geom(sphere(position(Vec3.NZ), radius(0.1)),
             material(texture(pigment(YELLOW)))).apply();
    }

}
