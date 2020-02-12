package com.ryan.ctf;

public class Player {

  // In terms of pixel steps
  private static final int RUN_SPEED = 2;
  private static final int WALK_SPEED = 1;
  private static final int JUMP_VELOCITY = 15;

  // Change in one axis (one pixel)
  private static final float PIXEL_STEP = 0.03125f;

  private boolean _isXSpeedPositive;
  private boolean _isYSpeedPositive;

  private boolean _jump;

  private int _speedX;
  private int _speedY;

  private float _x;
  private float _y;

  public void jump() {
    this._jump = true;
  }

  public void update(World world) {
    int xSign = this._isXSpeedPositive ? 1 : -1;
    int ySign = this._isYSpeedPositive ? 1 : -1;

    if(this._speedX > 0) {
      if(this._speedY > 0) {
        if(this._speedY >= this._speedX) {
          int factor = this._speedY / this._speedX;

          boolean zeroX = false;
          boolean zeroY = false;

          for(int i = 0; i < this._speedY; i++) {

            float ny = this._y;
            float nx = this._x;

            if(factor % i == 0 && !zeroX) {
              nx += xSign * PIXEL_STEP;
            }
            if(!zeroY) {
              ny += ySign * PIXEL_STEP;
            }

            if(!zeroX && horizontalCollision(nx, ny, xSign, world)) {
              setXVelocity(0);
              zeroX = true;
            } else {
              this._x = nx;
            }

            if(!zeroY && verticalCollision(nx, ny, ySign, world)) {
              setYVelocity(0);
              zeroY = true;
            } else {
              this._y = ny;
            }
          }
        } else {
          int factor = this._speedX / this._speedY;

          boolean zeroX = false;
          boolean zeroY = false;

          for(int i = 0; i < this._speedX; i++) {

            float ny = this._y;
            float nx = this._x;

            if(!zeroX) {
              nx += xSign * PIXEL_STEP;
            }
            if(factor % i == 0 && !zeroY) {
              ny += ySign * PIXEL_STEP;
            }

            if(!zeroX && horizontalCollision(nx, ny, xSign, world)) {
              setXVelocity(0);
              zeroX = true;
            } else {
              this._x = nx;
            }

            if(!zeroY && verticalCollision(nx, ny, ySign, world)) {
              setYVelocity(0);
              zeroY = true;
            } else {
              this._y = ny;
            }
          }
        }
      } else {
        for(int i = 0; i < this._speedX; i++) {
          float nx = this._x + xSign * PIXEL_STEP;
          if(horizontalCollision(nx, this._y, xSign, world)) {
            setXVelocity(0);
            break;
          } else {
            this._x = nx;
          }
        }
      }
    } else if(this._speedY > 0) {
      for(int i = 0; i < this._speedY; i++) {
        float ny = this._y + ySign * PIXEL_STEP;
        if(verticalCollision(this._x, ny, ySign, world)) {
          setYVelocity(0);
          break;
        } else {
          this._y = ny;
        }
      }
    }

    // Apply gravity
    if(!standingOnGround(world)) {
      if(this._speedY > 0) {
        if(this._isYSpeedPositive) {
          this._speedY--;
        } else {
          this._speedY++;
        }
      } else {
        this._isYSpeedPositive = false;
        this._speedY++;
      }
    } else if(this._jump) {
      this._speedY = JUMP_VELOCITY;
      this._isYSpeedPositive = true;
    }
    this._jump = false;
  }

  private boolean standingOnGround(World world) {
    int currentX = (int)Math.floor(this._x);
    return this._y % 1 == 0
        && ((world.getBlock(currentX, (int)this._y - 1) != null)
        || (this._x % 1 != 0 && (world.getBlock(currentX + 1, (int)this._y - 1) != null)));
  }

  private boolean horizontalCollision(float x, float y, int xSign, World world) {
    return xSign > 0
        ? world.getBlock((int)Math.ceil(x), (int)y) != null
        : world.getBlock((int)Math.floor(x), (int)y) != null;
  }

  private boolean verticalCollision(float x, float y, int ySign, World world) {
    return ySign > 0
        ? world.getBlock((int)x, (int)Math.ceil(y)) != null
        : world.getBlock((int)x, (int)Math.floor(y)) != null;
  }

  public void setXVelocity(int xVelocity) {
    this._speedX = Math.abs(xVelocity);
    this._isXSpeedPositive = this._speedX >= 0;
  }

  public void setYVelocity(int yVelocity) {
    this._speedY = Math.abs(yVelocity);
    this._isYSpeedPositive = this._speedY >= 0;
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
