package edu.potsdam.cs.hpc.jtrace.common;

/**
 * Three-space vector used to represent Euclidean vectors and position vectors.
 *
 * @author lavierijp
 */
public final class Vec3
{
    /** Positive X unit vector. */
    public static final Vec3 X = new Vec3(1d, 0d, 0d);

    /** Positive Y unit vector. */
    public static final Vec3 Y = new Vec3(0d, 1d, 0d);

    /** Positive Z unit vector. */
    public static final Vec3 Z = new Vec3(0d, 0d, 1d);

    /** The origin or zero vector. */
    public static final Vec3 O = new Vec3();

    /** Negative X unit vector. */
    public static final Vec3 NX = new Vec3(-1d, 0d, 0d);

    /** Negative Y unit vector. */
    public static final Vec3 NY = new Vec3(0d, -1d, 0d);

    /** Negative Z unit vector. */
    public static final Vec3 NZ = new Vec3(0d, 0d, -1d);

    public double x, y, z;

    /**
     * Constructs a new zero vector.
     */
    public Vec3 ()
    {
        this(0d, 0d, 0d);
    }

    /**
     * Constructs a new vector with the given coordinates.
     * 
     * @param x
     *            the x-coordinate.
     * @param y
     *            The y-coordinate.
     * @param z
     *            The z-coordinate.
     */
    public Vec3 (double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Calculates the addition of two vectors.
     * 
     * @param v
     *            The other vector to calculate addition with this vector.
     * @return A new vector that is the result of adding this vector to another
     *         vector.
     */
    public Vec3 add (Vec3 v)
    {
        return new Vec3(x + v.x, y + v.y, z + v.z);
    }

    /**
     * Sets this vector to itself added with another vector.
     * 
     * @param v
     *            The other vector to add to this vector.
     * @return This newly mutated vector.
     */
    public Vec3 addeq (Vec3 v)
    {
        x += v.x;
        y += v.y;
        z += v.z;
        return this;
    }

    /**
     * Calculates the subtraction of two vectors.
     * 
     * @param v
     *            The other vector to calculate subtraction with this vector.
     * @return A new vector that is the result of subtracting another vector
     *         from this vector.
     */
    public Vec3 sub (Vec3 v)
    {
        return new Vec3(x - v.x, y - v.y, z - v.z);
    }

    /**
     * Sets this vector to itself minus another vector.
     * 
     * @param v
     *            The other vector to subtract from this vector.
     * @return This newly mutated vector.
     */
    public Vec3 subeq (Vec3 v)
    {
        x -= v.x;
        y -= v.y;
        z -= v.z;
        return this;
    }


    
    
    
    
    public Vec3 cross (Vec3 v)
    {
        return new Vec3(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x);
    }

    /**
     * Gets the normed direction of another vector relative to this one.
     * 
     * @param v
     *            A vector.
     * @return A unit vector pointing in the direction of the given vector from
     *         this one.
     */
    public Vec3 directionTo (Vec3 v)
    {
        return v.sub(this).snorm();
    }

    public Vec3 mul (double d)
    {
        return new Vec3(d * x, d * y, d * z);
    }

    public Vec3 div (double d)
    {
        return new Vec3(x / d, y / d, z / d);
    }

    public double dot (Vec3 v)
    {
        return x * v.x + y * v.y + z * v.z;
    }
    
    public Vec3 reflect (Vec3 normal, double dot)
    {
        return sub(normal.mul(2.0d * dot));
    }

    /**
     * gets the distance from this vector to another.
     * 
     * @param v
     *            the other vector.
     * @return the distance between the two vectors.
     */
    double distanceTo (Vec3 v)
    {
        double dx = v.x - x, dy = v.y - y, dz = v.z - z;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    Vec3 norm ()
    {
        return this.div(Math.sqrt(this.dot(this)));
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
    public boolean equals (Object obj)
    {
        if (this == obj)
            return true;
        if (obj instanceof Vec3) {
            Vec3 o = (Vec3) obj;
            return x == o.x && y == o.y && z == o.z;
        }
        return false;
    }

    @Override
    public String toString ()
    {
        StringBuilder sb = new StringBuilder(32);
        String d = ", ";
        sb.append('<').append(x).append(d).append(y).append(d).append(z)
                .append('>');
        return sb.toString();
    }
}
