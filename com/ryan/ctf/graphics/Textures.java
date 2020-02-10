package com.ryan.ctf.graphics;

import android.content.Context;

public class Textures {

  private static Texture wood;
  private static Texture rock;
  private static Texture dirt;
  private static Texture sprite;

  public static void load(Context context) {
    wood = Texture.loadFromAsset(context, "textures/wood32.png");
    rock = Texture.loadFromAsset(context, "textures/rock32.png");
    dirt = Texture.loadFromAsset(context, "textures/dirt32.png");
    sprite = Texture.loadFromAsset(context, "textures/sprite.png");
  }

  public static Texture getSprite() {
    return sprite;
  }

  public static Texture getRock() {
    return rock;
  }

  public static Texture getDirt() {
    return dirt;
  }

  public static Texture getWood() {
    return wood;
  }
}
