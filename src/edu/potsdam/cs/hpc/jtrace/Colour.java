package edu.potsdam.cs.hpc.jtrace;

/**
 * @author  jlavieri
 * @version 2012-06-23
 * @since   2012-03-15
 */
class Colour
{
  double r, g, b, f, t; // Red, Green, Blue, Filter, Transmit

  Colour ()
  {
    this(0d);
  }

  Colour (double d)
  {
    this(d, d, d);
  }

  Colour (double r, double g, double b)
  {
    this(r, g, b, 0d, 0d);
  }

  Colour (double r, double g, double b, double f, double t)
  {
    this.r = r;
    this.g = g;
    this.b = b;
    this.f = f;
    this.t = t;
  }

  void set (double r, double g, double b, double f, double t)
  {
    set(r, g, b);
    this.f = f;
    this.t = t;
  }

  void set (double r, double g, double b)
  {
    this.r = r;
    this.g = g;
    this.b = b;
  }

  Colour add (Colour c)
  {
    return new Colour(r + c.r, g + c.g, b + c.b, f + c.f, t + c.t);
  }

  Colour mul (double d)
  {
    return new Colour(d * r, d * g, d * b, d * f, d * t);
  }

  Colour sadd (Colour c)
  {
    r += c.r;
    g += c.g;
    b += c.b;
    f += c.f;
    t += c.t;
    return this;
  }

  int toInt ()
  {
    int r = Math.min((int)(this.r * 255d), 255);
    int b = Math.min((int)(this.b * 255d), 255);
    int g = Math.min((int)(this.g * 255d), 255);
    return (r << 020) + (g << 010) + b;
  }

  @Override
  public String toString ()
  {
    StringBuilder sb = new StringBuilder(32);
    String d = ", ";
    sb.append('<')
      .append(r).append(d)
      .append(g).append(d)
      .append(b).append(d)
      .append(f).append(d)
      .append(t).append('>');
    return sb.toString();
  }
}