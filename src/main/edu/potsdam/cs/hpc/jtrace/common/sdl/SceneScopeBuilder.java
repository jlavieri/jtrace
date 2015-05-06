package edu.potsdam.cs.hpc.jtrace.common.sdl;

public abstract class SceneScopeBuilder
{
    protected final SceneBuilder sb;
    
    SceneScopeBuilder (SceneBuilder sb)
    {
        this.sb = sb;
    }
    
    public abstract void apply ();
}
