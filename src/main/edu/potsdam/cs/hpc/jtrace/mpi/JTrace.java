package edu.potsdam.cs.hpc.jtrace.mpi;

import static mpi.MPI.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import javax.imageio.ImageIO;

import mpi.MPIException;
import edu.potsdam.cs.hpc.jtrace.common.RenderConfiguration;
import edu.potsdam.cs.hpc.jtrace.common.RenderSettings;
import edu.potsdam.cs.hpc.jtrace.common.RenderSettingsBuilder;
import edu.potsdam.cs.hpc.jtrace.common.Renderer;
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
        
        AtomicReference<int []> image = new AtomicReference<>();
        image.set(getARGB(new Renderer(scene.get(), renderConfig.get()).render()));
        AtomicReference<int []> imagesBuffer = new AtomicReference<>();
        AtomicReference<int []> imagesOffsets = new AtomicReference<>();
        
        gather(imagesBuffer, imagesOffsets, image, 0);
        
        if (rank == 0) {
            BufferedImage merge = new BufferedImage(renderConfig.get().width,
                        renderConfig.get().height, BufferedImage.TYPE_INT_ARGB);
            for (int i = 0; i < size; i++) {
                RenderConfiguration rc = configList.get(i);
                int startX = rc.startCol;
                int startY = rc.startRow;
                int w = rc.stopCol - rc.startCol + 1;
                int h = rc.stopRow - rc.startRow + 1;
                int [] rgbArray = imagesBuffer.get();
                int offset = imagesOffsets.get()[i];
                int scansize = rc.width;
                merge.setRGB(startX, startY, w, h, rgbArray, offset, scansize);
            }
            ImageIO.write(merge, "png", renderSettings.outputFile);
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

    private static int [] getARGB (BufferedImage image)
    {
        return image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0,
                            image.getWidth());
    }

    /**
     * Broadcasts a serializable object from a given root to the rest of the
     * group.
     * 
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
     * 
     * @param list
     *            The list of objects to scatter.
     * @param ref
     *            The destination buffer.
     * @param root
     *            The root rank.
     * @throws IOException
     *             If an I/O error occurs.
     * @throws MPIException
     *             If an MPI error occurs.
     * @throws ClassNotFoundException
     *             If something horrible goes wrong.
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
        
        ByteArrayInputStream bais = new ByteArrayInputStream(dstBuf);
        ref.set((T) new ObjectInputStream(bais).readObject());
    }

    /**
     * Gathers from all ranks in the group from the given atomic reference and
     * puts it all in the given list.
     * 
     * @param list
     *            The destination list.
     * @param ref
     *            The source reference.
     * @param root
     *            The root rank.
     * @throws IOException
     *             If an I/O error occurs.
     * @throws MPIException
     *             If an MPI error occurs.
     * @throws ClassNotFoundException
     *             If something horrible goes wrong.
     */
    @SuppressWarnings("unchecked")
    private static <T> void gather (List<T> list, AtomicReference<T> ref, int root) throws IOException, MPIException, ClassNotFoundException
    {
        byte [] srcBuf = null;
        byte [] dstBuf = null;
        int [] pos = null;
        int [] srcLen = new int[1];
        int [] dstLen = null;
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        new ObjectOutputStream(baos).writeObject(ref.get());
        srcBuf = baos.toByteArray();
        srcLen[0] = srcBuf.length;
        
        if (rank == root) {
            dstLen = new int[size];
            pos = new int[size];
        }
        
        COMM_WORLD.gather(srcLen, 1, INT, dstLen, 1, INT, root);
        
        if (rank == root) {
            int sum = 0;
            pos = new int[size];
            for (int i = 0; i < size; i++) {
                pos[i] = sum;
                sum += dstLen[i];
            }
            dstBuf = new byte[sum];
        }
        
        COMM_WORLD.gatherv(srcBuf, srcLen[0], BYTE, dstBuf, dstLen, pos, BYTE, root);
        
        if (rank == root)
            for (int i = 0; i < size; i++) {
                ByteArrayInputStream bais = new ByteArrayInputStream(dstBuf, pos[i], dstLen[i]);
                list.add((T) new ObjectInputStream(bais).readObject());
            }
    }

    /**
     * Gathers integers from a source buffer and concatenates them into a
     * destination buffer.
     * 
     * @param dstBufRef
     *            The destination buffer reference.
     * @param dstLenRef
     *            The destination length reference.
     * @param dstPosRef
     *            The destination offset reference.
     * @param srcRef
     *            The source buffer reference.
     * @param root
     *            The root rank.
     * @throws MPIException
     *             If an MPI error occurs.
     */
    private static <T> void gather (AtomicReference<int[]> dstBufRef,
            AtomicReference<int[]> dstPosRef,
            AtomicReference<int[]> srcRef, int root) throws MPIException
    {
        int [] srcBuf = srcRef.get();
        int [] srcLen = new int[]{srcBuf.length};
        int [] dstBuf = null;
        int [] dstLen = null;
        int [] pos = null;
        
        if (rank == root)
            dstLen = new int[size];
        
        COMM_WORLD.gather(srcLen, 1, INT, dstLen, 1, INT, root);
        
        if (rank == root) {
            int sum = 0;
            pos = new int[size];
            for (int i = 0; i < size; i++) {
                pos[i] = sum;
                sum += dstLen[i];
            }
            dstBuf = new int[sum];
        }
        
        COMM_WORLD.gatherv(srcBuf, srcLen[0], INT, dstBuf, dstLen, pos, INT, root);
        
        if (rank == root) {
            dstBufRef.set(dstBuf);
            dstPosRef.set(pos);
        }
    }
}