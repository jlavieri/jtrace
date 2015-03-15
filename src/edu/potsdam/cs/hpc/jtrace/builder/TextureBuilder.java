package edu.potsdam.cs.hpc.jtrace.builder;

import edu.potsdam.cs.hpc.jtrace.Color;
import edu.potsdam.cs.hpc.jtrace.Finish;
import edu.potsdam.cs.hpc.jtrace.Pigment;
import edu.potsdam.cs.hpc.jtrace.Texture;

/**
 * @author jlavieri
 * @version 2015-03-15
 * @since 2015-03-12
 */
public class TextureBuilder
{
    private final MaterialBuilder mb;

    Pigment pigment = Pigment.DEFAULT;
    Finish finish = Finish.DEFAULT;

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
