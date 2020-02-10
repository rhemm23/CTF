package com.ryan.ctf.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES30;
import android.opengl.GLUtils;

import java.io.IOException;

public class Texture {

  private int _texture;

  private Texture() { }

  public int getId() {
    return this._texture;
  }

  public static Texture loadFromAsset(Context context, String file) {
    Texture texture = new Texture();
    int[] textureHandle = new int[1];
    GLES30.glGenTextures(1, textureHandle, 0);
    if(textureHandle[0] != 0) {
      try {
        Bitmap bitmap = BitmapFactory.decodeStream(context.getAssets().open(file));
        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, textureHandle[0]);
        GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MIN_FILTER, GLES30.GL_NEAREST);
        GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MAG_FILTER, GLES30.GL_NEAREST);
        GLUtils.texImage2D(GLES30.GL_TEXTURE_2D, 0, bitmap, 0);
        bitmap.recycle();
      } catch(IOException e) {
        throw new RuntimeException("Error loading texture", e);
      }
    } else {
      throw new RuntimeException("Error loading texture");
    }
    texture._texture = textureHandle[0];
    return texture;
  }
}
