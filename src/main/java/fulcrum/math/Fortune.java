/*
 * Fortune.java
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Set;

import com.google.j2objc.annotations.AutoreleasePool;
import com.google.j2objc.annotations.Weak;

import fulcrum.text.Strings;

/**
 * An implementation of Fortune's algorithm that generates a bare-bones 2D
 * Voronoi diagram in {@code n log n} time.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 * @see https://en.wikipedia.org/wiki/Fortune's_algorithm
 */
public final class Fortune {

  /** Index of the x-coordinate in 2D. */
  private static final int X = 0;
  /** Index of the y-coordinate in 2D. */
  private static final int Y = 1;

  /**
   * Applies Fortune's algorithm to the specified sites and returns a structure
   * containing the sites and Voronoi edges.
   */
  public static Fortune create(Vector._2D... sites) {
    assert sites != null;
    return create(new HashSet<Vector._2D>(Arrays.asList(sites)));
  }

  /**
   * Applies Fortune's algorithm to the specified sites and returns a structure
   * containing the sites and Voronoi edges.
   */
  public static Fortune create(Iterable<Vector._2D> sites) {
    assert sites != null;
    HashSet<Vector._2D> set = new HashSet<Vector._2D>();
    for (Vector._2D site : sites)
      set.add(site);
    HashSet<Edge> edges = new HashSet<Fortune.Edge>();
    HashSet<Triangle._2D> triangles = new HashSet<Triangle._2D>();
    apply(set, edges, triangles);
    return new Fortune(Collections.unmodifiableSet(set), Collections.unmodifiableSet(edges),
        Collections.unmodifiableSet(triangles));
  }

  /**
   * Applies Fortune's algorithm to the specified sites and populates the
   * supplied set with the Voronoi edges.
   */
  @AutoreleasePool
  private static void apply(HashSet<Vector._2D> sites, HashSet<Edge> edges, HashSet<Triangle._2D> triangles) {
    if (sites.size() < 2)
      return;
    if (sites.size() == 2) {
      Iterator<Vector._2D> iter = sites.iterator();
      Edge edge = new Edge(iter.next(), iter.next());
      edge.finish();
      edges.add(edge);
      return;
    }
    new Algorithm(sites, edges, triangles).apply();
  }

  /** The sites provided as input to the algorithm. */
  private final Set<Vector._2D> sites;
  /** The edges returned as output from the algorithm. */
  private final Set<Edge> edges;
  /** The triangulation discovered by the algorithm. */
  private final Set<Triangle._2D> triangles;

  /** Creates a new implementation of Fortune's algorithm. */
  private Fortune(Set<Vector._2D> sites, Set<Edge> edges, Set<Triangle._2D> triangles) {
    this.sites = sites;
    this.edges = edges;
    this.triangles = triangles;
    for (Edge edge : edges) {
      if (edge.type == Edge.Type.SEGMENT)
        System.out.println(edge.begin().distance(edge.end()));
    }
  }

  /** Returns the sites provided as input to the algorithm. */
  public Set<Vector._2D> sites() {
    return sites;
  }

  /** Returns the edges returned as output from the algorithm. */
  public Set<Edge> edges() {
    return edges;
  }

  /** Returns the triangulation discovered by the algorithm. */
  public Set<Triangle._2D> triangles() {
    return triangles;
  }

  /**
   * Description of a Voronoi edge found by applying Fortune's algorithm.
   * 
   * @author Lonnie Pryor III (lonnie@pryor.us.com)
   */
  public static final class Edge {

    /** The type that specifies at what ends this edge is infinite. */
    private Type type = null;
    /** The site to the left of (or possibly below) this edge. */
    private Vector._2D left = null;
    /** The site to the right of (or possibly above) this edge. */
    private Vector._2D right = null;
    /** The vertex that this edge begins at, may be at infinity. */
    private Vector._2D begin = null;
    /** The vertex that this edge ends at, may be at infinity. */
    private Vector._2D end = null;
    /** The direction that this edge points. */
    private Vector._2D direction = null;
    /** A point somewhere on the edge that is not at the beginning or end. */
    private Vector._2D location = null;
    /** True if this edge has been unswapped. */
    private boolean marked = false;

    /** Creates a new edge that is infinite in both directions. */
    private Edge(Vector._2D left, Vector._2D right) {
      this.left = left;
      this.right = right;
    }

    /** Returns the type that specifies at what ends this edge is infinite. */
    public Type type() {
      return type;
    }

    /** Returns the site to the left of (or possibly below) this edge. */
    public Vector._2D left() {
      return left;
    }

    /** Returns the site to the right of (or possibly above) this edge. */
    public Vector._2D right() {
      return right;
    }

    /** Returns the vertex that this edge begins at, may be at infinity. */
    public Vector._2D begin() {
      return begin;
    }

    /** Returns the vertex that this edge ends at, may be at infinity. */
    public Vector._2D end() {
      return end;
    }

    /** Returns the direction that this edge points. */
    public Vector._2D direction() {
      return direction;
    }

    /**
     * Returns a point somewhere on the edge that is not at the beginning or
     * end.
     */
    public Vector._2D location() {
      return location;
    }

    /** Swaps the sides the sites are on. */
    private void swap() {
      Vector._2D tmp = left;
      left = right;
      right = tmp;
    }

    /** Adds a vertex to this edge. */
    private void add(Vector._2D vertex) {
      if (begin == null)
        begin = vertex;
      else if (end == null)
        end = vertex;
      else
        throw new IllegalStateException();
    }

    /** Marks this edge as unswapped. */
    private void mark() {
      marked = true;
    }

    /**
     * Finishes this edge by calculating values for all the dependent fields and
     * performing final site swapping.
     */
    private void finish() {
      if (!marked && end == null && left.value(Y) == right.value(Y) && left.value(X) < right.value(X))
        swap();
      if (begin == null) {
        type = Type.LINE;
        direction = Vector.create(-(right.value(Y) - left.value(Y)), right.value(X) - left.value(X)).normalize();
        location = left.add(right.subtract(left).divide(2f));
        begin = infinity(location, direction.multiply(-1f));
        end = infinity(location, direction);
      } else if (end == null) {
        type = Type.RAY;
        direction = Vector.create(-(right.value(Y) - left.value(Y)), right.value(X) - left.value(X)).normalize();
        location = begin.add(direction);
        end = infinity(location, direction);
      } else {
        type = Type.SEGMENT;
        Vector._2D tmp = end.subtract(begin);
        direction = tmp.normalize();
        location = begin.add(tmp.divide(2f));
      }
      if (left.value(X) > right.value(X) || left.value(X) == right.value(X) && left.value(Y) > right.value(Y))
        swap();
      mark();
    }

    /**
     * Creates the infinite end point of a line at {@code location} with the
     * specified {@code direction}.
     */
    private Vector._2D infinity(Vector._2D location, Vector._2D direction) {
      float x = 0f, y = 0f;
      if (direction.value(X) < 0f)
        x = Float.NEGATIVE_INFINITY;
      else if (direction.value(X) > 0f)
        x = Float.POSITIVE_INFINITY;
      else
        x = location.value(X);
      if (direction.value(Y) < 0f)
        y = Float.NEGATIVE_INFINITY;
      else if (direction.value(Y) > 0f)
        y = Float.POSITIVE_INFINITY;
      else
        y = location.value(Y);
      return Vector.create(x, y);
    }
    
    /* @see Object#toString() */
    @Override
    public String toString() {
      return Strings.format(getClass(), type, begin, location, end);
    }

    /**
     * The possible types of edges.
     * 
     * @author Lonnie Pryor III (lonnie@pryor.us.com)
     */
    public static enum Type {
      /** An edge that extends to infinity in both directions. */
      LINE,
      /** An edge that extends to infinity in one direction. */
      RAY,
      /** An edge that is bounded on both ends. */
      SEGMENT
    }

  }

  /**
   * Implementation of Fortune's algorithm.
   * 
   * @author Lonnie Pryor III (lonnie@pryor.us.com)
   */
  private static final class Algorithm {

    /** The set to collect the generated edges in. */
    private final HashSet<Edge> edges;
    /** The set to collect the generated triangles in. */
    private final HashSet<Triangle._2D> triangles;
    /** The currently active circle candidates for the parabolas. */
    private final HashMap<Node.Parabola, Event.Circle> circles = new HashMap<Node.Parabola, Event.Circle>();
    /** The parabolas that require examination after handling an event. */
    private final ArrayList<Node.Parabola> pending = new ArrayList<Node.Parabola>();
    /** The queue of events that remain to be processed. */
    private final PriorityQueue<Event> queue = new PriorityQueue<Event>();
    /** The root of the tree that tracks the active parabolas and intersections. */
    private Node root = null;

    /** Creates a new algorithm implementation. */
    Algorithm(HashSet<Vector._2D> sites, HashSet<Edge> edges, HashSet<Triangle._2D> triangles) {
      for (Vector._2D site : sites)
        queue.add(new Event.Site(site));
      this.edges = edges;
      this.triangles = triangles;
    }

    /** Processes all the events in the queue and populates the set of edges. */
    void apply() {
      if (queue.isEmpty())
        return;
      while (!queue.isEmpty())
        proceed();
      root.finish();
      for (Edge edge : edges)
        edge.finish();
    }

    /** Processes a single event from the head of the queue. */
    @AutoreleasePool
    void proceed() {
      Event event = queue.poll();
      if (event == null)
        return;
      if (event instanceof Event.Site)
        process((Event.Site) event);
      else
        process((Event.Circle) event);
    }

    /** Processes a site event by inserting a new parabola into the beach line. */
    void process(Event.Site event) {
      if (root == null) {
        Node.Parabola node = new Node.Parabola(event.location());
        pending.add(node);
        root = node;
        return;
      }
      Node.Parabola parabola = root.find(event.location());
      Edge edge = new Edge(parabola.site(), event.location());
      Node node = null;
      if (edge.left().value(Y) == edge.right().value(Y)) {
        Node.Parabola a = null, b = null;
        if (edge.left().value(X) < edge.right().value(X)) {
          a = new Node.Parabola(edge.left());
          b = new Node.Parabola(edge.right());
          node = new Node.Intersection(edge, false, a, b);
        } else {
          a = new Node.Parabola(edge.right());
          b = new Node.Parabola(edge.left());
          node = new Node.Intersection(edge, true, a, b);
        }
        pending.add(a);
        pending.add(b);
      } else {
        Node.Parabola a = new Node.Parabola(edge.left());
        Node.Parabola b = new Node.Parabola(edge.right());
        Node.Parabola c = new Node.Parabola(edge.left());
        node = new Node.Intersection(edge, false, a, new Node.Intersection(edge, true, b, c));
        pending.add(a);
        pending.add(b);
        pending.add(c);
      }
      edges.add(edge);
      Node.Intersection parent = parabola.parent();
      if (parent == null)
        root = node;
      else
        parent.replace(parabola, node);
      flush(event.location().value(Y));
    }

    /**
     * Processes a circle event by validating it and removing the associated
     * parabola from the tree.
     */
    void process(Event.Circle event) {
      circles.remove(event.middle());
      if (!event.valid())
        return;
      Node.Parabola middle = event.middle();
      Node.Parabola left = middle.leftNeighbor();
      Node.Parabola right = middle.rightNeighbor();
      if (left == null || middle.parent() == null || right == null || !left.site().equals(event.left().site())
          || !right.site().equals(event.right().site()))
        return;
      pending.add(left);
      pending.add(right);
      Node.Intersection parent = middle.parent();
      Node leftSibling = parent.leftChild();
      Node rightSibling = parent.rightChild();
      Node.Intersection intersection = null;
      if (leftSibling == middle) {
        intersection = left.rightAncestor();
        parent.parent().replace(parent, rightSibling);
      } else {
        intersection = middle.rightAncestor();
        parent.parent().replace(parent, leftSibling);
      }
      triangles.add(event.triangle());
      parent.edge().add(event.circumcenter());
      intersection.edge().add(event.circumcenter());
      Edge edge = new Edge(left.site(), right.site());
      edge.add(event.circumcenter());
      edges.add(edge);
      Node.Intersection node = new Node.Intersection(edge, false, intersection.leftChild(), intersection.rightChild());
      if (intersection.parent() == null)
        root = node;
      else
        intersection.parent().replace(intersection, node);
      flush(event.location().value(Y));
    }

    /** Updates the circle events for parabolas in the pending list. */
    void flush(float ys) {
      for (Node.Parabola parabola : pending) {
        if (circles.containsKey(parabola))
          circles.remove(parabola).invalidate();
        Event.Circle event = parabola.checkForEvent(ys);
        if (event != null) {
          queue.add(event);
          circles.put(parabola, event);
        }
      }
      pending.clear();
    }

  }

  /**
   * Base class for nodes in the tree representation of the beach line.
   * 
   * @author Lonnie Pryor III (lonnie@pryor.us.com)
   */
  private static abstract class Node {

    /** The parent of this node if one exists. */
    @Weak
    private Intersection parent = null;

    /** Returns the parent of this node if one exists. */
    final Intersection parent() {
      return parent;
    }

    /** Returns the ancestor directly left of this node if one exists. */
    final Intersection leftAncestor() {
      if (parent == null)
        return null;
      if (parent.rightChild() == this)
        return parent;
      return parent.leftAncestor();
    }

    /** Returns the ancestor directly right of this node if one exists. */
    final Intersection rightAncestor() {
      if (parent == null)
        return null;
      if (parent.leftChild() == this)
        return parent;
      return parent.rightAncestor();
    }

    /** Returns the farthest-left descendant of this node. */
    abstract Parabola leftmostDescendant();

    /** Returns the farthest-right descendant of this node. */
    abstract Parabola rightmostDescendant();

    /**
     * Searches the tree represented by this node for a matching parabola if one
     * exists.
     */
    abstract Parabola find(Vector._2D location);

    /** Sets the parent of this node. */
    final void setParent(Intersection parent) {
      this.parent = parent;
    }

    /** Performs any final clean-up operations on this node. */
    abstract void finish();

    /**
     * A node representing a parabolic beach line component between a site and
     * the drag line.
     * 
     * @author Lonnie Pryor III (lonnie@pryor.us.com)
     */
    static final class Parabola extends Node {

      /** The site that this parabola tracks. */
      private final Vector._2D site;

      /** Creates a new parabola. */
      Parabola(Vector._2D site) {
        this.site = site;
      }

      /** The site method. */
      Vector._2D site() {
        return site;
      }

      /** Returns the parabola to the left of this parabola if one exists. */
      Parabola leftNeighbor() {
        Intersection ancestor = leftAncestor();
        return ancestor == null ? null : ancestor.leftChild().rightmostDescendant();
      }

      /** Returns the parabola to the right of this parabola if one exists. */
      Parabola rightNeighbor() {
        Intersection ancestor = rightAncestor();
        return ancestor == null ? null : ancestor.rightChild().leftmostDescendant();
      }

      /** Tries to create a circle event centered on this parabola. */
      Event.Circle checkForEvent(float ys) {
        Parabola left = leftNeighbor();
        Parabola right = rightNeighbor();
        if (left == null || right == null || left.site == right.site || left.site == site || site == right.site)
          return null;
        Vector._2D vThis = site.subtract(left.site);
        Vector._2D vThat = right.site.subtract(left.site);
        if (vThis.value(X) * vThat.value(Y) <= vThis.value(Y) * vThat.value(X))
          return null;
        Event.Circle circle = new Event.Circle(left, this, right);
        if (circle.location().value(Y) < ys)
          return null;
        return circle;
      }

      /* @see Fortune.Node#leftmostDescendant() */
      @Override
      Parabola leftmostDescendant() {
        return this;
      }

      /* @see Fortune.Node#rightmostDescendant() */
      @Override
      Parabola rightmostDescendant() {
        return this;
      }

      /* @see Fortune.Node#find(float, float) */
      @Override
      Parabola find(Vector._2D location) {
        return this;
      }

      /* @see Fortune.Node#finish() */
      @Override
      void finish() {}

    }

    /**
     * A node representing the point on the beach line where two parabolas
     * intersect.
     */
    static final class Intersection extends Node {

      /** The edge being created for this intersection. */
      private final Edge edge;
      /** True if the intersecting parabolas are in reverse x-order. */
      private final boolean swapped;
      /** The child of this node on the left side. */
      private Node leftChild = null;
      /** The child of this node on the right side. */
      private Node rightChild = null;

      /** Creates a new intersection. */
      Intersection(Edge edge, boolean swapped, Node left, Node right) {
        this.edge = edge;
        this.swapped = swapped;
        setLeftChild(left);
        setRightChild(right);
      }

      /** Returns the edge being created for this intersection. */
      Edge edge() {
        return edge;
      }

      /** Returns the child of this node on the left side. */
      Node leftChild() {
        return leftChild;
      }

      /** Returns the child of this node on the right side. */
      Node rightChild() {
        return rightChild;
      }

      /** Sets the child of this node on the left side. */
      void setLeftChild(Node leftChild) {
        leftChild.setParent(this);
        this.leftChild = leftChild;
      }

      /** Sets the child of this node on the right side. */
      void setRightChild(Node rightChild) {
        rightChild.setParent(this);
        this.rightChild = rightChild;
      }

      /** Replaces the specified old child with the supplied new child. */
      void replace(Node oldChild, Node newChild) {
        if (leftChild == oldChild)
          setLeftChild(newChild);
        else if (rightChild == oldChild)
          setRightChild(newChild);
        else
          throw new IllegalArgumentException();
      }

      /* @see Fortune.Node#leftmostDescendant() */
      @Override
      Parabola leftmostDescendant() {
        return leftChild.leftmostDescendant();
      }

      /* @see Fortune.Node#rightmostDescendant() */
      @Override
      Parabola rightmostDescendant() {
        return rightChild.rightmostDescendant();
      }

      /* @see Fortune.Node#find(float, float) */
      @Override
      Parabola find(Vector._2D location) {
        float ys = location.value(Y), delta = 0f;
        if (swapped)
          delta = intersect(edge.right(), edge.left(), ys);
        else
          delta = intersect(edge.left(), edge.right(), ys);
        if (Math.round(location.value(X) - delta) < 0f)
          return leftChild.find(location);
        else
          return rightChild.find(location);
      }

      /**
       * Returns the x-coordinate of the intersection point between two
       * parabolas.
       */
      float intersect(Vector._2D a, Vector._2D b, float ys) {
        float ax = a.value(X), ay = a.value(Y);
        float bx = b.value(X), by = b.value(Y);
        if (ay == ys && by == ys)
          return (ax + bx) / 2f;
        if (ay == ys)
          return ax;
        if (by == ys)
          return bx;
        float a1 = 1 / (2 * (ay - ys));
        float a2 = 1 / (2 * (by - ys));
        if (a1 == a2)
          return (ax + bx) / 2f;
        float xs1 = Math.round(0.5f
            / (2f * a1 - 2f * a2)
            * (4f * a1 * ax - 4f * a2 * bx + 2f * Numbers.sqrt(-8f * a1 * ax * a2 * bx - 2f * a1 * ay + 2f * a1 * by
                + 4f * a1 * a2 * bx * bx + 2f * a2 * ay + 4f * a2 * a1 * ax * ax - 2f * a2 * by)));
        float xs2 = Math.round(0.5f
            / (2f * a1 - 2f * a2)
            * (4f * a1 * ax - 4f * a2 * bx - 2f * Numbers.sqrt(-8f * a1 * ax * a2 * bx - 2f * a1 * ay + 2f * a1 * by
                + 4f * a1 * a2 * bx * bx + 2f * a2 * ay + 4f * a2 * a1 * ax * ax - 2f * a2 * by)));
        if (xs1 > xs2) {
          float tmp = xs1;
          xs1 = xs2;
          xs2 = tmp;
        }
        return ay < by ? xs1 : xs2;
      }

      /* @see Fortune.Node#finish() */
      @Override
      void finish() {
        if (swapped)
          edge.swap();
        edge.mark();
        leftChild.finish();
        rightChild.finish();
      }

    }

  }

  /**
   * The base class for the types of events encountered while moving the sweep
   * line.
   * 
   * @author Lonnie Pryor III (lonnie@pryor.us.com)
   */
  private static abstract class Event implements Comparable<Event> {

    /** Returns the location of this event in the diagram. */
    abstract Vector._2D location();

    /* @see Comparable#compareTo(Object) */
    public final int compareTo(Event that) {
      return location().compareTo(that.location());
    }

    /**
     * The event fired for every distinct site supplied as input to the
     * algorithm.
     * 
     * @author Lonnie Pryor III (lonnie@pryor.us.com)
     */
    static final class Site extends Event {

      /** The location of the underlying site. */
      private final Vector._2D location;

      /** Creates a new site event. */
      Site(Vector._2D location) {
        this.location = location;
      }

      /* @see Fortune.Event#location() */
      @Override
      Vector._2D location() {
        return location;
      }

    }

    /**
     * The event fired for every possible circle encountered by the algorithm.
     * 
     * @author Lonnie Pryor III (lonnie@pryor.us.com)
     */
    static final class Circle extends Event {

      /** The node to the left of the two other nodes. */
      private final Node.Parabola left;
      /** The node in the middle of the two other nodes. */
      private final Node.Parabola middle;
      /** The node to the right of the two other nodes. */
      private final Node.Parabola right;
      /** The triangle formed by the three nodes. */
      private final Triangle._2D triangle;
      /** The circumcenter of the triangle formed by the three nodes. */
      private final Vector._2D circumcenter;
      /** The location of this event in the diagram. */
      private final Vector._2D location;
      /** True if this circle event is still valid. */
      private boolean valid = true;

      /** Creates a new circle event. */
      Circle(Node.Parabola left, Node.Parabola middle, Node.Parabola right) {
        this.left = left;
        this.middle = middle;
        this.right = right;
        this.triangle = Triangle.create(left.site(), middle.site(), right.site());
        this.circumcenter = triangle.circumcenter();
        this.location = Vector.create(circumcenter.value(X),
            Math.round(circumcenter.value(Y) + middle.site().distance(circumcenter)));
      }

      /** Returns the node to the left of the two other nodes. */
      Node.Parabola left() {
        return left;
      }

      /** Returns the node in the middle of the two other nodes. */
      Node.Parabola middle() {
        return middle;
      }

      /** Returns the node to the right of the two other nodes. */
      Node.Parabola right() {
        return right;
      }

      /** Returns the triangle formed by the three nodes. */
      Triangle._2D triangle() {
        return triangle;
      }

      /** Returns the circumcenter of the triangle formed by the three nodes. */
      Vector._2D circumcenter() {
        return circumcenter;
      }

      /** Returns true if this circle event is still valid. */
      boolean valid() {
        return valid;
      }

      /** Marks this event as invalid. */
      void invalidate() {
        valid = false;
      }

      /* @see Fortune.Event#location() */
      @Override
      Vector._2D location() {
        return location;
      }

    }

  }

}
