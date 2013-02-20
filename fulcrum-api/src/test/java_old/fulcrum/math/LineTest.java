/*
 * LineTest.java
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

import java.util.Arrays;
import java.util.Iterator;

import junit.framework.TestCase;

/**
 * Test case for {@link Line}.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
public class LineTest extends TestCase {

  /**
   * Lines provide basic object operations including accessors, hashing,
   * equality and string conversion.
   */
  public void testProvidesBasicObjectOperations() {
    verifyBasicLineOperations(2, Line.create(Vector.create(1f, 2f), Vector.create(5f, 6f)),
        Line.create(Vector.create(11f, 12f), Vector.create(15f, 16f)), Vector.create(1f, 2f), Vector.create(5f, 6f));
    verifyBasicLineOperations(3, Line.create(Vector.create(1f, 2f, 3f), Vector.create(5f, 6f, 7f)),
        Line.create(Vector.create(11f, 12f, 13f), Vector.create(15f, 16f, 17f)), Vector.create(1f, 2f, 3f),
        Vector.create(5f, 6f, 7f));
    verifyBasicLineOperations(4, Line.create(Vector.create(1f, 2f, 3f, 4f), Vector.create(5f, 6f, 7f, 8f)),
        Line.create(Vector.create(11f, 12f, 13f, 14f), Vector.create(15f, 16f, 17f, 18f)),
        Vector.create(1f, 2f, 3f, 4f), Vector.create(5f, 6f, 7f, 8f));
  }

  /**
   * Verifies the basic accessors, hashing, equality and string conversion
   * methods.
   */
  private void verifyBasicLineOperations(int dimensions, Line a, Line b, Vector begin, Vector end) {
    assertEquals(dimensions, a.dimensions());
    assertEquals(dimensions, b.dimensions());
    assertEquals(begin, a.begin());
    assertEquals(end, a.end());
    assertEquals(0, a.indexOf(begin));
    assertEquals(1, a.indexOf(end));
    assertEquals(begin, a.vertex(0));
    assertEquals(end, a.vertex(1));
    assertTrue(a.hashCode() == a.hashCode());
    assertTrue(b.hashCode() == b.hashCode());
    assertFalse(a.hashCode() == b.hashCode());
    assertFalse(a.equals(null));
    assertFalse(b.equals(null));
    assertTrue(a.equals(a));
    assertTrue(b.equals(b));
    assertFalse(a.equals(b));
    assertFalse(b.equals(a));
    assertTrue(a.toString().startsWith("|"));
    assertTrue(a.toString().endsWith("|"));
    assertTrue(a.toString().contains(begin.toString()));
    assertTrue(a.toString().contains(end.toString()));
  }

  /**
   * Lines are capable of calculating the distance to other points in space.
   */
  public void testProvidesDistanceToPointCalculations() {
    assertEquals(Numbers.sqrt(2f),
        Line.create(Vector.create(0f, 0f), Vector.create(2f, 2f)).distance(Vector.create(2f, 0f)));
    assertEquals(Numbers.sqrt(2f),
        Line.create(Vector.create(0f, 0f, 0f), Vector.create(2f, 2f, 0f)).distance(Vector.create(2f, 0f, 0f)));
    assertEquals(
        Numbers.sqrt(2f),
        Line.create(Vector.create(0f, 0f, 0f, 0f), Vector.create(2f, 2f, 0f, 0f)).distance(
            Vector.create(2f, 0f, 0f, 0f)));
  }

  /**
   * Lines are capable of calculating the vectors that represent their direction
   * and midpoint.
   */
  public void testProvidesDirectionAndMidpointCalculations() {
    verifyDirectionAndMidpoint(Line.create(Vector.create(0f, 0f), Vector.create(2f, 2f)), Vector.create(2f, 2f),
        Vector.create(1f, 1f));
    verifyDirectionAndMidpoint(Line.create(Vector.create(0f, 0f, 0f), Vector.create(2f, 2f, 2f)),
        Vector.create(2f, 2f, 2f), Vector.create(1f, 1f, 1f));
    verifyDirectionAndMidpoint(Line.create(Vector.create(0f, 0f, 0f, 0f), Vector.create(2f, 2f, 2f, 2f)),
        Vector.create(2f, 2f, 2f, 2f), Vector.create(1f, 1f, 1f, 1f));
  }

  /** Verifies the direction and midpoint calculations. */
  private void verifyDirectionAndMidpoint(Line a, Vector direction, Vector midpoint) {
    assertEquals(direction, a.direction());
    assertEquals(midpoint, a.midpoint());
  }

  /**
   * Lines are capable of returning their axis-aligned bounding boxes.
   */
  public void testProvidesAxisAlignedBoundingBoxes() {
    verifyBoundingBoxCalculation(Line.create(Vector.create(1f, 1f), Vector.create(3f, 3f)),
        Line.create(Vector.create(3f, 3f), Vector.create(1f, 1f)),
        Box.create(Vector.create(1f, 1f), Vector.create(3f, 3f)));
    verifyBoundingBoxCalculation(Line.create(Vector.create(1f, 1f, 1f), Vector.create(3f, 3f, 3f)),
        Line.create(Vector.create(3f, 3f, 3f), Vector.create(1f, 1f, 1f)),
        Box.create(Vector.create(1f, 1f, 1f), Vector.create(3f, 3f, 3f)));
    verifyBoundingBoxCalculation(Line.create(Vector.create(1f, 1f, 1f, 1f), Vector.create(3f, 3f, 3f, 3f)),
        Line.create(Vector.create(3f, 3f, 3f, 3f), Vector.create(1f, 1f, 1f, 1f)),
        Box.create(Vector.create(1f, 1f, 1f, 1f), Vector.create(3f, 3f, 3f, 3f)));
  }

  /** Verifies the bounding box calculations. */
  private void verifyBoundingBoxCalculation(Line a, Line b, Box box) {
    assertEquals(box, a.bounds());
    assertEquals(box, b.bounds());
  }

  /**
   * Lines are capable of iterators over their vertices.
   */
  public void testProvidesIteratorsOverTheVertices() {
    Line._2D l2 = Line.create(Vector.create(3f, 3f), Vector.create(1f, 1f));
    verifyVertexIteration(l2, l2, l2.begin(), l2.end());
    Line._3D l3 = Line.create(Vector.create(3f, 3f, 3f), Vector.create(1f, 1f, 1f));
    verifyVertexIteration(l3, l3, l3.begin(), l3.end());
    Line._4D l4 = Line.create(Vector.create(3f, 3f, 3f, 3f), Vector.create(1f, 1f, 1f, 1f));
    verifyVertexIteration(l4, l4, l4.begin(), l4.end());
  }

  /** Verifies the iteration of vertices. */
  private void verifyVertexIteration(Line a, Iterable<? extends Vector> b, Vector begin, Vector end) {
    Iterator<? extends Vector> ai = a.iterate(), bi = b.iterator(), ci = Arrays.asList(begin, end).iterator();
    while (ai.hasNext() || bi.hasNext() || ci.hasNext()) {
      Vector av = ai.next(), bv = bi.next(), cv = ci.next();
      assertEquals(av, cv);
      assertEquals(bv, cv);
    }
  }

  /**
   * Lines provide tests for equality and approximation with other lines.
   */
  public void testProvidesLineEqualityAndApproximationOperations() {
    verifyEqualityAndApproximation(
        Line.create(Vector.create(0f, 0f), Vector.create(1f, 1f)),
        Line.create(Vector.create(0f, 0f), Vector.create(1f, 1f)),
        Line.create(Vector.create(0.1f, 0.1f), Vector.create(0.9f, 0.9f)),
        Line.create(Vector.create(5f, 5f), Vector.create(7f, 7f)));
    verifyEqualityAndApproximation(
        Line.create(Vector.create(0f, 0f, 0f), Vector.create(1f, 1f, 1f)),
        Line.create(Vector.create(0f, 0f, 0f), Vector.create(1f, 1f, 1f)),
        Line.create(Vector.create(0.1f, 0.1f, 0.1f), Vector.create(0.9f, 0.9f, 0.9f)),
        Line.create(Vector.create(5f, 5f, 5f), Vector.create(7f, 7f, 7f)));
    verifyEqualityAndApproximation(
        Line.create(Vector.create(0f, 0f, 0f, 0f), Vector.create(1f, 1f, 1f, 1f)),
        Line.create(Vector.create(0f, 0f, 0f, 0f), Vector.create(1f, 1f, 1f, 1f)),
        Line.create(Vector.create(0.1f, 0.1f, 0.1f, 0.1f), Vector.create(0.9f, 0.9f, 0.9f, 0.9f)),
        Line.create(Vector.create(5f, 5f, 5f, 5f), Vector.create(7f, 7f, 7f, 7f)));
  }

  /** Verifies the line equality and approximation operations. */
  private void verifyEqualityAndApproximation(Line a, Line same, Line close, Line different) {
    assertTrue(a.equalTo(same));
    assertTrue(!a.equalTo(close));
    assertTrue(!a.equalTo(different));
    assertTrue(a.equalTo(same, 0.2f));
    assertTrue(a.equalTo(close, 0.2f));
    assertTrue(!a.equalTo(different, 0.2f));
  }

}
