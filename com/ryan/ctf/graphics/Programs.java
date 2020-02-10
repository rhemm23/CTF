package com.ryan.ctf.graphics;

import android.content.Context;

public class Programs {
  public static void load(Context context) {
    TexturedVerticesProgram.assureInstance(context);
  }
}
