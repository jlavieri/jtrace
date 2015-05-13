package edu.potsdam.cs.hpc.jtrace.mpi;

import mpi.MPI;
import mpi.MPIException;

/**
 * JTrace MPI version.
 *
 * @author lavierijp
 */
public class JTrace
{
    public static void main(String [] args) throws MPIException
    {
        MPI.Init(args);
        
        System.out.println(MPI.COMM_WORLD.getRank());
        
        /*
        RenderSettings renderSettings = new RenderSettingsBuilder(args).build();
        System.out.println("Tracing...");
        new Renderer(renderSettings).render();
        */
        
        MPI.Finalize();
    }
}