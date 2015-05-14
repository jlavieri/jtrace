package edu.potsdam.cs.hpc.jtrace.mpi;

import static mpi.MPI.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import mpi.Datatype;
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
        
        System.out.println(COMM_WORLD.getRank());
        
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
            
            // Broadcast scene to workers.
            COMM_WORLD.bcast(sba.length, 1, INT, 0);
            Datatype sbaVec = Datatype.createVector(sba.length, 1,
                                                    BYTE.getSize(), BYTE);
            COMM_WORLD.bcast(sba, 1, sbaVec, 0);
            
            //new Renderer(renderSettings).render();
        
        } else {
            int[] sbaLength = new int[1];
            COMM_WORLD.recv(sbaLength, 1, INT, 0, 10);
            byte [] sba = new byte[sbaLength[0]];
            Datatype sbaVec = Datatype.createVector(sba.length, 1, BYTE.getSize(), BYTE);
            COMM_WORLD.recv(sba, 1, sbaVec, 0, 11);
            System.out.println(COMM_WORLD.getRank() + ": " + sba.length);
        }
        
        Finalize();
    }
}