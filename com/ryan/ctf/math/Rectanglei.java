package com.ryan.ctf.math;

public class Rectanglei {

  private int _x1;
  private int _y1;
  private int _x2;
  private int _y2;

  public Rectanglei(int x1, int y1, int x2, int y2) {
    this._x1 = x1;
    this._y1 = y1;
    this._x2 = x2;
    this._y2 = y2;
  }

  public void setX1(int x1) {
    this._x1 = x1;
  }

  public void setY1(int y1) {
    this._y1 = y1;
  }

  public void setX2(int x2) {
    this._x2 = x2;
  }

  public void setY2(int y2) {
    this._y2 = y2;
  }

  public int getX1() {
    return this._x1;
  }

  public int getY1() {
    return this._y1;
  }

  public int getX2() {
    return this._x2;
  }

  public int getY2() {
    return this._y2;
  }
}
