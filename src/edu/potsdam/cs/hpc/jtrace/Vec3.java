package edu.potsdam.cs.hpc.jtrace;

/**
 * Three-space vector used to represent Euclidean vectors and position vectors.
 *
 * @author  lavierijp
 * @version 2012-06-23
 * @since   2012-03-15
 */
class Vec3
{
  static final Vec3
      X = new Vec3(1d, 0d, 0d),
      Y = new Vec3(0d, 1d, 0d),
      Z = new Vec3(0d, 0d, 1d),
      O = new Vec3(),
      NX = new Vec3(-1d, 0d, 0d),
      NY = new Vec3(0d, -1d, 0d),
      NZ = new Vec3(0d, 0d, -1d);

  double x, y, z;

  Vec3 ()
  {
    this(0d, 0d, 0d);
  }

  Vec3 (double x, double y, double z)
  {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  Vec3 add (Vec3 v)
  {
    return new Vec3(x + v.x, y + v.y, z + v.z);
  }

  Vec3 sub (Vec3 v)
  {
    return new Vec3(x - v.x, y - v.y, z - v.z);
  }

  Vec3 crs (Vec3 v)
  {
    return new Vec3(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x);
  }

  Vec3 dir (Vec3 v)
  {
    return this.sub(v).snorm();
  }

  Vec3 mul (double d)
  {
    return new Vec3(d * x, d * y, d * z);
  }

  Vec3 div (double d)
  {
    return new Vec3(x / d, y / d, z / d);
  }

  double dot (Vec3 v)
  {
    return x * v.x + y * v.y + z * v.z;
  }

  double dis (Vec3 v)
  {
    double
      dx = v.x - x,
      dy = v.y - y,
      dz = v.z - z;
    return Math.sqrt(dx * dx + dy * dy + dz * dz);
  }

  Vec3 norm ()
  {
    return this.div(Math.sqrt(this.dot(this)));
  }

  Vec3 sadd (Vec3 v)
  {
    x += v.x;
    y += v.y;
    z += v.z;
    return this;
  }

  Vec3 sdiv (double d)
  {
    x /= d;
    y /= d;
    z /= d;
    return this;
  }

  Vec3 snorm ()
  {
    return this.sdiv(Math.sqrt(this.dot(this)));
  }

  @Override
  public String toString ()
  {
    StringBuilder sb = new StringBuilder(32);
    String d = ", ";
    sb.append('<')
      .append(x).append(d)
      .append(y).append(d)
      .append(z).append('>');
    return sb.toString();
  }
}