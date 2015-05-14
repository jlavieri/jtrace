package edu.potsdam.cs.hpc.jtrace.common;

import java.awt.Dimension;
import java.io.Serializable;

public class RenderConfiguration implements Serializable
{
    private static final long serialVersionUID = 7362561360504232226L;
    
    public final int width, height, startCol, startRow, stopCol, stopRow;
    
    public RenderConfiguration (int width, int height, int startCol,
            int startRow, int stopCol, int stopRow)
    {
        this.width = width;
        this.height = height;
        this.startCol = startCol;
        this.startRow = startRow;
        this.stopCol = stopCol;
        this.stopRow = stopRow;
    }

    public RenderConfiguration (Dimension d)
    {
        this(d.width, d.height, 0, 0, d.width - 1, d.height - 1);
    }

    @Override
    public String toString ()
    {
        return String.format("RC((%s,%s),(%s,%s),(%s,%s))", width, height, startCol, startRow, stopCol, stopRow);
    }
}
