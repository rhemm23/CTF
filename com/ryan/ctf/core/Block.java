package com.ryan.ctf.core;

import com.ryan.ctf.graphics.Texture;

public class Block extends GameObject {

  private static final int BLOCK_SIZE = 32;

  private BlockTypes _blockType;

  public Block(BlockTypes blockType) {
    this(ORIGIN_X, ORIGIN_Y, blockType);
  }

  public Block(int x, int y, BlockTypes blockType) {
    super(x, y, BLOCK_SIZE, BLOCK_SIZE);
    this._blockType = blockType;
  }

  public Texture getTexture() {
    return this._blockType.getTexture();
  }
}
