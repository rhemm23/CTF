package com.ryan.ctf.core;

public abstract class Entity extends GameObject {

  private float _xVelocity;
  private float _yVelocity;

  public Entity(float width, float height) {
    super(width, height);
  }

  public Entity(float x, float y, float width, float height) {
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
