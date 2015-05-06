import edu.potsdam.cs.hpc.jtrace.common.color.*;


import static edu.potsdam.cs.hpc.jtrace.common.Vec3.*;
import static edu.potsdam.cs.hpc.jtrace.common.color.Color.*;
import static edu.potsdam.cs.hpc.jtrace.common.sdl.LightType.*;
import static edu.potsdam.cs.hpc.jtrace.common.sdl.PatternType.*;
import static edu.potsdam.cs.hpc.jtrace.common.sdl.Projection.*;
import edu.potsdam.cs.hpc.jtrace.common.Vec3;

import edu.potsdam.cs.hpc.jtrace.common.sdl.*;

/**
 * The traditional reflective sphere over checkered plane.
 * 
 * @author  jlavieri
 * @since   2015-04-23
 */
public class RsocpScene extends SceneDescription
{
    @Override
    public void describeScene()
    {
        globalSettings(
            ambientLight(GREY10)
        ).apply();
        
        camera(
            perspective,
            position(0, 1, -4),
            lookAt(0, 1, 0)
        ).apply();
        
        light(
            point,
            position(3000, 3000, -3000)
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
            plane(),
            material(
                texture(
                    pigment(
                        checker,
                        colorList(Color.BLACK, Color.WHITE)
                    )
                )
            )
        ).apply();
        
        geom(
            sphere(
                position(0, 1.35, 0)
            ),
            material(
                texture(
                    pigment(BLUE),
                    finish(
                        reflection(0.4),
                        specular(0.6)
                    )
                )
            )
        ).apply();
    }
}
