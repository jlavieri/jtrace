package edu.potsdam.cs.hpc.jtrace.common.sdl;

import edu.potsdam.cs.hpc.jtrace.common.pattern.CheckerPattern;
import edu.potsdam.cs.hpc.jtrace.common.pattern.GradientPattern;
import edu.potsdam.cs.hpc.jtrace.common.pattern.Pattern;

public enum PatternType
{
    checker(new CheckerPattern()),
    gradient(new GradientPattern());
    
    Pattern pattern;
    
    PatternType (Pattern pattern)
    {
        this.pattern = pattern;
    }
}