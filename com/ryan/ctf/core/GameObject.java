package com.ryan.ctf.core;

import android.opengl.GLES30;

import com.ryan.ctf.graphics.Texture;
import com.ryan.ctf.graphics.TexturedVerticesProgram;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public abstract class GameObject implements IDrawable {

  private static final float[] textureCoordinates = {
      0f, 1f,
      0f, 0f,
      1f, 1f,
      1f, 0f
  };

  // Same buffer for all textures
  private static final FloatBuffer textureCoordinateBuffer;

  static {
    // Setup texture coordinate buffer
    textureCoordinateBuffer = ByteBuffer
        .allocateDirect(textureCoordinates.length * 4)
        .order(ByteOrder.nativeOrder())
        .asFloatBuffer();

    // Put the default array of coordinates
    textureCoordinateBuffer.put(textureCoordinates);
    textureCoordinateBuffer.rewind();
  }

  private TexturedVerticesProgram _shaderProgram;
  private FloatBuffer _positionBuffer;

  private float _x;
  private float _y;

  private final float _height;
  private final float _width;

  public GameObject(float width, float height) {
    this(0f, 0f, width, height);
  }

  public GameObject(float x, float y, float width, float height) {
    this._shaderProgram = TexturedVerticesProgram.getInstance();
    this._height = height;
    this._width = width;

    // Setup 3d position buffer
    this._positionBuffer = ByteBuffer
        .allocateDirect(48)
        .order(ByteOrder.nativeOrder())
        .asFloatBuffer();

    // Set w/ buffer update
    setX(x);
    setY(y);
  }

  protected abstract Texture getTexture();

  public void setX(float x) {
    this._x = x;

    // Update 3d space
    this._positionBuffer.put(0, this._x);
    this._positionBuffer.put(3, this._x);
    this._positionBuffer.put(6, this._x + this._width);
    this._positionBuffer.put(9, this._x + this._width);
  }

  public void setY(float y) {
    this._y = y;

    // Update 3d space
    this._positionBuffer.put(1, this._y);
    this._positionBuffer.put(4, this._y + this._height);
    this._positionBuffer.put(7, this._y);
    this._positionBuffer.put(10, this._y + this._height);
  }

  public float getX() {
    return this._x;
  }

  public float getY() {
    return this._y;
  }

  public float getWidth() {
    return this._width;
  }

  public float getHeight() {
    return this._height;
  }

  /*
   * Determines if two game objects collide
   */
  public boolean collidesWith(GameObject obj) {
    return this._x < obj._x + obj._width
        && this._x + this._width > obj._x
        && this._y < obj._y + obj._height
        && this._y + this._height > obj._y;
  }

  /*
   * Draw the object to the screen
   */
  public void draw(float[] viewProjectionMatrix) {
    this._shaderProgram.use();
    this._shaderProgram.setPositionPointer(this._positionBuffer);
    this._shaderProgram.setTextureCoordinatePointer(textureCoordinateBuffer);
    this._shaderProgram.setTexture(getTexture());
    this._shaderProgram.setViewProjectionMatrix(viewProjectionMatrix);
    GLES30.glDrawArrays(GLES30.GL_TRIANGLE_STRIP, 0, 4);
    this._shaderProgram.disableAttributes();
  }
}
