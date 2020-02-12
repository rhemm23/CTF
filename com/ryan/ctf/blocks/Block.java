package com.ryan.ctf.blocks;

import com.ryan.ctf.graphics.Texture;

/*
 * A basic square component that makes up the game's world
 */
public abstract class Block {

  private final int _x;
  private final int _y;

  public Block(int x, int y) {
    this._x = x;
    this._y = y;
  }

  public int getX() {
    return this._x;
  }

  public int getY() {
    return this._y;
  }

  public abstract Texture getTexture();
}
