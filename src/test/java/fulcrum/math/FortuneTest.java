/*
 * FortuneTest.java
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
 * Test case for {@link Fortune}.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
public class FortuneTest extends TestCase {

  /**
   * The Fortune's algorithm implementation is capable of generating a diagram
   * with a diamond-shaped polygon at the origin and four edges that radiate
   * outward infinitely along the horizontal and vertical axis.
   */
  public void testGeneratesEdgesWithCentralDiamond() {
    Vector._2D[] sites = {
        //
        Vector.create(-1f, 1f), //
        Vector.create(1f, 1f), //
        Vector.create(1f, -1f), //
        Vector.create(-1f, -1f), //
        Vector.create(0f, 0f) //
    };
    float NI = Float.NEGATIVE_INFINITY, PI = Float.POSITIVE_INFINITY;
    Line._2D[] expected = {
        //
        Line.create(Vector.create(0f, -1f), Vector.create(-1f, 0f)), //
        Line.create(Vector.create(0f, -1f), Vector.create(1f, 0f)), //
        Line.create(Vector.create(-1f, 0f), Vector.create(0f, 1f)), //
        Line.create(Vector.create(1f, 0f), Vector.create(0f, 1f)), //
        Line.create(Vector.create(NI, 0f), Vector.create(-1f, 0f)), //
        Line.create(Vector.create(0f, 1f), Vector.create(0f, PI)), //
        Line.create(Vector.create(1f, 0f), Vector.create(PI, 0f)), //
        Line.create(Vector.create(0f, NI), Vector.create(0f, -1f)) //
    };
    verifyEdges(Arrays.asList(expected), Fortune.create(sites), 0.000001f);
  }

  /**
   * The Fortune's algorithm implementation is capable of generating a diagram
   * with a square-shaped polygon at the origin and four edges that radiate
   * outward infinitely along the diagonal axis.
   */
  public void testGeneratesEdgesWithCentralSquare() {
    Vector._2D[] sites = {
        //
        Vector.create(-1f, 0f), //
        Vector.create(1f, 0f), //
        Vector.create(0f, -1f), //
        Vector.create(0f, 1f), //
        Vector.create(0f, 0f) //
    };
    float HF = 1f / 2f, NI = Float.NEGATIVE_INFINITY, PI = Float.POSITIVE_INFINITY;
    Line._2D[] expected = {
        //
        Line.create(Vector.create(-HF, HF), Vector.create(-HF, -HF)), //
        Line.create(Vector.create(-HF, -HF), Vector.create(HF, -HF)), //
        Line.create(Vector.create(HF, -HF), Vector.create(HF, HF)), //
        Line.create(Vector.create(HF, HF), Vector.create(-HF, HF)), //
        Line.create(Vector.create(-HF, HF), Vector.create(NI, PI)), //
        Line.create(Vector.create(-HF, -HF), Vector.create(NI, NI)), //
        Line.create(Vector.create(HF, -HF), Vector.create(PI, NI)), //
        Line.create(Vector.create(HF, HF), Vector.create(PI, PI)) //
    };
    verifyEdges(Arrays.asList(expected), Fortune.create(sites), 0.000001f);
  }

  /**
   * The Fortune's algorithm implementation is capable of generating a diagram
   * with nothing but infinitely tall vertical stripes.
   */
  public void testGeneratesEdgesWithVerticalStripes() {
    Vector._2D[] sites = {
        //
        Vector.create(-2f, 0f), //
        Vector.create(-1f, 0f), //
        Vector.create(0f, 0f), //
        Vector.create(1f, 0f), //
        Vector.create(2f, 0f) //
    };
    float NI = Float.NEGATIVE_INFINITY, PI = Float.POSITIVE_INFINITY;
    Line._2D[] expected = {
        //
        Line.create(Vector.create(-1.5f, NI), Vector.create(-1.5f, PI)), //
        Line.create(Vector.create(-0.5f, NI), Vector.create(-0.5f, PI)), //
        Line.create(Vector.create(0.5f, NI), Vector.create(0.5f, PI)), //
        Line.create(Vector.create(1.5f, NI), Vector.create(1.5f, PI)) //
    };
    verifyEdges(Arrays.asList(expected), Fortune.create(sites), 0.000001f);
  }

  /**
   * The Fortune's algorithm implementation is capable of generating a diagram
   * with nothing but infinitely wide horizontal stripes.
   */
  public void testGeneratesEdgesWithHorizontalStripes() {
    Vector._2D[] sites = {
        //
        Vector.create(0f, -2f), //
        Vector.create(0f, -1f), //
        Vector.create(0f, 0f), //
        Vector.create(0f, 1f), //
        Vector.create(0f, 2f) //
    };
    float NI = Float.NEGATIVE_INFINITY, PI = Float.POSITIVE_INFINITY;
    Line._2D[] expected = {
        //
        Line.create(Vector.create(NI, -1.5f), Vector.create(PI, -1.5f)), //
        Line.create(Vector.create(NI, -0.5f), Vector.create(PI, -0.5f)), //
        Line.create(Vector.create(NI, 0.5f), Vector.create(PI, 0.5f)), //
        Line.create(Vector.create(NI, 1.5f), Vector.create(PI, 1.5f)) //
    };
    verifyEdges(Arrays.asList(expected), Fortune.create(sites), 0.000001f);
  }

  /**
   * The Fortune's algorithm implementation is capable of generating a diagram
   * from a large number of sites.
   */
  public void testGeneratesEdgesFromLargeSiteSets() {
    int side = 32;
    Vector._2D[] sites = new Vector._2D[side * side];
    Random random = new Random(0x12345678);
    for (int x = 0; x < side; ++x)
      for (int y = 0; y < side; ++y)
        sites[x * side + y] = Vector.create(x * side + random.nextInteger(side - 2) + 1,
            y * side + random.nextInteger(side - 2) + 1);
    Fortune actual = Fortune.create(sites);
    assertTrue(actual.edges().size() > sites.length);
    for (Fortune.Edge a : actual.edges())
      verifyEdge(a);
  }

  /** Verifies that the generated edges are correct. */
  private void verifyEdges(Collection<Line._2D> expected, Fortune actual, float delta) {
    assertEquals(expected.size(), actual.edges().size());
    for (Fortune.Edge a : actual.edges()) {
      verifyEdge(a);
      assertTrue(Line.create(a.begin(), a.end()).toString(), matchEdge(expected, a, delta));
    }
  }

  /** Verifies that an edge is internally consistent. */
  private void verifyEdge(Fortune.Edge a) {
    switch (a.type()) {
    case LINE:
      assertTrue((a.direction().value(0) < 0f) == (a.begin().value(0) == Float.POSITIVE_INFINITY));
      assertTrue((a.direction().value(0) > 0f) == (a.begin().value(0) == Float.NEGATIVE_INFINITY));
      assertTrue((a.direction().value(1) < 0f) == (a.begin().value(1) == Float.POSITIVE_INFINITY));
      assertTrue((a.direction().value(1) > 0f) == (a.begin().value(1) == Float.NEGATIVE_INFINITY));
      assertTrue((a.direction().value(0) < 0f) == (a.end().value(0) == Float.NEGATIVE_INFINITY));
      assertTrue((a.direction().value(0) > 0f) == (a.end().value(0) == Float.POSITIVE_INFINITY));
      assertTrue((a.direction().value(1) < 0f) == (a.end().value(1) == Float.NEGATIVE_INFINITY));
      assertTrue((a.direction().value(1) > 0f) == (a.end().value(1) == Float.POSITIVE_INFINITY));
      break;
    case RAY:
      assertFalse(Float.isInfinite(a.begin().value(0)));
      assertFalse(Float.isInfinite(a.begin().value(1)));
      assertTrue((a.direction().value(0) < 0f) == (a.end().value(0) == Float.NEGATIVE_INFINITY));
      assertTrue((a.direction().value(0) > 0f) == (a.end().value(0) == Float.POSITIVE_INFINITY));
      assertTrue((a.direction().value(1) < 0f) == (a.end().value(1) == Float.NEGATIVE_INFINITY));
      assertTrue((a.direction().value(1) > 0f) == (a.end().value(1) == Float.POSITIVE_INFINITY));
      assertFalse(a.begin().equalTo(a.location()));
      break;
    case SEGMENT:
      assertFalse(Float.isInfinite(a.begin().value(0)));
      assertFalse(Float.isInfinite(a.begin().value(1)));
      assertFalse(Float.isInfinite(a.end().value(0)));
      assertFalse(Float.isInfinite(a.end().value(1)));
      assertFalse(a.begin().equalTo(a.end()));
      assertFalse(a.begin().equalTo(a.location()));
      assertFalse(a.end().equalTo(a.location()));
      break;
    }
    assertTrue(a.left().value(0) < a.right().value(0) || //
        a.left().value(0) == a.right().value(0) && a.left().value(1) <= a.right().value(1));
  }

  /** Verifies that a single edge is correct. */
  private boolean matchEdge(Collection<Line._2D> expected, Fortune.Edge actual, float delta) {
    for (Line._2D e : expected)
      if (e.begin().equalTo(actual.begin(), delta) && e.end().equalTo(actual.end(), delta)
          || e.begin().equalTo(actual.end(), delta) && e.end().equalTo(actual.begin(), delta))
        return true;
    System.out.println(expected);
    return false;
  }

}