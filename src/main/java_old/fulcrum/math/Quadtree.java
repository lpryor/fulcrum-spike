/*
 * Quadtree.java
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
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import com.google.j2objc.annotations.Weak;
import com.google.j2objc.annotations.WeakOuter;

import fulcrum.Function;
import fulcrum.text.Strings;

/**
 * A set that provides optimized spatial indexing by recursively dividing a
 * bounding box into equal-sized quadrants.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
public final class Quadtree<T> extends AbstractSet<T> {

  /** Index of the x-coordinate in 2D. */
  private static final int X = 0;
  /** Index of the y-coordinate in 2D. */
  private static final int Y = 1;

  /** The bounds that this tree contains. */
  private final Box._2D bounds;
  /** The adapter that determines the location of entries. */
  private final Function<T, Box._2D> adapter;
  /** The offset used to map locations into quadtree space. */
  private final Vector._2D offset;
  /** The scale used to map locations into quadtree space. */
  private final Vector._2D scale;
  /** The maximum number of entries a node will accept before creating children. */
  private final int maxEntriesPerNode;
  /** The tree of quadrants for spatial indexing. */
  private final Node tree;
  /** The entries currently in this quadtree. */
  private final HashMap<T, Leaf> entries = new HashMap<T, Leaf>();

  /** Creates a new quadtree. */
  public Quadtree(Box._2D bounds, Function<T, Box._2D> adapter) {
    this(bounds, adapter, 4);
  }

  /** Creates a new quadtree. */
  public Quadtree(Box._2D bounds, Function<T, Box._2D> adapter, int maxEntriesPerNode) {
    assert bounds != null;
    assert adapter != null;
    Vector._2D size = bounds.size();
    float xSize = size.value(X), ySize = size.value(Y);
    if (xSize == 0f || ySize == 0f)
      throw new IllegalArgumentException("bounds");
    if (maxEntriesPerNode < 1)
      throw new IllegalArgumentException("maxEntriesPerNode");
    this.bounds = bounds;
    this.adapter = adapter;
    this.offset = bounds.lower().multiply(-1f);
    this.scale = Vector.create(((1 << 24) - 1) / xSize, ((1 << 24) - 1) / ySize);
    this.maxEntriesPerNode = maxEntriesPerNode;
    this.tree = new Node(24);
  }

  /** Returns all the entries that intersect the specified point. */
  public Collection<T> search(Vector._2D query) {
    assert query != null;
    return search(Box.create(query, query));
  }

  /** Returns all the entries that intersect the specified box. */
  public Collection<T> search(Box._2D query) {
    assert query != null;
    Box._2D box = bounds.intersection(query);
    if (box == null)
      return Collections.emptyList();
    LinkedList<T> results = new LinkedList<T>();
    int lx = lower(box, X), ly = lower(box, Y), ux = upper(box, X), uy = upper(box, Y);
    tree.search(box, 0, 0, lx, ly, ux, uy, results);
    return results;
  }

  /**
   * Updates the specified entry in this set, adding or removing it if required.
   */
  public boolean update(T e) {
    Box._2D box = bounds.intersection(adapter.apply(e));
    if (box == null) {
      Leaf leaf = entries.remove(e);
      if (leaf == null)
        return false;
      removeFromTree(leaf);
      return true;
    }
    Leaf leaf = entries.get(e);
    if (leaf == null) {
      leaf = new Leaf(e, box);
      entries.put(e, leaf);
      tree.add(leaf);
      return true;
    }
    if (box.equalTo(leaf.box()))
      return false;
    Node parent = leaf.parent();
    parent.remove(leaf);
    leaf.setBox(box);
    tree.add(leaf);
    cleanUpEmptyNodes(parent);
    return true;
  }

  /**
   * Updates the specified entries in this set, adding or removing them if
   * required.
   */
  public boolean updateAll(Collection<? extends T> c) {
    if (c == this)
      return false;
    boolean result = false;
    LinkedList<Node> parents = new LinkedList<Node>();
    for (Object o : c.toArray()) {
      @SuppressWarnings("unchecked")
      T e = (T) o;
      Box._2D box = bounds.intersection(adapter.apply(e));
      if (box == null) {
        Leaf leaf = entries.remove(e);
        if (leaf == null)
          continue;
        Node parent = leaf.parent();
        parent.remove(leaf);
        parents.add(parent);
        result = true;
        continue;
      }
      Leaf leaf = entries.get(e);
      if (leaf == null) {
        leaf = new Leaf(e, box);
        entries.put(e, leaf);
        tree.add(leaf);
        result = true;
        continue;
      }
      if (box.equalTo(leaf.box()))
        continue;
      Node parent = leaf.parent();
      parent.remove(leaf);
      parents.add(parent);
      leaf.setBox(box);
      tree.add(leaf);
      result = true;
    }
    for (Node parent : parents)
      cleanUpEmptyNodes(parent);
    return result;
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
    return entries.containsKey(o);
  }

  /* @see AbstractCollection#containsAll(Collection) */
  @Override
  public boolean containsAll(Collection<?> c) {
    return c == this || entries.keySet().containsAll(c);
  }

  /* @see AbstractCollection#toArray() */
  @Override
  public Object[] toArray() {
    return entries.keySet().toArray();
  }

  /* @see AbstractCollection#toArray(T[]) */
  @Override
  public <U> U[] toArray(U[] a) {
    return entries.keySet().toArray(a);
  }

  /* @see AbstractCollection#iterator() */
  @Override
  public Iterator<T> iterator() {
    return new Iteration();
  }

  /* @see AbstractCollection#add(Object) */
  @Override
  public boolean add(T e) {
    if (entries.containsKey(e))
      return false;
    Box._2D box = bounds.intersection(adapter.apply(e));
    if (box == null)
      return false;
    Leaf leaf = new Leaf(e, box);
    entries.put(e, leaf);
    tree.add(leaf);
    return true;
  }

  /* @see AbstractCollection#addAll(Collection) */
  @Override
  public boolean addAll(Collection<? extends T> c) {
    if (c == this)
      return false;
    boolean result = false;
    for (Object o : c.toArray()) {
      @SuppressWarnings("unchecked")
      T e = (T) o;
      result |= add(e);
    }
    return result;
  }

  /* @see AbstractCollection#remove(Object) */
  @Override
  public boolean remove(Object o) {
    Leaf leaf = entries.remove(o);
    if (leaf == null)
      return false;
    removeFromTree(leaf);
    return true;
  }

  /* @see AbstractSet#removeAll(Collection) */
  @Override
  public boolean removeAll(Collection<?> c) {
    if (c == this) {
      if (isEmpty())
        return false;
      clear();
      return true;
    }
    boolean result = false;
    for (Object o : c.toArray())
      result |= remove(o);
    return result;
  }

  /* @see AbstractCollection#retainAll(Collection) */
  @Override
  public boolean retainAll(Collection<?> c) {
    if (c == this)
      return false;
    if (c.isEmpty()) {
      if (isEmpty())
        return false;
      clear();
      return true;
    }
    HashSet<T> toRemove = new HashSet<T>(entries.keySet());
    toRemove.removeAll(c);
    return removeAll(toRemove);
  }

  /* @see AbstractCollection#clear() */
  @Override
  public void clear() {
    entries.clear();
    tree.clear();
  }

  /* @see AbstractCollection#toString() */
  @Override
  public String toString() {
    return Strings.format(getClass(), entries.keySet());
  }

  /** Returns the lower bound geohash for the specified index. */
  private int lower(Box._2D box, int index) {
    return (int) Numbers.floor((box.lower().value(index) + offset.value(index)) * scale.value(index));
  }

  /** Returns the upper bound geohash for the specified index. */
  private int upper(Box._2D box, int index) {
    return (int) Numbers.ceil((box.upper().value(index) + offset.value(index)) * scale.value(index));
  }

  /** Removes a leaf from the tree. */
  private void removeFromTree(Leaf leaf) {
    Node parent = leaf.parent();
    parent.remove(leaf);
    cleanUpEmptyNodes(parent);
  }

  /** Removes all empty nodes starting with the supplied node. */
  private void cleanUpEmptyNodes(Node node) {
    for (Node current = node; current.isEmpty();) {
      Node next = current.parent();
      if (next == null)
        break;
      next.remove(current);
      current = next;
    }
  }

  /**
   * An iterator over the entries in a quadtree.
   * 
   * @author Lonnie Pryor III (lonnie@pryor.us.com)
   */
  private final class Iteration implements Iterator<T> {

    /** The iterator over the entries. */
    private final Iterator<Map.Entry<T, Leaf>> iter = entries.entrySet().iterator();
    /** The leaf associated with the last key returned. */
    private Leaf leaf = null;

    /* @see Iterator#hasNext() */
    @Override
    public boolean hasNext() {
      return iter.hasNext();
    }

    /* @see Iterator#next() */
    @Override
    public T next() {
      Map.Entry<T, Leaf> current = iter.next();
      leaf = current.getValue();
      return current.getKey();
    }

    /* @see Iterator#remove() */
    @Override
    public void remove() {
      iter.remove();
      removeFromTree(leaf);
      leaf = null;
    }

  }

  /**
   * A node in the quadtree bound to a sub-quadrant of the space.
   * 
   * @author Lonnie Pryor III (lonnie@pryor.us.com)
   */
  @WeakOuter
  private final class Node {

    /** The level of this node in the range [0, {@code maxTreeDepth}). */
    private final int level;
    /** The parent of this node or null if it is the root. */
    @Weak
    private Node parent;
    /** The first leaf in the chain of leaves contained in this node. */
    private Leaf leaves = null;
    /** The south-west child of this node. */
    private Node sw = null;
    /** The north-west child of this node. */
    private Node nw = null;
    /** The south-east child of this node. */
    private Node se = null;
    /** The north-east child of this node. */
    private Node ne = null;

    /** Creates a new node. */
    Node(int level) {
      this.level = level;
      this.parent = null;
    }

    /** Creates a new node. */
    private Node(int level, Node parent) {
      this.level = level;
      this.parent = parent;
    }

    /** Returns the parent of this node. */
    Node parent() {
      return parent;
    }

    /** Returns true if this node contains no leaves or children. */
    boolean isEmpty() {
      return leaves == null && !hasChildren();
    }

    /** Returns true if this node contains children. */
    boolean hasChildren() {
      return sw != null || nw != null || se != null || ne != null;
    }

    /** Searches this node for entries that intersect the specified query. */
    private void search(Box._2D query, int xPrefix, int yPrefix, int xLower, int yLower, int xUpper, int yUpper,
        LinkedList<T> results) {
      if (level < 1) {
        collect(query, results);
        return;
      }
      int xMin = xPrefix, yMin = yPrefix;
      int max = (1 << level) - 1, xMax = xPrefix | max, yMax = yPrefix | max;
      if (xLower <= xMin && yLower <= yMin && xUpper >= xMax && yUpper >= yMax) {
        collectAll(query, results);
        return;
      }
      collect(query, results);
      int mask = mask(), xMid = xPrefix | mask, yMid = yPrefix | mask;
      if (xLower < xMid) {
        if (sw != null && yLower < yMid)
          sw.search(query, xPrefix, yPrefix, xLower, yLower, xUpper, yUpper, results);
        if (nw != null && yUpper >= yMid)
          nw.search(query, xPrefix, yMid, xLower, yLower, xUpper, yUpper, results);
      }
      if (xUpper >= xMid) {
        if (se != null && yLower < yMid)
          se.search(query, xMid, yPrefix, xLower, yLower, xUpper, yUpper, results);
        if (ne != null && yUpper >= yMid)
          ne.search(query, xMid, yMid, xLower, yLower, xUpper, yUpper, results);
      }
    }

    /**
     * Collects all the entries in this node that intersect the specified query.
     */
    private void collect(Box._2D query, LinkedList<T> results) {
      for (Leaf leaf = leaves; leaf != null; leaf = leaf.next())
        if (leaf.box().intersects(query))
          results.add(leaf.value());
    }

    /**
     * Collects all the entries in this node and all of its descendants that
     * intersect the specified query.
     */
    private void collectAll(Box._2D query, LinkedList<T> results) {
      collect(query, results);
      if (sw != null)
        sw.collectAll(query, results);
      if (nw != null)
        nw.collectAll(query, results);
      if (se != null)
        se.collectAll(query, results);
      if (ne != null)
        ne.collectAll(query, results);
    }

    /** Adds a leaf to the appropriate pace in the tree. */
    void add(Leaf leaf) {
      add(leaf, lower(leaf.box(), X), lower(leaf.box(), Y), upper(leaf.box(), X), upper(leaf.box(), Y));
    }

    /** Adds a leaf to the appropriate pace in the tree. */
    private void add(Leaf leaf, int xLower, int yLower, int xUpper, int yUpper) {
      if (level < 1) {
        addLeafToThis(leaf);
        return;
      }
      if (!hasChildren()) {
        if (leaves == null || leaves.count() < maxEntriesPerNode) {
          addLeafToThis(leaf);
          return;
        }
        if (leaves.count() == maxEntriesPerNode) {
          for (Leaf current = leaves; current != null;) {
            Leaf next = current.next();
            remove(current);
            if (!addLeafToChild(current, lower(current.box(), X), lower(current.box(), Y), upper(current.box(), X),
                upper(current.box(), Y)))
              addLeafToThis(current);
            current = next;
          }
        }
      }
      if (!addLeafToChild(leaf, xLower, yLower, xUpper, yUpper))
        addLeafToThis(leaf);
    }

    /** Adds a leaf to this node's list. */
    private void addLeafToThis(Leaf leaf) {
      leaf.setParent(this);
      leaf.setPrevious(null);
      leaf.setNext(leaves);
      if (leaves != null)
        leaves.setPrevious(leaf);
      leaves = leaf;
    }

    /** Attempts to add a leaf to a child of this node. */
    private boolean addLeafToChild(Leaf leaf, int xLower, int yLower, int xUpper, int yUpper) {
      int mask = mask(), xi = xLower & mask, yi = yLower & mask;
      if ((xUpper & mask) != xi || (yUpper & mask) != yi)
        return false;
      Node child = null;
      if (xi == 0) {
        if (yi == 0)
          child = sw == null ? sw = new Node(level - 1, this) : sw;
        else
          child = nw == null ? nw = new Node(level - 1, this) : nw;
      } else if (yi == 0)
        child = se == null ? se = new Node(level - 1, this) : se;
      else
        child = ne == null ? ne = new Node(level - 1, this) : ne;
      child.add(leaf, xLower, yLower, xUpper, yUpper);
      return true;
    }

    /** The mask used to select child quadrants. */
    int mask() {
      return 1 << level - 1;
    }

    /** Removes a leaf from this node. */
    void remove(Leaf leaf) {
      assert leaf.parent() == this;
      Leaf previous = leaf.previous(), next = leaf.next();
      leaf.setParent(null);
      if (previous != null) {
        leaf.setPrevious(null);
        previous.setNext(next);
      }
      if (next != null) {
        leaf.setNext(null);
        next.setPrevious(previous);
      }
      if (leaves == leaf)
        leaves = next;
    }

    /** Removes a child from this node. */
    void remove(Node child) {
      child.parent = null;
      if (child == sw)
        sw = null;
      else if (child == nw)
        nw = null;
      else if (child == se)
        se = null;
      else if (child == ne)
        ne = null;
    }

    /** Clears the contents of this node. */
    void clear() {
      leaves = null;
      sw = null;
      nw = null;
      se = null;
      ne = null;
    }

  }

  /**
   * A leaf in the tree that wraps one entry.
   * 
   * @author Lonnie Pryor III (lonnie@pryor.us.com)
   */
  @WeakOuter
  private final class Leaf {

    /** The value of the entry. */
    private final T value;
    /** The area that the value takes up in space. */
    private Box._2D box;
    /** The parent of this leaf in the tree. */
    @Weak
    private Node parent = null;
    /** The previous leaf in the parent's collection of leaves. */
    @Weak
    private Leaf previous = null;
    /** The next leaf in the parent's collection of leaves. */
    private Leaf next = null;

    /** Creates a new leaf. */
    Leaf(T value, Box._2D box) {
      this.value = value;
      this.box = box;
    }

    /** Returns the value of the entry. */
    T value() {
      return value;
    }

    /** Returns the area that the value takes up in space. */
    Box._2D box() {
      return box;
    }

    /** Returns the parent of this leaf in the tree. */
    Node parent() {
      return parent;
    }

    /** Returns the previous leaf in the parent's collection of leaves. */
    Leaf previous() {
      return previous;
    }

    /** Returns the next leaf in the parent's collection of leaves. */
    Leaf next() {
      return next;
    }

    /** Returns the number of leaves in this chain. */
    int count() {
      return next == null ? 1 : next.count() + 1;
    }

    /** Sets the area that the value takes up in space. */
    void setBox(Box._2D box) {
      this.box = box;
    }

    /** Sets the parent of this leaf in the tree. */
    void setParent(Node parent) {
      this.parent = parent;
    }

    /** Sets the previous leaf in the parent's collection of leaves. */
    void setPrevious(Leaf previous) {
      this.previous = previous;
    }

    /** Sets the next leaf in the parent's collection of leaves. */
    void setNext(Leaf next) {
      this.next = next;
    }

    /* @see java.lang.Object#toString() */
    @Override
    public String toString() {
      String result = value.toString() + " (" + (previous == null ? "" : previous.value) + ")";
      if (next != null)
        result += ", " + next.toString();
      return result;
    }

  }

}
