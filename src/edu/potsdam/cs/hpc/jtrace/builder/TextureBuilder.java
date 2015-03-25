package edu.potsdam.cs.hpc.jtrace.builder;

import edu.potsdam.cs.hpc.jtrace.Color;
import edu.potsdam.cs.hpc.jtrace.Finish;
import edu.potsdam.cs.hpc.jtrace.Pigment;
import edu.potsdam.cs.hpc.jtrace.Texture;

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

    public TextureBuilder pigment(Color color)
    {
        pigment = new Pigment(color);
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
