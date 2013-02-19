/*
 * package.scala
 * 
 * Copyright (c) 2013 Lonnie Pryor III
 */
package fulcrum

/**
 * Common mathematical utilities and conversions.
 *
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
package object math {

  /** @see Math#E. */
  val E = scala.math.E.toFloat
  /** @see Math#PI. */
  val Pi = scala.math.Pi.toFloat
  /** @see Math#PI. */
  val HalfPi = Pi / 2f
  /** @see Math#PI. */
  val QuarterPi = Pi / 4f
  /** @see Math#PI. */
  val TwoPi = Pi * 2f

  /**
   * Common mathematical operations mapped directly onto the float type.
   * 
   * @author Lonnie Pryor III (lonnie@pryor.us.com)
   */
  implicit final class FloatOperations(val value: Float) extends AnyVal {

    /** Returns the square root of the value. */
    @inline
    def sqrt: Float = scala.math.sqrt(value).toFloat

    /** Returns the sine of the value. */
    @inline
    def sin: Float = scala.math.sin(value).toFloat

    /** Returns the cosine of the value. */
    @inline
    def cos: Float = scala.math.cos(value).toFloat

    /** Returns the arctangent of the value. */
    @inline
    def acos: Float = scala.math.acos(value).toFloat

    /** Raises the value to the specified power. */
    @inline
    def pow(exponent: Float) = scala.math.pow(value, exponent).toFloat

    /** Clamps the value in the specified range. */
    @inline
    def clamp(min: Float, max: Float): Float =
      if (value < min) min else if (value > max) max else value
      
  }

}