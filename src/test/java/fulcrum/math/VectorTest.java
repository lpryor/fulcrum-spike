/*
 * VectorTest.java
 * 
 * Copyright (c) 2012 Lonnie Pryor III
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fulcrum.math;

import junit.framework.TestCase;

/**
 * Test case for {@link Vector}.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
public class VectorTest extends TestCase {

  /**
   * Vectors provide basic object operations including accessors, comparison,
   * hashing, equality and string conversion.
   */
  public void testProvidesBasicObjectOperations() {
    verifyBasicVectorOperations(2, Vector.create(1f, 2f), Vector.create(5f, 6f), 1f, 2f);
    verifyBasicVectorOperations(3, Vector.create(1f, 2f, 3f), Vector.create(5f, 6f, 7f), 1f, 2f, 3f);
    verifyBasicVectorOperations(4, Vector.create(1f, 2f, 3f, 4f), Vector.create(5f, 6f, 7f, 8f), 1f, 2f, 3f, 4f);
  }

  /**
   * Verifies the basic accessors, comparison, hashing, equality and string
   * conversion methods.
   */
  private void verifyBasicVectorOperations(int dimensions, Vector a, Vector b, Float... values) {
    assertEquals(dimensions, a.dimensions());
    assertEquals(dimensions, b.dimensions());
    for (int i = 0; i < dimensions; ++i)
      assertEquals(values[i], a.value(i), 0.001f);
    assertEquals(0, a.compareTo(a));
    assertEquals(-1, a.compareTo(b));
    assertEquals(1, b.compareTo(a));
    assertTrue(a.hashCode() == a.hashCode());
    assertTrue(b.hashCode() == b.hashCode());
    assertFalse(a.hashCode() == b.hashCode());
    assertFalse(a.equals(null));
    assertFalse(b.equals(null));
    assertTrue(a.equals(a));
    assertTrue(b.equals(b));
    assertFalse(a.equals(b));
    assertFalse(b.equals(a));
    assertTrue(a.toString().startsWith("("));
    assertTrue(a.toString().endsWith(")"));
    for (Float value : values)
      assertTrue(a.toString().contains(String.valueOf(value)));
  }

  /** Vectors can be converted between dimensions. */
  public void testProvidesConversionBetweenDimensions() {
    Vector._2D _2d = Vector.create(1f, 2f);
    Vector._3D _3d = _2d.extend(0f);
    Vector._4D _4d = _3d.extend(0f);
    assertEquals(_2d, _2d.to2D());
    assertEquals(_3d, _2d.to3D());
    assertEquals(_4d, _2d.to4D());
    assertEquals(_2d, _3d.to2D());
    assertEquals(_3d, _3d.to3D());
    assertEquals(_4d, _3d.to4D());
    assertEquals(_2d, _4d.to2D());
    assertEquals(_3d, _4d.to3D());
    assertEquals(_4d, _4d.to4D());
  }

  /**
   * Vectors are capable of calculating their length.
   */
  public void testProvidesTheLengthOfVectors() {
    assertEquals(Numbers.sqrt(8f), Vector.create(2f, 2f).length(), 0.001f);
    assertEquals(Numbers.sqrt(12f), Vector.create(2f, 2f, 2f).length(), 0.001f);
    assertEquals(Numbers.sqrt(16f), Vector.create(2f, 2f, 2f, 2f).length(), 0.001f);
  }

  /**
   * Vectors are capable of calculating their angle, distance, and dot product
   * against another vector.
   */
  public void testProvidesAngleDistanceAndDotProductCalculations() {
    verifyAngleDistanceAndDot(Vector.create(1f, 3f), Vector.create(3f, -1f), Numbers.HALF_PI, Numbers.sqrt(20f), 0f);
  }

  /**
   * Verifies the angle, distance, and dot product calculations.
   */
  private void verifyAngleDistanceAndDot(Vector v1, Vector v2, float angle, float distance, float dot) {
    assertEquals(angle, v1.angle(v2), 0.001f);
    assertEquals(distance, v1.distance(v2), 0.001f);
    assertEquals(dot, v1.dot(v2), 0.001f);
    if (v1.dimensions() < 4)
      verifyAngleDistanceAndDot(v1.extend(0f), v2.extend(0f), angle, distance, dot);
  }

  /**
   * Vectors are capable of being normalized to a unit vector.
   */
  public void testProvidesNormalizationToUnitVectors() {
    assertEquals(1f, Vector.create(2f, 2f).normalize().length(), 0.001f);
    assertEquals(1f, Vector.create(2f, 2f, 2f).normalize().length(), 0.001f);
    assertEquals(1f, Vector.create(2f, 2f, 2f, 2f).normalize().length(), 0.001f);
  }

  /**
   * Vectors provide common arithmetic operations with scalar values.
   */
  public void testProvidesArithmeticOperationsWithScalarsAndVectors() {
    verifyScalarArithmetic(Vector.create(4f, 5f), 2f);
    verifyScalarArithmetic(Vector.create(4f, 5f, 6f), 2f);
    verifyScalarArithmetic(Vector.create(4f, 5f, 6f, 7f), 2f);
    verifyVectorArithmetic(Vector.create(4f, 5f), Vector.create(2f, 4f));
    verifyVectorArithmetic(Vector.create(4f, 5f, 6f), Vector.create(2f, 4f, 8f));
    verifyVectorArithmetic(Vector.create(4f, 5f, 6f, 7f), Vector.create(2f, 4f, 8f, 16f));
  }

  /** Verifies the scalar arithmetic operations. */
  private void verifyScalarArithmetic(Vector v, float scalar) {
    Vector added = v.add(scalar);
    for (int i = 0; i < v.dimensions(); ++i)
      assertEquals(v.value(i) + scalar, added.value(i));
    Vector subtracted = v.subtract(scalar);
    for (int i = 0; i < v.dimensions(); ++i)
      assertEquals(v.value(i) - scalar, subtracted.value(i));
    Vector multiplied = v.multiply(scalar);
    for (int i = 0; i < v.dimensions(); ++i)
      assertEquals(v.value(i) * scalar, multiplied.value(i));
    Vector divided = v.divide(scalar);
    for (int i = 0; i < v.dimensions(); ++i)
      assertEquals(v.value(i) / scalar, divided.value(i));
  }

  /** Verifies the vector arithmetic operations. */
  private <V extends Vector> void verifyVectorArithmetic(V v, V vector) {
    Vector added = v.add(vector);
    for (int i = 0; i < v.dimensions(); ++i)
      assertEquals(v.value(i) + vector.value(i), added.value(i));
    Vector subtracted = v.subtract(vector);
    for (int i = 0; i < v.dimensions(); ++i)
      assertEquals(v.value(i) - vector.value(i), subtracted.value(i));
    Vector multiplied = v.multiply(vector);
    for (int i = 0; i < v.dimensions(); ++i)
      assertEquals(v.value(i) * vector.value(i), multiplied.value(i));
    Vector divided = v.divide(vector);
    for (int i = 0; i < v.dimensions(); ++i)
      assertEquals(v.value(i) / vector.value(i), divided.value(i));
  }

  /**
   * Vectors provide common comparison operations with scalar values.
   */
  public void testProvidesComparisonOperationsWithScalarsAndVectors() {
    verifyScalarComparisons(Vector.create(2f, 2f), 1f, 2f, 3f);
    verifyScalarComparisons(Vector.create(2f, 2f, 2f), 1f, 2f, 3f);
    verifyScalarComparisons(Vector.create(2f, 2f, 2f, 2f), 1f, 2f, 3f);
    verifyVectorComparisons(Vector.create(2f, 2f), Vector.create(1f, 1f), Vector.create(2f, 2f), Vector.create(3f, 3f));
    verifyVectorComparisons(Vector.create(2f, 2f, 2f), Vector.create(1f, 1f, 1f), Vector.create(2f, 2f, 2f),
        Vector.create(3f, 3f, 3f));
    verifyVectorComparisons(Vector.create(2f, 2f, 2f, 2f), Vector.create(1f, 1f, 1f, 1f),
        Vector.create(2f, 2f, 2f, 2f), Vector.create(3f, 3f, 3f, 3f));
  }

  /** Verifies the scalar comparison operations. */
  private void verifyScalarComparisons(Vector v, float low, float equal, float high) {
    assertTrue(!v.equalTo(low));
    assertTrue(v.equalTo(equal));
    assertTrue(!v.equalTo(high));
    assertTrue(!v.equalTo(low, 0.2f));
    assertTrue(v.equalTo(equal, 0.2f));
    assertTrue(v.equalTo(equal + 0.1f, 0.2f));
    assertTrue(v.equalTo(equal - 0.1f, 0.2f));
    assertTrue(!v.equalTo(high, 0.2f));
    assertTrue(!v.lessThan(low));
    assertTrue(!v.lessThan(equal));
    assertTrue(v.lessThan(high));
    assertTrue(!v.lessThanOrEqualTo(low));
    assertTrue(v.lessThanOrEqualTo(equal));
    assertTrue(v.lessThanOrEqualTo(high));
    assertTrue(v.greaterThan(low));
    assertTrue(!v.greaterThan(equal));
    assertTrue(!v.greaterThan(high));
    assertTrue(v.greaterThanOrEqualTo(low));
    assertTrue(v.greaterThanOrEqualTo(equal));
    assertTrue(!v.greaterThanOrEqualTo(high));
  }

  /** Verifies the vector comparison operations. */
  private <V extends Vector> void verifyVectorComparisons(V v, V low, V equal, V high) {
    assertTrue(!v.equalTo(low));
    assertTrue(v.equalTo(equal));
    assertTrue(!v.equalTo(high));
    assertTrue(!v.equalTo(low, 0.2f));
    assertTrue(v.equalTo(equal, 0.2f));
    assertTrue(v.equalTo(equal.add(0.1f), 0.2f));
    assertTrue(v.equalTo(equal.subtract(0.1f), 0.2f));
    assertTrue(!v.equalTo(high, 0.2f));
    assertTrue(!v.lessThan(low));
    assertTrue(!v.lessThan(equal));
    assertTrue(v.lessThan(high));
    assertTrue(!v.lessThanOrEqualTo(low));
    assertTrue(v.lessThanOrEqualTo(equal));
    assertTrue(v.lessThanOrEqualTo(high));
    assertTrue(v.greaterThan(low));
    assertTrue(!v.greaterThan(equal));
    assertTrue(!v.greaterThan(high));
    assertTrue(v.greaterThanOrEqualTo(low));
    assertTrue(v.greaterThanOrEqualTo(equal));
    assertTrue(!v.greaterThanOrEqualTo(high));
  }

}
