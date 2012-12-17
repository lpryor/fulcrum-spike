/*
 * Voronoi.java
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

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.google.j2objc.annotations.AutoreleasePool;

import fulcrum.text.Strings;

/**
 * Representation of a two-dimensional Voronoi diagram bounded by a box.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
public final class Voronoi extends AbstractMap<Vector._2D, Polygon._2D> {

  /** Index of the x-coordinate in 2D. */
  private static final int X = 0;
  /** Index of the y-coordinate in 2D. */
  private static final int Y = 1;

  /** Creates a new Voronoi diagram. */
  public static Voronoi create(Box._2D bounds, Vector._2D... sites) {
    assert bounds != null;
    assert sites != null;
    return create(bounds, Arrays.asList(sites));
  }

  /** Creates a new Voronoi diagram. */
  public static Voronoi create(Box._2D bounds, Iterable<Vector._2D> sites) {
    assert bounds != null;
    assert sites != null;
    return create(bounds, Fortune.create(sites));
  }

  /** Creates a new Voronoi diagram. */
  public static Voronoi create(Box._2D bounds, Fortune fortune) {
    assert bounds != null;
    assert fortune != null;
    HashMap<Vector._2D, Polygon._2D> entries = new HashMap<Vector._2D, Polygon._2D>(fortune.sites().size());
    apply(bounds, fortune, entries);
    return new Voronoi(Collections.unmodifiableMap(entries));
  }

  /** Constructs the Voronoi cells and records them in the supplied map. */
  @AutoreleasePool
  private static void apply(Box._2D bounds, Fortune fortune, HashMap<Vector._2D, Polygon._2D> entries) {
    // Handle diagrams with no edges.
    if (fortune.edges().isEmpty()) {
      if (!fortune.sites().isEmpty())
        entries.put(
            fortune.sites().iterator().next(),
            Polygon.create(
                //
                Vector.create(bounds.lower().value(X), bounds.upper().value(Y)),
                Vector.create(bounds.lower().value(X), bounds.lower().value(Y)),
                Vector.create(bounds.upper().value(X), bounds.lower().value(Y)),
                Vector.create(bounds.upper().value(X), bounds.upper().value(Y))));
      return;
    }
    // Calculate the bounds of the full diagram that we will later crop.
    float xmin = bounds.lower().value(X), ymin = bounds.lower().value(Y);
    float xmax = bounds.upper().value(X), ymax = bounds.upper().value(Y);
    for (Vector._2D site : fortune.sites()) {
      xmin = Math.min(xmin, site.value(X) - 1f);
      ymin = Math.min(ymin, site.value(Y) - 1f);
      xmax = Math.max(xmax, site.value(X) + 1f);
      ymax = Math.max(ymax, site.value(Y) + 1f);
    }
    Box._2D diagram = Box.create(Vector.create(xmin, ymin), Vector.create(xmax, ymax));
    // Distribute the edges to the cells.
    HashMap<Vector._2D, Cell> cells = new HashMap<Vector._2D, Cell>(fortune.sites().size());
    for (Fortune.Edge edge : fortune.edges()) {
      Cell left = cells.get(edge.left());
      if (left == null)
        cells.put(edge.left(), left = new Cell(edge.left()));
      left.edges().add(edge);
      Cell right = cells.get(edge.right());
      if (right == null)
        cells.put(edge.right(), right = new Cell(edge.right()));
      right.edges().add(edge);
    }
    // Calculate and crop the cell perimeters.
    ArrayList<Vector._2D> vertices = new ArrayList<Vector._2D>();
    for (Cell cell : cells.values()) {
      cell.calculatePerimeter(diagram, vertices);
      Polygon._2D perimeter = Polygon.create(vertices.toArray(new Vector._2D[vertices.size()])).crop(bounds);
      if (perimeter != null)
        entries.put(cell.site(), perimeter);
      vertices.clear();
    }
  }

  /** The entries in this diagram. */
  private final Map<Vector._2D, Polygon._2D> entries;

  /** Creates a new Voronoi diagram. */
  private Voronoi(Map<Vector._2D, Polygon._2D> entries) {
    this.entries = entries;
  }

  /* @see AbstractMap#isEmpty() */
  @Override
  public boolean isEmpty() {
    return entries.isEmpty();
  }

  /* @see AbstractMap#size() */
  @Override
  public int size() {
    return entries.size();
  }

  /* @see AbstractMap#containsKey(Object) */
  @Override
  public boolean containsKey(Object key) {
    return entries.containsKey(key);
  }

  /* @see AbstractMap#containsValue(Object) */
  @Override
  public boolean containsValue(Object value) {
    return entries.containsValue(value);
  }

  /* @see AbstractMap#get(Object) */
  @Override
  public Polygon._2D get(Object key) {
    return entries.get(key);
  }

  /* @see AbstractMap#keySet() */
  @Override
  public Set<Vector._2D> keySet() {
    return entries.keySet();
  }

  /* @see AbstractMap#values() */
  @Override
  public Collection<Polygon._2D> values() {
    return entries.values();
  }

  /* @see AbstractMap#entrySet() */
  @Override
  public Set<Map.Entry<Vector._2D, Polygon._2D>> entrySet() {
    return entries.entrySet();
  }

  /* @see AbstractMap#toString() */
  @Override
  public String toString() {
    return Strings.format(getClass(), entrySet());
  }

  /**
   * A utility object used for calculating the cell perimeters.
   * 
   * @author Lonnie Pryor III (lonnie@pryor.us.com)
   */
  private static final class Cell implements Comparator<Fortune.Edge> {

    /** The site in the diagram. */
    private final Vector._2D site;
    /** The set of edges sorted in counter-clockwise order. */
    private final TreeSet<Fortune.Edge> edges = new TreeSet<Fortune.Edge>(this);

    /** Creates a new Voronoi cell. */
    Cell(Vector._2D site) {
      this.site = site;
    }

    /** Returns the site in the diagram. */
    Vector._2D site() {
      return site;
    }

    /** Returns the set of edges sorted in counter-clockwise order. */
    TreeSet<Fortune.Edge> edges() {
      return edges;
    }

    /**
     * Calculates the polygon that is the intersection of this cell and the
     * specified bounds.
     */
    @AutoreleasePool
    void calculatePerimeter(Box._2D box, ArrayList<Vector._2D> vertices) {
      Iterator<Fortune.Edge> iter = edges.iterator();
      for (Fortune.Edge cursor = edges.last(); iter.hasNext();) {
        Fortune.Edge next = iter.next();
        bridge(box, cursor, next, vertices);
        cursor = next;
      }
    }

    /**
     * Generates the vertices between {@code begin} and {@code end} cropped to
     * the diagram box.
     */
    private void bridge(Box._2D box, Fortune.Edge current, Fortune.Edge next, ArrayList<Vector._2D> vertices) {
      // If there is a single point where the edges meet use it and return.
      Vector._2D begin = null;
      switch (current.type()) {
      case SEGMENT:
        if (compare(current.begin(), current.end()) < 0)
          vertices.add(current.end());
        else
          vertices.add(current.begin());
        return;
      case RAY:
        if (compare(current.begin(), current.location()) < 0)
          begin = intersect(box, current.begin(), current.direction());
        else {
          vertices.add(current.begin());
          return;
        }
        break;
      case LINE:
        if (compare(current.location(), current.location().add(current.direction())) < 0)
          begin = intersect(box, current.location(), current.direction());
        else
          begin = intersect(box, current.location(), current.direction().multiply(-1f));
        break;
      }
      // Find the start point of the next edge and trace between the two.
      Vector._2D end = null;
      switch (next.type()) {
      case RAY:
        end = intersect(box, next.begin(), next.direction());
        break;
      default:
        if (compare(next.location(), next.location().add(next.direction())) < 0)
          end = intersect(box, next.location(), next.direction().multiply(-1f));
        else
          end = intersect(box, next.location(), next.direction());
      }
      trace(box, begin, end, vertices);
    }

    /**
     * Returns the intersection point of the specified ray with the supplied
     * box.
     */
    private Vector._2D intersect(Box._2D box, Vector._2D begin, Vector._2D direction) {
      float xmin = box.lower().value(X), xmax = box.upper().value(X);
      float ymin = box.lower().value(Y), ymax = box.upper().value(Y);
      float bx = begin.value(X), by = begin.value(Y);
      float dx = direction.value(X), dy = direction.value(Y);
      if (dx == 0f)
        return Vector.create(bx, dy < 0f ? ymin : ymax);
      if (dy == 0f)
        return Vector.create(dx < 0f ? xmin : xmax, by);
      float x = 0f, y = 0f;
      if (dx < 0f) {
        x = xmin;
        y = by + (dy / dx) * (xmin - bx);
      } else {
        x = xmax;
        y = by + (dy / dx) * (xmax - bx);
      }
      if (y >= ymin && y <= ymax)
        return Vector.create(x, y);
      if (dy < 0f) {
        x = bx + (ymin - by) / (dy / dx);
        y = ymin;
      } else {
        x = bx + (ymax - by) / (dy / dx);
        y = ymax;
      }
      return Vector.create(x, y);
    }

    /** Traces counter-clockwise around a box between two vertices. */
    private void trace(Box._2D box, Vector._2D begin, Vector._2D end, ArrayList<Vector._2D> vertices) {
      float xmin = box.lower().value(X), xmax = box.upper().value(X);
      float ymin = box.lower().value(Y), ymax = box.upper().value(Y);
      float ex = end.value(X), ey = end.value(Y);
      Vector._2D current = begin;
      while (true) {
        vertices.add(current);
        float cx = current.value(X), cy = current.value(Y);
        // On left edge.
        if (cx == xmin) {
          if (ex == xmin)
            break;
          if (cy != ymin) {
            current = box.lower();
            continue;
          }
        }
        // On bottom edge
        if (cy == ymin) {
          if (ey == ymin)
            break;
          if (cx != xmax) {
            current = Vector.create(xmax, ymin);
            continue;
          }
        }
        // On right edge.
        if (cx == xmax) {
          if (ex == xmax)
            break;
          if (cy != ymax) {
            current = box.upper();
            continue;
          }
        }
        // On top edge.
        if (ey == ymax)
          break;
        current = Vector.create(xmin, ymax);
      }
      vertices.add(end);
    }

    /* @see Comparator#compare(Object, Object) */
    @Override
    public int compare(Fortune.Edge left, Fortune.Edge right) {
      float sx = site.value(X), sy = site.value(Y);
      if (left.location().value(X) < sx) {
        if (right.location().value(X) >= sx)
          return -1;
        if (left.location().value(Y) >= sy) {
          if (right.location().value(Y) < sy)
            return -1;
        } else if (right.location().value(Y) >= sy)
          return 1;
      } else if (right.location().value(X) < sx)
        return 1;
      else if (left.location().value(Y) < sy) {
        if (right.left().value(Y) >= sy)
          return -1;
      } else if (right.location().value(Y) < sy)
        return 1;
      return compare(left.location(), right.location());
    }

    /** Compares two points in counter-clockwise order around the site. */
    private int compare(Vector._2D left, Vector._2D right) {
      float sx = site.value(X), lx = left.value(X), rx = right.value(X);
      float sy = site.value(Y), ly = left.value(Y), ry = right.value(Y);
      float sum = (lx - sx) * (ly + sy) + (rx - lx) * (ry + ly) + (sx - rx) * (sy + ry);
      if (sum < 0f)
        return -1;
      if (sum > 0f)
        return 1;
      return 0;
    }

  }

}
