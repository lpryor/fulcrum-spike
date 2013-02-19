/*
 * Simplex.scala
 * 
 * Copyright (c) 2013 Lonnie Pryor III and others.
 * 
 * Portions of this file are licensed under the Apache License,
 * Version 2.0 (the "License") you may not use this file except in 
 * compliance with the License. You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * The code in this file is based on version 2012-03-09 of the SimplexNoise 
 * implementation by Peter Eastman available at:
 * http://webstaff.itn.liu.se/~stegu/simplexnoise/SimplexNoise.java
 * The following attributions are reproduced from the original file:
 * 
 * Based on example code by Stefan Gustavson (stegu@itn.liu.se).
 * Optimizations by Peter Eastman (peastman@drizzle.stanford.edu).
 * Better rank ordering method by Stefan Gustavson in 2012.
 *
 * This code was placed in the public domain by its original author,
 * Stefan Gustavson. You may use it as you see fit, but attribution is
 * appreciated.
 */
package fulcrum.noise

import fulcrum.math._
import fulcrum.util.Random

/**
 * A speed-improved simplex noise algorithm for 2D, 3D and 4D.
 *
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
final class Simplex private (
  permutations: Array[Short],
  permutationsModulus: Array[Short])
  extends Noise2 with Noise3 with Noise4 {

  import Simplex._

  /* @see Noise2#apply(float, float) */
  override def apply(x: Float, y: Float) = {
    var n0, n1, n2 = 0f // Noise contributions from the three corners
    // Skew the input space to determine which simplex cell we're in
    val s = (x + y) * SKEW_2D // Hairy factor for 2D
    val i = (x + s).floor.toInt
    val j = (y + s).floor.toInt
    val t = (i + j) * UNSKEW_2D
    val X0 = i - t // Unskew the cell origin back to (x,y) space
    val Y0 = j - t
    val x0 = x - X0 // The x,y distances from the cell origin
    val y0 = y - Y0
    // For the 2D case, the simplex shape is an equilateral triangle.
    // Determine which simplex we are in.
    var i1, j1 = 0 // Offsets for second (middle) corner of simplex in (i,j)
    // coordinates
    if (x0 > y0) {
      i1 = 1
      j1 = 0
    } // lower triangle, XY order: (0,0)->(1,0)->(1,1)
    else {
      i1 = 0
      j1 = 1
    } // upper triangle, YX order: (0,0)->(0,1)->(1,1)
    // A step of (1,0) in (i,j) means a step of (1-c,-c) in (x,y), and
    // a step of (0,1) in (i,j) means a step of (-c,1-c) in (x,y), where
    // c = (3-sqrt(3))/6
    val x1 = x0 - i1 + UNSKEW_2D // Offsets for middle corner in (x,y)
    // unskewed
    // coords
    val y1 = y0 - j1 + UNSKEW_2D
    val x2 = x0 - 1f + 2f * UNSKEW_2D // Offsets for last corner in (x,y)
    // unskewed
    // coords
    val y2 = y0 - 1f + 2f * UNSKEW_2D
    // Work out the hashed gradient indices of the three simplex corners
    val ii = i & 255
    val jj = j & 255
    val gi0 = permutationsModulus(ii + permutations(jj))
    val gi1 = permutationsModulus(ii + i1 + permutations(jj + j1))
    val gi2 = permutationsModulus(ii + 1 + permutations(jj + 1))
    // Calculate the contribution from the three corners
    var t0 = 0.5f - x0 * x0 - y0 * y0
    if (t0 < 0)
      n0 = 0f
    else {
      t0 *= t0
      n0 = t0 * t0 * GRADIENT_3D(gi0).dot(x0, y0) // (x,y) of grad3 used for 2D
      // gradient
    }
    var t1 = 0.5f - x1 * x1 - y1 * y1
    if (t1 < 0)
      n1 = 0f
    else {
      t1 *= t1
      n1 = t1 * t1 * GRADIENT_3D(gi1).dot(x1, y1)
    }
    var t2 = 0.5f - x2 * x2 - y2 * y2
    if (t2 < 0)
      n2 = 0f
    else {
      t2 *= t2
      n2 = t2 * t2 * GRADIENT_3D(gi2).dot(x2, y2)
    }
    // Add contributions from each corner to get the final noise value.
    // The result is scaled to return values in the interval [-1,1].
    (70f * (n0 + n1 + n2)).clamp(-1f, 1f)
  }

  /* @see Noise3#apply(float, float, float) */
  override def apply(x: Float, y: Float, z: Float) = {
    var n0, n1, n2, n3 = 0f // Noise contributions from the four corners
    // Skew the input space to determine which simplex cell we're in
    val s = (x + y + z) * SKEW_3D // Very nice and simple skew factor for 3D
    val i = (x + s).floor.toInt
    val j = (y + s).floor.toInt
    val k = (z + s).floor.toInt
    val t = (i + j + k) * UNSKEW_3D
    val X0 = i - t // Unskew the cell origin back to (x,y,z) space
    val Y0 = j - t
    val Z0 = k - t
    val x0 = x - X0 // The x,y,z distances from the cell origin
    val y0 = y - Y0
    val z0 = z - Z0
    // For the 3D case, the simplex shape is a slightly irregular tetrahedron.
    // Determine which simplex we are in.
    var i1, j1, k1 = 0 // Offsets for second corner of simplex in (i,j,k)
    // coordinates
    var i2, j2, k2 = 0 // Offsets for third corner of simplex in (i,j,k)
    // coordinates
    if (x0 >= y0) {
      if (y0 >= z0) {
        i1 = 1
        j1 = 0
        k1 = 0
        i2 = 1
        j2 = 1
        k2 = 0
      } // X Y Z order
      else if (x0 >= z0) {
        i1 = 1
        j1 = 0
        k1 = 0
        i2 = 1
        j2 = 0
        k2 = 1
      } // X Z Y order
      else {
        i1 = 0
        j1 = 0
        k1 = 1
        i2 = 1
        j2 = 0
        k2 = 1
      } // Z X Y order
    } else { // x0<y0
      if (y0 < z0) {
        i1 = 0
        j1 = 0
        k1 = 1
        i2 = 0
        j2 = 1
        k2 = 1
      } // Z Y X order
      else if (x0 < z0) {
        i1 = 0
        j1 = 1
        k1 = 0
        i2 = 0
        j2 = 1
        k2 = 1
      } // Y Z X order
      else {
        i1 = 0
        j1 = 1
        k1 = 0
        i2 = 1
        j2 = 1
        k2 = 0
      } // Y X Z order
    }
    // A step of (1,0,0) in (i,j,k) means a step of (1-c,-c,-c) in (x,y,z),
    // a step of (0,1,0) in (i,j,k) means a step of (-c,1-c,-c) in (x,y,z), and
    // a step of (0,0,1) in (i,j,k) means a step of (-c,-c,1-c) in (x,y,z),
    // where
    // c = 1/6.
    val x1 = x0 - i1 + UNSKEW_3D // Offsets for second corner in (x,y,z)
    // coordinates
    val y1 = y0 - j1 + UNSKEW_3D
    val z1 = z0 - k1 + UNSKEW_3D
    val x2 = x0 - i2 + 2f * UNSKEW_3D // Offsets for third corner in (x,y,z)
    // coordinates
    val y2 = y0 - j2 + 2f * UNSKEW_3D
    val z2 = z0 - k2 + 2f * UNSKEW_3D
    val x3 = x0 - 1f + 3f * UNSKEW_3D // Offsets for last corner in (x,y,z)
    // coordinates
    val y3 = y0 - 1f + 3f * UNSKEW_3D
    val z3 = z0 - 1f + 3f * UNSKEW_3D
    // Work out the hashed gradient indices of the four simplex corners
    val ii = i & 255
    val jj = j & 255
    val kk = k & 255
    val gi0 = permutationsModulus(ii + permutations(jj + permutations(kk)))
    val gi1 = permutationsModulus(ii + i1 + permutations(jj + j1 + permutations(kk + k1)))
    val gi2 = permutationsModulus(ii + i2 + permutations(jj + j2 + permutations(kk + k2)))
    val gi3 = permutationsModulus(ii + 1 + permutations(jj + 1 + permutations(kk + 1)))
    // Calculate the contribution from the four corners
    var t0 = 0.6f - x0 * x0 - y0 * y0 - z0 * z0
    if (t0 < 0)
      n0 = 0f
    else {
      t0 *= t0
      n0 = t0 * t0 * GRADIENT_3D(gi0).dot(x0, y0, z0)
    }
    var t1 = 0.6f - x1 * x1 - y1 * y1 - z1 * z1
    if (t1 < 0)
      n1 = 0f
    else {
      t1 *= t1
      n1 = t1 * t1 * GRADIENT_3D(gi1).dot(x1, y1, z1)
    }
    var t2 = 0.6f - x2 * x2 - y2 * y2 - z2 * z2
    if (t2 < 0)
      n2 = 0f
    else {
      t2 *= t2
      n2 = t2 * t2 * GRADIENT_3D(gi2).dot(x2, y2, z2)
    }
    var t3 = 0.6f - x3 * x3 - y3 * y3 - z3 * z3
    if (t3 < 0)
      n3 = 0f
    else {
      t3 *= t3
      n3 = t3 * t3 * GRADIENT_3D(gi3).dot(x3, y3, z3)
    }
    // Add contributions from each corner to get the final noise value.
    // The result is scaled to stay just inside [-1,1]
    (32f * (n0 + n1 + n2 + n3)).clamp(-1f, 1f)
  }

  /* @see Noise4#apply(float, float, float, float) */
  override def apply(x: Float, y: Float, z: Float, w: Float) = {
    var n0, n1, n2, n3, n4 = 0f // Noise contributions from the five corners
    // Skew the (x,y,z,w) space to determine which cell of 24 simplices we're in
    val s = (x + y + z + w) * SKEW_4D // Factor for 4D skewing
    val i = (x + s).floor.toInt
    val j = (y + s).floor.toInt
    val k = (z + s).floor.toInt
    val l = (w + s).floor.toInt
    val t = (i + j + k + l) * UNSKEW_4D // Factor for 4D unskewing
    val X0 = i - t // Unskew the cell origin back to (x,y,z,w) space
    val Y0 = j - t
    val Z0 = k - t
    val W0 = l - t
    val x0 = x - X0 // The x,y,z,w distances from the cell origin
    val y0 = y - Y0
    val z0 = z - Z0
    val w0 = w - W0
    // For the 4D case, the simplex is a 4D shape I won't even try to describe.
    // To find out which of the 24 possible simplices we're in, we need to
    // determine the magnitude ordering of x0, y0, z0 and w0.
    // Six pair-wise comparisons are performed between each possible pair
    // of the four coordinates, and the results are used to rank the numbers.
    var rankx = 0
    var ranky = 0
    var rankz = 0
    var rankw = 0
    if (x0 > y0)
      rankx += 1
    else
      ranky += 1
    if (x0 > z0)
      rankx += 1
    else
      rankz += 1
    if (x0 > w0)
      rankx += 1
    else
      rankw += 1
    if (y0 > z0)
      ranky += 1
    else
      rankz += 1
    if (y0 > w0)
      ranky += 1
    else
      rankw += 1
    if (z0 > w0)
      rankz += 1
    else
      rankw += 1
    var i1, j1, k1, l1 = 0 // The integer offsets for the second simplex corner
    var i2, j2, k2, l2 = 0 // The integer offsets for the third simplex corner
    var i3, j3, k3, l3 = 0 // The integer offsets for the fourth simplex corner
    // simplex[c] is a 4-vector with the numbers 0, 1, 2 and 3 in some order.
    // Many values of c will never occur, since e.g. x>y>z>w makes x<z, y<w and
    // x<w
    // impossible. Only the 24 indices which have non-zero entries make any
    // sense.
    // We use a thresholding to set the coordinates in turn from the largest
    // magnitude.
    // Rank 3 denotes the largest coordinate.
    i1 = if (rankx >= 3) 1 else 0
    j1 = if (ranky >= 3) 1 else 0
    k1 = if (rankz >= 3) 1 else 0
    l1 = if (rankw >= 3) 1 else 0
    // Rank 2 denotes the second largest coordinate.
    i2 = if (rankx >= 2) 1 else 0
    j2 = if (ranky >= 2) 1 else 0
    k2 = if (rankz >= 2) 1 else 0
    l2 = if (rankw >= 2) 1 else 0
    // Rank 1 denotes the second smallest coordinate.
    i3 = if (rankx >= 1) 1 else 0
    j3 = if (ranky >= 1) 1 else 0
    k3 = if (rankz >= 1) 1 else 0
    l3 = if (rankw >= 1) 1 else 0
    // The fifth corner has all coordinate offsets = 1, so no need to compute
    // that.
    val x1 = x0 - i1 + UNSKEW_4D // Offsets for second corner in (x,y,z,w)
    // coordinates
    val y1 = y0 - j1 + UNSKEW_4D
    val z1 = z0 - k1 + UNSKEW_4D
    val w1 = w0 - l1 + UNSKEW_4D
    val x2 = x0 - i2 + 2f * UNSKEW_4D // Offsets for third corner in
    // (x,y,z,w)
    // coordinates
    val y2 = y0 - j2 + 2f * UNSKEW_4D
    val z2 = z0 - k2 + 2f * UNSKEW_4D
    val w2 = w0 - l2 + 2f * UNSKEW_4D
    val x3 = x0 - i3 + 3f * UNSKEW_4D // Offsets for fourth corner in
    // (x,y,z,w)
    // coordinates
    val y3 = y0 - j3 + 3f * UNSKEW_4D
    val z3 = z0 - k3 + 3f * UNSKEW_4D
    val w3 = w0 - l3 + 3f * UNSKEW_4D
    val x4 = x0 - 1f + 4f * UNSKEW_4D // Offsets for last corner in (x,y,z,w)
    // coordinates
    val y4 = y0 - 1f + 4f * UNSKEW_4D
    val z4 = z0 - 1f + 4f * UNSKEW_4D
    val w4 = w0 - 1f + 4f * UNSKEW_4D
    // Work out the hashed gradient indices of the five simplex corners
    val ii = i & 255
    val jj = j & 255
    val kk = k & 255
    val ll = l & 255
    val gi0 = permutations(ii + permutations(jj + permutations(kk + permutations(ll)))) % 32
    val gi1 = permutations(ii + i1 + permutations(jj + j1 + permutations(kk + k1 + permutations(ll + l1)))) % 32
    val gi2 = permutations(ii + i2 + permutations(jj + j2 + permutations(kk + k2 + permutations(ll + l2)))) % 32
    val gi3 = permutations(ii + i3 + permutations(jj + j3 + permutations(kk + k3 + permutations(ll + l3)))) % 32
    val gi4 = permutations(ii + 1 + permutations(jj + 1 + permutations(kk + 1 + permutations(ll + 1)))) % 32
    // Calculate the contribution from the five corners
    var t0 = 0.6f - x0 * x0 - y0 * y0 - z0 * z0 - w0 * w0
    if (t0 < 0)
      n0 = 0f
    else {
      t0 *= t0
      n0 = t0 * t0 * GRADIENT_4D(gi0).dot(x0, y0, z0, w0)
    }
    var t1 = 0.6f - x1 * x1 - y1 * y1 - z1 * z1 - w1 * w1
    if (t1 < 0)
      n1 = 0f
    else {
      t1 *= t1
      n1 = t1 * t1 * GRADIENT_4D(gi1).dot(x1, y1, z1, w1)
    }
    var t2 = 0.6f - x2 * x2 - y2 * y2 - z2 * z2 - w2 * w2
    if (t2 < 0)
      n2 = 0f
    else {
      t2 *= t2
      n2 = t2 * t2 * GRADIENT_4D(gi2).dot(x2, y2, z2, w2)
    }
    var t3 = 0.6f - x3 * x3 - y3 * y3 - z3 * z3 - w3 * w3
    if (t3 < 0)
      n3 = 0f
    else {
      t3 *= t3
      n3 = t3 * t3 * GRADIENT_4D(gi3).dot(x3, y3, z3, w3)
    }
    var t4 = 0.6f - x4 * x4 - y4 * y4 - z4 * z4 - w4 * w4
    if (t4 < 0)
      n4 = 0f
    else {
      t4 *= t4
      n4 = t4 * t4 * GRADIENT_4D(gi4).dot(x4, y4, z4, w4)
    }
    // Sum up and scale the result to cover the range [-1,1]
    (27f * (n0 + n1 + n2 + n3 + n4)).clamp(-1f, 1f)
  }

}

/**
 * Factory for Simplex noise generators.
 *
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
object Simplex {

  /** The 3D gradient table. */
  private val GRADIENT_3D = Array(Gradient(1, 1, 0), Gradient(-1, 1, 0), Gradient(1, -1, 0), Gradient(-1, -1, 0),
    Gradient(1, 0, 1), Gradient(-1, 0, 1), Gradient(1, 0, -1), Gradient(-1, 0, -1), Gradient(0, 1, 1),
    Gradient(0, -1, 1), Gradient(0, 1, -1), Gradient(0, -1, -1))
  /** The 4D gradient table. */
  private val GRADIENT_4D = Array(Gradient(0, 1, 1, 1), Gradient(0, 1, 1, -1), Gradient(0, 1, -1, 1),
    Gradient(0, 1, -1, -1), Gradient(0, -1, 1, 1), Gradient(0, -1, 1, -1), Gradient(0, -1, -1, 1),
    Gradient(0, -1, -1, -1), Gradient(1, 0, 1, 1), Gradient(1, 0, 1, -1), Gradient(1, 0, -1, 1), Gradient(1, 0, -1, -1),
    Gradient(-1, 0, 1, 1), Gradient(-1, 0, 1, -1), Gradient(-1, 0, -1, 1), Gradient(-1, 0, -1, -1),
    Gradient(1, 1, 0, 1), Gradient(1, 1, 0, -1), Gradient(1, -1, 0, 1), Gradient(1, -1, 0, -1), Gradient(-1, 1, 0, 1),
    Gradient(-1, 1, 0, -1), Gradient(-1, -1, 0, 1), Gradient(-1, -1, 0, -1), Gradient(1, 1, 1, 0),
    Gradient(1, 1, -1, 0), Gradient(1, -1, 1, 0), Gradient(1, -1, -1, 0), Gradient(-1, 1, 1, 0), Gradient(-1, 1, -1, 0),
    Gradient(-1, -1, 1, 0), Gradient(-1, -1, -1, 0))
  /** Skewing factor for 2D. */
  private val SKEW_2D = 0.5f * (3f.sqrt - 1f)
  /** Unskewing factor for 2D. */
  private val UNSKEW_2D = (3f - 3f.sqrt) / 6f
  /** Skewing factor for 3D. */
  private val SKEW_3D = 1f / 3f
  /** Unskewing factor for 3D. */
  private val UNSKEW_3D = 1f / 6f
  /** Skewing factor for 4D. */
  private val SKEW_4D = (5f.sqrt - 1f) / 4f
  /** Unskewing factor for 4D. */
  private val UNSKEW_4D = (5f - 5f.sqrt) / 20f

  /** Creates a new Simplex noise generator. */
  def apply(seed: Int): Simplex = {
    val permutations = new Array[Short](512)
    val permutationsModulus = new Array[Short](permutations.length)
    val p = new Array[Short](permutations.length / 2)
    var i = 0
    while (i < p.length) {
      p(i) = i.toShort
      i += 1
    }
    val rng = Random(seed)
    i = p.length - 1
    while (i >= 0) {
      val j = rng.nextInteger(i + 1)
      val tmp = p(j)
      p(j) = p(i)
      p(i) = tmp
      i -= 1
    }
    System.arraycopy(p, 0, permutations, 0, p.length)
    System.arraycopy(p, 0, permutations, p.length, p.length)
    i = 0
    while (i < 512) {
      permutationsModulus(i) = (permutations(i) % 12).toShort
      i += 1
    }
    new Simplex(permutations, permutationsModulus)
  }

  /**
   * A utility to speed up gradient computations (array access is a lot slower
   * than member access).
   *
   * @author Lonnie Pryor III (lonnie@pryor.us.com)
   */
  private final case class Gradient(x: Float, y: Float, z: Float, w: Float = 0f) {

    /** 2D dot product. */
    @inline
    def dot(x: Float, y: Float) = this.x * x + this.y * y

    /** 3D dot product. */
    @inline
    def dot(x: Float, y: Float, z: Float) = this.x * x + this.y * y + this.z * z

    /** 4D dot product. */
    @inline
    def dot(x: Float, y: Float, z: Float, w: Float) = this.x * x + this.y * y + this.z * z + this.w * w

  }
}