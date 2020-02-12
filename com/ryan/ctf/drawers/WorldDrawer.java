package com.ryan.ctf.drawers;

import com.ryan.ctf.World;
import com.ryan.ctf.math.Rectanglei;

/*
 * Draws specified blocks of a world
 */
public class WorldDrawer implements IDrawable {

  private BlockDrawer[][] _blockDrawers;
  private Rectanglei _renderBounds;
  private World _world;

  public WorldDrawer() { }

  public WorldDrawer(World world, Rectanglei renderBounds) {
    this._renderBounds = renderBounds;
    updateWorld(world);
  }

  private void updateWorld(World world) {
    // Update world
    this._world = world;

    // Update drawer array size
    this._blockDrawers = new BlockDrawer[world.getHeight()][world.getWidth()];

    // Build texture drawers
    for(int y = 0; y < world.getHeight(); y++) {
      for(int x = 0; x < world.getWidth(); x++) {
        if(this._world.getBlock(x, y) != null) {
          this._blockDrawers[y][x] = new BlockDrawer(this._world.getBlock(x, y));
        }
      }
    }
  }

  /*
   * Set the world to render
   */
  public void setWorld(World world) {
    updateWorld(world);
  }

  /*
   * Set the bounds of the world of which to render
   */
  public void setRenderBounds(Rectanglei renderBounds) {
    this._renderBounds = renderBounds;
  }

  /*
   * Draw all blocks in the world within the render bounds to the screen
   */
  public void draw(float[] viewProjectionMatrix) {
    for(int y = this._renderBounds.getY1(); y < this._renderBounds.getY2(); y++) {
      for(int x = this._renderBounds.getX1(); x < this._renderBounds.getX2(); x++) {
        if(this._blockDrawers[y][x] != null) {
          this._blockDrawers[y][x].draw(viewProjectionMatrix);
        }
      }
    }
  }
}
