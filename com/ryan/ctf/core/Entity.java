package com.ryan.ctf.core;

public abstract class Entity extends GameObject {

  private int _xVelocity;
  private int _yVelocity;

  public Entity(int width, int height) {
    super(width, height);
  }

  public Entity(int x, int y, int width, int height) {
    super(x, y, width, height);
  }

  public void setXVelocity(int xVelocity) {
    this._xVelocity = xVelocity;
  }

  public void setYVelocity(int yVelocity) {
    this._yVelocity = yVelocity;
  }

  public float getXVelocity() {
    return this._xVelocity;
  }

  public float getYVelocity() {
    return this._yVelocity;
  }
}
