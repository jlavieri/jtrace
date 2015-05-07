package edu.potsdam.cs.hpc.jtrace.common.sdl;

import edu.potsdam.cs.hpc.jtrace.common.Scene;
import edu.potsdam.cs.hpc.jtrace.common.Vec3;
import edu.potsdam.cs.hpc.jtrace.common.color.Color;
import edu.potsdam.cs.hpc.jtrace.common.color.ColorList;
import edu.potsdam.cs.hpc.jtrace.common.color.ColorMap;
import edu.potsdam.cs.hpc.jtrace.common.color.IColor;


public abstract class SceneDescription
{
    protected SceneBuilder scene = new SceneBuilder();
    
    protected abstract void describeScene();
    
    public Scene getScene ()
    {
        describeScene();
        return scene.getScene();
    }

    // Scene Scope Elements =========================================
    
    protected GlobalSettingsBuilder globalSettings (Object ... tokens)
    {
        GlobalSettingsBuilder gsb = new GlobalSettingsBuilder(scene);
        for (Object tok : tokens) {
            if (tok instanceof BackgroundColor)
                gsb.setBackground((BackgroundColor)tok);
            else if (tok instanceof AmbientLightColor)
                gsb.setAmbientLight((AmbientLightColor)tok);
            else if (tok instanceof UseQuickColor)
                gsb.useQuickColor((UseQuickColor)tok);
        }
        return gsb;
    }
    
    protected CameraBuilder camera (Object ... tokens)
    {
        CameraBuilder cb = new CameraBuilder(scene);
        for (Object tok : tokens) {
            if (tok instanceof Projection)
                cb.setProjection((Projection)tok);
            else if (tok instanceof Position)
                cb.setPosition((Position)tok);
            else if (tok instanceof LookAt)
                cb.setLookAt((LookAt)tok);
        }
        return cb;
    }
    
    protected LightBuilder light (Object ... tokens)
    {
        LightBuilder lb = new LightBuilder(scene);
        for (Object tok : tokens) {
            if (tok instanceof LightType)
                lb.setType((LightType)tok);
            else if (tok instanceof Position)
                lb.setPosition((Position)tok);
            else if (tok instanceof ColorVal)
                lb.setColor((ColorVal)tok);
        }
        return lb;
    }
    
    protected SkySphereBuilder skySphere (Object ... tokens)
    {
        SkySphereBuilder ssb = new SkySphereBuilder(scene);
        for (Object tok : tokens)
            if (tok instanceof TextureBuilder)
                ssb.setTexture((TextureBuilder)tok);
        return ssb;
    }
    
    protected GeomBuilder geom (Object ... tokens)
    {
        GeomBuilder gb = new GeomBuilder(scene);
        for (Object tok : tokens) {
            if (tok instanceof QuickColor)
                gb.setQuickColor((QuickColor)tok);
            else if (tok instanceof PrimitiveBuilder)
                gb.setPrimitive((PrimitiveBuilder)tok);
            else if (tok instanceof MaterialBuilder)
                gb.setMaterial((MaterialBuilder)tok);
        }
        return gb;
    }
    
    // Primitive Types ==============================================
    
    protected static SphereBuilder sphere (Object ... tokens)
    {
        SphereBuilder sb = new SphereBuilder();
        for (Object tok : tokens) {
            if (tok instanceof Position)
                sb.setPosition((Position)tok);
            else if (tok instanceof Radius)
                sb.setRadius((Radius)tok);
        }
        return sb;
    }
    
    protected static PlaneBuilder plane (Object ... tokens)
    {
        PlaneBuilder pb = new PlaneBuilder();
        for (Object tok : tokens) {
            if (tok instanceof Position)
                pb.setPosition((Position)tok);
            else if (tok instanceof Normal)
                pb.setNormal((Normal)tok);
        }
        return pb;
    }
    
    // Material Types ===============================================
    
    protected static MaterialBuilder material (Object ... tokens)
    {
        MaterialBuilder mb = new MaterialBuilder();
        for (Object tok : tokens)
            if (tok instanceof TextureBuilder)
                mb.setTexture((TextureBuilder)tok);
        return mb;
    }
    
    protected static TextureBuilder texture (Object ... tokens)
    {
        TextureBuilder tb = new TextureBuilder();
        for (Object tok : tokens) {
            if (tok instanceof PigmentBuilder)
                tb.setPigment((PigmentBuilder)tok);
            else if (tok instanceof FinishBuilder)
                tb.setFinish((FinishBuilder)tok);
        }
        return tb;
    }
    
    protected static PigmentBuilder pigment (Object ... tokens)
    {
        PigmentBuilder pb = new PigmentBuilder();
        for (Object tok : tokens) {
            if (tok instanceof PatternType)
                pb.setPattern((PatternType)tok);
            else if (tok instanceof IColor)
                pb.setColor((IColor)tok);
        }
        return pb;
    }
    
    protected static FinishBuilder finish (Object ... tokens)
    {
        FinishBuilder fb = new FinishBuilder();
        for (Object tok : tokens) {
            if (tok instanceof Diffuse)
                fb.setDiffuse((Diffuse)tok);
            else if (tok instanceof Reflection)
                fb.setReflection((Reflection)tok);
            else if (tok instanceof Specular)
                fb.setSpecular((Specular)tok);
        }
        return fb;
    }
    
    // Vector Types =================================================
    
    protected static Position position (Vec3 v)
    {
        return new Position(v);
    }

    protected static Position position (double x, double y, double z)
    {
        return new Position(x, y, z);
    }
    
    protected static Normal normal (Vec3 v)
    {
        return new Normal(v);
    }

    protected static Normal normal (double x, double y, double z)
    {
        return new Normal(x, y, z);
    }
    
    protected static LookAt lookAt (Vec3 v)
    {
        return new LookAt(v);
    }
    
    protected static LookAt lookAt (double x, double y, double z)
    {
        return new LookAt(x, y, z);
    }
    
    // Color Types ==================================================
    
    protected static ColorVal color (Color color)
    {
        return new ColorVal(color);
    }
    
    protected static BackgroundColor background (Color color)
    {
        return new BackgroundColor(color);
    }
    
    protected static AmbientLightColor ambientLight (Color color)
    {
        return new AmbientLightColor(color);
    }
    
    protected static QuickColor quickColor (Color color)
    {
        return new QuickColor(color);
    }
    
    protected static ColorList colorList (Color ... colors)
    {
       ColorList colorList = new ColorList();
       for (Color color : colors)
           colorList.add(color);
       return colorList;
    }
    
    static class CME
    {
        double position;
        Color color;
        
        CME(double position, Color color)
        {
            this.position = position;
            this.color = color;
        }
    }
    
    protected static ColorMap colorMap (Object ... tokens)
    {
        ColorMap colorMap = new ColorMap();
        for (int i = 0; i < tokens.length; i++) {
            Object tok = tokens[i];
            Double position;
            Color color;
            if (tok instanceof Double) {
                position = (Double)tok;
                tok = tokens[++i];
            } else
                throw new IllegalArgumentException(
                   "Expected double in color map. Found " + tok.getClass());
            if (tok instanceof Color)
                color = (Color)tok;
            else
                throw new IllegalArgumentException(
                    "Expected Color in color map. Found " + tok.getClass());
            colorMap.put(position, color);
        }
        return colorMap;
    }
    
    // Double Types =================================================
    
    protected static Radius radius (double radius)
    {
        return new Radius(radius);
    }
    
    protected static Diffuse diffuse (double diffuse)
    {
        return new Diffuse(diffuse);
    }
    
    protected static Reflection reflection (double reflection)
    {
        return new Reflection(reflection);
    }
    
    protected static Specular specular (double specular)
    {
        return new Specular(specular);
    }
    
    // Flags ========================================================
    
    protected static UseQuickColor useQuickColor (boolean useQuickColor)
    {
        return new UseQuickColor(useQuickColor);
    }
}
