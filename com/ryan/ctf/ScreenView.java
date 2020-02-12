package com.ryan.ctf;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class ScreenView extends GLSurfaceView {

  private final ScreenRenderer renderer;

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    renderer.jump();
    return super.onTouchEvent(event);
  }

  public ScreenView(Context context){
    super(context);
    setEGLContextClientVersion(3);
    renderer = new ScreenRenderer(context);
    setRenderer(renderer);
  }
}
