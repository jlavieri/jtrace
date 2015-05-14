package edu.potsdam.cs.hpc.jtrace.mpi;

import static mpi.MPI.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.atomic.AtomicReference;

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
    private static int rank;
    
    public static void main(String [] args) throws MPIException, IOException, ClassNotFoundException
    {
        Init(args);
        
        rank = COMM_WORLD.getRank();
        
        AtomicReference<Scene> scene = new AtomicReference<>();
        
        if (rank == 0) {
            RenderSettings renderSettings = new RenderSettingsBuilder(args).build();
            System.out.println("Tracing...");
            scene.set(Scenes.getSceneFromFile(renderSettings.inputFile));
        }
        
        bcast(scene, 0);
        
        System.out.printf("rank %s scene: %s", rank, scene.get());
        
        Finalize();
    }
    
    /**
     * Broadcasts a serializable object from a given root to the rest of the
     * group.
     * 
     * @param <T>
     *            The reference type.
     * @param ref
     *            An atomic reference to the object to be broadcast.
     * @param root
     *            The source root rank for the broadcast.
     * @throws IOException
     *             If an I/O error occurs.
     * @throws MPIException
     *             If an MPI error occurs.
     * @throws ClassNotFoundException
     *             If something horrible goes wrong.
     */
    @SuppressWarnings("unchecked")
    private static <T> void bcast (AtomicReference<T> ref, int root)
            throws IOException, MPIException, ClassNotFoundException
    {
        int [] len = new int[1];
        byte [] buf = null;

        if (rank == root) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            new ObjectOutputStream(baos).writeObject(ref.get());
            buf = baos.toByteArray();
            len[0] = buf.length;
        }

        COMM_WORLD.bcast(len, 1, INT, root);

        if (rank != root)
            buf = new byte[len[0]];

        COMM_WORLD.bcast(buf, len[0], BYTE, root);

        if (rank != root) {
            ByteArrayInputStream bais = new ByteArrayInputStream(buf);
            ref.set((T) new ObjectInputStream(bais).readObject());
        }
    }
}