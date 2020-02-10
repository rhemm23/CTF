package com.ryan.ctf.blocks;

import com.ryan.ctf.graphics.Texture;
import com.ryan.ctf.graphics.Textures;

public class Rock extends Block {
  public Rock(float x, float y) {
    super(x, y);
  }

  public Texture getTexture() {
    return Textures.getRock();
  }
}
