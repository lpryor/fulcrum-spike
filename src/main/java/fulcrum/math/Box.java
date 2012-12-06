/*
 * Box.java
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

/**
 * Representation of a multidimensional, axis-aligned bounding box.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
public abstract class Box implements Cloneable {

  /** Creates a new two-dimensional box with the specified bounds. */
  public static Box._2D create(Vector._2D corner1, Vector._2D corner2) {
    return new Box._2D(corner1, corner2);
  }

  /** Creates a new three-dimensional box with the specified bounds. */
  public static Box._3D create(Vector._3D corner1, Vector._3D corner2) {
    return new Box._3D(corner1, corner2);
  }

  /** Creates a new four-dimensional box with the specified bounds. */
  public static Box._4D create(Vector._4D corner1, Vector._4D corner2) {
    return new Box._4D(corner1, corner2);
  }

  /** Creates a new box. */
  private Box() {}

  /** Returns the number of dimensions in this box. */
  public final int dimensions() {
    return lower().dimensions();
  }

  /** Returns the corner that sits at the lower bound of this box. */
  public abstract Vector lower();

  /** Returns the corner that sits at the upper bound of this box. */
  public abstract Vector upper();

  /** Returns the size of this box. */
  public abstract Vector size();

  /** Returns true if this box contains the specified point. */
  public final boolean contains(Vector point) {
    assert point != null;
    return lower().lessThanOrEqualTo(point) && upper().greaterThanOrEqualTo(point);
  }

  /** Returns true if this box contains that box. */
  public final boolean contains(Box that) {
    assert that != null;
    return lower().lessThanOrEqualTo(that.lower()) && upper().greaterThanOrEqualTo(that.upper());
  }

  /** Returns true if this box intersects that box. */
  public final boolean intersects(Box that) {
    assert that != null;
    int dimensions = dimensions();
    Vector thisLower = lower(), thisUpper = upper();
    Vector thatLower = that.lower(), thatUpper = that.upper();
    for (int i = 0; i < dimensions; ++i)
      if (thatUpper.value(i) < thisLower.value(i) || thatLower.value(i) > thisUpper.value(i))
        return false;
    return true;
  }

  /**
   * Returns true if each bound of this box is equal to the corresponding bound
   * of that box.
   */
  public final boolean equalTo(Box that) {
    assert that != null;
    return lower().equalTo(that.lower()) && upper().equalTo(that.upper());
  }

  /**
   * Returns true if each bound of this box is approximately equal to the
   * corresponding bound of that box, using the supplied delta to compute the
   * approximation.
   */
  public final boolean equalTo(Box that, float delta) {
    assert that != null;
    assert delta >= 0f;
    return lower().equalTo(that.lower(), delta) && upper().equalTo(that.upper(), delta);
  }

  /* @see Object#hashCode() */
  @Override
  public final int hashCode() {
    return getClass().hashCode() ^ lower().hashCode() ^ upper().hashCode();
  }

  /* @see Object#equals(Object) */
  @Override
  public final boolean equals(Object obj) {
    if (obj == this)
      return true;
    if (obj == null || !obj.getClass().equals(getClass()))
      return false;
    return equalTo((Box) obj);
  }

  /* @see Object#toString() */
  @Override
  public final String toString() {
    String l = lower().toString();
    String u = upper().toString();
    StringBuilder builder = new StringBuilder(l.length() + u.length() + 4);
    builder.append('[');
    builder.append(l);
    builder.append(", ");
    builder.append(u);
    builder.append(']');
    return builder.toString();
  }

  /**
   * Implementation of a two-dimensional box.
   * 
   * @author Lonnie Pryor III (lonnie@pryor.us.com)
   */
  public static final class _2D extends Box {

    /** The corner that sits at the lower bound of this box. */
    private final Vector._2D lower;
    /** The corner that sits at the upper bound of this box. */
    private final Vector._2D upper;

    /** Creates a new two-dimensional box. */
    public _2D(Vector._2D corner1, Vector._2D corner2) {
      assert corner1 != null;
      assert corner2 != null;
      if (corner1.lessThanOrEqualTo(corner2)) {
        lower = corner1;
        upper = corner2;
      } else if (corner1.greaterThan(corner2)) {
        lower = corner2;
        upper = corner1;
      } else {
        lower = Vector.create(Math.min(corner1.value(0), corner2.value(0)), //
            Math.min(corner1.value(1), corner2.value(1)));
        upper = Vector.create(Math.max(corner1.value(0), corner2.value(0)), //
            Math.max(corner1.value(1), corner2.value(1)));
      }
    }

    /* @see Box#lower() */
    @Override
    public Vector._2D lower() {
      return lower;
    }

    /* @see Box#upper() */
    @Override
    public Vector._2D upper() {
      return upper;
    }

    /* @see Box#size() */
    @Override
    public Vector._2D size() {
      return upper.subtract(lower);
    }

  }

  /**
   * Implementation of a three-dimensional box.
   * 
   * @author Lonnie Pryor III (lonnie@pryor.us.com)
   */
  public static final class _3D extends Box {

    /** The corner that sits at the lower bound of this box. */
    private final Vector._3D lower;
    /** The corner that sits at the upper bound of this box. */
    private final Vector._3D upper;

    /** Creates a new three-dimensional box. */
    public _3D(Vector._3D corner1, Vector._3D corner2) {
      assert corner1 != null;
      assert corner2 != null;
      if (corner1.lessThanOrEqualTo(corner2)) {
        lower = corner1;
        upper = corner2;
      } else if (corner1.greaterThan(corner2)) {
        lower = corner2;
        upper = corner1;
      } else {
        lower = Vector.create(Math.min(corner1.value(0), corner2.value(0)), //
            Math.min(corner1.value(1), corner2.value(1)), //
            Math.min(corner1.value(2), corner2.value(2)));
        upper = Vector.create(Math.max(corner1.value(0), corner2.value(0)), //
            Math.max(corner1.value(1), corner2.value(1)), //
            Math.max(corner1.value(2), corner2.value(2)));
      }
    }

    /* @see Box#lower() */
    @Override
    public Vector._3D lower() {
      return lower;
    }

    /* @see Box#upper() */
    @Override
    public Vector._3D upper() {
      return upper;
    }

    /* @see Box#size() */
    @Override
    public Vector._3D size() {
      return upper.subtract(lower);
    }

  }

  /**
   * Implementation of a four-dimensional box.
   * 
   * @author Lonnie Pryor III (lonnie@pryor.us.com)
   */
  public static final class _4D extends Box {

    /** The corner that sits at the lower bound of this box. */
    private final Vector._4D lower;
    /** The corner that sits at the upper bound of this box. */
    private final Vector._4D upper;

    /** Creates a new four-dimensional box. */
    public _4D(Vector._4D corner1, Vector._4D corner2) {
      assert corner1 != null;
      assert corner2 != null;
      if (corner1.lessThanOrEqualTo(corner2)) {
        lower = corner1;
        upper = corner2;
      } else if (corner1.greaterThan(corner2)) {
        lower = corner2;
        upper = corner1;
      } else {
        lower = Vector.create(Math.min(corner1.value(0), corner2.value(0)), //
            Math.min(corner1.value(1), corner2.value(1)), //
            Math.min(corner1.value(2), corner2.value(2)), //
            Math.min(corner1.value(3), corner2.value(3)));
        upper = Vector.create(Math.max(corner1.value(0), corner2.value(0)), //
            Math.max(corner1.value(1), corner2.value(1)), //
            Math.max(corner1.value(2), corner2.value(2)), //
            Math.max(corner1.value(3), corner2.value(3)));
      }
    }

    /* @see Box#lower() */
    @Override
    public Vector._4D lower() {
      return lower;
    }

    /* @see Box#upper() */
    @Override
    public Vector._4D upper() {
      return upper;
    }

    /* @see Box#size() */
    @Override
    public Vector._4D size() {
      return upper.subtract(lower);
    }

  }

}
