/*
 * Line.java
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
 * Representation of a multidimensional line.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
public abstract class Line implements Cloneable {

  /** Creates a new two-dimensional line with the specified vertices. */
  public static Line._2D create(Vector._2D begin, Vector._2D end) {
    return new Line._2D(begin, end);
  }

  /** Creates a new three-dimensional line with the specified vertices. */
  public static Line._3D create(Vector._3D begin, Vector._3D end) {
    return new Line._3D(begin, end);
  }

  /** Creates a new four-dimensional line with the specified vertices. */
  public static Line._4D create(Vector._4D begin, Vector._4D end) {
    return new Line._4D(begin, end);
  }

  /** Creates a new line. */
  private Line() {}

  /** Returns the number of dimensions in this line. */
  public final int dimensions() {
    return begin().dimensions();
  }

  /**
   * Returns the index of the specified vertex in the array { {@link #begin()},
   * {@link #end()} .
   */
  public final int indexOf(Vector vertex) {
    assert vertex != null;
    if (begin().equals(vertex))
      return 0;
    if (end().equals(vertex))
      return 1;
    return -1;
  }

  /** Returns the shortest distance from this line to the specified point. */
  public final float distance(Vector point) {
    assert point != null;
    Vector direction = direction();
    Vector begin = begin();
    float denominator = direction.dot(direction);
    if (denominator == 0f)
      return begin.distance(point);
    return point.distance(begin.add(direction.multiply( //
        point.subtract(begin).dot(direction) / denominator)));
  }

  /** Returns the vertex this line begins at. */
  public abstract Vector begin();

  /** Returns the vertex this line ends at. */
  public abstract Vector end();

  /**
   * Returns the vertex at the specified index in the array { {@link #begin()},
   * {@link #end()} .
   */
  public abstract Vector vertex(int index);

  /** Returns a vector that represents the direction of this line. */
  public abstract Vector direction();

  /** Returns the mid-point of this line. */
  public abstract Vector midpoint();

  /** Calculates the bounding box of this line. */
  public abstract Box bounds();

  /** Returns an iterator over the vertices of this line. */
  public abstract Iterator<? extends Vector> iterate();

  /**
   * Returns true if each vertex of this line is equal to the corresponding vertex
   * of that line.
   */
  public final boolean equalTo(Line that) {
    assert that != null;
    return begin().equalTo(that.begin()) && end().equalTo(that.end());
  }

  /**
   * Returns true if each vertex of this line is approximately equal to the
   * corresponding vertex of that line, using the supplied delta to compute the
   * approximation.
   */
  public final boolean equalTo(Line that, float delta) {
    assert that != null;
    assert delta >= 0f;
    return begin().equalTo(that.begin(), delta) && end().equalTo(that.end(), delta);
  }

  /* @see Object#hashCode() */
  @Override
  public final int hashCode() {
    return getClass().hashCode() ^ begin().hashCode() ^ end().hashCode();
  }

  /* @see Object#equals(Object) */
  @Override
  public final boolean equals(Object obj) {
    if (obj == this)
      return true;
    if (obj == null || !obj.getClass().equals(getClass()))
      return false;
    return equalTo((Line) obj);
  }

  /* @see Object#toString() */
  @Override
  public final String toString() {
    String b = begin().toString();
    String e = end().toString();
    StringBuilder builder = new StringBuilder(b.length() + e.length() + 4);
    builder.append('|');
    builder.append(b);
    builder.append(", ");
    builder.append(e);
    builder.append('|');
    return builder.toString();
  }

  /**
   * Implementation of a two-dimensional line.
   * 
   * @author Lonnie Pryor III (lonnie@pryor.us.com)
   */
  public static final class _2D extends Line implements Iterable<Vector._2D> {

    /** The vertex this line begins at. */
    private final Vector._2D begin;
    /** The vertex this line ends at. */
    private final Vector._2D end;

    /** Creates a new two-dimensional line. */
    public _2D(Vector._2D begin, Vector._2D end) {
      assert begin != null;
      assert end != null;
      this.begin = begin;
      this.end = end;
    }

    /* @see Line#begin() */
    @Override
    public Vector._2D begin() {
      return begin;
    }

    /* @see Line#end() */
    @Override
    public Vector._2D end() {
      return end;
    }

    /* @see Line#vertex(int) */
    @Override
    public Vector._2D vertex(int index) {
      switch (index) {
      case 0:
        return begin;
      case 1:
        return end;
      default:
        throw new IndexOutOfBoundsException(String.valueOf(index));
      }
    }

    /* @see Line#direction() */
    @Override
    public Vector._2D direction() {
      return end.subtract(begin);
    }

    /* @see Line#midpoint() */
    @Override
    public Vector._2D midpoint() {
      return begin.add(direction().divide(2f));
    }

    /* @see Line#bounds() */
    @Override
    public Box._2D bounds() {
      return Box.create(begin, end);
    }

    /* @see Line#iterate() */
    @Override
    public Iterator<? extends Vector> iterate() {
      return iterator();
    }

    /* @see Iterable#iterator() */
    @Override
    public Iterator<Vector._2D> iterator() {
      return Collections.unmodifiableList(Arrays.asList(begin, end)).iterator();
    }

  }

  /**
   * Implementation of a three-dimensional line.
   * 
   * @author Lonnie Pryor III (lonnie@pryor.us.com)
   */
  public static final class _3D extends Line implements Iterable<Vector._3D> {

    /** The vertex this line begins at. */
    private final Vector._3D begin;
    /** The vertex this line ends at. */
    private final Vector._3D end;

    /** Creates a new three-dimensional line. */
    public _3D(Vector._3D begin, Vector._3D end) {
      assert begin != null;
      assert end != null;
      this.begin = begin;
      this.end = end;
    }

    /* @see Line#begin() */
    @Override
    public Vector._3D begin() {
      return begin;
    }

    /* @see Line#end() */
    @Override
    public Vector._3D end() {
      return end;
    }

    /* @see Line#vertex(int) */
    @Override
    public Vector._3D vertex(int index) {
      switch (index) {
      case 0:
        return begin;
      case 1:
        return end;
      default:
        throw new IndexOutOfBoundsException(String.valueOf(index));
      }
    }

    /* @see Line#direction() */
    @Override
    public Vector._3D direction() {
      return end.subtract(begin);
    }

    /* @see Line#midpoint() */
    @Override
    public Vector._3D midpoint() {
      return begin.add(direction().divide(2f));
    }

    /* @see Line#bounds() */
    @Override
    public Box._3D bounds() {
      return Box.create(begin, end);
    }

    /* @see Line#iterate() */
    @Override
    public Iterator<? extends Vector> iterate() {
      return iterator();
    }

    /* @see Iterable#iterator() */
    @Override
    public Iterator<Vector._3D> iterator() {
      return Collections.unmodifiableList(Arrays.asList(begin, end)).iterator();
    }

  }

  /**
   * Implementation of a four-dimensional line.
   * 
   * @author Lonnie Pryor III (lonnie@pryor.us.com)
   */
  public static final class _4D extends Line implements Iterable<Vector._4D> {

    /** The vertex this line begins at. */
    private final Vector._4D begin;
    /** The vertex this line ends at. */
    private final Vector._4D end;

    /** Creates a new four-dimensional line. */
    public _4D(Vector._4D begin, Vector._4D end) {
      assert begin != null;
      assert end != null;
      this.begin = begin;
      this.end = end;
    }

    /* @see Line#begin() */
    @Override
    public Vector._4D begin() {
      return begin;
    }

    /* @see Line#end() */
    @Override
    public Vector._4D end() {
      return end;
    }

    /* @see Line#vertex(int) */
    @Override
    public Vector._4D vertex(int index) {
      switch (index) {
      case 0:
        return begin;
      case 1:
        return end;
      default:
        throw new IndexOutOfBoundsException(String.valueOf(index));
      }
    }

    /* @see Line#direction() */
    @Override
    public Vector._4D direction() {
      return end.subtract(begin);
    }

    /* @see Line#midpoint() */
    @Override
    public Vector._4D midpoint() {
      return begin.add(direction().divide(2f));
    }

    /* @see Line#bounds() */
    @Override
    public Box._4D bounds() {
      return Box.create(begin, end);
    }

    /* @see Line#iterate() */
    @Override
    public Iterator<? extends Vector> iterate() {
      return iterator();
    }

    /* @see Iterable#iterator() */
    @Override
    public Iterator<Vector._4D> iterator() {
      return Collections.unmodifiableList(Arrays.asList(begin, end)).iterator();
    }

  }

}
