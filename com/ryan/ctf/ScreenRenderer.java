package com.ryan.ctf;

import android.content.Context;

import com.ryan.ctf.core.Block;
import com.ryan.ctf.core.BlockTypes;
import com.ryan.ctf.core.Player;
import com.ryan.ctf.core.World;
import com.ryan.ctf.graphics.Programs;
import com.ryan.ctf.graphics.Textures;

public class ScreenRenderer extends Renderer {

  private static final int TICKS = 64;

  private Context _context;
  private Camera _camera;
  private Player _player;
  private World _world;

  public ScreenRenderer(Context context) {
    super(TICKS);
    this._context = context;
  }

  @Override
  protected void load() {
    super.load();
    Textures.load(this._context);
    Programs.load(this._context);

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

    this._camera.setZoom(64);
    this._player.setY(256);
    this._player.setX(64);
    this._player.setXVelocity(1);

    // Add drawables
    addDrawable(this._world);
  }

  @Override
  public void update() {
    super.update();
    this._world.update();
    this._camera.setPosition(this._player.getX(), this._player.getY());
  }

  @Override
  public void onViewportChanged(int width, int height) {
    this._camera.setViewport(width, height);
  }
}
