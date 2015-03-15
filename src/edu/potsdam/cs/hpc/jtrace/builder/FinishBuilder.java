package edu.potsdam.cs.hpc.jtrace.builder;

import edu.potsdam.cs.hpc.jtrace.Finish;

/**
 * @author  jlavieri
 * @version 2015-03-15
 * @since   2015-03-15
 */
public class FinishBuilder
{
    private final TextureBuilder tb;
    
    double diffuse = Finish.DEFAULT_DIFFUSE;

    public FinishBuilder(TextureBuilder tb)
    {
        this.tb = tb;
    }


    public FinishBuilder diffuse(double diffuse)
    {
        this.diffuse = diffuse;
        return this;
    }


    public TextureBuilder end()
    {
        tb.finish = new Finish(diffuse);
        return tb;
    }

}
