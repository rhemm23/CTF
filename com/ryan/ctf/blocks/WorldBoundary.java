package com.ryan.ctf.blocks;

import com.ryan.ctf.graphics.Texture;

public class WorldBoundary extends Block {
  public WorldBoundary() {
    super(0f, 0f);
  }

  public Texture getTexture() {
    throw new RuntimeException("World boundary does not have a texture");
  }
}
