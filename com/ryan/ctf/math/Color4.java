package com.ryan.ctf.math;

/*
 * A rgba color
 */
public class Color4 {

  public float R;
  public float G;
  public float B;
  public float A;

  public Color4() { }

  public Color4(float r, float g, float b, float a) {
    this.R = r;
    this.G = g;
    this.B = b;
    this.A = a;
  }

  public float[] asFloatArray() {
    return new float[] {
        this.R, this.G, this.B, this.A
    };
  }
}
