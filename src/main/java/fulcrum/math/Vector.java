/*
 * Vector.java
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
 * Representation of a multidimensional value.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
public abstract class Vector implements Cloneable, Comparable<Vector> {

  /** Creates a new two-dimensional vector with the specified components. */
  public static _2D create(float value0, float value1) {
    return new _2D(value0, value1);
  }

  /** Creates a new three-dimensional vector with the specified components. */
  public static _3D create(float value0, float value1, float value2) {
    return new _3D(value0, value1, value2);
  }

  /** Creates a new four-dimensional vector with the specified components. */
  public static _4D create(float value0, float value1, float value2, float value3) {
    return new _4D(value0, value1, value2, value3);
  }

  /** Creates a new vector. */
  private Vector() {}

  /** Returns the number of dimensions in this vector. */
  public abstract int dimensions();

  /** Returns the distance of this vector from the origin. */
  public final float length() {
    float result = 0f;
    int dimensions = dimensions();
    for (int i = 0; i < dimensions; ++i) {
      float tmp = value(i);
      result += tmp * tmp;
    }
    return Numbers.sqrt(result);
  }

  /** Returns the value at the specified dimension. */
  public abstract float value(int dimension);

  /** Returns the angle between this and another vector. */
  public final float angle(Vector that) {
    assert that != null;
    float result = 0f;
    float denominator = length() * that.length();
    if (denominator != 0f)
      result = dot(that) / denominator;
    if (result >= 1f)
      return 0f;
    if (result <= -1f)
      return Numbers.PI;
    return Numbers.acos(result);
  }

  /** Returns the distance to another vector. */
  public final float distance(Vector that) {
    assert that != null;
    float result = 0f;
    int dimensions = dimensions();
    for (int i = 0; i < dimensions; ++i) {
      float tmp = value(i) - that.value(i);
      result += tmp * tmp;
    }
    return Numbers.sqrt(result);
  }

  /** Calculates the dot product with another vector. */
  public final float dot(Vector that) {
    assert that != null;
    float result = 0;
    int dimensions = dimensions();
    for (int i = 0; i < dimensions; ++i)
      result += value(i) * that.value(i);
    return result;
  }

  /** Creates a vector of the same direction with length one. */
  public abstract Vector normalize();

  /** Creates a vector by removing dimensions from this vector. */
  public abstract Vector reduce();

  /** Creates a vector by adding dimensions to this vector. */
  public abstract Vector extend(float component);

  /** Adds the supplied scalar value to each component of this vector. */
  public abstract Vector add(float scalar);

  /** Subtracts the supplied scalar value from each component of this vector. */
  public abstract Vector subtract(float scalar);

  /** Multiplies each component of this vector with the supplied scalar value. */
  public abstract Vector multiply(float scalar);

  /** Divides each component of this vector by the supplied scalar value. */
  public abstract Vector divide(float scalar);

  /**
   * Adds each component of this vector to the corresponding component of that
   * vector.
   */
  public abstract Vector add(Vector that);

  /**
   * Subtracts each component of this vector from the corresponding component of
   * that vector.
   */
  public abstract Vector subtract(Vector that);

  /**
   * Multiplies each component of this vector with the corresponding component
   * of that vector.
   */
  public abstract Vector multiply(Vector that);

  /**
   * Divides each component of this vector by the corresponding component of
   * that vector.
   */
  public abstract Vector divide(Vector that);

  /**
   * Returns true if each component of this vector is equal to the specified
   * scalar.
   */
  public final boolean equalTo(float scalar) {
    int dimensions = dimensions();
    for (int i = 0; i < dimensions; ++i)
      if (value(i) != scalar)
        return false;
    return true;
  }

  /**
   * Returns true if each component of this vector is approximately equal to the
   * specified scalar, using the supplied delta to compute the approximation.
   */
  public final boolean equalTo(float scalar, float delta) {
    assert delta >= 0f;
    int dimensions = dimensions();
    for (int i = 0; i < dimensions; ++i)
      if (Math.abs(value(i) - scalar) > delta)
        return false;
    return true;
  }

  /**
   * Returns true if each component of this vector is less than the specified
   * scalar.
   */
  public final boolean lessThan(float scalar) {
    int dimensions = dimensions();
    for (int i = 0; i < dimensions; ++i)
      if (value(i) >= scalar)
        return false;
    return true;
  }

  /**
   * Returns true if each component of this vector is less than or equal to the
   * specified scalar.
   */
  public final boolean lessThanOrEqualTo(float scalar) {
    int dimensions = dimensions();
    for (int i = 0; i < dimensions; ++i)
      if (value(i) > scalar)
        return false;
    return true;
  }

  /**
   * Returns true if each component of this vector is greater than the specified
   * scalar.
   */
  public final boolean greaterThan(float scalar) {
    int dimensions = dimensions();
    for (int i = 0; i < dimensions; ++i)
      if (value(i) <= scalar)
        return false;
    return true;
  }

  /**
   * Returns true if each component of this vector is greater than or equal to
   * the specified scalar.
   */
  public final boolean greaterThanOrEqualTo(float scalar) {
    int dimensions = dimensions();
    for (int i = 0; i < dimensions; ++i)
      if (value(i) < scalar)
        return false;
    return true;
  }

  /**
   * Returns true if each component of this vector is equal to the corresponding
   * component of that vector.
   */
  public final boolean equalTo(Vector that) {
    assert that != null;
    int dimensions = dimensions();
    for (int i = 0; i < dimensions; ++i)
      if (value(i) != that.value(i))
        return false;
    return true;
  }

  /**
   * Returns true if each component of this vector is approximately equal to the
   * corresponding component of that vector, using the supplied delta to compute
   * the approximation.
   */
  public final boolean equalTo(Vector that, float delta) {
    assert that != null;
    assert delta >= 0f;
    int dimensions = dimensions();
    for (int i = 0; i < dimensions; ++i)
      if (value(i) != that.value(i) && Math.abs(value(i) - that.value(i)) > delta)
        return false;
    return true;
  }

  /**
   * Returns true if each component of this vector is less than the
   * corresponding component of that vector.
   */
  public final boolean lessThan(Vector that) {
    assert that != null;
    int dimensions = dimensions();
    for (int i = 0; i < dimensions; ++i)
      if (value(i) >= that.value(i))
        return false;
    return true;
  }

  /**
   * Returns true if each component of this vector is less than or equal to the
   * corresponding component of that vector.
   */
  public final boolean lessThanOrEqualTo(Vector that) {
    assert that != null;
    int dimensions = dimensions();
    for (int i = 0; i < dimensions; ++i)
      if (value(i) > that.value(i))
        return false;
    return true;
  }

  /**
   * Returns true if each component of this vector is greater than the
   * corresponding component of that vector.
   */
  public final boolean greaterThan(Vector that) {
    assert that != null;
    int dimensions = dimensions();
    for (int i = 0; i < dimensions; ++i)
      if (value(i) <= that.value(i))
        return false;
    return true;
  }

  /**
   * Returns true if each component of this vector is greater than or equal to
   * the corresponding component of that vector.
   */
  public final boolean greaterThanOrEqualTo(Vector that) {
    assert that != null;
    int dimensions = dimensions();
    for (int i = 0; i < dimensions; ++i)
      if (value(i) < that.value(i))
        return false;
    return true;
  }

  /** Returns a 2D version of this vector. */
  public abstract _2D to2D();

  /** Returns a 3D version of this vector. */
  public abstract _3D to3D();

  /** Returns a 4D version of this vector. */
  public abstract _4D to4D();

  /* @see Comparable#compareTo(Object) */
  @Override
  public final int compareTo(Vector that) {
    int dimensions = dimensions();
    if (dimensions < that.dimensions())
      return -1;
    if (dimensions > that.dimensions())
      return 1;
    for (int i = dimensions - 1; i >= 0; --i) {
      float thisValue = value(i), thatValue = that.value(i);
      if (thisValue < thatValue)
        return -1;
      if (thisValue > thatValue)
        return 1;
    }
    return 0;
  }

  /* @see Object#hashCode() */
  @Override
  public final int hashCode() {
    int dimensions = dimensions(), hash = getClass().hashCode();
    for (int i = 0; i < dimensions; ++i)
      hash ^= Float.floatToIntBits(value(i));
    return hash;
  }

  /* @see Object#equals(Object) */
  @Override
  public final boolean equals(Object obj) {
    if (obj == this)
      return true;
    if (obj == null || !obj.getClass().equals(getClass()))
      return false;
    return equalTo((Vector) obj);
  }

  /* @see Object#toString() */
  @Override
  public final String toString() {
    int dimensions = dimensions();
    StringBuilder builder = new StringBuilder(dimensions * 10);
    builder.append("(");
    for (int i = 0; i < dimensions; ++i)
      builder.append(value(i)).append(", ");
    if (builder.length() > 1)
      builder.setLength(builder.length() - 2);
    builder.append(")");
    return builder.toString();
  }

  /**
   * Implementation of a two-dimensional vector.
   * 
   * @author Lonnie Pryor III (lonnie@pryor.us.com)
   */
  public static final class _2D extends Vector {

    /** The component at index {@code 0}. */
    private final float value0;
    /** The component at index {@code 1}. */
    private final float value1;

    /** Creates a new two-dimensional vector. */
    public _2D(float value0, float value1) {
      this.value0 = value0;
      this.value1 = value1;
    }

    /* @see Vector#dimensions() */
    @Override
    public int dimensions() {
      return 2;
    }

    /* @see Vector#value(int) */
    @Override
    public float value(int dimension) {
      switch (dimension) {
      case 0:
        return value0;
      case 1:
        return value1;
      default:
        throw new IndexOutOfBoundsException(String.valueOf(dimension));
      }
    }

    /* @see Vector#normalize() */
    @Override
    public _2D normalize() {
      float length = length();
      if (length == 0f || length == 1f)
        return this;
      return divide(length);
    }

    /* @see Vector#reduce() */
    @Override
    public Vector reduce() {
      throw new IllegalStateException("Cannot reduce a two-dimensional vector.");
    }

    /* @see Vector#extend(float) */
    @Override
    public _3D extend(float component) {
      return create(value0, value1, component);
    }

    /* @see Vector#add(float) */
    @Override
    public _2D add(float scalar) {
      return create(value0 + scalar, value1 + scalar);
    }

    /* @see Vector#subtract(float) */
    @Override
    public _2D subtract(float scalar) {
      return create(value0 - scalar, value1 - scalar);
    }

    /* @see Vector#multiply(float) */
    @Override
    public _2D multiply(float scalar) {
      return create(value0 * scalar, value1 * scalar);
    }

    /* @see Vector#divide(float) */
    @Override
    public _2D divide(float scalar) {
      return create(value0 / scalar, value1 / scalar);
    }

    /* @see Vector#add(Vector) */
    @Override
    public _2D add(Vector that) {
      return create(value0 + that.value(0), value1 + that.value(1));
    }

    /* @see Vector#subtract(Vector) */
    @Override
    public _2D subtract(Vector that) {
      return create(value0 - that.value(0), value1 - that.value(1));
    }

    /* @see Vector#multiply(Vector) */
    @Override
    public _2D multiply(Vector that) {
      return create(value0 * that.value(0), value1 * that.value(1));
    }

    /* @see Vector#divide(Vector) */
    @Override
    public _2D divide(Vector that) {
      return create(value0 / that.value(0), value1 / that.value(1));
    }

    /* @see Vector#to2D() */
    public _2D to2D() {
      return this;
    }

    /* @see Vector#to3D() */
    public _3D to3D() {
      return create(value0, value1, 0f);
    }

    /* @see Vector#to4D() */
    public _4D to4D() {
      return create(value0, value1, 0f, 0f);
    }

  }

  /**
   * Implementation of a three-dimensional vector.
   * 
   * @author Lonnie Pryor III (lonnie@pryor.us.com)
   */
  public static final class _3D extends Vector {

    /** The component at index {@code 0}. */
    private final float value0;
    /** The component at index {@code 1}. */
    private final float value1;
    /** The component at index {@code 2}. */
    private final float value2;

    /** Creates a new three-dimensional vector. */
    public _3D(float value0, float value1, float value2) {
      this.value0 = value0;
      this.value1 = value1;
      this.value2 = value2;
    }

    /* @see Vector#dimensions() */
    @Override
    public int dimensions() {
      return 3;
    }

    /* @see Vector#value(int) */
    @Override
    public float value(int dimension) {
      switch (dimension) {
      case 0:
        return value0;
      case 1:
        return value1;
      case 2:
        return value2;
      default:
        throw new IndexOutOfBoundsException(String.valueOf(dimension));
      }
    }

    /* @see Vector#normalize() */
    @Override
    public _3D normalize() {
      float length = length();
      if (length == 0f || length == 1f)
        return this;
      return divide(length);
    }

    /* @see Vector#reduce() */
    @Override
    public _2D reduce() {
      return create(value0, value1);
    }

    /* @see Vector#extend(float) */
    @Override
    public _4D extend(float component) {
      return create(value0, value1, value2, component);
    }

    /* @see Vector#add(float) */
    @Override
    public _3D add(float scalar) {
      return create(value0 + scalar, value1 + scalar, value2 + scalar);
    }

    /* @see Vector#subtract(float) */
    @Override
    public _3D subtract(float scalar) {
      return create(value0 - scalar, value1 - scalar, value2 - scalar);
    }

    /* @see Vector#multiply(float) */
    @Override
    public _3D multiply(float scalar) {
      return create(value0 * scalar, value1 * scalar, value2 * scalar);
    }

    /* @see Vector#divide(float) */
    @Override
    public _3D divide(float scalar) {
      assert scalar != 0f;
      return create(value0 / scalar, value1 / scalar, value2 / scalar);
    }

    /* @see Vector#add(Vector) */
    @Override
    public _3D add(Vector that) {
      return create(value0 + that.value(0), value1 + that.value(1), value2 + that.value(2));
    }

    /* @see Vector#subtract(Vector) */
    @Override
    public _3D subtract(Vector that) {
      return create(value0 - that.value(0), value1 - that.value(1), value2 - that.value(2));
    }

    /* @see Vector#multiply(Vector) */
    @Override
    public _3D multiply(Vector that) {
      return create(value0 * that.value(0), value1 * that.value(1), value2 * that.value(2));
    }

    /* @see Vector#divide(Vector) */
    @Override
    public _3D divide(Vector that) {
      assert that.value(0) != 0f;
      assert that.value(1) != 0f;
      assert that.value(2) != 0f;
      return create(value0 / that.value(0), value1 / that.value(1), value2 / that.value(2));
    }

    /* @see Vector#to2D() */
    public _2D to2D() {
      return create(value0, value1);
    }

    /* @see Vector#to3D() */
    public _3D to3D() {
      return this;
    }

    /* @see Vector#to4D() */
    public _4D to4D() {
      return create(value0, value1, value2, 0f);
    }

  }

  /**
   * Implementation of a four-dimensional vector.
   * 
   * @author Lonnie Pryor III (lonnie@pryor.us.com)
   */
  public static final class _4D extends Vector {

    /** The component at index {@code 0}. */
    private final float value0;
    /** The component at index {@code 1}. */
    private final float value1;
    /** The component at index {@code 2}. */
    private final float value2;
    /** The component at index {@code 3}. */
    private final float value3;

    /** Creates a new four-dimensional vector. */
    public _4D(float value0, float value1, float value2, float value3) {
      this.value0 = value0;
      this.value1 = value1;
      this.value2 = value2;
      this.value3 = value3;
    }

    /* @see Vector#dimensions() */
    @Override
    public int dimensions() {
      return 4;
    }

    /* @see Vector#value(int) */
    @Override
    public float value(int dimension) {
      switch (dimension) {
      case 0:
        return value0;
      case 1:
        return value1;
      case 2:
        return value2;
      case 3:
        return value3;
      default:
        throw new IndexOutOfBoundsException(String.valueOf(dimension));
      }
    }

    /* @see Vector#normalize() */
    @Override
    public _4D normalize() {
      float length = length();
      if (length == 0f || length == 1f)
        return this;
      return divide(length);
    }

    /* @see Vector#reduce() */
    @Override
    public _3D reduce() {
      return create(value0, value1, value2);
    }

    /* @see Vector#extend(float) */
    @Override
    public Vector extend(float component) {
      throw new IllegalStateException("Cannot extend a four-dimensional vector.");
    }

    /* @see Vector#add(float) */
    @Override
    public _4D add(float scalar) {
      return create(value0 + scalar, value1 + scalar, value2 + scalar, value3 + scalar);
    }

    /* @see Vector#subtract(float) */
    @Override
    public _4D subtract(float scalar) {
      return create(value0 - scalar, value1 - scalar, value2 - scalar, value3 - scalar);
    }

    /* @see Vector#multiply(float) */
    @Override
    public _4D multiply(float scalar) {
      return create(value0 * scalar, value1 * scalar, value2 * scalar, value3 * scalar);
    }

    /* @see Vector#divide(float) */
    @Override
    public _4D divide(float scalar) {
      assert scalar != 0f;
      return create(value0 / scalar, value1 / scalar, value2 / scalar, value3 / scalar);
    }

    /* @see Vector#add(Vector) */
    @Override
    public _4D add(Vector that) {
      return create(value0 + that.value(0), value1 + that.value(1), value2 + that.value(2), value3 + that.value(3));
    }

    /* @see Vector#subtract(Vector) */
    @Override
    public _4D subtract(Vector that) {
      return create(value0 - that.value(0), value1 - that.value(1), value2 - that.value(2), value3 - that.value(3));
    }

    /* @see Vector#multiply(Vector) */
    @Override
    public _4D multiply(Vector that) {
      return create(value0 * that.value(0), value1 * that.value(1), value2 * that.value(2), value3 * that.value(3));
    }

    /* @see Vector#divide(Vector) */
    @Override
    public _4D divide(Vector that) {
      assert that.value(0) != 0f;
      assert that.value(1) != 0f;
      assert that.value(2) != 0f;
      assert that.value(3) != 0f;
      return create(value0 / that.value(0), value1 / that.value(1), value2 / that.value(2), value3 / that.value(3));
    }

    /* @see Vector#to2D() */
    public _2D to2D() {
      return create(value0, value1);
    }

    /* @see Vector#to3D() */
    public _3D to3D() {
      return create(value0, value1, value2);
    }

    /* @see Vector#to4D() */
    public _4D to4D() {
      return this;
    }

  }

}
