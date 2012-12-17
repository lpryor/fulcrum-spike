/*
 * Delaunay.java
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

import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.google.j2objc.annotations.AutoreleasePool;

import fulcrum.text.Strings;

/**
 * Representation of a two-dimensional Delaunay triangulation.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
public final class Delaunay extends AbstractSet<Triangle._2D> {

  /** Index of the x-coordinate in 2D. */
  private static final int X = 0;
  /** Index of the y-coordinate in 2D. */
  private static final int Y = 1;

  /** Creates a new Delaunay triangulation. */
  public static Delaunay create(Vector._2D... sites) {
    assert sites != null;
    return create(Arrays.asList(sites));
  }

  /** Creates a new Delaunay triangulation. */
  public static Delaunay create(Iterable<Vector._2D> sites) {
    assert sites != null;
    return create(Fortune.create(sites));
  }

  /** Creates a new Delaunay triangulation. */
  public static Delaunay create(Fortune fortune) {
    assert fortune != null;
    HashSet<Triangle._2D> entries = new HashSet<Triangle._2D>(fortune.triangles().size());
    apply(fortune, entries);
    return new Delaunay(Collections.unmodifiableSet(entries));
  }

  /** Extracts the Delaunay triangles and records them in the supplied set. */
  @AutoreleasePool
  private static void apply(Fortune fortune, HashSet<Triangle._2D> entries) {
    for (Triangle._2D triangle : fortune.triangles()) {
      float bx = triangle.begin().value(X), mx = triangle.middle().value(X), ex = triangle.end().value(X);
      float by = triangle.begin().value(Y), my = triangle.middle().value(Y), ey = triangle.end().value(Y);
      float sum = (mx - bx) * (my + by) + (ex - mx) * (ey + my) + (bx - ex) * (by + ey);
      if (sum < 0f)
        entries.add(triangle);
      else
        entries.add(Triangle.create(triangle.end(), triangle.middle(), triangle.begin()));
    }
  }

  /** The entries in this triangulation. */
  private final Set<Triangle._2D> entries;

  /** Creates a new Delaunay triangulation. */
  private Delaunay(Set<Triangle._2D> entries) {
    this.entries = entries;
  }

  /* @see AbstractCollection#isEmpty() */
  @Override
  public boolean isEmpty() {
    return entries.isEmpty();
  }

  /* @see AbstractCollection#size() */
  @Override
  public int size() {
    return entries.size();
  }

  /* @see AbstractCollection#contains(Object) */
  @Override
  public boolean contains(Object o) {
    return entries.contains(o);
  }

  /* @see AbstractCollection#containsAll(Collection) */
  @Override
  public boolean containsAll(Collection<?> c) {
    return entries.containsAll(c);
  }

  /* @see AbstractCollection#iterator() */
  @Override
  public Iterator<Triangle._2D> iterator() {
    return entries.iterator();
  }

  /* @see AbstractCollection#toArray() */
  @Override
  public Object[] toArray() {
    return entries.toArray();
  }

  /* @see AbstractCollection#toArray(T[]) */
  @Override
  public <T> T[] toArray(T[] a) {
    return entries.toArray(a);
  }

  /* @see AbstractCollection#toString() */
  @Override
  public String toString() {
    return Strings.format(getClass(), entries);
  }

}