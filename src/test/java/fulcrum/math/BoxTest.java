/*
 * BoxTest.java
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
 * Test case for {@link Box}.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
public class BoxTest extends TestCase {

  /**
   * Vectors provide basic object operations including accessors, hashing,
   * equality and string conversion.
   */
  public void testProvidesBasicObjectOperations() {
    verifyBasicBoxOperations(2, Box.create(Vector.create(5f, 2f), Vector.create(1f, 6f)),
        Box.create(Vector.create(11f, 12f), Vector.create(15f, 16f)), Vector.create(1f, 2f), Vector.create(5f, 6f));
    verifyBasicBoxOperations(3, Box.create(Vector.create(5f, 2f, 7f), Vector.create(1f, 6f, 3f)),
        Box.create(Vector.create(11f, 12f, 13f), Vector.create(15f, 16f, 17f)), Vector.create(1f, 2f, 3f),
        Vector.create(5f, 6f, 7f));
    verifyBasicBoxOperations(4, Box.create(Vector.create(5f, 2f, 7f, 4f), Vector.create(1f, 6f, 3f, 8f)),
        Box.create(Vector.create(11f, 12f, 13f, 14f), Vector.create(15f, 16f, 17f, 18f)),
        Vector.create(1f, 2f, 3f, 4f), Vector.create(5f, 6f, 7f, 8f));
  }

  /** Vectors can be converted between dimensions. */
  public void testProvidesConversionBetweenDimensions() {
    Box._2D _2d = Box.create(Vector.create(1f, 2f), Vector.create(2f, 3f));
    Box._3D _3d = Box.create(_2d.lower().extend(0f), _2d.upper().extend(0f));
    Box._4D _4d = Box.create(_3d.lower().extend(0f), _3d.upper().extend(0f));
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
   * Verifies the basic accessors, hashing, equality and string conversion
   * methods.
   */
  private void verifyBasicBoxOperations(int dimensions, Box a, Box b, Vector lower, Vector upper) {
    assertEquals(dimensions, a.dimensions());
    assertEquals(dimensions, b.dimensions());
    assertEquals(lower, a.lower());
    assertEquals(upper, a.upper());
    assertTrue(a.hashCode() == a.hashCode());
    assertTrue(b.hashCode() == b.hashCode());
    assertFalse(a.hashCode() == b.hashCode());
    assertFalse(a.equals(null));
    assertFalse(b.equals(null));
    assertTrue(a.equals(a));
    assertTrue(b.equals(b));
    assertFalse(a.equals(b));
    assertFalse(b.equals(a));
    assertTrue(a.toString().startsWith("["));
    assertTrue(a.toString().endsWith("]"));
    assertTrue(a.toString().contains(lower.toString()));
    assertTrue(a.toString().contains(upper.toString()));
  }

  /**
   * Boxes are capable of calculating their size.
   */
  public void testProvidesTheSizeOfBoxes() {
    assertEquals(Vector.create(2f, 2f), Box.create(Vector.create(0f, 0f), Vector.create(2f, 2f)).size());
    assertEquals(Vector.create(2f, 2f, 2f), Box.create(Vector.create(0f, 0f, 0f), Vector.create(2f, 2f, 2f)).size());
    assertEquals(Vector.create(2f, 2f, 2f, 2f), Box
        .create(Vector.create(0f, 0f, 0f, 0f), Vector.create(2f, 2f, 2f, 2f)).size());
  }

  /**
   * Boxes provide tests for equality and approximation with other boxes.
   */
  public void testProvidesBoxEqualityAndApproximationOperations() {
    verifyEqualityAndApproximation(Box.create(Vector.create(0f, 0f), Vector.create(1f, 1f)),
        Box.create(Vector.create(0f, 0f), Vector.create(1f, 1f)),
        Box.create(Vector.create(0.1f, 0.1f), Vector.create(0.9f, 0.9f)),
        Box.create(Vector.create(5f, 5f), Vector.create(7f, 7f)));
    verifyEqualityAndApproximation(Box.create(Vector.create(0f, 0f, 0f), Vector.create(1f, 1f, 1f)),
        Box.create(Vector.create(0f, 0f, 0f), Vector.create(1f, 1f, 1f)),
        Box.create(Vector.create(0.1f, 0.1f, 0.1f), Vector.create(0.9f, 0.9f, 0.9f)),
        Box.create(Vector.create(5f, 5f, 5f), Vector.create(7f, 7f, 7f)));
    verifyEqualityAndApproximation(Box.create(Vector.create(0f, 0f, 0f, 0f), Vector.create(1f, 1f, 1f, 1f)),
        Box.create(Vector.create(0f, 0f, 0f, 0f), Vector.create(1f, 1f, 1f, 1f)),
        Box.create(Vector.create(0.1f, 0.1f, 0.1f, 0.1f), Vector.create(0.9f, 0.9f, 0.9f, 0.9f)),
        Box.create(Vector.create(5f, 5f, 5f, 5f), Vector.create(7f, 7f, 7f, 7f)));
  }

  /** Verifies the box equality and approximation operations. */
  private void verifyEqualityAndApproximation(Box a, Box same, Box close, Box different) {
    assertTrue(a.equalTo(same));
    assertTrue(!a.equalTo(close));
    assertTrue(!a.equalTo(different));
    assertTrue(a.equalTo(same, 0.2f));
    assertTrue(a.equalTo(close, 0.2f));
    assertTrue(!a.equalTo(different, 0.2f));
  }

  /**
   * Boxes are capable of determining if they contain a point or box as well as
   * if they intersect another box.
   */
  public void testProvidesContainmentAndIntersectionCalculations() {
    verifyContainmentAndIntersection(Box.create(Vector.create(1f, 1f), Vector.create(4f, 4f)), Vector.create(2f, 2f),
        Vector.create(0f, 0f), Box.create(Vector.create(2f, 2f), Vector.create(3f, 3f)),
        Box.create(Vector.create(-1f, -1f), Vector.create(0f, 0f)),
        Box.create(Vector.create(0f, 0f), Vector.create(2f, 2f)));
    verifyContainmentAndIntersection(Box.create(Vector.create(1f, 1f, 1f), Vector.create(4f, 4f, 4f)),
        Vector.create(2f, 2f, 2f), Vector.create(0f, 0f, 0f),
        Box.create(Vector.create(2f, 2f, 2f), Vector.create(3f, 3f, 3f)),
        Box.create(Vector.create(-1f, -1f, -1f), Vector.create(0f, 0f, 0f)),
        Box.create(Vector.create(0f, 0f, 0f), Vector.create(2f, 2f, 2f)));
    verifyContainmentAndIntersection(Box.create(Vector.create(1f, 1f, 1f, 1f), Vector.create(4f, 4f, 4f, 4f)),
        Vector.create(2f, 2f, 2f, 2f), Vector.create(0f, 0f, 0f, 0f),
        Box.create(Vector.create(2f, 2f, 2f, 2f), Vector.create(3f, 3f, 3f, 3f)),
        Box.create(Vector.create(-1f, -1f, -1f, -1f), Vector.create(0f, 0f, 0f, 0f)),
        Box.create(Vector.create(0f, 0f, 0f, 0f), Vector.create(2f, 2f, 2f, 2f)));
  }

  /** Verifies the containment and intersection operations. */
  private void verifyContainmentAndIntersection(Box a, Vector vin, Vector vout, Box bin, Box bout, Box boverlap) {
    assertTrue(a.contains(vin));
    assertTrue(!a.contains(vout));
    assertTrue(a.contains(bin));
    assertTrue(!a.contains(bout));
    assertTrue(!a.contains(boverlap));
    assertTrue(a.intersects(bin));
    assertTrue(!a.intersects(bout));
    assertTrue(a.intersects(boverlap));
  }

  /**
   * Boxes are capable of calculating their intersection and union bounds with
   * other boxes.
   */
  public void testProvidesIntersectionsAndUnionsWithOtherBoxes() {
    verifyIntersectionsAndUnions(Box.create(Vector.create(0f, 0f), Vector.create(2f, 2f)),
        Box.create(Vector.create(1f, 1f), Vector.create(3f, 3f)),
        Box.create(Vector.create(1f, 1f), Vector.create(2f, 2f)),
        Box.create(Vector.create(0f, 0f), Vector.create(3f, 3f)));
    verifyIntersectionsAndUnions(Box.create(Vector.create(0f, 0f, 0f), Vector.create(2f, 2f, 2f)),
        Box.create(Vector.create(1f, 1f, 1f), Vector.create(3f, 3f, 3f)),
        Box.create(Vector.create(1f, 1f, 1f), Vector.create(2f, 2f, 2f)),
        Box.create(Vector.create(0f, 0f, 0f), Vector.create(3f, 3f, 3f)));
    verifyIntersectionsAndUnions(Box.create(Vector.create(0f, 0f, 0f, 0f), Vector.create(2f, 2f, 2f, 2f)),
        Box.create(Vector.create(1f, 1f, 1f, 1f), Vector.create(3f, 3f, 3f, 3f)),
        Box.create(Vector.create(1f, 1f, 1f, 1f), Vector.create(2f, 2f, 2f, 2f)),
        Box.create(Vector.create(0f, 0f, 0f, 0f), Vector.create(3f, 3f, 3f, 3f)));
  }

  private void verifyIntersectionsAndUnions(Box a, Box b, Box intersection, Box union) {
    assertEquals(a.intersection(b), intersection);
    assertEquals(a.union(b), union);
  }

}
