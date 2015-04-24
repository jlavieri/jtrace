package edu.potsdam.cs.hpc.jtrace.common.sdl;

import edu.potsdam.cs.hpc.jtrace.common.pattern.CheckerPattern;
import edu.potsdam.cs.hpc.jtrace.common.pattern.Pattern;

public enum PatternType
{
    checker(new CheckerPattern());
    
    Pattern pattern;
    
    PatternType (Pattern pattern)
    {
        this.pattern = pattern;
    }
}