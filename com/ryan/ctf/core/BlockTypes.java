package com.ryan.ctf.core;

import com.ryan.ctf.graphics.Texture;
import com.ryan.ctf.graphics.Textures;

public enum BlockTypes {

  DIRT (Textures.getDirt()),
  ROCK (Textures.getRock()),
  WOOD (Textures.getWood());

  private final Texture _texture;

  private BlockTypes(Texture texture) {
    this._texture = texture;
  }

  public Texture getTexture() {
    return this._texture;
  }
}
