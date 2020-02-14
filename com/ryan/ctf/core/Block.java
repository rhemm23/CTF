package com.ryan.ctf.core;

import com.ryan.ctf.graphics.Texture;

public class Block extends GameObject {

  private BlockTypes _blockType;
  private int _blockSize;

  public Block(int blockSize, BlockTypes blockType) {
    this(ORIGIN_X, ORIGIN_Y, blockSize, blockType);
  }

  public Block(int x, int y, int blockSize, BlockTypes blockType) {
    super(x, y, blockSize, blockSize);
    this._blockType = blockType;
    this._blockSize = blockSize;
  }

  public Texture getTexture() {
    return this._blockType.getTexture();
  }
}
