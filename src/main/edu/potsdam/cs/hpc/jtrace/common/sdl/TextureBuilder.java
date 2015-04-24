package edu.potsdam.cs.hpc.jtrace.common.sdl;

import edu.potsdam.cs.hpc.jtrace.common.material.Finish;
import edu.potsdam.cs.hpc.jtrace.common.material.Texture;
import edu.potsdam.cs.hpc.jtrace.common.material.pigment.Pigment;

public class TextureBuilder
{
    private static final Pigment DEFAULT_PIGMENT = PigmentBuilder.DEFAULT;
    private static final Finish DEFAULT_FINISH = FinishBuilder.DEFAULT;

    static final Texture DEFAULT = new Texture(DEFAULT_PIGMENT, DEFAULT_FINISH);

    private Pigment pigment = DEFAULT_PIGMENT;
    private Finish finish = DEFAULT_FINISH;

    void setPigment (PigmentBuilder pigment)
    {
        this.pigment = pigment.eval();
    }

    void setFinish (FinishBuilder finish)
    {
        this.finish = finish.eval();
    }

    Texture eval ()
    {
        return new Texture(pigment, finish);
    }

}
