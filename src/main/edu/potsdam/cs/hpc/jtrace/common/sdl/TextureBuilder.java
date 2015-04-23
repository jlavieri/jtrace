package edu.potsdam.cs.hpc.jtrace.common.sdl;

import edu.potsdam.cs.hpc.jtrace.common.color.Color;
import edu.potsdam.cs.hpc.jtrace.common.material.Finish;
import edu.potsdam.cs.hpc.jtrace.common.material.Texture;
import edu.potsdam.cs.hpc.jtrace.common.material.pigment.Pigment;

public class TextureBuilder
{
    private static final Pigment DEFAULT_PIGMENT = PigmentBuilder.DEFAULT;
    private static final Finish DEFAULT_FINISH = FinishBuilder.DEFAULT;

    static final Texture DEFAULT = new Texture(DEFAULT_PIGMENT, DEFAULT_FINISH);

    private final MaterialBuilder mb;

    Pigment pigment = DEFAULT_PIGMENT;
    Finish finish = DEFAULT_FINISH;

    public TextureBuilder(MaterialBuilder mb)
    {
        this.mb = mb;
    }

    public PigmentBuilder pigment()
    {
        return new PigmentBuilder(this);
    }
    
    public TextureBuilder pigment(Color color)
    {
        new PigmentBuilder(this).color(color).end();
        return this;
    }

    public FinishBuilder finish()
    {
        return new FinishBuilder(this);
    }

    public MaterialBuilder end()
    {
        mb.texture = new Texture(pigment, finish);
        return mb;
    }

}
