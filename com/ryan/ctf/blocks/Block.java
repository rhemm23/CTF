package com.ryan.ctf.blocks;

import com.ryan.ctf.graphics.Texture;

public abstract class Block {

  private final float _x;
  private final float _y;

  public Block(float x, float y) {
    this._x = x;
    this._y = y;
  }

  public float getX() {
    return this._x;
  }

  public float getY() {
    return this._y;
  }

  public abstract Texture getTexture();
}
