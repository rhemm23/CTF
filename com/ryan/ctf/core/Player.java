package com.ryan.ctf.core;

import com.ryan.ctf.graphics.Texture;
import com.ryan.ctf.graphics.Textures;

public class Player extends Entity {

  private static final int PLAYER_SIZE = 32;

  public Player() {
    this(ORIGIN_X, ORIGIN_Y);
  }

  public Player(int x, int y) {
    super(x, y, PLAYER_SIZE, PLAYER_SIZE);
  }

  public Texture getTexture() {
    return Textures.getSprite();
  }
}
