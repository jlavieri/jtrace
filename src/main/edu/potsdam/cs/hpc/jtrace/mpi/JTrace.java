package edu.potsdam.cs.hpc.jtrace.mpi;

import mpi.MPI;
import mpi.MPIException;
import edu.potsdam.cs.hpc.jtrace.common.RenderSettings;
import edu.potsdam.cs.hpc.jtrace.common.RenderSettingsBuilder;
import edu.potsdam.cs.hpc.jtrace.common.Renderer;

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
        
        if (MPI.COMM_WORLD.getRank() == 0) {
        
            RenderSettings renderSettings = new RenderSettingsBuilder(args).build();
            System.out.println("Tracing...");
            new Renderer(renderSettings).render();
        
        }
        
        MPI.Finalize();
    }
}