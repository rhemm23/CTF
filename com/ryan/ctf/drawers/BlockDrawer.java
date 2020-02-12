package com.ryan.ctf.drawers;

import com.ryan.ctf.blocks.Block;

/*
 * Draws a block's texture to the screen in the z = 0 plane
 */
public class BlockDrawer implements IDrawable {

  private TextureDrawer _textureDrawer;
  private Block _block;

  public BlockDrawer() { }

  public BlockDrawer(Block block) {
    updateBlock(block);
  }

  /*
   * Update the block reference, and create a new
   * texture drawer
   */
  private void updateBlock(Block block) {
    this._block = block;
    this._textureDrawer = new TextureDrawer(
        block.getX(), block.getY(), block.getTexture());
  }

  /*
   * Set the current block
   */
  public void setBlock(Block block) {
    updateBlock(block);
  }

  /*
   * Draw the block to the screen
   */
  public void draw(float[] viewProjectionMatrix) {
    this._textureDrawer.draw(viewProjectionMatrix);
  }
}
