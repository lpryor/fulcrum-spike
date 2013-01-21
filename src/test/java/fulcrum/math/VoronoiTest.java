/*
 * VoronoiTest.java
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

import java.util.LinkedHashMap;
import java.util.Map;

import fulcrum.util.Random;

import junit.framework.TestCase;

/**
 * Test case for {@link Voronoi}.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
public class VoronoiTest extends TestCase {

  /** Index of the x-coordinate in 2D. */
  private static final int X = 0;
  /** Index of the y-coordinate in 2D. */
  private static final int Y = 1;

  /**
   * The Vonoroi diagram implementation returns valid results for diagrams with
   * less than two sites.
   */
  public void testHandlesDiagramsWithLessThanTwoSites() {
    Box._2D bounds = Box.create(Vector.create(0f, 0f), Vector.create(4f, 4f));
    Voronoi v = Voronoi.create(bounds);
    assertTrue(v.isEmpty());
    Vector._2D site = Vector.create(1f, 1f);
    v = Voronoi.create(bounds, site);
    assertEquals(1, v.size());
    Polygon._2D p = v.get(site);
    assertNotNull(p);
    assertEquals(4, p.vertices());
    assertEquals(Vector.create(bounds.lower().value(X), bounds.upper().value(Y)), p.vertex(0));
    assertEquals(bounds.lower(), p.vertex(1));
    assertEquals(Vector.create(bounds.upper().value(X), bounds.lower().value(Y)), p.vertex(2));
    assertEquals(bounds.upper(), p.vertex(3));
  }

  /**
   * The Vonoroi diagram implementation is capable of generating a diagram from
   * three sites organically distributed in a plane.
   */
  public void testGeneratesDiagramsWithThreeSites() {
    Box._2D bounds = Box.create(Vector.create(0f, 0f), Vector.create(256f, 128f));
    Map<Vector._2D, Polygon._2D> expected = new LinkedHashMap<Vector._2D, Polygon._2D>();
    Vector._2D a = Vector.create(78f, 77f), b = Vector.create(156f, 102f), c = Vector.create(196f, 63f);
    expected.put(a, Polygon.create( //
        Vector.create(104.6602f, 128f), //
        Vector.create(0f, 128f), //
        Vector.create(0f, 0f), //
        Vector.create(128.6949f, 0f), //
        Vector.create(133.2852f, 38.6900f)));
    expected.put(b, Polygon.create( //
        Vector.create(220.3625f, 128f), //
        Vector.create(104.6602f, 128f), //
        Vector.create(133.2852f, 38.6900f)));
    expected.put(c, Polygon.create( //
        Vector.create(128.6949f, 0f), //
        Vector.create(256f, 0f), //
        Vector.create(256f, 128f), //
        Vector.create(220.3625f, 128f), //
        Vector.create(133.2852f, 38.6900f)));
    verifyDiagram(expected, Voronoi.create(bounds, expected.keySet()), 0.001f);
  }

  /**
   * The Vonoroi diagram implementation is capable of generating a diagram with
   * a diamond-shaped polygon at the origin and four pentagons on its diagonal
   * edges.
   */
  public void testGeneratesDiagramsWithCentralDiamond() {
    Box._2D bounds = Box.create(Vector.create(-2f, -2f), Vector.create(2f, 2f));
    Map<Vector._2D, Polygon._2D> expected = new LinkedHashMap<Vector._2D, Polygon._2D>();
    expected.put(Vector.create(0f, 0f), Polygon.create( //
        Vector.create(0f, 1f), //
        Vector.create(-1f, 0f), //
        Vector.create(0f, -1f), //
        Vector.create(1f, 0f)));
    expected.put(Vector.create(-1f, 1f), Polygon.create( //
        Vector.create(0f, 2f), //
        Vector.create(-2f, 2f), //
        Vector.create(-2f, 0f), //
        Vector.create(-1f, 0f), //
        Vector.create(0f, 1f)));
    expected.put(Vector.create(1f, 1f), Polygon.create( //
        Vector.create(2f, 0f), //
        Vector.create(2f, 2f), //
        Vector.create(0f, 2f), //
        Vector.create(0f, 1f), //
        Vector.create(1f, 0f)));
    expected.put(Vector.create(1f, -1f), Polygon.create( //
        Vector.create(2f, -2f), //
        Vector.create(2f, 0f), //
        Vector.create(1f, 0f), //
        Vector.create(0f, -1f), //
        Vector.create(0f, -2f)));
    expected.put(Vector.create(-1f, -1f), Polygon.create( //
        Vector.create(-1f, 0f), //
        Vector.create(-2f, 0f), //
        Vector.create(-2f, -2f), //
        Vector.create(0f, -2f), //
        Vector.create(0f, -1f)));
    verifyDiagram(expected, Voronoi.create(bounds, expected.keySet()), 0.000001f);
  }

  /**
   * The Vonoroi diagram implementation is capable of generating a diagram with
   * a square-shaped polygon at the origin with two trapezoids above and below
   * and two hexagons on the left and right.
   */
  public void testGeneratesDiagramsWithCentralSqare() {
    Box._2D bounds = Box.create(Vector.create(-3f, -2f), Vector.create(3f, 2f));
    Map<Vector._2D, Polygon._2D> expected = new LinkedHashMap<Vector._2D, Polygon._2D>();
    expected.put(Vector.create(0f, 0f), Polygon.create( //
        Vector.create(-0.5f, 0.5f), //
        Vector.create(-0.5f, -0.5f), //
        Vector.create(0.5f, -0.5f), //
        Vector.create(0.5f, 0.5f)));
    expected.put(Vector.create(-1f, 0f), Polygon.create( //
        Vector.create(-0.5f, 0.5f), //
        Vector.create(-2f, 2f), //
        Vector.create(-3f, 2f), //
        Vector.create(-3f, -2f), //
        Vector.create(-2f, -2f), //
        Vector.create(-0.5f, -0.5f)));
    expected.put(Vector.create(1f, 0f), Polygon.create( //
        Vector.create(3f, -2f), //
        Vector.create(3f, 2f), //
        Vector.create(2f, 2f), //
        Vector.create(0.5f, 0.5f), //
        Vector.create(0.5f, -0.5f), //
        Vector.create(2f, -2f)));
    expected.put(Vector.create(0f, -1f), Polygon.create( //
        Vector.create(-0.5f, -0.5f), //
        Vector.create(-2f, -2f), //
        Vector.create(2f, -2f), //
        Vector.create(0.5f, -0.5f)));
    expected.put(Vector.create(0f, 1f), Polygon.create( //
        Vector.create(2f, 2f), //
        Vector.create(-2f, 2f), //
        Vector.create(-0.5f, 0.5f), //
        Vector.create(0.5f, 0.5f)));
    verifyDiagram(expected, Voronoi.create(bounds, expected.keySet()), 0.000001f);
  }

  /**
   * The Vonoroi diagram implementation is capable of generating a diagram with
   * multiple rectangles in a row.
   */
  public void testGeneratesDiagramsWithVerticalStripes() {
    Box._2D bounds = Box.create(Vector.create(-2f, -2f), Vector.create(2f, 2f));
    Map<Vector._2D, Polygon._2D> expected = new LinkedHashMap<Vector._2D, Polygon._2D>();
    expected.put(Vector.create(-1f, 0f), Polygon.create( //
        Vector.create(-0.5f, -2f), //
        Vector.create(-0.5f, 2f), //
        Vector.create(-2f, 2f), //
        Vector.create(-2f, -2f)));
    expected.put(Vector.create(0f, 0f), Polygon.create( //
        Vector.create(0.5f, -2f), //
        Vector.create(0.5f, 2f), //
        Vector.create(-0.5f, 2f), //
        Vector.create(-0.5f, -2f)));
    expected.put(Vector.create(1f, 0f), Polygon.create( //
        Vector.create(0.5f, 2f), //
        Vector.create(0.5f, -2f), //
        Vector.create(2f, -2f), //
        Vector.create(2f, 2f)));
    verifyDiagram(expected, Voronoi.create(bounds, expected.keySet()), 0.000001f);
  }

  /**
   * The Vonoroi diagram implementation is capable of generating a diagram with
   * multiple rectangles in a column.
   */
  public void testGeneratesDiagramsWithHorizontalStripes() {
    Box._2D bounds = Box.create(Vector.create(-2f, -2f), Vector.create(2f, 2f));
    Map<Vector._2D, Polygon._2D> expected = new LinkedHashMap<Vector._2D, Polygon._2D>();
    expected.put(Vector.create(0f, -1f), Polygon.create( //
        Vector.create(2f, -2f), //
        Vector.create(2f, -0.5f), //
        Vector.create(-2f, -0.5f), //
        Vector.create(-2f, -2f)));
    expected.put(Vector.create(0f, 1f), Polygon.create( //
        Vector.create(-2f, 2f), //
        Vector.create(-2f, 0.5f), //
        Vector.create(2f, 0.5f), //
        Vector.create(2f, 2f)));
    expected.put(Vector.create(0f, 0f), Polygon.create( //
        Vector.create(2f, 0.5f), //
        Vector.create(-2f, 0.5f), //
        Vector.create(-2f, -0.5f), //
        Vector.create(2f, -0.5f)));
    verifyDiagram(expected, Voronoi.create(bounds, expected.keySet()), 0.000001f);
  }

  /**
   * The Vonoroi diagram implementation is capable of generating a diagram from
   * a large number of random sites.
   */
  public void testGeneratesDiagramsFromLargeSiteSets() {
    int side = 32;
    Vector._2D[] sites = new Vector._2D[side * side];
    Random random = new Random(0x12345678);
    for (int x = 0; x < side; ++x)
      for (int y = 0; y < side; ++y)
        sites[x * side + y] = Vector.create(x * side + random.nextInteger(side - 2) + 1,
            y * side + random.nextInteger(side - 2) + 1);
    assertEquals(sites.length,
        Voronoi.create(Box.create(Vector.create(0f, 0f), Vector.create(side * side, side * side)), sites).size());
  }

  /**
   * The Vonoroi diagram implementation is capable of generating a diagram where
   * sites fall outside the bounding box.
   */
  public void testCropsDiagramsWithSitesThatOverflowTheBoundingBox() {
    Box._2D bounds = Box.create(Vector.create(-1.75f, -1.75f), Vector.create(-0.25f, -0.25f));
    Map<Vector._2D, Polygon._2D> expected = new LinkedHashMap<Vector._2D, Polygon._2D>();
    expected.put(Vector.create(-1f, -1f), Polygon.create( //
        Vector.create(-0.25f, -1.75f), //
        Vector.create(-0.25f, -0.75f), //
        Vector.create(-0.75f, -0.25f), //
        Vector.create(-1.75f, -0.25f), //
        Vector.create(-1.75f, -1.75f)));
    expected.put(Vector.create(0f, 0f), Polygon.create( //
        Vector.create(-0.25f, -0.25f), //
        Vector.create(-0.75f, -0.25f), //
        Vector.create(-0.25f, -0.75f)));
    expected.put(Vector.create(1f, 1f), null);
    Voronoi v = Voronoi.create(bounds, expected.keySet());
    expected.remove(Vector.create(1f, 1f));
    verifyDiagram(expected, v, 0.000001f);
  }

  /**
   * The Vonoroi diagram implementation is capable of generating a diagram where
   * vertices fall outside the bounding box.
   */
  public void testGeneratesDiagramsWithVerticesThatOverflowTheBoundingBox() {
    Box._2D bounds = Box.create(Vector.create(0f, 0f), Vector.create(512f, 128f));
    Map<Vector._2D, Polygon._2D> expected = new LinkedHashMap<Vector._2D, Polygon._2D>();
    expected.put(Vector.create(40f, 80f), Polygon.create( //
        Vector.create(0f, 128f), //
        Vector.create(0f, 0f), //
        Vector.create(98.020294f, 0f), //
        Vector.create(130.23798f, 128f)));
    expected.put(Vector.create(187f, 43f), Polygon.create( //
        Vector.create(130.23798f, 128f), //
        Vector.create(98.020294f, 0f), //
        Vector.create(274.37796f, 0f), //
        Vector.create(244.77242f, 128f)));
    expected.put(Vector.create(334f, 77f), Polygon.create( //
        Vector.create(384.69498f, 0f), //
        Vector.create(399.8814f, 128f), //
        Vector.create(244.77242f, 128f), //
        Vector.create(274.37796f, 0f)));
    expected.put(Vector.create(452f, 63f), Polygon.create( //
        Vector.create(512f, 0f), //
        Vector.create(512f, 128f), //
        Vector.create(399.8814f, 128f), //
        Vector.create(384.69498f, 0f)));
    verifyDiagram(expected, Voronoi.create(bounds, expected.keySet()), 0.001f);
  }

  /** Verifies the shape of all the cells in a diagram. */
  private void verifyDiagram(Map<Vector._2D, Polygon._2D> expected, Voronoi actual, float delta) {
    assertEquals(expected.keySet(), actual.keySet());
    for (Map.Entry<Vector._2D, Polygon._2D> cell : expected.entrySet()) {
      Polygon._2D e = cell.getValue(), a = actual.get(cell.getKey());
      assertEquals(e.vertices(), a.vertices());
      for (int i = 0; i < e.vertices(); ++i)
        assertTrue(e + " != " + a, e.vertex(i).equalTo(a.vertex(i), delta));
    }
  }

}
