package com.ryan.ctf.blocks;

import com.ryan.ctf.blocks.Block;
import com.ryan.ctf.graphics.Texture;
import com.ryan.ctf.graphics.Textures;

public class Wood extends Block {
  public Wood(float x, float y) {
    super(x, y);
  }

  public Texture getTexture() {
    return Textures.getWood();
  }
}
