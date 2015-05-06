package edu.potsdam.cs.hpc.jtrace.common.sdl;

import edu.potsdam.cs.hpc.jtrace.common.SkySphere;
import edu.potsdam.cs.hpc.jtrace.common.material.Texture;

public final class SkySphereBuilder extends SceneScopeBuilder
{
    private Texture texture = TextureBuilder.DEFAULT;
    
    SkySphereBuilder (SceneBuilder sb)
    {
        super(sb);
    }

    void setTexture (TextureBuilder texture)
    {
        this.texture = texture.eval();
    }
    
    @Override
    public void apply ()
    {
        sb.setSkySphere(new SkySphere(texture));
    }
}
