package com.ryan.ctf;

import com.ryan.ctf.blocks.Block;

/*
 * The reality of the player object
 */
public class World {

  private static final int DEFAULT_WORLD_WIDTH = 256;
  private static final int DEFAULT_WORLD_HEIGHT = 256;

  private static final float DEFAULT_WORLD_GRAVITY = 0.0042f;

  private Block[][] _blocks;

  private float _gravity;

  private int _height;
  private int _width;

  public World() {
    this(DEFAULT_WORLD_WIDTH, DEFAULT_WORLD_HEIGHT);

  }

  public World(int width, int height) {
    this._blocks = new Block[height][width];
    this._gravity = DEFAULT_WORLD_GRAVITY;
    this._height = height;
    this._width = width;
  }

  /*
   * Gets the block at the specified position
   */
  public Block getBlock(int x, int y) {
    return this._blocks[y][x];
  }

  /*
   * Sets the block at the specified position
   */
  public void setBlock(int x, int y, Block block) {
    this._blocks[y][x] = block;
  }

  public int getHeight() {
    return this._height;
  }

  public int getWidth() {
    return this._width;
  }

  public void setGravity(float gravity) {
    this._gravity = gravity;
  }

  public float getGravity() {
    return this._gravity;
  }
}
