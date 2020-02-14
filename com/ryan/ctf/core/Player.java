package com.ryan.ctf.core;

import com.ryan.ctf.graphics.Texture;
import com.ryan.ctf.graphics.Textures;

public class Player extends Entity {

  public Player() {
    this(1f, 1f);
  }

  public Player(float x, float y) {
    super(x, y, 1f, 1f);
  }

  public Texture getTexture() {
    return Textures.getSprite();
  }
}
