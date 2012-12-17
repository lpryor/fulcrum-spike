/*
 * DelaunayTest.java
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
import java.util.Collection;

import fulcrum.util.Random;

import junit.framework.TestCase;

/**
 * Test case for {@link Delaunay}.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
public class DelaunayTest extends TestCase {

  /** Index of the x-coordinate in 2D. */
  private static final int X = 0;
  /** Index of the y-coordinate in 2D. */
  private static final int Y = 1;

  /**
   * The Delaunay triangulation implementation returns valid results for
   * diagrams with less than three sites.
   */
  public void testHandlesTriangulationsWithLessThanThreeSites() {
    Delaunay d = Delaunay.create();
    assertTrue(d.isEmpty());
    Vector._2D site1 = Vector.create(1f, 1f);
    d = Delaunay.create(site1);
    assertTrue(d.isEmpty());
    Vector._2D site2 = Vector.create(2f, 2f);
    d = Delaunay.create(site1, site2);
    assertTrue(d.isEmpty());
  }

  /**
   * The Delaunay triangulation implementation is capable of generating a
   * triangulation for sites in the shape of an "X".
   */
  public void testGeneratesTrianglesFromSitesInAnX() {
    Vector._2D[] sites = {
        //
        Vector.create(-1f, 1f), //
        Vector.create(1f, 1f), //
        Vector.create(1f, -1f), //
        Vector.create(-1f, -1f), //
        Vector.create(0f, 0f) //
    };
    Triangle._2D[] triangles = {
        //
        Triangle.create(sites[0], sites[1], sites[4]), //
        Triangle.create(sites[0], sites[3], sites[4]), //
        Triangle.create(sites[2], sites[3], sites[4]), //
        Triangle.create(sites[1], sites[2], sites[4]) //
    };
    Delaunay d = Delaunay.create(sites);
    verifyAndMatchTriangles(Arrays.asList(triangles), d, 0.000001f);
  }

  /**
   * The Delaunay triangulation implementation is capable of generating a
   * triangulation for sites in the shape of a "+" Symbol.
   */
  public void testGeneratesTrianglesFromPlusShapedSites() {
    Vector._2D[] sites = {
        //
        Vector.create(-1f, 0f), //
        Vector.create(1f, 0f), //
        Vector.create(0f, -1f), //
        Vector.create(0f, 1f), //
        Vector.create(0f, 0f) //
    };
    Triangle._2D[] triangles = {
        //
        Triangle.create(sites[0], sites[2], sites[4]), //
        Triangle.create(sites[0], sites[3], sites[4]), //
        Triangle.create(sites[1], sites[3], sites[4]), //
        Triangle.create(sites[2], sites[1], sites[4]) //
    };
    Delaunay d = Delaunay.create(sites);
    verifyAndMatchTriangles(Arrays.asList(triangles), d, 0.000001f);
  }

  /**
   * The Delaunay triangulation implementation is capable of handling site sets
   * that have no triangles.
   */
  public void testHandlesDiagramsWithNoTriangles() {
    Vector._2D[] sites = {
        //
        Vector.create(-2f, 0f), //
        Vector.create(-1f, 0f), //
        Vector.create(0f, 0f), //
        Vector.create(1f, 0f), //
        Vector.create(2f, 0f) //
    };
    assertTrue(Delaunay.create(sites).isEmpty());
    sites = new Vector._2D[] {
        //
        Vector.create(0f, -2f), //
        Vector.create(0f, -1f), //
        Vector.create(0f, 0f), //
        Vector.create(0f, 1f), //
        Vector.create(0f, 2f) //
    };
    assertTrue(Delaunay.create(sites).isEmpty());
  }

  /**
   * The Delaunay triangulation implementation is capable of generating a
   * triangulation from a large number of random sites.
   */
  public void testGeneratesEdgesFromLargeSiteSets() {
    int side = 32;
    Vector._2D[] sites = new Vector._2D[side * side];
    Random random = new Random(0x12345678);
    for (int x = 0; x < side; ++x)
      for (int y = 0; y < side; ++y)
        sites[x * side + y] = Vector.create(x * side + random.nextInteger(side - 2) + 1,
            y * side + random.nextInteger(side - 2) + 1);
    Delaunay actual = Delaunay.create(sites);
    assertFalse(actual.isEmpty());
  }

  /** Verifies that the generated triangles are correct. */
  private void verifyAndMatchTriangles(Collection<Triangle._2D> expected, Delaunay actual, float delta) {
    assertEquals(expected.size(), actual.size());
    for (Triangle._2D a : actual)
      assertTrue(a.toString(), matchTriangle(expected, a, delta));
  }

  /** Verifies that a single triangle is correct. */
  private boolean matchTriangle(Collection<Triangle._2D> expected, Triangle._2D actual, float delta) {
    float bx = actual.begin().value(X), mx = actual.middle().value(X), ex = actual.end().value(X);
    float by = actual.begin().value(Y), my = actual.middle().value(Y), ey = actual.end().value(Y);
    float sum = (mx - bx) * (my + by) + (ex - mx) * (ey + my) + (bx - ex) * (by + ey);
    assertTrue(sum < 0f);
    for (Triangle._2D e : expected)
      if (matchVertices(e, actual, delta))
        return true;
    return false;
  }

  /** Verifies that two triangles have the same vertices. */
  private boolean matchVertices(Triangle._2D expected, Triangle._2D actual, float delta) {
    for (int a = 0; a < 3; ++a)
      if (!matchVertex(expected, actual.vertex(a), delta))
        return false;
    return true;
  }

  /** Verifies that a vertex appears in a triangle. */
  private boolean matchVertex(Triangle._2D expected, Vector._2D actual, float delta) {
    for (int e = 0; e < 3; ++e)
      if (expected.vertex(e).equalTo(actual, delta))
        return true;
    return false;
  }

}
