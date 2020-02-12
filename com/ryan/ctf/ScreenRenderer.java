package com.ryan.ctf;

import android.content.Context;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;

import com.ryan.ctf.blocks.Dirt;
import com.ryan.ctf.blocks.Rock;
import com.ryan.ctf.blocks.Wood;
import com.ryan.ctf.drawers.WorldDrawer;
import com.ryan.ctf.graphics.Programs;
import com.ryan.ctf.drawers.TextureDrawer;
import com.ryan.ctf.graphics.Textures;
import com.ryan.ctf.math.Rectanglei;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class ScreenRenderer implements GLSurfaceView.Renderer {

  public static final int TICKS = 64;
  public static final float SECONDS_PER_TICK = 1f / TICKS;

  private Rectanglei _renderBounds;
  private WorldDrawer _worldDrawer;
  private TextureDrawer _textureDrawer;

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

  public void jump() {
    this._player.jump();
  }

  public void onSurfaceCreated(GL10 unused, EGLConfig config) {
    GLES30.glEnable(GLES30.GL_BLEND);
    GLES30.glBlendFunc(GLES30.GL_SRC_ALPHA, GLES30.GL_ONE_MINUS_SRC_ALPHA);
    GLES30.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    load(this._context);

    // Setup objects
    this._camera = new Camera();
    this._player = new Player();
    this._world = new World();

    // Build world
    for(int i = 0; i < this._world.getWidth(); i++) {
      this._world.setBlock(i, 0, new Rock(i, 0));
      this._world.setBlock(i, 1, new Dirt(i, 1));
      this._world.setBlock(i, 2, new Wood(i, 2));
    }

    this._world.setBlock(7, 3, new Rock(7, 3));

    this._player.setY(5);
    //this._player.setXVelocity(2);
    _renderBounds = new Rectanglei(0, 0, 10, 10);
    this._worldDrawer = new WorldDrawer(_world, _renderBounds);
    this._camera.setPosition(_player.getX(), _player.getY());
    this._textureDrawer = new TextureDrawer(_player.getX(), _player.getY(), Textures.getSprite());
  }

  public void tick() {
    this._player.update(this._world);
    this._textureDrawer.setPosition(_player.getX(), _player.getY());
    this._camera.setPosition(_player.getX(), _player.getY());
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
      this._unprocessedTime += elapsed / 1000000000.0;
      if(this._unprocessedTime > SECONDS_PER_TICK) {
        this._unprocessedTime -= SECONDS_PER_TICK;
        tick();
      }
    }

    GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT);
    float[] vpMatrix = this._camera.getViewProjectionMatrix();
    this._worldDrawer.draw(vpMatrix);
    this._textureDrawer.draw(vpMatrix);
  }

  public void onSurfaceChanged(GL10 unused, int width, int height) {
    this._camera.setViewport(width, height);
    this._camera.setZoom(2);
  }
}
