import edu.potsdam.cs.hpc.jtrace.common.Color;
import edu.potsdam.cs.hpc.jtrace.common.builder.*;
import edu.potsdam.cs.hpc.jtrace.common.scenes.*;

/**
 * The traditional reflective sphere over checkered plane.
 * 
 * @author  jlavieri
 * @version 2015-03-24
 * @since   2015-03-25
 */
public class RsocpScene implements SceneDescription
{
    @Override
    public void getSceneDescription(SceneBuilder sb)
    {
        sb
        .globalSettings()
            .ambientLight(Color.GREY10)
        .end()
        .camera()
            .perspective()
            .position(0, 1, -4)
            .lookAt(0, 1, 0)
        .end()
        .light()
            .point()
            .position(3000, 3000, -3000)
        .end()
        .geom()
            .plane()
            .end()
            .material()
                .texture()
                    .pigment(Color.GREY)
                .end()
            .end()
        .end()
        .geom()
            .sphere()
                .position(0, 1.35, 0)
            .end()
            .material()
                .texture()
                    .pigment(Color.BLUE)
                    .finish()
                        .reflection(0.4)
                    .end()
                .end()
            .end()
        .end();
    }
}
