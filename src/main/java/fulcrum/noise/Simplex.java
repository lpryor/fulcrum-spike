/*
 * Simplex.java
 * 
 * Copyright (c) 2012 Lonnie Pryor III and others.
 * 
 * Portions of this file are licensed under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in 
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
package fulcrum.noise;

import fulcrum.math.Numbers;
import fulcrum.util.Random;

/**
 * A speed-improved simplex noise algorithm for 2D, 3D and 4D.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
public final class Simplex implements Noise._2D, Noise._3D, Noise._4D, Cloneable {

  /** The 3D gradient table. */
  private static final Gradient[] GRADIENT_3D = { new Gradient(1, 1, 0), new Gradient(-1, 1, 0),
      new Gradient(1, -1, 0), new Gradient(-1, -1, 0), new Gradient(1, 0, 1), new Gradient(-1, 0, 1),
      new Gradient(1, 0, -1), new Gradient(-1, 0, -1), new Gradient(0, 1, 1), new Gradient(0, -1, 1),
      new Gradient(0, 1, -1), new Gradient(0, -1, -1) };
  /** The 4D gradient table. */
  private static final Gradient[] GRADIENT_4D = { new Gradient(0, 1, 1, 1), new Gradient(0, 1, 1, -1),
      new Gradient(0, 1, -1, 1), new Gradient(0, 1, -1, -1), new Gradient(0, -1, 1, 1), new Gradient(0, -1, 1, -1),
      new Gradient(0, -1, -1, 1), new Gradient(0, -1, -1, -1), new Gradient(1, 0, 1, 1), new Gradient(1, 0, 1, -1),
      new Gradient(1, 0, -1, 1), new Gradient(1, 0, -1, -1), new Gradient(-1, 0, 1, 1), new Gradient(-1, 0, 1, -1),
      new Gradient(-1, 0, -1, 1), new Gradient(-1, 0, -1, -1), new Gradient(1, 1, 0, 1), new Gradient(1, 1, 0, -1),
      new Gradient(1, -1, 0, 1), new Gradient(1, -1, 0, -1), new Gradient(-1, 1, 0, 1), new Gradient(-1, 1, 0, -1),
      new Gradient(-1, -1, 0, 1), new Gradient(-1, -1, 0, -1), new Gradient(1, 1, 1, 0), new Gradient(1, 1, -1, 0),
      new Gradient(1, -1, 1, 0), new Gradient(1, -1, -1, 0), new Gradient(-1, 1, 1, 0), new Gradient(-1, 1, -1, 0),
      new Gradient(-1, -1, 1, 0), new Gradient(-1, -1, -1, 0) };
  /** Skewing factor for 2D. */
  private static final float SKEW_2D = 0.5f * (Numbers.sqrt(3f) - 1f);
  /** Unskewing factor for 2D. */
  private static final float UNSKEW_2D = (3f - Numbers.sqrt(3f)) / 6f;
  /** Skewing factor for 3D. */
  private static final float SKEW_3D = 1f / 3f;
  /** Unskewing factor for 3D. */
  private static final float UNSKEW_3D = 1f / 6f;
  /** Skewing factor for 4D. */
  private static final float SKEW_4D = (Numbers.sqrt(5f) - 1f) / 4f;
  /** Unskewing factor for 4D. */
  private static final float UNSKEW_4D = (5f - Numbers.sqrt(5f)) / 20f;

  /**
   * This method is supposedly a *lot* faster than using
   * {@code (int)Math.floor(x)}.
   */
  private static int fastfloor(float x) {
    int xi = (int) x;
    return x < xi ? xi - 1 : xi;
  }

  /** The permutation table. */
  private final short[] permutations = new short[512];
  /** The permutation modulus table. */
  private final short[] permutationsModulus = new short[512];

  /** Creates a new simplex noise generator. */
  public Simplex(int seed) {
    short p[] = new short[permutations.length / 2];
    for (int i = 0; i < p.length; ++i)
      p[i] = (short) i;
    Random rng = new Random(seed);
    for (int i = p.length - 1; i >= 0; --i) {
      int j = rng.nextInteger(i + 1);
      short tmp = p[j];
      p[j] = p[i];
      p[i] = tmp;
    }
    System.arraycopy(p, 0, permutations, 0, p.length);
    System.arraycopy(p, 0, permutations, p.length, p.length);
    for (int i = 0; i < 512; i++)
      permutationsModulus[i] = (short) (permutations[i] % 12);
  }

  /* @see Noise._2D#sample(float, float) */
  @Override
  public float sample(float x, float y) {
    float n0, n1, n2; // Noise contributions from the three corners
    // Skew the input space to determine which simplex cell we're in
    float s = (x + y) * SKEW_2D; // Hairy factor for 2D
    int i = fastfloor(x + s);
    int j = fastfloor(y + s);
    float t = (i + j) * UNSKEW_2D;
    float X0 = i - t; // Unskew the cell origin back to (x,y) space
    float Y0 = j - t;
    float x0 = x - X0; // The x,y distances from the cell origin
    float y0 = y - Y0;
    // For the 2D case, the simplex shape is an equilateral triangle.
    // Determine which simplex we are in.
    int i1, j1; // Offsets for second (middle) corner of simplex in (i,j)
                // coordinates
    if (x0 > y0) {
      i1 = 1;
      j1 = 0;
    } // lower triangle, XY order: (0,0)->(1,0)->(1,1)
    else {
      i1 = 0;
      j1 = 1;
    } // upper triangle, YX order: (0,0)->(0,1)->(1,1)
    // A step of (1,0) in (i,j) means a step of (1-c,-c) in (x,y), and
    // a step of (0,1) in (i,j) means a step of (-c,1-c) in (x,y), where
    // c = (3-sqrt(3))/6
    float x1 = x0 - i1 + UNSKEW_2D; // Offsets for middle corner in (x,y)
                                    // unskewed
    // coords
    float y1 = y0 - j1 + UNSKEW_2D;
    float x2 = x0 - 1f + 2f * UNSKEW_2D; // Offsets for last corner in (x,y)
                                         // unskewed
    // coords
    float y2 = y0 - 1f + 2f * UNSKEW_2D;
    // Work out the hashed gradient indices of the three simplex corners
    int ii = i & 255;
    int jj = j & 255;
    int gi0 = permutationsModulus[ii + permutations[jj]];
    int gi1 = permutationsModulus[ii + i1 + permutations[jj + j1]];
    int gi2 = permutationsModulus[ii + 1 + permutations[jj + 1]];
    // Calculate the contribution from the three corners
    float t0 = 0.5f - x0 * x0 - y0 * y0;
    if (t0 < 0)
      n0 = 0f;
    else {
      t0 *= t0;
      n0 = t0 * t0 * GRADIENT_3D[gi0].dot(x0, y0); // (x,y) of grad3 used for 2D
                                                   // gradient
    }
    float t1 = 0.5f - x1 * x1 - y1 * y1;
    if (t1 < 0)
      n1 = 0f;
    else {
      t1 *= t1;
      n1 = t1 * t1 * GRADIENT_3D[gi1].dot(x1, y1);
    }
    float t2 = 0.5f - x2 * x2 - y2 * y2;
    if (t2 < 0)
      n2 = 0f;
    else {
      t2 *= t2;
      n2 = t2 * t2 * GRADIENT_3D[gi2].dot(x2, y2);
    }
    // Add contributions from each corner to get the final noise value.
    // The result is scaled to return values in the interval [-1,1].
    return Numbers.clamp(70f * (n0 + n1 + n2), -1f, 1f);
  }

  /* @see Noise._3D#sample(float, float, float) */
  @Override
  public float sample(float x, float y, float z) {
    float n0, n1, n2, n3; // Noise contributions from the four corners
    // Skew the input space to determine which simplex cell we're in
    float s = (x + y + z) * SKEW_3D; // Very nice and simple skew factor for 3D
    int i = fastfloor(x + s);
    int j = fastfloor(y + s);
    int k = fastfloor(z + s);
    float t = (i + j + k) * UNSKEW_3D;
    float X0 = i - t; // Unskew the cell origin back to (x,y,z) space
    float Y0 = j - t;
    float Z0 = k - t;
    float x0 = x - X0; // The x,y,z distances from the cell origin
    float y0 = y - Y0;
    float z0 = z - Z0;
    // For the 3D case, the simplex shape is a slightly irregular tetrahedron.
    // Determine which simplex we are in.
    int i1, j1, k1; // Offsets for second corner of simplex in (i,j,k)
                    // coordinates
    int i2, j2, k2; // Offsets for third corner of simplex in (i,j,k)
                    // coordinates
    if (x0 >= y0) {
      if (y0 >= z0) {
        i1 = 1;
        j1 = 0;
        k1 = 0;
        i2 = 1;
        j2 = 1;
        k2 = 0;
      } // X Y Z order
      else if (x0 >= z0) {
        i1 = 1;
        j1 = 0;
        k1 = 0;
        i2 = 1;
        j2 = 0;
        k2 = 1;
      } // X Z Y order
      else {
        i1 = 0;
        j1 = 0;
        k1 = 1;
        i2 = 1;
        j2 = 0;
        k2 = 1;
      } // Z X Y order
    } else { // x0<y0
      if (y0 < z0) {
        i1 = 0;
        j1 = 0;
        k1 = 1;
        i2 = 0;
        j2 = 1;
        k2 = 1;
      } // Z Y X order
      else if (x0 < z0) {
        i1 = 0;
        j1 = 1;
        k1 = 0;
        i2 = 0;
        j2 = 1;
        k2 = 1;
      } // Y Z X order
      else {
        i1 = 0;
        j1 = 1;
        k1 = 0;
        i2 = 1;
        j2 = 1;
        k2 = 0;
      } // Y X Z order
    }
    // A step of (1,0,0) in (i,j,k) means a step of (1-c,-c,-c) in (x,y,z),
    // a step of (0,1,0) in (i,j,k) means a step of (-c,1-c,-c) in (x,y,z), and
    // a step of (0,0,1) in (i,j,k) means a step of (-c,-c,1-c) in (x,y,z),
    // where
    // c = 1/6.
    float x1 = x0 - i1 + UNSKEW_3D; // Offsets for second corner in (x,y,z)
                                    // coordinates
    float y1 = y0 - j1 + UNSKEW_3D;
    float z1 = z0 - k1 + UNSKEW_3D;
    float x2 = x0 - i2 + 2f * UNSKEW_3D; // Offsets for third corner in (x,y,z)
    // coordinates
    float y2 = y0 - j2 + 2f * UNSKEW_3D;
    float z2 = z0 - k2 + 2f * UNSKEW_3D;
    float x3 = x0 - 1f + 3f * UNSKEW_3D; // Offsets for last corner in (x,y,z)
    // coordinates
    float y3 = y0 - 1f + 3f * UNSKEW_3D;
    float z3 = z0 - 1f + 3f * UNSKEW_3D;
    // Work out the hashed gradient indices of the four simplex corners
    int ii = i & 255;
    int jj = j & 255;
    int kk = k & 255;
    int gi0 = permutationsModulus[ii + permutations[jj + permutations[kk]]];
    int gi1 = permutationsModulus[ii + i1 + permutations[jj + j1 + permutations[kk + k1]]];
    int gi2 = permutationsModulus[ii + i2 + permutations[jj + j2 + permutations[kk + k2]]];
    int gi3 = permutationsModulus[ii + 1 + permutations[jj + 1 + permutations[kk + 1]]];
    // Calculate the contribution from the four corners
    float t0 = 0.6f - x0 * x0 - y0 * y0 - z0 * z0;
    if (t0 < 0)
      n0 = 0f;
    else {
      t0 *= t0;
      n0 = t0 * t0 * GRADIENT_3D[gi0].dot(x0, y0, z0);
    }
    float t1 = 0.6f - x1 * x1 - y1 * y1 - z1 * z1;
    if (t1 < 0)
      n1 = 0f;
    else {
      t1 *= t1;
      n1 = t1 * t1 * GRADIENT_3D[gi1].dot(x1, y1, z1);
    }
    float t2 = 0.6f - x2 * x2 - y2 * y2 - z2 * z2;
    if (t2 < 0)
      n2 = 0f;
    else {
      t2 *= t2;
      n2 = t2 * t2 * GRADIENT_3D[gi2].dot(x2, y2, z2);
    }
    float t3 = 0.6f - x3 * x3 - y3 * y3 - z3 * z3;
    if (t3 < 0)
      n3 = 0f;
    else {
      t3 *= t3;
      n3 = t3 * t3 * GRADIENT_3D[gi3].dot(x3, y3, z3);
    }
    // Add contributions from each corner to get the final noise value.
    // The result is scaled to stay just inside [-1,1]
    return Numbers.clamp(32f * (n0 + n1 + n2 + n3), -1f, 1f);
  }

  /* @see Noise._4D#sample(float, float, float, float) */
  @Override
  public float sample(float x, float y, float z, float w) {
    float n0, n1, n2, n3, n4; // Noise contributions from the five corners
    // Skew the (x,y,z,w) space to determine which cell of 24 simplices we're in
    float s = (x + y + z + w) * SKEW_4D; // Factor for 4D skewing
    int i = fastfloor(x + s);
    int j = fastfloor(y + s);
    int k = fastfloor(z + s);
    int l = fastfloor(w + s);
    float t = (i + j + k + l) * UNSKEW_4D; // Factor for 4D unskewing
    float X0 = i - t; // Unskew the cell origin back to (x,y,z,w) space
    float Y0 = j - t;
    float Z0 = k - t;
    float W0 = l - t;
    float x0 = x - X0; // The x,y,z,w distances from the cell origin
    float y0 = y - Y0;
    float z0 = z - Z0;
    float w0 = w - W0;
    // For the 4D case, the simplex is a 4D shape I won't even try to describe.
    // To find out which of the 24 possible simplices we're in, we need to
    // determine the magnitude ordering of x0, y0, z0 and w0.
    // Six pair-wise comparisons are performed between each possible pair
    // of the four coordinates, and the results are used to rank the numbers.
    int rankx = 0;
    int ranky = 0;
    int rankz = 0;
    int rankw = 0;
    if (x0 > y0)
      rankx++;
    else
      ranky++;
    if (x0 > z0)
      rankx++;
    else
      rankz++;
    if (x0 > w0)
      rankx++;
    else
      rankw++;
    if (y0 > z0)
      ranky++;
    else
      rankz++;
    if (y0 > w0)
      ranky++;
    else
      rankw++;
    if (z0 > w0)
      rankz++;
    else
      rankw++;
    int i1, j1, k1, l1; // The integer offsets for the second simplex corner
    int i2, j2, k2, l2; // The integer offsets for the third simplex corner
    int i3, j3, k3, l3; // The integer offsets for the fourth simplex corner
    // simplex[c] is a 4-vector with the numbers 0, 1, 2 and 3 in some order.
    // Many values of c will never occur, since e.g. x>y>z>w makes x<z, y<w and
    // x<w
    // impossible. Only the 24 indices which have non-zero entries make any
    // sense.
    // We use a thresholding to set the coordinates in turn from the largest
    // magnitude.
    // Rank 3 denotes the largest coordinate.
    i1 = rankx >= 3 ? 1 : 0;
    j1 = ranky >= 3 ? 1 : 0;
    k1 = rankz >= 3 ? 1 : 0;
    l1 = rankw >= 3 ? 1 : 0;
    // Rank 2 denotes the second largest coordinate.
    i2 = rankx >= 2 ? 1 : 0;
    j2 = ranky >= 2 ? 1 : 0;
    k2 = rankz >= 2 ? 1 : 0;
    l2 = rankw >= 2 ? 1 : 0;
    // Rank 1 denotes the second smallest coordinate.
    i3 = rankx >= 1 ? 1 : 0;
    j3 = ranky >= 1 ? 1 : 0;
    k3 = rankz >= 1 ? 1 : 0;
    l3 = rankw >= 1 ? 1 : 0;
    // The fifth corner has all coordinate offsets = 1, so no need to compute
    // that.
    float x1 = x0 - i1 + UNSKEW_4D; // Offsets for second corner in (x,y,z,w)
    // coordinates
    float y1 = y0 - j1 + UNSKEW_4D;
    float z1 = z0 - k1 + UNSKEW_4D;
    float w1 = w0 - l1 + UNSKEW_4D;
    float x2 = x0 - i2 + 2f * UNSKEW_4D; // Offsets for third corner in
                                         // (x,y,z,w)
    // coordinates
    float y2 = y0 - j2 + 2f * UNSKEW_4D;
    float z2 = z0 - k2 + 2f * UNSKEW_4D;
    float w2 = w0 - l2 + 2f * UNSKEW_4D;
    float x3 = x0 - i3 + 3f * UNSKEW_4D; // Offsets for fourth corner in
                                         // (x,y,z,w)
    // coordinates
    float y3 = y0 - j3 + 3f * UNSKEW_4D;
    float z3 = z0 - k3 + 3f * UNSKEW_4D;
    float w3 = w0 - l3 + 3f * UNSKEW_4D;
    float x4 = x0 - 1f + 4f * UNSKEW_4D; // Offsets for last corner in (x,y,z,w)
    // coordinates
    float y4 = y0 - 1f + 4f * UNSKEW_4D;
    float z4 = z0 - 1f + 4f * UNSKEW_4D;
    float w4 = w0 - 1f + 4f * UNSKEW_4D;
    // Work out the hashed gradient indices of the five simplex corners
    int ii = i & 255;
    int jj = j & 255;
    int kk = k & 255;
    int ll = l & 255;
    int gi0 = permutations[ii + permutations[jj + permutations[kk + permutations[ll]]]] % 32;
    int gi1 = permutations[ii + i1 + permutations[jj + j1 + permutations[kk + k1 + permutations[ll + l1]]]] % 32;
    int gi2 = permutations[ii + i2 + permutations[jj + j2 + permutations[kk + k2 + permutations[ll + l2]]]] % 32;
    int gi3 = permutations[ii + i3 + permutations[jj + j3 + permutations[kk + k3 + permutations[ll + l3]]]] % 32;
    int gi4 = permutations[ii + 1 + permutations[jj + 1 + permutations[kk + 1 + permutations[ll + 1]]]] % 32;
    // Calculate the contribution from the five corners
    float t0 = 0.6f - x0 * x0 - y0 * y0 - z0 * z0 - w0 * w0;
    if (t0 < 0)
      n0 = 0f;
    else {
      t0 *= t0;
      n0 = t0 * t0 * GRADIENT_4D[gi0].dot(x0, y0, z0, w0);
    }
    float t1 = 0.6f - x1 * x1 - y1 * y1 - z1 * z1 - w1 * w1;
    if (t1 < 0)
      n1 = 0f;
    else {
      t1 *= t1;
      n1 = t1 * t1 * GRADIENT_4D[gi1].dot(x1, y1, z1, w1);
    }
    float t2 = 0.6f - x2 * x2 - y2 * y2 - z2 * z2 - w2 * w2;
    if (t2 < 0)
      n2 = 0f;
    else {
      t2 *= t2;
      n2 = t2 * t2 * GRADIENT_4D[gi2].dot(x2, y2, z2, w2);
    }
    float t3 = 0.6f - x3 * x3 - y3 * y3 - z3 * z3 - w3 * w3;
    if (t3 < 0)
      n3 = 0f;
    else {
      t3 *= t3;
      n3 = t3 * t3 * GRADIENT_4D[gi3].dot(x3, y3, z3, w3);
    }
    float t4 = 0.6f - x4 * x4 - y4 * y4 - z4 * z4 - w4 * w4;
    if (t4 < 0)
      n4 = 0f;
    else {
      t4 *= t4;
      n4 = t4 * t4 * GRADIENT_4D[gi4].dot(x4, y4, z4, w4);
    }
    // Sum up and scale the result to cover the range [-1,1]
    return Numbers.clamp(27f * (n0 + n1 + n2 + n3 + n4), -1f, 1f);
  }

  /**
   * A utility to speed up gradient computations (array access is a lot slower
   * than member access).
   * 
   * @author Lonnie Pryor III (lonnie@pryor.us.com)
   */
  private static final class Gradient {

    /** The x-coordinate. */
    final float x;
    /** The y-coordinate. */
    final float y;
    /** The z-coordinate. */
    final float z;
    /** The w-coordinate. */
    final float w;

    /** Creates a new gradient. */
    Gradient(float x, float y, float z) {
      this.x = x;
      this.y = y;
      this.z = z;
      this.w = 0f;
    }

    /** Creates a new gradient. */
    Gradient(float x, float y, float z, float w) {
      this.x = x;
      this.y = y;
      this.z = z;
      this.w = w;
    }

    /** 2D dot product. */
    float dot(float x, float y) {
      return this.x * x + this.y * y;
    }

    /** 3D dot product. */
    float dot(float x, float y, float z) {
      return this.x * x + this.y * y + this.z * z;
    }

    /** 4D dot product. */
    float dot(float x, float y, float z, float w) {
      return this.x * x + this.y * y + this.z * z + this.w * w;
    }

  }

}