package com.ryan.ctf.graphics;

import android.content.Context;
import android.opengl.GLES30;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * A graphics shader program
 */
public class Shader {

  private static final String TAG = Shader.class.getName();
  private int _shader;

  private Shader() { }

  /*
   * Gets the OpenGL shader ID
   */
  public int getId() {
    return this._shader;
  }

  /*
   * Loads the shader object from a file asset
   */
  public static Shader loadFromAsset(Context context, String file, int type) {
    Shader shader = new Shader();
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(
          new InputStreamReader(context.getAssets().open(file), "UTF-8"));
      String line;
      StringBuilder sb = new StringBuilder();
      while((line = reader.readLine()) != null) {
        sb.append(line);
      }
      shader._shader = GLES30.glCreateShader(type);
      GLES30.glShaderSource(shader._shader, sb.toString());
      GLES30.glCompileShader(shader._shader);
      return shader;
    } catch (IOException e) {
      Log.e(TAG, "Failed to open asset reader", e);
    } finally {
      if(reader != null) {
        try {
          reader.close();
        } catch(IOException e) {
          Log.e(TAG, "Failed to close asset reader", e);
        }
      }
    }
    return null;
  }
}
