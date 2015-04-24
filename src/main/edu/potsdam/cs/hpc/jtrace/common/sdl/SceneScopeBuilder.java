package edu.potsdam.cs.hpc.jtrace.common.sdl;

abstract class SceneScopeBuilder
{
    protected final SceneBuilder sb;
    
    SceneScopeBuilder (SceneBuilder sb)
    {
        this.sb = sb;
    }
    
    abstract void apply ();
}
