package com.ryan.ctf.core;

public abstract class Entity extends GameObject {

  private float _xVelocity;
  private float _yVelocity;

  public Entity(int width, int height) {
    super(width, height);
  }

  public Entity(int x, int y, int width, int height) {
    super(x, y, width, height);
  }

  public void setXVelocity(float xVelocity) {
    this._xVelocity = xVelocity;
  }

  public void setYVelocity(float yVelocity) {
    this._yVelocity = yVelocity;
  }

  public float getXVelocity() {
    return this._xVelocity;
  }

  public float getYVelocity() {
    return this._yVelocity;
  }
}
