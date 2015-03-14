package edu.potsdam.cs.hpc.jtrace;

/**
 * Three-space vector used to represent Euclidean vectors and position vectors.
 *
 * @author lavierijp
 * @version 2015-03-12
 * @since 2012-03-15
 */
public class Vec3
{
    public static final Vec3 X = new Vec3(1d, 0d, 0d), Y = new Vec3(0d, 1d, 0d),
            Z = new Vec3(0d, 0d, 1d), O = new Vec3(),
            NX = new Vec3(-1d, 0d, 0d), NY = new Vec3(0d, -1d, 0d),
            NZ = new Vec3(0d, 0d, -1d);

    double x, y, z;

    public Vec3()
    {
        this(0d, 0d, 0d);
    }

    public Vec3(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    Vec3 add(Vec3 v)
    {
        return new Vec3(x + v.x, y + v.y, z + v.z);
    }

    public Vec3 sub(Vec3 v)
    {
        return new Vec3(x - v.x, y - v.y, z - v.z);
    }

    Vec3 cross(Vec3 v)
    {
        return new Vec3(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x);
    }

    /**
     * Gets the direction of another vector relative to this one.
     * 
     * @param v A vector.
     * @return A unit vector pointing in the direction of the given vector from
     * this one.
     */
    public Vec3 directionTo(Vec3 v)
    {
        return v.sub(this).snorm();
    }

    public Vec3 mul(double d)
    {
        return new Vec3(d * x, d * y, d * z);
    }

    Vec3 div(double d)
    {
        return new Vec3(x / d, y / d, z / d);
    }

    public double dot(Vec3 v)
    {
        return x * v.x + y * v.y + z * v.z;
    }

    /**
     * gets the distance from this vector to another.
     * @param v the other vector.
     * @return the distance between the two vectors.
     */
    double distanceTo(Vec3 v)
    {
        double dx = v.x - x, dy = v.y - y, dz = v.z - z;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    Vec3 norm()
    {
        return this.div(Math.sqrt(this.dot(this)));
    }

    Vec3 sadd(Vec3 v)
    {
        x += v.x;
        y += v.y;
        z += v.z;
        return this;
    }

    Vec3 sdiv(double d)
    {
        x /= d;
        y /= d;
        z /= d;
        return this;
    }

    Vec3 snorm()
    {
        return this.sdiv(Math.sqrt(this.dot(this)));
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj instanceof Vec3) {
            Vec3 o = (Vec3)obj;
            return x == o.x && y == o.y && z == o.z;
        }
        return false;
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder(32);
        String d = ", ";
        sb.append('<').append(x).append(d).append(y).append(d).append(z)
                .append('>');
        return sb.toString();
    }
}