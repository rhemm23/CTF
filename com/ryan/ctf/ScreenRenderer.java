package com.ryan.ctf;

import android.content.Context;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

import com.ryan.ctf.blocks.Block;
import com.ryan.ctf.blocks.Dirt;
import com.ryan.ctf.blocks.Rock;
import com.ryan.ctf.blocks.Wood;
import com.ryan.ctf.graphics.Programs;
import com.ryan.ctf.drawers.TextureDrawer;
import com.ryan.ctf.graphics.Textures;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class ScreenRenderer implements GLSurfaceView.Renderer {

  public static final int TICKS = 64;
  public static final float SECONDS_PER_TICK = 1f / TICKS;

  private TextureDrawer _textureDrawer;

  private Joysticks _joystick;

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

  public void movePlayer(float dx, float dy) {
    this._player.move(dx, dy);
  }

  private void load(Context context) {
    Textures.load(context);
    Programs.load(context);
  }

  public void processTouchEvent(MotionEvent event) {
    if(!this._joystick.processTouchEvent(event)) {
      this._player.jump();
    }
  }

  public void onSurfaceCreated(GL10 unused, EGLConfig config) {
    GLES30.glEnable(GLES30.GL_BLEND);
    GLES30.glBlendFunc(GLES30.GL_SRC_ALPHA, GLES30.GL_ONE_MINUS_SRC_ALPHA);
    GLES30.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    load(this._context);

    // Setup objects
    this._textureDrawer = new TextureDrawer();
    this._camera = new Camera();
    this._player = new Player();
    this._world = new World();

    // Build world
    for(int i = 0; i < World.WORLD_SIZE; i++) {
      this._world.Blocks[0][i] = new Rock(i, 0);
      this._world.Blocks[1][i] = new Dirt(i, 1);
      this._world.Blocks[2][i] = new Wood(i, 2);
    }

    this._player.setY(3);
    this._camera.setTargetLocation(_player.getX(), _player.getY());
  }

  public void tick() {
    this._player.update(this._world);
    this._camera.setTargetLocation(_player.getX(), _player.getY());
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

    // Construct view bounds
    int[] bounds = this._camera.getViewBounds();

    // Render visible blocks
    for(int y = bounds[2]; y < bounds[3]; y++) {
      for(int x = bounds[0]; x < bounds[1]; x++) {
        Block block = this._world.Blocks[y][x];
        if(block != null) {
          this._textureDrawer.draw(block.getX(), block.getY(), block.getTexture(), vpMatrix);
        }
      }
    }

    this._textureDrawer.draw(_player.getX(), _player.getY(), Textures.getSprite(), vpMatrix);
    this._joystick.draw();
  }

  public void onSurfaceChanged(GL10 unused, int width, int height) {
    this._camera.setViewDimensions(width, height);
    this._joystick = new Joysticks(width, height, this._player);
  }
}
