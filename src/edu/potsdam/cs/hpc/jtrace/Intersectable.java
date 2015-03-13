package edu.potsdam.cs.hpc.jtrace;

public interface Intersectable
{
    abstract double intersect(Ray ray);

    abstract double [] intersecta(Ray ray);
}
