/*
 * Octaves.java
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
package fulcrum.noise;

import fulcrum.math.Numbers;

/**
 * Noise generators that merge the samples from multiple other generators.
 * 
 * @author Lonnie Pryor III
 */
public abstract class OctavesOld implements NoiseOld, Cloneable {

  /** Creates a new two-dimensional multi-octave noise generator. */
  public static OctavesOld._2D create(float persistence, NoiseOld._2D... octaves) {
    return new OctavesOld._2D(persistence, octaves);
  }

  /** Creates a new three-dimensional multi-octave noise generator. */
  public static OctavesOld._3D create(float persistence, NoiseOld._3D... octaves) {
    return new OctavesOld._3D(persistence, octaves);
  }

  /** Creates a new four-dimensional multi-octave noise generator. */
  public static OctavesOld._4D create(float persistence, NoiseOld._4D... octaves) {
    return new OctavesOld._4D(persistence, octaves);
  }

  /** The persistence of component samples. */
  protected final float persistence;

  /** Creates a new multi-octave noise generator. */
  private OctavesOld(float persistence) {
    this.persistence = persistence;
  }

  /**
   * A two-dimensional multi-octave noise generator.
   * 
   * @author Lonnie Pryor III
   */
  public static final class _2D extends OctavesOld implements NoiseOld._2D {

    /** The octaves that are merged together. */
    private final NoiseOld._2D[] octaves;

    /** Creates a new two-dimensional multi-octave noise generator. */
    public _2D(float persistence, NoiseOld._2D... octaves) {
      super(persistence);
      assert octaves != null;
      if (octaves.length == 0)
        throw new IllegalArgumentException("octaves");
      this.octaves = octaves.clone();
    }

    /* @see Noise._2D#sample(float, float) */
    @Override
    public float sample(float x, float y) {
      float amplitude = 1f, total = 0f, deniminator = 0f;
      for (int i = 0; i < octaves.length; i++) {
        deniminator += amplitude;
        total += amplitude * octaves[i].sample(x, y);
        amplitude *= persistence;
      }
      return Numbers.clamp(total / deniminator, -1f, 1f);
    }

  }

  /**
   * A three-dimensional multi-octave noise generator.
   * 
   * @author Lonnie Pryor III
   */
  public static final class _3D extends OctavesOld implements NoiseOld._3D {

    /** The octaves that are merged together. */
    private final NoiseOld._3D[] octaves;

    /** Creates a new three-dimensional multi-octave noise generator. */
    public _3D(float persistence, NoiseOld._3D... octaves) {
      super(persistence);
      assert octaves != null;
      if (octaves.length == 0)
        throw new IllegalArgumentException("octaves");
      this.octaves = octaves.clone();
    }

    /* @see Noise._3D#sample(float, float, float) */
    @Override
    public float sample(float x, float y, float z) {
      float amplitude = 1f, total = 0f, deniminator = 0f;
      for (int i = 0; i < octaves.length; i++) {
        deniminator += amplitude;
        total += amplitude * octaves[i].sample(x, y, z);
        amplitude *= persistence;
      }
      return Numbers.clamp(total / deniminator, -1f, 1f);
    }

  }

  /**
   * A four-dimensional multi-octave noise generator.
   * 
   * @author Lonnie Pryor III
   */
  public static final class _4D extends OctavesOld implements NoiseOld._4D {

    /** The octaves that are merged together. */
    private final NoiseOld._4D[] octaves;

    /** Creates a new four-dimensional multi-octave noise generator. */
    public _4D(float persistence, NoiseOld._4D... octaves) {
      super(persistence);
      assert octaves != null;
      if (octaves.length == 0)
        throw new IllegalArgumentException("octaves");
      this.octaves = octaves.clone();
    }

    /* @see Noise._4D#sample(float, float, float, float) */
    @Override
    public float sample(float x, float y, float z, float w) {
      float amplitude = 1f, total = 0f, deniminator = 0f;
      for (int i = 0; i < octaves.length; i++) {
        deniminator += amplitude;
        total += amplitude * octaves[i].sample(x, y, z, w);
        amplitude *= persistence;
      }
      return Numbers.clamp(total / deniminator, -1f, 1f);
    }

  }

}
