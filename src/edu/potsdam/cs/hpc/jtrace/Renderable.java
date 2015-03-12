package edu.potsdam.cs.hpc.jtrace;

/**
 * @author  lavierijp
 * @version 2012-06-23
 * @since   2012-03-15
 */
abstract class Renderable
{
  Texture tex = new Texture();
  abstract double intersect (Ray ray);
  abstract double [] intersecta (Ray ray);
}