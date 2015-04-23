package edu.potsdam.cs.hpc.jtrace.common.sdl;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import edu.potsdam.cs.hpc.jtrace.common.Scene;

public class Scenes
{

    public static Scene getSceneFromFile(File inputFile)
    {
        Scene scene = null;
        try {
            if (inputFile.toString().equals("TestScene")) {
                TestScene testScene = new TestScene();
                SceneBuilder sceneBuilder = new SceneBuilder();
                testScene.getSceneDescription(sceneBuilder);
                return sceneBuilder.getScene();
            }
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            String parentDirectory = inputFile.getParent();
            if (parentDirectory == null)
                throw new IllegalArgumentException(
                        String.format("Could not get parent directory of %s. Check that the file is correct.",
                                      inputFile));
            Path classPath = Paths.get(inputFile.getParent());
            String className = inputFile.getName();
            className = className.substring(0, className.lastIndexOf('.'));
            URLClassLoader currentClassLoader = ((URLClassLoader) ClassLoader
                    .getSystemClassLoader());
            URL [] currentClassPaths = currentClassLoader.getURLs();
            int cpan = currentClassPaths.length;
            URL [] newClassPathURLs = new URL[cpan + 1];
            System.arraycopy(currentClassPaths, 0, newClassPathURLs, 0, cpan);
            newClassPathURLs[cpan] = classPath.toUri().toURL();
            List<String> compilerArgs = new ArrayList<>();
            compilerArgs.add("-cp");
            StringBuilder cpsb = new StringBuilder();
            for (URL url : newClassPathURLs)
                cpsb.append(url.getPath()).append(':');
            cpsb.deleteCharAt(cpsb.length() - 1);
            System.out.printf("Compiling %s\nClasspath %s\n", inputFile,
                              cpsb.toString());
            compilerArgs.add(cpsb.toString());
            compilerArgs.add(inputFile.getAbsolutePath());
            String [] compArgsStringArray = new String[compilerArgs.size()];
            for (int i = 0; i < compilerArgs.size(); i++)
                compArgsStringArray[i] = compilerArgs.get(i);
            ClassLoader classLoader = new URLClassLoader(newClassPathURLs);
            int status = compiler.run(null, null, null, compArgsStringArray);
            if (status != 0)
                System.exit(status);
            Class<?> sceneClass = Class.forName(className, true, classLoader);
            SceneDescription sceneDescription = (SceneDescription) sceneClass
                    .newInstance();
            SceneBuilder sb = new SceneBuilder();
            sceneDescription.getSceneDescription(sb);
            scene = sb.getScene();
        } catch (SecurityException | ClassNotFoundException
                | IllegalAccessException | IllegalArgumentException
                | InstantiationException | MalformedURLException e) {
            e.printStackTrace();
        }
        return scene;
    }
}
