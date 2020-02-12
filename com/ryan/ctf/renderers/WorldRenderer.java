package com.ryan.ctf.renderers;

import android.opengl.GLSurfaceView;

import com.ryan.ctf.World;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class WorldRenderer implements GLSurfaceView.Renderer {

  private World _world;

  public WorldRenderer() { }

  public WorldRenderer(World world) {
    this._world = world;
  }

  public void setWorld(World world) {
    this._world = world;
  }

  public void onSurfaceCreated(GL10 unused, EGLConfig config) {

  }

  public void onDrawFrame(GL10 unused) {

  }

  public void onSurfaceChanged(GL10 unused, int width, int height) {

  }
}
