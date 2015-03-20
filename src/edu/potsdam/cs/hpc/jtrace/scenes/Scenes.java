package edu.potsdam.cs.hpc.jtrace.scenes;

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

import edu.potsdam.cs.hpc.jtrace.Scene;
import edu.potsdam.cs.hpc.jtrace.builder.SceneBuilder;

public class Scenes
{

    public static Scene getSceneFromFile(File inputFile)
    {
        Scene scene = null;
        try {
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
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
