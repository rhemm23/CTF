package com.ryan.ctf;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class ScreenView extends GLSurfaceView {

  private final ScreenRenderer renderer;

  public ScreenView(Context context){
    super(context);
    setEGLContextClientVersion(3);
    renderer = new ScreenRenderer(context);
    setRenderer(renderer);
  }
}
