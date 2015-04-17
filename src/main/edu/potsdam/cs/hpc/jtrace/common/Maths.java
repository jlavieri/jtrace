package edu.potsdam.cs.hpc.jtrace.common;

/**
 * Basic mathematics.
 *
 * @author  lavierijp
 * @version 2012-06-23
 * @since   2012-06-04
 */
class Maths
{
  static final double BIG_VALUE = 1e10d;
  static final double EPSILON = 1e-10d;

  static double div (double a, double b)
  {
    if (a == 0d)
      return 0;
    if (b == 0d)
      return signum(a) * BIG_VALUE;
    return a / b;
  }

  static int signum (double d)
  {
    return d == 0d ? 0 : d < 0d ? -1 : 1;
  }

  static boolean equ (double a, double b)
  {
    return a - b + EPSILON > 0d && a - b - EPSILON < 0d;
  }

  static double [] calcRealRoot (double ... c)
  {
    switch (c.length) {
      case 2:
        return calcRealLinearRoot(c[0], c[1]);
      case 3:
        return calcRealQuadraticRoot(c[0], c[1], c[2]);
      case 4:
        return calcRealCubicRoot(c[0], c[1], c[2], c[3]);
      case 5:
        return calcRealQuarticRoot(c[0], c[1], c[2], c[3], c[4]);
    }
    return new double[1];
  }

  static double [] calcRealLinearRoot (double c0, double c1)
  {
    double [] ret = new double[1];
    if (c1 == 0d)
      ret[0] = 0d;
    else
      ret[0] = div(-c0, c1);
    return ret;
  }

  static double [] calcRealQuadraticRoot (double c0, double c1, double c2)
  {
    if (c2 == 0d)
      return calcRealLinearRoot(c0, c1);
    return null;
  }

  static double [] calcRealCubicRoot (double d, double e, double f,
      double g)
  {
    // TODO Auto-generated method stub
    return null;
  }

  static double [] calcRealQuarticRoot (double d, double e, double f,
      double g, double h)
  {
    // TODO Auto-generated method stub
    return null;
  }
}