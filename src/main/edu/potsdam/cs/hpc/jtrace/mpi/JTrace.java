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
import edu.potsdam.cs.hpc.jtrace.common.RenderConfiguration;
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
        RenderSettings renderSettings = null;
        
        if (rank == 0) {
            renderSettings = new RenderSettingsBuilder(args).build();
            System.out.println("Tracing...");
            scene.set(Scenes.getSceneFromFile(renderSettings.inputFile));
        }
        
        broadcast(scene, 0);
        
        AtomicReference<RenderConfiguration> renderConfig = new AtomicReference<>();
        List<RenderConfiguration> configList = null;
        if (rank == 0)
            configList = getRenderConfigList(renderSettings);
        
        scatter(configList, renderConfig, 0);
        
        if (rank != 0) {
            System.out.println(renderConfig.get());
            //BufferedImage bi = new Renderer(scene, renderConfig).render();
        }
        
        Finalize();
    }
    
    private static List<RenderConfiguration> getRenderConfigList (
            RenderSettings renderSettings)
    {
        List<RenderConfiguration> renderConfigs = new ArrayList<>(size);
        int width = renderSettings.dimension.width;
        int height = renderSettings.dimension.height;
        int totalArea = width * height;
        double regionLength = Math.sqrt((double)totalArea / (double)size);
        int nCols = (int)Math.round((double)width / regionLength);
        int nRows = (int)Math.round((double)height / regionLength);
        int colWidth = width / nCols;
        int rowHeight = height / nRows;
        
        // Standard region.
        for (int i = 0; i < nCols - 1; i++)
            for (int j = 0; j < nRows - 1; j++) {
                int startCol = i * colWidth;
                int startRow = j * rowHeight;
                int stopCol = (i + 1) * colWidth - 1;
                int stopRow = (j + 1) * rowHeight - 1;
                renderConfigs.add(new RenderConfiguration(width, height,
                                         startCol, startRow, stopCol, stopRow));
            }
        
        if (width >= height) { // Landscape, square, degenerate
            // Last column less bottom cell.
            for (int j = 0; j < nRows - 1; j++) {
                int startCol = (nCols - 1) * colWidth;
                int startRow = j * rowHeight;
                int stopCol = width - 1;
                int stopRow = (j + 1) * rowHeight - 1;
                renderConfigs.add(new RenderConfiguration(width, height,
                                         startCol, startRow, stopCol, stopRow));
            }
            
            // Last row less right-most cell.
            int nColsInLastRow = size - (nRows - 1) * nCols;
            int colWidthInLastRow = width / nColsInLastRow;
            for (int i = 0; i < nColsInLastRow - 1; i++) {
                int startCol = i * colWidthInLastRow;
                int startRow = (nRows - 1) * rowHeight;
                int stopCol = (i + 1) * colWidthInLastRow - 1;
                int stopRow = height - 1;
                renderConfigs.add(new RenderConfiguration(width, height,
                                         startCol, startRow, stopCol, stopRow));
            }
            
            // Last cell.
            int startCol = (nColsInLastRow - 1) * colWidthInLastRow;
            int startRow = (nRows - 1) * rowHeight;
            int stopCol = width - 1;
            int stopRow = height - 1;
            renderConfigs.add(new RenderConfiguration(width, height,
                                     startCol, startRow, stopCol, stopRow));
        } else { // Portrait
            // Last row less right-most cell.
            for (int i = 0; i < nRows - 1; i++) {
                int startCol = i * colWidth;
                int startRow = (nRows - 1) * rowHeight;
                int stopCol = (i + 1) * colWidth - 1;
                int stopRow = height - 1;
                renderConfigs.add(new RenderConfiguration(width, height,
                                         startCol, startRow, stopCol, stopRow));
            }
            
            // Last column less bottom cell.
            int nRowsInLastCol = size - (nCols - 1) * nRows;
            int rowHeightInLastCol = height / nRowsInLastCol;
            for (int j = 0; j < nRowsInLastCol - 1; j++) {
                int startCol = (nCols - 1) * colWidth;
                int startRow = j * rowHeightInLastCol;
                int stopCol = width - 1;
                int stopRow = (j + 1) * rowHeightInLastCol - 1;
                renderConfigs.add(new RenderConfiguration(width, height,
                                         startCol, startRow, stopCol, stopRow));
            }
            
            // Last cell.
            int startCol = (nCols - 1) * colWidth;
            int startRow = (nRowsInLastCol - 1) * rowHeightInLastCol;
            int stopCol = width - 1;
            int stopRow = height - 1;
            renderConfigs.add(new RenderConfiguration(width, height,
                                     startCol, startRow, stopCol, stopRow));
        }
        return renderConfigs;
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
        if (rank == root && list.size() != size)
            throw new IllegalArgumentException(String.format(
                 "The number of items in the provided list is %s, but there are %s ranks in the group.", list.size(), size));
        
        byte [] srcBuf = null;
        byte [] dstBuf = null;
        int [] pos = new int[size];
        int [] srcLen = new int[size];
        int [] dstLen = new int [1];
        
        if (rank == root) {
            int sum = 0;
            ByteArrayOutputStream [] baosa = new ByteArrayOutputStream[size];
            for (int i = 0; i < size; i++) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                new ObjectOutputStream(baos).writeObject(list.get(i));
                pos[i] = sum;
                sum += srcLen[i] = baos.size();
                baosa[i] = baos;
            }
            srcBuf = new byte[sum];
            for (int i = 0; i < size; i++)
                System.arraycopy(baosa[i].toByteArray(), 0, srcBuf, pos[i], srcLen[i]);
        }
        
        COMM_WORLD.scatter(srcLen, 1, INT, dstLen, 1, INT, root);
        
        dstBuf = new byte[dstLen[0]];
        
        COMM_WORLD.scatterv(srcBuf, srcLen, pos, BYTE, dstBuf, dstLen[0], BYTE, root);
        
        if (rank != root) {
            ByteArrayInputStream bais = new ByteArrayInputStream(dstBuf);
            ref.set((T) new ObjectInputStream(bais).readObject());
        }
    }
}