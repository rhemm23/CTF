package com.ryan.ctf.graphics;

import android.content.Context;

/*
 * A collection of all shader programs used
 */
public class Programs {

  /*
   * Loads all shader programs
   */
  public static void load(Context context) {
    TexturedVerticesProgram.assureInstance(context);
    ColoredVerticesProgram.assureInstance(context);
  }
}
