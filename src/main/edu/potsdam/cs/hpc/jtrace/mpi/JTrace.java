package edu.potsdam.cs.hpc.jtrace.mpi;

import static mpi.MPI.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
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
    private static int rank, size;
    
    public static void main(String [] args) throws MPIException, IOException, ClassNotFoundException
    {
        Init(args);
        
        rank = COMM_WORLD.getRank();
        size = COMM_WORLD.getSize();
        
        AtomicReference<Scene> scene = new AtomicReference<>();
        
        if (rank == 0) {
            RenderSettings renderSettings = new RenderSettingsBuilder(args).build();
            System.out.println("Tracing...");
            scene.set(Scenes.getSceneFromFile(renderSettings.inputFile));
        }
        
        broadcast(scene, 0);
        
        AtomicReference<String> renderConfig = new AtomicReference<>();
        
        List<String> list = new ArrayList<>();
        list.add("_A_");
        list.add("_B_");
        list.add("_C_");
        list.add("_D_");
        list.add("_E_");
        list.add("_F_");
        list.add("_G_");
        list.add("_H_");
        list.add("_I_");
        list.add("_J_");
        list.add("_K_");
        list.add("_L_");
        list.add("_M_");
        list.add("_N_");
        list.add("_O_");
        
        
        scatter(list, renderConfig, 0);
        
        if (rank != 0) {
            System.out.println(renderConfig.get());
            //BufferedImage bi = new Renderer(scene, renderConfig).render();
        }
        
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
    private static <T> void broadcast (AtomicReference<T> ref, int root)
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
    
    /**
     * Scatters a list of objects to the rest of the group and puts the result
     * in the the given atomic reference.
     * @param list
     * @param ref
     * @param root
     * @throws IOException
     * @throws MPIException
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
    private static <T> void scatter (List<T> list, AtomicReference<T> ref,
            int root) throws IOException, MPIException, ClassNotFoundException
    {
        int n = size - 1;
        if (list.size() != n)
            throw new IllegalArgumentException(String.format(
                 "The number of items in the provided list is %s, but there are %s non-root ranks in the group.", list.size(), n));
        
        byte [] buf = null;
        int [] pos = new int[n];
        int [] len = new int[n];
        
        if (rank == root) {
            int sum = 0;
            ByteArrayOutputStream [] baosa = new ByteArrayOutputStream[n];
            for (int i = 0; i < n; i++) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                new ObjectOutputStream(baos).writeObject(list.get(i));
                pos[i] = sum;
                sum += len[i] = baos.size();
                baosa[i] = baos;
            }
            buf = new byte[sum];
            for (int i = 0; i < n; i++)
                System.arraycopy(baosa[i].toByteArray(), 0, buf, pos[i], len[i]);
        }
        
        COMM_WORLD.scatter(len, 1, INT, len, 1, INT, root);
        
        if (rank != root)
            buf = new byte[len[0]];
        
        COMM_WORLD.scatterv(buf, len, pos, BYTE, buf, len[0], BYTE, root);
        
        if (rank != root) {
            ByteArrayInputStream bais = new ByteArrayInputStream(buf);
            ref.set((T) new ObjectInputStream(bais).readObject());
        }
    }
}