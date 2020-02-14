package com.ryan.ctf;

import android.content.Context;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;

import com.ryan.ctf.core.Block;
import com.ryan.ctf.core.BlockTypes;
import com.ryan.ctf.core.Player;
import com.ryan.ctf.core.World;
import com.ryan.ctf.graphics.Programs;
import com.ryan.ctf.graphics.Textures;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class ScreenRenderer implements GLSurfaceView.Renderer {

  public static final int TICKS = 64;
  public static final float SECONDS_PER_TICK = 1f / TICKS;

  private float _unprocessedTime;
  private long _previousTime;
  private Context _context;
  private Camera _camera;
  private Player _player;
  private World _world;

  public ScreenRenderer(Context context) {
    this._unprocessedTime = 0f;
    this._context = context;
  }

  private void load(Context context) {
    Textures.load(context);
    Programs.load(context);
  }

  public void onSurfaceCreated(GL10 unused, EGLConfig config) {
    GLES30.glEnable(GLES30.GL_BLEND);
    GLES30.glBlendFunc(GLES30.GL_SRC_ALPHA, GLES30.GL_ONE_MINUS_SRC_ALPHA);
    GLES30.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    load(this._context);

    // Setup objects
    this._camera = new Camera();
    this._player = new Player();
    this._world = new World(32);
    this._world.addGameObject(this._player);

    for(int i = 0; i < 20; i++) {
      this._world.addGameObject(new Block(i * 32, 0, 32, BlockTypes.DIRT));
      this._world.addGameObject(new Block(i * 32, 32, 32, BlockTypes.ROCK));
      this._world.addGameObject(new Block(i * 32, 64, 32, BlockTypes.WOOD));
    }

    this._player.setY(256);
  }

  public void tick() {
    this._world.update();
    this._camera.setPosition(this._player.getX(), this._player.getY());
  }

  public void onDrawFrame(GL10 unused) {
    if(this._previousTime == 0) {
      this._previousTime = System.nanoTime();
    } else {
      // Update time
      long currentTime = System.nanoTime();
      long elapsed = currentTime - this._previousTime;
      this._previousTime = currentTime;

      // Handle tick
      this._unprocessedTime += elapsed / 1000000000f;
      if(this._unprocessedTime >= SECONDS_PER_TICK) {
        this._unprocessedTime -= SECONDS_PER_TICK;
        tick();
      }
    }

    GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT);
    this._world.draw(this._camera.getViewProjectionMatrix(), this._camera.getViewBounds());
  }

  public void onSurfaceChanged(GL10 unused, int width, int height) {
    this._camera.setViewport(width, height);
    this._camera.setZoom(64);
  }
}
