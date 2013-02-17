/*
 * QuadtreeTest.java
 * 
 * Copyright (c) 2012 Lonnie Pryor III
 */
package fulcrum.math;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import junit.framework.TestCase;
import fulcrum.Function;
import fulcrum.math.Box._2D;
import fulcrum.util.Random;

/**
 * Test case for {@link Quadtree}.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
public class QuadtreeTest extends TestCase {

  /**
   * The quadtree implementation implements the set interface using an internal
   * hash map instance.
   */
  public void testImplementsSetUsingHashMap() {
    final LinkedHashMap<String, Box._2D> map = new LinkedHashMap<String, Box._2D>();
    map.put("big", Box.create(Vector.create(-2f, -2f), Vector.create(2f, 2f)));
    map.put("left", Box.create(Vector.create(-2f, -2f), Vector.create(-1f, 2f)));
    map.put("right", Box.create(Vector.create(1f, -2f), Vector.create(2f, 2f)));
    map.put("bottom", Box.create(Vector.create(-2f, -2f), Vector.create(2f, -1f)));
    map.put("top", Box.create(Vector.create(-2f, 1f), Vector.create(2f, 2f)));
    Quadtree<String> tree = new Quadtree<String>(Box.create(Vector.create(-16f, -16f), Vector.create(16f, 16f)),
        new TestAdapter(map));
    // Test adding and removing a single element.
    assertTrue(tree.isEmpty());
    assertEquals(0, tree.size());
    assertFalse(tree.contains("big"));
    assertTrue(tree.add("big"));
    assertFalse(tree.isEmpty());
    assertEquals(1, tree.size());
    assertTrue(tree.contains("big"));
    assertFalse(tree.add("big"));
    assertFalse(tree.isEmpty());
    assertEquals(1, tree.size());
    assertTrue(tree.contains("big"));
    assertTrue(tree.remove("big"));
    assertTrue(tree.isEmpty());
    assertEquals(0, tree.size());
    assertFalse(tree.contains("big"));
    assertFalse(tree.remove("big"));
    // Test adding and removing multiple elements.
    assertTrue(tree.addAll(map.keySet()));
    assertFalse(tree.isEmpty());
    assertEquals(map.size(), tree.size());
    assertTrue(tree.containsAll(map.keySet()));
    assertFalse(tree.addAll(map.keySet()));
    assertFalse(tree.isEmpty());
    assertEquals(map.size(), tree.size());
    assertTrue(tree.containsAll(map.keySet()));
    assertTrue(tree.removeAll(map.keySet()));
    assertTrue(tree.isEmpty());
    assertEquals(0, tree.size());
    assertFalse(tree.containsAll(map.keySet()));
    assertFalse(tree.removeAll(map.keySet()));
    // Test retaining and clearing.
    assertTrue(tree.addAll(map.keySet()));
    assertFalse(tree.isEmpty());
    assertEquals(map.size(), tree.size());
    assertTrue(tree.containsAll(map.keySet()));
    assertTrue(tree.retainAll(Arrays.asList("left", "right")));
    assertFalse(tree.isEmpty());
    assertEquals(2, tree.size());
    assertTrue(tree.containsAll(Arrays.asList("left", "right")));
    tree.clear();
    assertTrue(tree.isEmpty());
    assertEquals(0, tree.size());
    // Test arrays and iterators.
    assertTrue(tree.addAll(map.keySet()));
    assertTrue(map.keySet().containsAll(Arrays.asList(tree.toArray())));
    assertTrue(map.keySet().containsAll(Arrays.asList(tree.toArray(new String[tree.size()]))));
    for (Iterator<String> i = tree.iterator(); i.hasNext();) {
      assertTrue(map.containsKey(i.next()));
      i.remove();
    }
    assertTrue(tree.isEmpty());
    assertEquals(0, tree.size());
    // Test hash code and equality.
    assertTrue(tree.addAll(map.keySet()));
    assertEquals(new HashSet<String>(map.keySet()).hashCode(), tree.hashCode());
    assertEquals(new HashSet<String>(map.keySet()), tree);
  }

  /**
   * The quadtree implementation recursively partitions arbitrary rectangles
   * into quadrants.
   */
  public void testRecursivelyPartitionsSpaceIntoQuadrants() {
    final LinkedHashMap<String, Box._2D> map = new LinkedHashMap<String, Box._2D>();
    map.put("big", Box.create(Vector.create(-2f, -2f), Vector.create(2f, 2f)));
    map.put("left", Box.create(Vector.create(-2f, -2f), Vector.create(-1f, 2f)));
    map.put("right", Box.create(Vector.create(1f, -2f), Vector.create(2f, 2f)));
    map.put("bottom", Box.create(Vector.create(-2f, -2f), Vector.create(2f, -1f)));
    map.put("top", Box.create(Vector.create(-2f, 1f), Vector.create(2f, 2f)));
    map.put("sw", Box.create(Vector.create(-2f, -2f), Vector.create(-1f, -1f)));
    map.put("nw", Box.create(Vector.create(-2f, 1f), Vector.create(-1f, 2f)));
    map.put("se", Box.create(Vector.create(1f, -2f), Vector.create(2f, -1f)));
    map.put("ne", Box.create(Vector.create(1f, 1f), Vector.create(2f, 2f)));
    map.put("outside", Box.create(Vector.create(100f, 100f), Vector.create(200f, 200f)));
    Quadtree<String> tree = new Quadtree<String>(Box.create(Vector.create(-16f, -16f), Vector.create(16f, 16f)),
        new TestAdapter(map));
    tree.addAll(map.keySet());
    assertEquals(9, tree.size());
    // Test some basic queries.
    verifySearch(tree, Box.create(Vector.create(-40f, -40f), Vector.create(-30f, -30f)));
    verifySearch(tree, Box.create(Vector.create(-0.5f, -0.5f), Vector.create(0.5f, 0.5f)), "big");
    verifySearch(tree, Box.create(Vector.create(-4f, -4f), Vector.create(4f, 4f)), "big", "left", "right", "bottom",
        "top", "sw", "nw", "se", "ne");
    // Test queries against four central quadrants.
    Box._2D[] boxes = { Box.create(Vector.create(-8f, -8f), Vector.create(0f, 0f)),
        Box.create(Vector.create(-8f, 0f), Vector.create(0f, 8f)),
        Box.create(Vector.create(0f, -8f), Vector.create(8f, 0f)),
        Box.create(Vector.create(0f, 0f), Vector.create(8f, 8f)) };
    verifySearch(tree, boxes[0], "big", "left", "bottom", "sw");
    verifySearch(tree, boxes[1], "big", "left", "top", "nw");
    verifySearch(tree, boxes[2], "big", "right", "bottom", "se");
    verifySearch(tree, boxes[3], "big", "right", "top", "ne");
    // Test removing from the root leaf list.
    tree.removeAll(Arrays.asList("big", "left", "right", "bottom", "top"));
    assertEquals(4, tree.size());
    // Test updating single elements in the tree.
    String[] keys = new String[] { "sw", "nw", "se", "ne" };
    for (int i = 0; i < keys.length; ++i) {
      map.put(keys[i], Box.create(map.get(keys[i]).lower().multiply(2f), map.get(keys[i]).upper().multiply(2f)));
      assertTrue(tree.update(keys[i]));
      verifySearch(tree, boxes[i], keys[i]);
      assertFalse(tree.update(keys[i]));
      verifySearch(tree, boxes[i], keys[i]);
    }
    // Test updating groups of elements in the tree.
    for (int i = 0; i < keys.length; ++i)
      map.put(keys[i], Box.create(map.get(keys[i]).lower().multiply(2f), map.get(keys[i]).upper().multiply(2f)));
    assertTrue(tree.updateAll(Arrays.asList(keys)));
    for (int i = 0; i < keys.length; ++i)
      verifySearch(tree, boxes[i], keys[i]);
    assertFalse(tree.updateAll(Arrays.asList(keys)));
    for (int i = 0; i < keys.length; ++i)
      verifySearch(tree, boxes[i], keys[i]);
    // Test adding and removing elements by updating.
    assertEquals(4, tree.size());
    Box._2D in = map.get("big"), out = map.get("outside");
    map.put("big", out);
    map.put("outside", in);
    assertFalse(tree.update("big"));
    assertTrue(tree.update("outside"));
    assertEquals(5, tree.size());
    map.put("big", in);
    map.put("outside", out);
    assertTrue(tree.updateAll(Arrays.asList("big", "outside")));
    assertEquals(5, tree.size());
    assertTrue(tree.remove("big"));
    assertEquals(4, tree.size());
    // Test the creation of deep branches in the tree.
    for (int i = 0; i < keys.length; ++i) {
      Box._2D box = map.get(keys[i]);
      String[] expected = new String[16];
      expected[0] = keys[i];
      for (int j = 1; j < expected.length; ++j) {
        expected[j] = keys[i] + j;
        map.put(expected[j], Box.create(box.lower().divide(1 << j), box.upper().divide(1 << j)));
        assertTrue(tree.add(expected[j]));
      }
      verifySearch(tree, boxes[i], expected);
      List<String> list = Arrays.asList(expected);
      Collections.reverse(list);
      assertTrue(tree.removeAll(list));
    }
    assertEquals(0, tree.size());
  }

  /**
   * The quadtree implementation is capable of handling large numbers of
   * entries.
   */
  public void testHandlesSetsWithManyEntries() {
    int side = 32;
    Map<String, Box._2D> map = new LinkedHashMap<String, Box._2D>(side * side);
    Random random = Random.apply(0x12345678);
    for (int x = 0; x < side; ++x) {
      for (int y = 0; y < side; ++y) {
        Vector._2D v = Vector.create(x * side + random.nextInteger(side - 2) + 1,
            y * side + random.nextInteger(side - 2) + 1);
        map.put(x + " x " + y, Box.create(v, v));
      }
    }
    Quadtree<String> tree = new Quadtree<String>(Box.create(Vector.create(0f, 0f),
        Vector.create(side * side, side * side)), new TestAdapter(map));
    tree.addAll(map.keySet());
    assertEquals(side * side, tree.size());
  }

  /** Verifies the results of a quadtree search. */
  private void verifySearch(Quadtree<String> tree, Box._2D query, String... expected) {
    assertEquals(new TreeSet<String>(Arrays.asList(expected)), new TreeSet<String>(tree.search(query)));
  }

  /**
   * An adapter for testing.
   * 
   * @author Lonnie Pryor III (lonnie@pryor.us.com)
   */
  private static final class TestAdapter implements Function<String, Box._2D> {

    /** The map to search. */
    private final Map<String, _2D> map;

    /** Creates a new test adapter. */
    private TestAdapter(Map<String, _2D> map) {
      this.map = map;
    }

    /* @see Function#apply(Object) */
    @Override
    public Box._2D apply(String input) {
      return map.get(input);
    }

  }

}
