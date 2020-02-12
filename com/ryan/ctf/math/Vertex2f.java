package com.ryan.ctf.math;

/*
 * A vertex with x and y components
 */
public class Vertex2f {

  private float _x;
  private float _y;

  public Vertex2f() { }

  public Vertex2f(float x, float y) {
    this._x = x;
    this._y = y;
  }

  public void setX(float x) {
    this._x = x;
  }

  public void setY(float y) {
    this._y = y;
  }

  public float getX() {
    return this._x;
  }

  public float getY() {
    return this._y;
  }
}
