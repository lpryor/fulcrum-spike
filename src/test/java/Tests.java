/*
 * Tests.java
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

import junit.framework.TestSuite;

/**
 * Runner for the engine test suite from the Objective-C side.
 * 
 * @author Lonnie Pryor III (lonnie@pryor.us.com)
 */
public class Tests extends TestSuite {

  /** Creates a new test suite. */
  public Tests() {
    super(new Class[] {

        //
        // fulcrum.text
        //
        fulcrum.text.StringsTest.class, //

        //
        // fulcrum.math
        //
        fulcrum.math.NumbersTest.class, //
        fulcrum.math.VectorTest.class, //
        fulcrum.math.BoxTest.class, //
        fulcrum.math.LineTest.class, //
        fulcrum.math.TriangleTest.class, //
        fulcrum.math.PolygonTest.class, //
        fulcrum.math.FortuneTest.class, //
        fulcrum.math.DelaunayTest.class, //
        fulcrum.math.VoronoiTest.class, //

        //
        // fulcrum.noise
        //
        fulcrum.noise.OctavesTest.class,
        fulcrum.noise.SimplexTest.class, //

        //
        // fulcrum.util
        //
        fulcrum.util.RandomTest.class //

        });
  }

}