package edu.potsdam.cs.hpc.jtrace.mpi;

import static mpi.MPI.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import mpi.MPIException;
import edu.potsdam.cs.hpc.jtrace.common.RenderSettings;
import edu.potsdam.cs.hpc.jtrace.common.RenderSettingsBuilder;
import edu.potsdam.cs.hpc.jtrace.common.Scene;
import edu.potsdam.cs.hpc.jtrace.common.sdl.Scenes;

/**
 * JTrace MPI version.
 *
 * @author lavierijp
 */
public class JTrace
{
    public static void main(String [] args) throws MPIException, IOException
    {
        Init(args);
        
        int rank = COMM_WORLD.getRank();
        int [] sbaLength = new int[1];
        byte [] sba = null;
        
        if (rank == 0) {
            // Build render settings
            RenderSettings renderSettings = new RenderSettingsBuilder(args).build();
            System.out.println("Tracing...");
            
            // Compile scene and convert to byte array.
            Scene scene = Scenes.getSceneFromFile(renderSettings.inputFile);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(scene);
            sba = bos.toByteArray();
            sbaLength[0] = sba.length;
            
            //new Renderer(renderSettings).render();
        }
        
        COMM_WORLD.bcast(sbaLength, 1, INT, 0);
        
        if (rank != 0)
            sba = new byte[sbaLength[0]];
        
        COMM_WORLD.bcast(sba, sba.length, BYTE, 0);
        
        System.out.printf("rank %s sba: %s", rank, sba);
        
        Finalize();
    }
}