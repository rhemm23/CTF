package com.ryan.ctf;

public class Player {

  public static final float JUMP_VELOCITY = 0.1f;
  public static final float GRAVITY = 0.0042f;

  private boolean _jumpHandle;
  private float _velocityX;
  private float _velocityY;
  private float _x;
  private float _y;

  public Player() {
    this._velocityX = 0.015625f;
    this._velocityY = 0f;
    this._x = 0f;
    this._y = 0f;
  }

  public void move(float dx, float dy) {
    this._x += dx;
    this._y += dy;
  }

  private boolean verticalCollision(float x, float y) {
    return this._x < (x + 1)
        && x < (this._x + 1)
        && this._y <= (y + 1)
        && y <= (this._y + 1);
  }

  private boolean horizontalCollision(float x, float y) {
    return this._x <= (x + 1)
        && x <= (this._x + 1)
        && this._y < (y + 1)
        && y < (this._y + 1);
  }

  public void jump() {
    this._jumpHandle = true;
  }

  private boolean isBlockSolid(World world, int x, int y) {
    return (x < 0) ||
        (y < 0) ||
        (x > World.WORLD_SIZE - 1) ||
        (y > World.WORLD_SIZE - 1) ||
        (world.Blocks[y][x] != null);
  }

  public void update(World world) {
    // Calculate collision ranges
    int minX = (int)Math.ceil(this._x - 1);
    int maxX = (int)Math.floor(this._x + 1);
    int minY = (int)Math.ceil(this._y - 1);
    int maxY = (int)Math.floor(this._y + 1);

    // Left, right, up, down
    boolean upMobility = true;
    boolean downMobility = true;
    boolean leftMobility = true;
    boolean rightMobility = true;

    // Check collision candidates
    for(int x = minX; x < maxX; x++) {
      for(int y = minY; y < maxY; y++) {
        if(isBlockSolid(world, x, y)) {
          // Calculate if we can pass horizontally / vertically
          boolean horizontalPass = !horizontalCollision(x, y);
          boolean verticalPass = !verticalCollision(x, y);

          // Update mobilities
          upMobility &= (y < this._y || verticalPass);
          downMobility &= (y > this._y || verticalPass);
          leftMobility &= (x > this._x || horizontalPass);
          rightMobility &= (x < this._x || horizontalPass);
        }
      }
    }

    if(this._velocityX > 0f) {
      if(!rightMobility) {
        this._x = (float)Math.floor(this._x);
        this._velocityX = 0f;
      }
    } else if(this._velocityX < 0f) {
      if(!leftMobility) {
        this._x = (float)Math.ceil(this._x);
        this._velocityX = 0f;
      }
    }

    if(this._velocityY > 0f) {
      if(!upMobility) {
        this._y = (float)Math.floor(this._y);
        this._velocityY = 0f;
      }
    }

    if(downMobility) {
      this._velocityY -= GRAVITY;
    } else {
      this._y = (float)Math.ceil(this._y);
      if(this._jumpHandle) {
        this._velocityY = JUMP_VELOCITY;
      } else {
        this._velocityY = 0f;
      }
    }

    // Update positions
    this._y += this._velocityY;
    this._x += this._velocityX;
    this._jumpHandle = false;
  }

  public void setX(float x) {
    this._x = x;
  }

  public void setY(float y) {
    this._y = y;
  }

  public float getX() {
    return this._x;
  }

  public float getY() {
    return this._y;
  }
}
