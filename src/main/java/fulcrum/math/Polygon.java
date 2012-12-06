/*
 * Polygon.java
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
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Representation of a two-dimensional closed polygon.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
public abstract class Polygon implements Cloneable {

  /** Creates a new two-dimensional line with the specified vertices. */
  public static Polygon._2D create(Vector._2D... vertices) {
    return new Polygon._2D(vertices);
  }

  /** Creates a new three-dimensional line with the specified vertices. */
  public static Polygon._3D create(Vector._3D... vertices) {
    return new Polygon._3D(vertices);
  }

  /** Creates a new four-dimensional line with the specified vertices. */
  public static Polygon._4D create(Vector._4D... vertices) {
    return new Polygon._4D(vertices);
  }

  /** Creates a new polygon. */
  private Polygon() {}

  /** Returns the number of dimensions in this polygon. */
  public final int dimensions() {
    return vertex(0).dimensions();
  }

  /** Returns the number of vertices in this polygon. */
  public abstract int vertices();

  /** Returns the index of the specified vertex. */
  public final int indexOf(Vector vertex) {
    assert vertex != null;
    int vertices = vertices();
    for (int i = 0; i < vertices; ++i)
      if (vertex(i).equals(vertex))
        return i;
    return -1;
  }

  /** Returns the vertex at the specified index. */
  public abstract Vector vertex(int index);

  /** Calculates the bounding box of this polygon. */
  public abstract Box bounds();

  /** Returns the edge that follows the specified vertex. */
  public abstract Line edge(int index);

  /** Returns this polygon cropped to the specified bounds or {@code null}. */
  public abstract Polygon crop(Box bounds);

  /** Returns an iterator over the vertices of this polygon. */
  public abstract Iterator<? extends Vector> iterate();

  /**
   * Returns true if each vertex of this polygon is equal to the corresponding
   * vertex of that polygon.
   */
  public final boolean equalTo(Polygon that) {
    assert that != null;
    int vertices = vertices();
    if (vertices != that.vertices())
      return false;
    for (int i = 0; i < vertices; ++i)
      if (!vertex(i).equalTo(that.vertex(i)))
        return false;
    return true;
  }

  /**
   * Returns true if each vertex of this polygon is approximately equal to the
   * corresponding vertex of that polygon, using the supplied delta to compute
   * the approximation.
   */
  public final boolean equalTo(Polygon that, float delta) {
    assert that != null;
    assert delta >= 0f;
    int vertices = vertices();
    if (vertices != that.vertices())
      return false;
    for (int i = 0; i < vertices; ++i)
      if (!vertex(i).equalTo(that.vertex(i), delta))
        return false;
    return true;
  }

  /* @see Object#hashCode() */
  @Override
  public final int hashCode() {
    int vertices = vertices(), hash = getClass().hashCode();
    for (int i = 0; i < vertices; ++i)
      hash ^= vertex(i).hashCode();
    return hash;
  }

  /* @see Object#equals(Object) */
  @Override
  public final boolean equals(Object obj) {
    if (obj == this)
      return true;
    if (obj == null || !obj.getClass().equals(getClass()))
      return false;
    return equalTo((Polygon) obj);
  }

  /* @see Object#toString() */
  @Override
  public final String toString() {
    int vertices = vertices();
    StringBuilder builder = new StringBuilder(vertices * dimensions() * 10 + vertices * 2);
    builder.append('{');
    for (int i = 0; i < vertices; ++i) {
      builder.append(vertex(i));
      builder.append(", ");
    }
    if (vertices > 0)
      builder.setLength(builder.length() - 2);
    builder.append('}');
    return builder.toString();
  }

  /** Utility function that crops against a single axis-aligned plane. */
  private static <V extends Vector> void crop(int dimension, V plane, V normal, LinkedList<V> input,
      LinkedList<V> output) {
    V previous = input.getLast();
    for (V current : input) {
      float begin = previous.value(dimension);
      float end = current.value(dimension);
      boolean below = normal.value(dimension) >= 0f;
      boolean beginIn = below ? begin >= plane.value(dimension) : begin < plane.value(dimension);
      boolean endIn = below ? end >= plane.value(dimension) : end < plane.value(dimension);
      if (beginIn && endIn)
        push(output, current);
      else if (beginIn)
        push(output, intersection(plane, normal, previous, current));
      else if (endIn) {
        push(output, intersection(plane, normal, previous, current));
        push(output, current);
      }
      previous = current;
    }
    if (output.size() > 1 && output.getFirst().equals(output.getLast()))
      output.removeFirst();
    input.clear();
  }

  /** The intersection method. */
  private static <V extends Vector> V intersection(V plane, V normal, V begin, V end) {
    Vector direction = end.subtract(begin);
    @SuppressWarnings("unchecked")
    V result = (V) begin.add(direction.multiply(normal.dot(plane.subtract(begin)) / normal.dot(direction)));
    return result;
  }

  /** The intersection method. */
  private static <V extends Vector> void push(LinkedList<V> list, V value) {
    if (list.isEmpty() || !list.getLast().equals(value))
      list.add(value);
  }

  /**
   * Implementation of a two-dimensional polygon.
   * 
   * @author Lonnie Pryor III (lonnie@pryor.us.com)
   */
  public static final class _2D extends Polygon implements Iterable<Vector._2D> {

    /** The vertices that define the shape of this polygon. */
    private final Vector._2D[] vertices;

    /** Creates a new two-dimensional polygon. */
    public _2D(Vector._2D... vertices) {
      this(true, vertices);
    }

    /** Creates a new two-dimensional polygon. */
    private _2D(boolean clone, Vector._2D... vertices) {
      assert vertices != null;
      if (vertices.length < 3)
        throw new IllegalArgumentException("vertices");
      this.vertices = clone ? vertices.clone() : vertices;
    }

    /* @see Polygon#vertices() */
    @Override
    public int vertices() {
      return vertices.length;
    }

    /* @see Polygon#vertex(int) */
    @Override
    public Vector._2D vertex(int index) {
      return vertices[index];
    }

    /* @see Polygon#bounds() */
    @Override
    public Box._2D bounds() {
      float min0 = Float.MAX_VALUE, max0 = -Float.MAX_VALUE;
      float min1 = Float.MAX_VALUE, max1 = -Float.MAX_VALUE;
      for (int i = 0; i < vertices.length; ++i) {
        Vector._2D v = vertex(i);
        float v0 = v.value(0), v1 = v.value(1);
        min0 = Math.min(min0, v0);
        min1 = Math.min(min1, v1);
        max0 = Math.max(max0, v0);
        max1 = Math.max(max1, v1);
      }
      return Box.create(Vector.create(min0, min1), Vector.create(max0, max1));
    }

    /* @see Polygon#edge(int) */
    @Override
    public Line._2D edge(int index) {
      int next = index + 1;
      return Line.create(vertex(index), next == vertices.length ? vertex(0) : vertex(next));
    }

    /* @see Polygon#crop(Box) */
    public Polygon._2D crop(Box bounds) {
      assert bounds != null;
      LinkedList<Vector._2D> a = new LinkedList<Vector._2D>(), b = new LinkedList<Vector._2D>();
      for (Vector._2D vertex : vertices)
        a.add(vertex);
      Vector lower = bounds.lower(), upper = bounds.upper();
      Polygon.crop(0, Vector.create(lower.value(0), 0f), Vector.create(1f, 0f), a, b);
      if (b.size() < 3)
        return null;
      Polygon.crop(0, Vector.create(upper.value(0), 0f), Vector.create(-1f, 0f), b, a);
      if (a.size() < 3)
        return null;
      Polygon.crop(1, Vector.create(0f, lower.value(1)), Vector.create(0f, 1f), a, b);
      if (b.size() < 3)
        return null;
      Polygon.crop(1, Vector.create(0f, upper.value(1)), Vector.create(0f, -1f), b, a);
      if (a.size() < 3)
        return null;
      return new Polygon._2D(false, a.toArray(new Vector._2D[a.size()]));
    }

    /* @see Polygon#iterate() */
    @Override
    public Iterator<? extends Vector> iterate() {
      return iterator();
    }

    /* @see Iterable#iterator() */
    @Override
    public Iterator<Vector._2D> iterator() {
      return Collections.unmodifiableList(Arrays.asList(vertices)).iterator();
    }

  }

  /**
   * Implementation of a three-dimensional polygon.
   * 
   * @author Lonnie Pryor III (lonnie@pryor.us.com)
   */
  public static final class _3D extends Polygon implements Iterable<Vector._3D> {

    /** The vertices that define the shape of this polygon. */
    private final Vector._3D[] vertices;

    /** Creates a new three-dimensional polygon. */
    public _3D(Vector._3D... vertices) {
      this(true, vertices);
    }

    /** Creates a new three-dimensional polygon. */
    private _3D(boolean clone, Vector._3D... vertices) {
      assert vertices != null;
      if (vertices.length < 3)
        throw new IllegalArgumentException("vertices");
      this.vertices = clone ? vertices.clone() : vertices;
    }

    /* @see Polygon#vertices() */
    @Override
    public int vertices() {
      return vertices.length;
    }

    /* @see Polygon#vertex(int) */
    @Override
    public Vector._3D vertex(int index) {
      return vertices[index];
    }

    /* @see Polygon#bounds() */
    @Override
    public Box._3D bounds() {
      float min0 = Float.MAX_VALUE, max0 = -Float.MAX_VALUE;
      float min1 = Float.MAX_VALUE, max1 = -Float.MAX_VALUE;
      float min2 = Float.MAX_VALUE, max2 = -Float.MAX_VALUE;
      for (int i = 0; i < vertices.length; ++i) {
        Vector._3D v = vertex(i);
        float v0 = v.value(0), v1 = v.value(1), v2 = v.value(2);
        min0 = Math.min(min0, v0);
        min1 = Math.min(min1, v1);
        min2 = Math.min(min2, v2);
        max0 = Math.max(max0, v0);
        max1 = Math.max(max1, v1);
        max2 = Math.max(max2, v2);
      }
      return Box.create(Vector.create(min0, min1, min2), Vector.create(max0, max1, max2));
    }

    /* @see Polygon#edge(int) */
    @Override
    public Line._3D edge(int index) {
      int next = index + 1;
      return Line.create(vertex(index), next == vertices.length ? vertex(0) : vertex(next));
    }

    /* @see Polygon#crop(Box) */
    public Polygon._3D crop(Box bounds) {
      assert bounds != null;
      LinkedList<Vector._3D> a = new LinkedList<Vector._3D>(), b = new LinkedList<Vector._3D>();
      for (Vector._3D vertex : vertices)
        a.add(vertex);
      Vector lower = bounds.lower(), upper = bounds.upper();
      Polygon.crop(0, Vector.create(lower.value(0), 0f, 0f), Vector.create(1f, 0f, 0f), a, b);
      if (b.size() < 3)
        return null;
      Polygon.crop(0, Vector.create(upper.value(0), 0f, 0f), Vector.create(-1f, 0f, 0f), b, a);
      if (a.size() < 3)
        return null;
      Polygon.crop(1, Vector.create(0f, lower.value(1), 0f), Vector.create(0f, 1f, 0f), a, b);
      if (b.size() < 3)
        return null;
      Polygon.crop(1, Vector.create(0f, upper.value(1), 0f), Vector.create(0f, -1f, 0f), b, a);
      if (a.size() < 3)
        return null;
      Polygon.crop(2, Vector.create(0f, 0f, lower.value(2)), Vector.create(0f, 0f, 1f), a, b);
      if (b.size() < 3)
        return null;
      Polygon.crop(2, Vector.create(0f, 0f, upper.value(2)), Vector.create(0f, 0f, -1f), b, a);
      if (a.size() < 3)
        return null;
      return new Polygon._3D(false, a.toArray(new Vector._3D[a.size()]));
    }

    /* @see Polygon#iterate() */
    @Override
    public Iterator<? extends Vector> iterate() {
      return iterator();
    }

    /* @see Iterable#iterator() */
    @Override
    public Iterator<Vector._3D> iterator() {
      return Collections.unmodifiableList(Arrays.asList(vertices)).iterator();
    }

  }

  /**
   * Implementation of a four-dimensional polygon.
   * 
   * @author Lonnie Pryor III (lonnie@pryor.us.com)
   */
  public static final class _4D extends Polygon implements Iterable<Vector._4D> {

    /** The vertices that define the shape of this polygon. */
    private final Vector._4D[] vertices;

    /** Creates a new four-dimensional polygon. */
    public _4D(Vector._4D... vertices) {
      this(true, vertices);
    }

    /** Creates a new four-dimensional polygon. */
    private _4D(boolean clone, Vector._4D... vertices) {
      assert vertices != null;
      if (vertices.length < 3)
        throw new IllegalArgumentException("vertices");
      this.vertices = clone ? vertices.clone() : vertices;
    }

    /* @see Polygon#vertices() */
    @Override
    public int vertices() {
      return vertices.length;
    }

    /* @see Polygon#vertex(int) */
    @Override
    public Vector._4D vertex(int index) {
      return vertices[index];
    }

    /* @see Polygon#bounds() */
    @Override
    public Box._4D bounds() {
      float min0 = Float.MAX_VALUE, max0 = -Float.MAX_VALUE;
      float min1 = Float.MAX_VALUE, max1 = -Float.MAX_VALUE;
      float min2 = Float.MAX_VALUE, max2 = -Float.MAX_VALUE;
      float min3 = Float.MAX_VALUE, max3 = -Float.MAX_VALUE;
      for (int i = 0; i < vertices.length; ++i) {
        Vector._4D v = vertex(i);
        float v0 = v.value(0), v1 = v.value(1), v2 = v.value(2), v3 = v.value(3);
        min0 = Math.min(min0, v0);
        min1 = Math.min(min1, v1);
        min2 = Math.min(min2, v2);
        min3 = Math.min(min3, v3);
        max0 = Math.max(max0, v0);
        max1 = Math.max(max1, v1);
        max2 = Math.max(max2, v2);
        max3 = Math.max(max3, v3);
      }
      return Box.create(Vector.create(min0, min1, min2, min3), Vector.create(max0, max1, max2, max3));
    }

    /* @see Polygon#edge(int) */
    @Override
    public Line._4D edge(int index) {
      int next = index + 1;
      return Line.create(vertex(index), next == vertices.length ? vertex(0) : vertex(next));
    }

    /* @see Polygon#crop(Box) */
    public Polygon._4D crop(Box bounds) {
      assert bounds != null;
      LinkedList<Vector._4D> a = new LinkedList<Vector._4D>(), b = new LinkedList<Vector._4D>();
      for (Vector._4D vertex : vertices)
        a.add(vertex);
      Vector lower = bounds.lower(), upper = bounds.upper();
      Polygon.crop(0, Vector.create(lower.value(0), 0f, 0f, 0f), Vector.create(1f, 0f, 0f, 0f), a, b);
      if (b.size() < 3)
        return null;
      Polygon.crop(0, Vector.create(upper.value(0), 0f, 0f, 0f), Vector.create(-1f, 0f, 0f, 0f), b, a);
      if (a.size() < 3)
        return null;
      Polygon.crop(1, Vector.create(0f, lower.value(1), 0f, 0f), Vector.create(0f, 1f, 0f, 0f), a, b);
      if (b.size() < 3)
        return null;
      Polygon.crop(1, Vector.create(0f, upper.value(1), 0f, 0f), Vector.create(0f, -1f, 0f, 0f), b, a);
      if (a.size() < 3)
        return null;
      Polygon.crop(2, Vector.create(0f, 0f, lower.value(2), 0f), Vector.create(0f, 0f, 1f, 0f), a, b);
      if (b.size() < 3)
        return null;
      Polygon.crop(2, Vector.create(0f, 0f, upper.value(2), 0f), Vector.create(0f, 0f, -1f, 0f), b, a);
      if (a.size() < 3)
        return null;
      Polygon.crop(3, Vector.create(0f, 0f, 0f, lower.value(3)), Vector.create(0f, 0f, 0f, 1f), a, b);
      if (b.size() < 3)
        return null;
      Polygon.crop(3, Vector.create(0f, 0f, 0f, upper.value(3)), Vector.create(0f, 0f, 0f, -1f), b, a);
      if (a.size() < 3)
        return null;
      return new Polygon._4D(false, a.toArray(new Vector._4D[a.size()]));
    }

    /* @see Polygon#iterate() */
    @Override
    public Iterator<? extends Vector> iterate() {
      return iterator();
    }

    /* @see Iterable#iterator() */
    @Override
    public Iterator<Vector._4D> iterator() {
      return Collections.unmodifiableList(Arrays.asList(vertices)).iterator();
    }

  }

}
