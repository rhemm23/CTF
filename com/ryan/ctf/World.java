/*
package com.ryan.ctf;

import com.ryan.ctf.blocks.Block;
import com.ryan.ctf.blocks.WorldBoundary;

*/
/*
 * The reality of the player object
 *//*

public class World {

  private static final int DEFAULT_WORLD_WIDTH = 256;
  private static final int DEFAULT_WORLD_HEIGHT = 256;

  private static final Block WORLD_BOUNDARY = new WorldBoundary();

  private Block[][] _blocks;

  private float _gravity;

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

  */
/*
   * Gets the block at the specified position
   *//*

  public Block getBlock(int x, int y) {
    if(x < 0 || y < 0 || x >= this._width || y >= this._height) {
      return WORLD_BOUNDARY;
    }
    return this._blocks[y][x];
  }

  */
/*
   * Sets the block at the specified position
   *//*

  public void setBlock(int x, int y, Block block) {
    this._blocks[y][x] = block;
  }

  public int getHeight() {
    return this._height;
  }

  public int getWidth() {
    return this._width;
  }
}
*/
