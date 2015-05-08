import edu.potsdam.cs.hpc.jtrace.common.color.*;

import static edu.potsdam.cs.hpc.jtrace.common.Vec3.*;
import static edu.potsdam.cs.hpc.jtrace.common.color.Color.*;
import static edu.potsdam.cs.hpc.jtrace.common.sdl.LightType.*;
import static edu.potsdam.cs.hpc.jtrace.common.sdl.PatternType.*;
import static edu.potsdam.cs.hpc.jtrace.common.sdl.Projection.*;

import static java.lang.Math.*;

import edu.potsdam.cs.hpc.jtrace.common.Vec3;

import edu.potsdam.cs.hpc.jtrace.common.sdl.*;

/**
 * The traditional reflective sphere over checkered plane.
 * 
 * @author  jlavieri
 * @since   2015-05-07
 */
public class SunsetSpheresScene extends SceneDescription
{
    @Override
    public void describeScene()
    {
        globalSettings(
            ambientLight(GREY10)
        ).apply();
        
        camera(
            perspective,
            position(-19.5, 3.5, -19.5),
            lookAt(0, 0, 0)
        ).apply();
        
        light(
            point,
            position(4000, 400, -3000)
        ).apply();
        
        skySphere(
            texture (
                pigment (
                    gradient,
                    colorMap(1.00, new Color(0.17, 0.02, 0.27),
                             0.16, new Color(0.17, 0.02, 0.27),
                             0.00, new Color(0.99, 0.69, 0.00))
                )
            )
        ).apply();
        
        geom(
            plane(
                  position(0, -20, 0),
                  normal(Y)
            ),
            material(
                texture(
                    pigment(
                        checker,
                        colorList(new Color(0.00, 0.25, 0.34),
                                  new Color(0.01, 0.26, 0.71))
                    ),
                    finish(
                       reflection(0.6),
                       specular(0.2)
                    )
                )
            )
        ).apply();
        
        MaterialBuilder sphereMat =
        material(
            texture(
                pigment(GREY80),
                finish(
                    reflection(0.8),
                    specular(0.8)
                )
            )
        );
        
        geom(
            sphere(
                position(O),
                radius(6)
            ),
            sphereMat
        ).apply();
        
        double r = 20.0;
        
        for (double t = 0.0; t < 2.0 * PI; t += PI / 20.0) {
            geom(
                 sphere(position(r * cos(t), 0.0, r * sin(t)), radius(0.5)),
                 sphereMat
            ).apply();
            geom(
                 sphere(position(0.0, r * cos(t), r * sin(t)), radius(0.5)),
                 sphereMat
            ).apply();
            geom(
                 sphere(position(r * cos(t), r * sin(t), 0.0), radius(0.5)),
                 sphereMat
            ).apply();
        }
    }
}
