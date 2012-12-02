/*
 * Triangle.java
 * 
 * Copyright (c) 2012 Lonnie Pryor III.
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
import java.util.Collections;
import java.util.Iterator;

/**
 * Representation of a multidimensional triangular shape.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
public abstract class Triangle implements Cloneable {

  /** Creates a new two-dimensional triangle with the specified vertices. */
  public static Triangle._2D create(Vector._2D begin, Vector._2D middle, Vector._2D end) {
    return new Triangle._2D(begin, middle, end);
  }

  /** Creates a new three-dimensional triangle with the specified vertices. */
  public static Triangle._3D create(Vector._3D begin, Vector._3D middle, Vector._3D end) {
    return new Triangle._3D(begin, middle, end);
  }

  /** Creates a new four-dimensional triangle with the specified vertices. */
  public static Triangle._4D create(Vector._4D begin, Vector._4D middle, Vector._4D end) {
    return new Triangle._4D(begin, middle, end);
  }

  /** Creates a new triangle. */
  private Triangle() {}

  /** Returns the number of dimensions in this triangle. */
  public final int dimensions() {
    return begin().dimensions();
  }

  /**
   * Returns the index of the specified vertex in the array { {@link #begin()},
   * {@link #middle()}, {@link #end()} .
   */
  public final int indexOf(Vector vertex) {
    assert vertex != null;
    if (begin().equals(vertex))
      return 0;
    if (middle().equals(vertex))
      return 1;
    if (end().equals(vertex))
      return 2;
    return -1;
  }

  /** Returns the vertex this triangle begins at. */
  public abstract Vector begin();

  /** Returns the vertex that appears in the middle of this triangle. */
  public abstract Vector middle();

  /** Returns the vertex this triangle ends at. */
  public abstract Vector end();

  /** Returns the circumcenter of this triangle. */
  public abstract Vector circumcenter();

  /**
   * Returns the vertex at the specified index in the array { {@link #begin()},
   * {@link #middle()}, {@link #end()} .
   */
  public abstract Vector vertex(int index);

  /** Calculates the bounding box of this triangle. */
  public abstract Box bounds();

  /** Returns the edge that starts at the specified vertex. */
  public abstract Line edge(int index);

  /** Returns true if this triangle contains the specified point. */
  public final boolean contains(Vector point) {
    assert point != null;
    Vector v0 = end().subtract(begin());
    Vector v1 = middle().subtract(begin());
    Vector v2 = point.subtract(begin());
    float dot00 = v0.dot(v0);
    float dot01 = v0.dot(v1);
    float dot02 = v0.dot(v2);
    float dot11 = v1.dot(v1);
    float dot12 = v1.dot(v2);
    float invDenom = 1 / (dot00 * dot11 - dot01 * dot01);
    float u = (dot11 * dot02 - dot01 * dot12) * invDenom;
    float v = (dot00 * dot12 - dot01 * dot02) * invDenom;
    return u >= 0 && v >= 0 && u + v < 1;
  }

  /** Returns an iterator over the vertices of this triangle. */
  public abstract Iterator<? extends Vector> iterate();

  /* @see Object#hashCode() */
  @Override
  public final int hashCode() {
    return getClass().hashCode() ^ begin().hashCode() ^ middle().hashCode() ^ end().hashCode();
  }

  /* @see Object#equals(Object) */
  @Override
  public final boolean equals(Object obj) {
    if (obj == this)
      return true;
    if (obj == null || !obj.getClass().equals(getClass()))
      return false;
    Triangle that = (Triangle) obj;
    return begin().equals(that.begin()) && middle().equals(that.middle()) && end().equals(that.end());
  }

  /* @see Object#toString() */
  @Override
  public final String toString() {
    String b = begin().toString();
    String m = middle().toString();
    String e = end().toString();
    StringBuilder builder = new StringBuilder(b.length() + m.length() + e.length() + 6);
    builder.append('<');
    builder.append(b);
    builder.append(", ");
    builder.append(m);
    builder.append(", ");
    builder.append(e);
    builder.append('>');
    return builder.toString();
  }

  /** Finds the circumcenter for triangles in any dimension. */
  private static <V extends Vector> V circumcenter(V begin, V middle, V end) {
    float a = end.subtract(middle).length(), a2 = a * a;
    float b = begin.subtract(end).length(), b2 = b * b;
    float c = middle.subtract(begin).length(), c2 = c * c;
    @SuppressWarnings("unchecked")
    V result = (V) begin.multiply(a2 * (b2 + c2 - a2)).add( //
        middle.multiply(b2 * (a2 + c2 - b2))).add( //
        end.multiply(c2 * (a2 + b2 - c2))).divide( //
        2 * (a2 * b2 + a2 * c2 + b2 * c2) - (a2 * a2 + b2 * b2 + c2 * c2));
    return result;
  }

  /**
   * Implementation of a two-dimensional triangle.
   * 
   * @author Lonnie Pryor III (lonnie@pryor.us.com)
   */
  public static final class _2D extends Triangle implements Iterable<Vector._2D> {

    /** The vertex this triangle begins at. */
    private final Vector._2D begin;
    /** The vertex that appears in the middle of this triangle. */
    private final Vector._2D middle;
    /** The vertex this triangle ends at. */
    private final Vector._2D end;

    /** Creates a new two-dimensional triangle. */
    public _2D(Vector._2D begin, Vector._2D middle, Vector._2D end) {
      assert begin != null;
      assert middle != null;
      assert end != null;
      this.begin = begin;
      this.middle = middle;
      this.end = end;
    }

    /* @see Triangle#begin() */
    @Override
    public Vector._2D begin() {
      return begin;
    }

    /* @see Triangle#middle() */
    @Override
    public Vector._2D middle() {
      return middle;
    }

    /* @see Triangle#end() */
    @Override
    public Vector._2D end() {
      return end;
    }

    /* @see Triangle#circumcenter() */
    @Override
    public Vector._2D circumcenter() {
      return Triangle.circumcenter(begin, middle, end);
    }

    /* @see Triangle#vertex(int) */
    @Override
    public Vector._2D vertex(int index) {
      switch (index) {
      case 0:
        return begin;
      case 1:
        return middle;
      case 2:
        return end;
      default:
        throw new IndexOutOfBoundsException(String.valueOf(index));
      }
    }

    /* @see Triangle#bounds() */
    public Box._2D bounds() {
      return Box.create( //
          Vector.create( //
              Math.min(begin.value(0), Math.min(middle.value(0), end.value(0))), //
              Math.min(begin.value(1), Math.min(middle.value(1), end.value(1)))), //
          Vector.create( //
              Math.max(begin.value(0), Math.max(middle.value(0), end.value(0))), //
              Math.max(begin.value(1), Math.max(middle.value(1), end.value(1)))));
    }

    /* @see Triangle#edge(int) */
    public Line._2D edge(int index) {
      switch (index) {
      case 0:
        return Line.create(begin, middle);
      case 1:
        return Line.create(middle, end);
      case 2:
        return Line.create(end, begin);
      default:
        throw new IndexOutOfBoundsException(String.valueOf(index));
      }
    }

    /* @see Triangle#iterate() */
    @Override
    public Iterator<? extends Vector> iterate() {
      return iterator();
    }

    /* @see Iterable#iterator() */
    @Override
    public Iterator<Vector._2D> iterator() {
      return Collections.unmodifiableList(Arrays.asList(begin, middle, end)).iterator();
    }

  }

  /**
   * Implementation of a three-dimensional triangle.
   * 
   * @author Lonnie Pryor III (lonnie@pryor.us.com)
   */
  public static final class _3D extends Triangle implements Iterable<Vector._3D> {

    /** The vertex this triangle begins at. */
    private final Vector._3D begin;
    /** The vertex that appears in the middle of this triangle. */
    private final Vector._3D middle;
    /** The vertex this triangle ends at. */
    private final Vector._3D end;

    /** Creates a new three-dimensional triangle. */
    public _3D(Vector._3D begin, Vector._3D middle, Vector._3D end) {
      assert begin != null;
      assert middle != null;
      assert end != null;
      this.begin = begin;
      this.middle = middle;
      this.end = end;
    }

    /* @see Triangle#begin() */
    @Override
    public Vector._3D begin() {
      return begin;
    }

    /* @see Triangle#middle() */
    @Override
    public Vector._3D middle() {
      return middle;
    }

    /* @see Triangle#end() */
    @Override
    public Vector._3D end() {
      return end;
    }

    /* @see Triangle#circumcenter() */
    @Override
    public Vector._3D circumcenter() {
      return Triangle.circumcenter(begin, middle, end);
    }

    /* @see Triangle#vertex(int) */
    @Override
    public Vector._3D vertex(int index) {
      switch (index) {
      case 0:
        return begin;
      case 1:
        return middle;
      case 2:
        return end;
      default:
        throw new IndexOutOfBoundsException(String.valueOf(index));
      }
    }

    /* @see Triangle#bounds() */
    public Box._3D bounds() {
      return Box.create( //
          Vector.create( //
              Math.min(begin.value(0), Math.min(middle.value(0), end.value(0))), //
              Math.min(begin.value(1), Math.min(middle.value(1), end.value(1))), //
              Math.min(begin.value(2), Math.min(middle.value(2), end.value(2)))), //
          Vector.create( //
              Math.max(begin.value(0), Math.max(middle.value(0), end.value(0))), //
              Math.max(begin.value(1), Math.max(middle.value(1), end.value(1))), //
              Math.max(begin.value(2), Math.max(middle.value(2), end.value(2)))));
    }

    /* @see Triangle#edge(int) */
    public Line._3D edge(int index) {
      switch (index) {
      case 0:
        return Line.create(begin, middle);
      case 1:
        return Line.create(middle, end);
      case 2:
        return Line.create(end, begin);
      default:
        throw new IndexOutOfBoundsException(String.valueOf(index));
      }
    }

    /* @see Triangle#iterate() */
    @Override
    public Iterator<? extends Vector> iterate() {
      return iterator();
    }

    /* @see Iterable#iterator() */
    @Override
    public Iterator<Vector._3D> iterator() {
      return Collections.unmodifiableList(Arrays.asList(begin, middle, end)).iterator();
    }

  }

  /**
   * Implementation of a four-dimensional triangle.
   * 
   * @author Lonnie Pryor III (lonnie@pryor.us.com)
   */
  public static final class _4D extends Triangle implements Iterable<Vector._4D> {

    /** The vertex this triangle begins at. */
    private final Vector._4D begin;
    /** The vertex that appears in the middle of this triangle. */
    private final Vector._4D middle;
    /** The vertex this triangle ends at. */
    private final Vector._4D end;

    /** Creates a new four-dimensional triangle. */
    public _4D(Vector._4D begin, Vector._4D middle, Vector._4D end) {
      assert begin != null;
      assert middle != null;
      assert end != null;
      this.begin = begin;
      this.middle = middle;
      this.end = end;
    }

    /* @see Triangle#begin() */
    @Override
    public Vector._4D begin() {
      return begin;
    }

    /* @see Triangle#middle() */
    @Override
    public Vector._4D middle() {
      return middle;
    }

    /* @see Triangle#end() */
    @Override
    public Vector._4D end() {
      return end;
    }

    /* @see Triangle#circumcenter() */
    @Override
    public Vector._4D circumcenter() {
      return Triangle.circumcenter(begin, middle, end);
    }

    /* @see Triangle#vertex(int) */
    @Override
    public Vector._4D vertex(int index) {
      switch (index) {
      case 0:
        return begin;
      case 1:
        return middle;
      case 2:
        return end;
      default:
        throw new IndexOutOfBoundsException(String.valueOf(index));
      }
    }

    /* @see Triangle#bounds() */
    public Box._4D bounds() {
      return Box.create( //
          Vector.create( //
              Math.min(begin.value(0), Math.min(middle.value(0), end.value(0))), //
              Math.min(begin.value(1), Math.min(middle.value(1), end.value(1))), //
              Math.min(begin.value(2), Math.min(middle.value(2), end.value(2))), //
              Math.min(begin.value(3), Math.min(middle.value(3), end.value(3)))), //
          Vector.create( //
              Math.max(begin.value(0), Math.max(middle.value(0), end.value(0))), //
              Math.max(begin.value(1), Math.max(middle.value(1), end.value(1))), //
              Math.max(begin.value(2), Math.max(middle.value(2), end.value(2))), //
              Math.max(begin.value(3), Math.max(middle.value(3), end.value(3)))));
    }

    /* @see Triangle#edge(int) */
    public Line._4D edge(int index) {
      switch (index) {
      case 0:
        return Line.create(begin, middle);
      case 1:
        return Line.create(middle, end);
      case 2:
        return Line.create(end, begin);
      default:
        throw new IndexOutOfBoundsException(String.valueOf(index));
      }
    }

    /* @see Triangle#iterate() */
    @Override
    public Iterator<? extends Vector> iterate() {
      return iterator();
    }

    /* @see Iterable#iterator() */
    @Override
    public Iterator<Vector._4D> iterator() {
      return Collections.unmodifiableList(Arrays.asList(begin, middle, end)).iterator();
    }

  }

}
