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
        
        if (COMM_WORLD.getRank() == 0) {
            // Build render settings
            RenderSettings renderSettings = new RenderSettingsBuilder(args).build();
            System.out.println("Tracing...");
            
            // Compile scene and convert to byte array.
            Scene scene = Scenes.getSceneFromFile(renderSettings.inputFile);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(scene);
            byte [] sba = bos.toByteArray();
            System.out.println("sba len: " + sba.length);
            
            sbaLength[0] = sba.length;
            /*Datatype sbaVec = Datatype.createVector(sba.length, 1,
                                                    BYTE.getSize(), BYTE);*/
            //COMM_WORLD.bcast(sba, 1, sbaVec, 0);
            
            //new Renderer(renderSettings).render();
        }
        
        COMM_WORLD.bcast(sbaLength, 1, INT, 0);
        
        System.out.printf("rank %s sba len: %s", rank, sbaLength[0]);
        
        Finalize();
    }
}