package edu.potsdam.cs.hpc.jtrace.common;

public class RenderConfiguration
{
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

    @Override
    public String toString ()
    {
        return String.format("RC((%s,%s),(%s,%s),(%s,%s))", width, height, startCol, startRow, stopCol, stopRow);
    }
}
