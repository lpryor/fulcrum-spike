/*
 * TriangleTest.java
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
 * Test case for {@link Triangle}.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
public class TriangleTest extends TestCase {

  /**
   * Triangles provide basic object operations including accessors, hashing,
   * equality and string conversion.
   */
  public void testProvidesBasicObjectOperations() {
    verifyBasicTriangleOperations(2,
        Triangle.create(Vector.create(1f, 2f), Vector.create(5f, 6f), Vector.create(9f, 10f)),
        Triangle.create(Vector.create(13f, 14f), Vector.create(17f, 18f), Vector.create(21f, 22f)),
        Vector.create(1f, 2f), Vector.create(5f, 6f), Vector.create(9f, 10f));
    verifyBasicTriangleOperations(3,
        Triangle.create(Vector.create(1f, 2f, 3f), Vector.create(5f, 6f, 7f), Vector.create(9f, 10f, 11f)),
        Triangle.create(Vector.create(13f, 14f, 15f), Vector.create(17f, 18f, 19f), Vector.create(21f, 22f, 23f)),
        Vector.create(1f, 2f, 3f), Vector.create(5f, 6f, 7f), Vector.create(9f, 10f, 11f));
    verifyBasicTriangleOperations(
        4,
        Triangle.create(Vector.create(1f, 2f, 3f, 4f), Vector.create(5f, 6f, 7f, 8f), Vector.create(9f, 10f, 11f, 12f)),
        Triangle.create(Vector.create(13f, 14f, 15f, 16f), Vector.create(17f, 18f, 19f, 20f),
            Vector.create(21f, 22f, 23f, 24f)), Vector.create(1f, 2f, 3f, 4f), Vector.create(5f, 6f, 7f, 8f),
        Vector.create(9f, 10f, 11f, 12f));
  }

  /**
   * Verifies the basic accessors, hashing, equality and string conversion
   * methods.
   */
  private void verifyBasicTriangleOperations( //
      int dimensions, Triangle a, Triangle b, Vector begin, Vector middle, Vector end) {
    assertEquals(dimensions, a.dimensions());
    assertEquals(dimensions, b.dimensions());
    assertEquals(begin, a.begin());
    assertEquals(middle, a.middle());
    assertEquals(end, a.end());
    assertEquals(0, a.indexOf(begin));
    assertEquals(1, a.indexOf(middle));
    assertEquals(2, a.indexOf(end));
    assertEquals(begin, a.vertex(0));
    assertEquals(middle, a.vertex(1));
    assertEquals(end, a.vertex(2));
    assertTrue(a.hashCode() == a.hashCode());
    assertTrue(b.hashCode() == b.hashCode());
    assertFalse(a.hashCode() == b.hashCode());
    assertFalse(a.equals(null));
    assertFalse(b.equals(null));
    assertTrue(a.equals(a));
    assertTrue(b.equals(b));
    assertFalse(a.equals(b));
    assertFalse(b.equals(a));
    assertTrue(a.toString().startsWith("<"));
    assertTrue(a.toString().endsWith(">"));
    assertTrue(a.toString().contains(begin.toString()));
    assertTrue(a.toString().contains(middle.toString()));
    assertTrue(a.toString().contains(end.toString()));
  }

  /**
   * Triangles are capable of calculating their circumcenters.
   */
  public void testProvidesCircumcenterCalculations() {
    verifyCircumcenter(Triangle.create(Vector.create(-1f, -2f), Vector.create(3f, -3f), Vector.create(2f, 3f)),
        Vector.create(73f / 46f, -7f / 46f));
    verifyCircumcenter(
        Triangle.create(Vector.create(-1f, -2f, 0f), Vector.create(3f, -3f, 0f), Vector.create(2f, 3f, 0f)),
        Vector.create(73f / 46f, -7f / 46f, 0f));
    verifyCircumcenter(
        Triangle.create(Vector.create(-1f, -2f, 0f, 0f), Vector.create(3f, -3f, 0f, 0f), Vector.create(2f, 3f, 0f, 0f)),
        Vector.create(73f / 46f, -7f / 46f, 0f, 0f));
  }

  /** Verifies the triangle circumcenter calculation. */
  private void verifyCircumcenter(Triangle a, Vector circumcenter) {
    Vector c = a.circumcenter();
    for (int i = 0; i < c.dimensions(); ++i)
      assertEquals(circumcenter.value(i), c.value(i), 0.001f);
  }

  /**
   * Triangles are capable of returning their axis-aligned bounding boxes.
   */
  public void testProvidesAxisAlignedBoundingBoxes() {
    verifyBoundingBoxCalculation(Triangle.create(Vector.create(1f, 1f), Vector.create(3f, 3f), Vector.create(1f, 3f)),
        Triangle.create(Vector.create(3f, 3f), Vector.create(1f, 1f), Vector.create(3f, 1f)),
        Box.create(Vector.create(1f, 1f), Vector.create(3f, 3f)));
    verifyBoundingBoxCalculation(
        Triangle.create(Vector.create(1f, 1f, 1f), Vector.create(3f, 3f, 3f), Vector.create(1f, 3f, 3f)),
        Triangle.create(Vector.create(3f, 3f, 3f), Vector.create(1f, 1f, 1f), Vector.create(3f, 1f, 1f)),
        Box.create(Vector.create(1f, 1f, 1f), Vector.create(3f, 3f, 3f)));
    verifyBoundingBoxCalculation(
        Triangle.create(Vector.create(1f, 1f, 1f, 1f), Vector.create(3f, 3f, 3f, 3f), Vector.create(1f, 3f, 3f, 3f)),
        Triangle.create(Vector.create(3f, 3f, 3f, 3f), Vector.create(1f, 1f, 1f, 1f), Vector.create(3f, 1f, 1f, 1f)),
        Box.create(Vector.create(1f, 1f, 1f, 1f), Vector.create(3f, 3f, 3f, 3f)));
  }

  /** Verifies the bounding box calculations. */
  private void verifyBoundingBoxCalculation(Triangle a, Triangle b, Box box) {
    assertEquals(box, a.bounds());
    assertEquals(box, b.bounds());
  }

  /**
   * Triangles are capable of returning their axis-aligned bounding boxes.
   */
  public void testProvidesEdgeCalculations() {
    Vector._2D a2 = Vector.create(0f, 0f), b2 = Vector.create(0f, 3f), c2 = Vector.create(3f, 0f);
    verifyEdgeCalculation(Triangle.create(a2, b2, c2), Line.create(a2, b2), Line.create(b2, c2), Line.create(c2, a2));
    Vector._3D a3 = Vector.create(0f, 0f, 0f), b3 = Vector.create(0f, 3f, 0f), c3 = Vector.create(3f, 0f, 0f);
    verifyEdgeCalculation(Triangle.create(a3, b3, c3), Line.create(a3, b3), Line.create(b3, c3), Line.create(c3, a3));
    Vector._4D a4 = Vector.create(0f, 0f, 0f, 0f), b4 = Vector.create(0f, 3f, 0f, 0f), c4 = Vector.create(3f, 0f, 0f,
        0f);
    verifyEdgeCalculation(Triangle.create(a4, b4, c4), Line.create(a4, b4), Line.create(b4, c4), Line.create(c4, a4));
  }

  /** Verifies the edge calculations. */
  private void verifyEdgeCalculation(Triangle a, Line edge0, Line edge1, Line edge2) {
    assertEquals(edge0, a.edge(0));
    assertEquals(edge1, a.edge(1));
    assertEquals(edge2, a.edge(2));
  }

  /**
   * Triangles are capable of determining if points lie within them.
   */
  public void testProvidesContainsCalculations() {
    verifyContainsCalculation(Triangle.create(Vector.create(0f, 0f), Vector.create(0f, 3f), Vector.create(3f, 0f)),
        Vector.create(1f, 1f), Vector.create(5f, 5f));
    verifyContainsCalculation(
        Triangle.create(Vector.create(0f, 0f, 0f), Vector.create(0f, 3f, 0f), Vector.create(3f, 0f, 0f)),
        Vector.create(1f, 1f, 0f), Vector.create(5f, 5f, 0f));
    verifyContainsCalculation(
        Triangle.create(Vector.create(0f, 0f, 0f, 0f), Vector.create(0f, 3f, 0f, 0f), Vector.create(3f, 0f, 0f, 0f)),
        Vector.create(1f, 1f, 0f, 0f), Vector.create(5f, 5f, 0f, 0f));
  }

  /** Verifies the contains point calculations. */
  private void verifyContainsCalculation(Triangle a, Vector in, Vector out) {
    assertTrue(a.contains(in));
    assertFalse(a.contains(out));
  }

  /**
   * Triangles are capable of iterators over their vertices.
   */
  public void testProvidesIteratorsOverTheVertices() {
    Triangle._2D t2 = Triangle.create(Vector.create(3f, 3f), Vector.create(1f, 1f), Vector.create(3f, 1f));
    verifyVertexIteration(t2, t2, t2.begin(), t2.middle(), t2.end());
    Triangle._3D t3 = Triangle.create(Vector.create(3f, 3f, 3f), Vector.create(1f, 1f, 1f), Vector.create(3f, 1f, 1f));
    verifyVertexIteration(t3, t3, t3.begin(), t3.middle(), t3.end());
    Triangle._4D t4 = Triangle.create(Vector.create(3f, 3f, 3f, 3f), Vector.create(1f, 1f, 1f, 1f),
        Vector.create(3f, 1f, 1f, 1f));
    verifyVertexIteration(t4, t4, t4.begin(), t4.middle(), t4.end());
  }

  /** Verifies the iteration of vertices. */
  private void verifyVertexIteration(Triangle a, Iterable<? extends Vector> b, Vector begin, Vector middle, Vector end) {
    Iterator<? extends Vector> ai = a.iterate(), bi = b.iterator(), ci = Arrays.asList(begin, middle, end).iterator();
    while (ai.hasNext() || bi.hasNext() || ci.hasNext()) {
      Vector av = ai.next(), bv = bi.next(), cv = ci.next();
      assertEquals(av, cv);
      assertEquals(bv, cv);
    }
  }

  /**
   * Triangles provide tests for equality and approximation with other triangles.
   */
  public void testProvidesTriangleEqualityAndApproximationOperations() {
    verifyEqualityAndApproximation(
        Triangle.create(Vector.create(0f, 0f), Vector.create(1f, 1f), Vector.create(0f, 1f)),
        Triangle.create(Vector.create(0f, 0f), Vector.create(1f, 1f), Vector.create(0f, 1f)),
        Triangle.create(Vector.create(0.1f, 0.1f), Vector.create(0.9f, 0.9f), Vector.create(0.1f, 0.9f)),
        Triangle.create(Vector.create(5f, 5f), Vector.create(7f, 7f), Vector.create(5f, 7f)));
    verifyEqualityAndApproximation(
        Triangle.create(Vector.create(0f, 0f, 0f), Vector.create(1f, 1f, 1f), Vector.create(0f, 1f, 1f)),
        Triangle.create(Vector.create(0f, 0f, 0f), Vector.create(1f, 1f, 1f), Vector.create(0f, 1f, 1f)),
        Triangle.create(Vector.create(0.1f, 0.1f, 0.1f), Vector.create(0.9f, 0.9f, 0.9f), Vector.create(0.1f, 0.9f, 0.9f)),
        Triangle.create(Vector.create(5f, 5f, 5f), Vector.create(7f, 7f, 7f), Vector.create(5f, 7f, 7f)));
    verifyEqualityAndApproximation(
        Triangle.create(Vector.create(0f, 0f, 0f, 0f), Vector.create(1f, 1f, 1f, 1f), Vector.create(0f, 1f, 1f, 1f)),
        Triangle.create(Vector.create(0f, 0f, 0f, 0f), Vector.create(1f, 1f, 1f, 1f), Vector.create(0f, 1f, 1f, 1f)),
        Triangle.create(Vector.create(0.1f, 0.1f, 0.1f, 0.1f), Vector.create(0.9f, 0.9f, 0.9f, 0.9f), Vector.create(0.1f, 0.9f, 0.9f, 0.9f)),
        Triangle.create(Vector.create(5f, 5f, 5f, 5f), Vector.create(7f, 7f, 7f, 7f), Vector.create(5f, 7f, 7f, 7f)));
  }

  /** Verifies the triangle equality and approximation operations. */
  private void verifyEqualityAndApproximation(Triangle a, Triangle same, Triangle close, Triangle different) {
    assertTrue(a.equalTo(same));
    assertTrue(!a.equalTo(close));
    assertTrue(!a.equalTo(different));
    assertTrue(a.equalTo(same, 0.2f));
    assertTrue(a.equalTo(close, 0.2f));
    assertTrue(!a.equalTo(different, 0.2f));
  }

}
