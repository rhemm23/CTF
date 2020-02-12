package com.ryan.ctf;

import com.ryan.ctf.blocks.Block;

public class World {

  private static final int DEFAULT_WORLD_WIDTH = 256;
  private static final int DEFAULT_WORLD_HEIGHT = 256;

  private Block[][] _blocks;

  private int _height;
  private int _width;

  public World() {
    this(DEFAULT_WORLD_WIDTH, DEFAULT_WORLD_HEIGHT);
  }

  public World(int width, int height) {
    this._blocks = new Block[height][width];
    this._height = height;
    this._width = width;
  }

  /*
   * Gets the block position as specific coordinates
   */
  public Block getBlock(int x, int y) {
    return this._blocks[y][x];
  }

  public int getHeight() {
    return this._height;
  }

  public int getWidth() {
    return this._width;
  }
}
