package edu.potsdam.cs.hpc.jtrace;

/**
 * @author jlavieri
 * @version 2015-03-11
 * @since 2012-03-15
 */
public class Color
{
    public static final Color
        BLACK = new Color(),
        GREY = new Color(0.5),
        WHITE = new Color(1),
        RED = new Color(1, 0, 0),
        GREEN = new Color(0, 1, 0),
        BLUE = new Color(0, 0, 1),
        CYAN = new Color(0, 1, 1),
        MAGENTA = new Color(1, 0, 1),
        YELLOW = new Color(1, 1, 0),
        ORANGE = new Color(1, 0.5, 0),
        CHARTREUSE = new Color(0.5, 1, 0),
        BLUE_VIOLET = new Color(0.5, 0, 1),
        DEEP_PINK = new Color(1, 0, 0.5),
        SPRING_GREEN = new Color(0, 1, 0.5),
        SKY_BLUE = new Color(0, 0.5, 1),
        CORAL = new Color(1, 0.5, 0.5),
        PALE_GREEN = new Color(0.5, 1, 0.5),
        SLATE_BLUE = new Color(0.5, 0.5, 1),
        SKY_AQUA = new Color(0.5, 1, 1),
        VIOLET = new Color(1, 0.5, 1),
        KHAKI = new Color(1, 1, 0.5),
        GREY10 = new Color(0.1),
        GREY20 = new Color(0.2),
        GREY30 = new Color(0.3),
        GREY40 = new Color(0.4),
        GREY50 = new Color(0.5),
        GREY60 = new Color(0.6),
        GREY70 = new Color(0.7),
        GREY80 = new Color(0.8),
        GREY90 = new Color(0.9);

    double r, g, b, f, t; // Red, Green, Blue, Filter, Transmit

    Color ()
    {
        this(0d);
    }

    Color (double d)
    {
        this(d, d, d);
    }

    Color (double r, double g, double b)
    {
        this(r, g, b, 0d, 0d);
    }

    Color (double r, double g, double b, double f, double t)
    {
        this.r = r;
        this.g = g;
        this.b = b;
        this.f = f;
        this.t = t;
    }

    public Color(Color c)
    {
        this(c.r, c.g, c.b, c.f, c.t);
    }

    void set(double r, double g, double b, double f, double t)
    {
        set(r, g, b);
        this.f = f;
        this.t = t;
    }

    void set(double r, double g, double b)
    {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    Color add(Color c)
    {
        return new Color(r + c.r, g + c.g, b + c.b, f + c.f, t + c.t);
    }

    Color mul(double d)
    {
        return new Color(d * r, d * g, d * b, d * f, d * t);
    }

    Color sadd(Color c)
    {
        r += c.r;
        g += c.g;
        b += c.b;
        f += c.f;
        t += c.t;
        return this;
    }
    
    Color smult(Color c)
    {
        r *= c.r;
        g *= c.g;
        b *= c.b;
        f *= c.f;
        t *= c.t;
        return this;
    }

    int toInt()
    {
        int r = Math.min((int) (this.r * 255d), 255);
        int b = Math.min((int) (this.b * 255d), 255);
        int g = Math.min((int) (this.g * 255d), 255);
        return (r << 020) + (g << 010) + b;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder(32);
        String d = ", ";
        sb.append('<').append(r).append(d).append(g).append(d).append(b)
                .append(d).append(f).append(d).append(t).append('>');
        return sb.toString();
    }
}