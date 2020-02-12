package com.ryan.ctf.math;

/*
 * A rgba color
 */
public class Color4 {

  private float _r;
  private float _g;
  private float _b;
  private float _a;

  public Color4() { }

  public Color4(float r, float g, float b, float a) {
    this._r = r;
    this._g = g;
    this._b = b;
    this._a = a;
  }

  public float getR() {
    return this._r;
  }

  public float getG() {
    return this._g;
  }

  public float getB() {
    return this._b;
  }

  public float getA() {
    return this._a;
  }

  public void setR(float r) {
    this._r = r;
  }

  public void setG(float g) {
    this._g = g;
  }

  public void setB(float b) {
    this._b = b;
  }

  public void setA(float a) {
    this._a = a;
  }

  public float[] asFloatArray() {
    return new float[] {
        this._r, this._g, this._b, this._a
    };
  }
}
