package com.ryan.ctf;

import android.opengl.GLES30;
import android.opengl.GLSurfaceView;

import com.ryan.ctf.core.IDrawable;

import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public abstract class Renderer implements GLSurfaceView.Renderer {

  private ArrayList<IDrawable> _drawables;

  private float[] _viewProjectionMatrix;
  private float _unprocessedTime;
  private float _secondsPerTick;
  private long _previousTime;

  public Renderer(int ticksPerSecond) {
    this._secondsPerTick = 1f / ticksPerSecond;
    this._drawables = new ArrayList<>();
  }

  protected void load() {
    // Setup opengl
    GLES30.glEnable(GLES30.GL_BLEND);
    GLES30.glBlendFunc(GLES30.GL_SRC_ALPHA, GLES30.GL_ONE_MINUS_SRC_ALPHA);
    GLES30.glClearColor(0f, 0f, 0f, 1f);
  }

  protected void update() { }

  protected void onViewportChanged(int width, int height) { }

  protected void addDrawable(IDrawable drawable) {
    this._drawables.add(drawable);
  }

  protected void setViewProjectionMatrix(float[] viewProjectionMatrix) {
    this._viewProjectionMatrix = viewProjectionMatrix;
  }

  public void onSurfaceCreated(GL10 unused, EGLConfig config) {
    load();
  }

  public void onDrawFrame(GL10 unused) {
    // Handle tick
    if(this._previousTime == 0) {
      this._previousTime = System.nanoTime();
    } else {
      // Update time
      long currentTime = System.nanoTime();
      long elapsed = currentTime - this._previousTime;
      this._previousTime = currentTime;

      // Handle tick
      this._unprocessedTime += elapsed / 1000000000f;
      if(this._unprocessedTime >= this._secondsPerTick) {
        this._unprocessedTime -= this._secondsPerTick;
        update();
      }
    }

    // Draw drawables
    GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT);
    for(IDrawable drawable : this._drawables) {
      drawable.draw(this._viewProjectionMatrix);
    }
  }

  public void onSurfaceChanged(GL10 unused, int width, int height) {
    onViewportChanged(width, height);
  }
}
