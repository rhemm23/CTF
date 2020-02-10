package com.ryan.ctf;

import android.opengl.GLES30;
import android.opengl.Matrix;

public class Camera {

  private static final float DEFAULT_ZOOM = 3.0f;

  private float[] _viewProjectionMatrix;
  private float[] _projectionMatrix;
  private float[] _viewMatrix;
  private int[] _viewBounds;

  private float _cameraX;
  private float _cameraY;

  private float _maxX;
  private float _minX;
  private float _maxY;
  private float _minY;
  private float _zoom;
  private float _ratio;

  public Camera() {
    this._viewProjectionMatrix = new float[16];
    this._projectionMatrix = new float[16];
    this._viewMatrix = new float[16];
    this._viewBounds = new int[4];
    this._zoom = DEFAULT_ZOOM;
  }

  public void setZoom(float zoom) {
    this._zoom = zoom;
    updateViewport();
  }

  public float getZoom() {
    return this._zoom;
  }

  private void updateViewport() {
    // Update bounds
    this._minX = this._ratio * this._zoom;
    this._maxX = World.WORLD_SIZE - (this._ratio * this._zoom);
    this._minY = this._zoom;
    this._maxY = World.WORLD_SIZE - this._zoom;

    // Create projection matrix
    Matrix.frustumM(_projectionMatrix, 0, -this._ratio * this._zoom,
        this._ratio * this._zoom, -this._zoom, this._zoom, 3, 4);
  }

  public void setViewDimensions(int width, int height) {
    GLES30.glViewport(0, 0, width, height);
    this._ratio = (float)width / height;
    updateViewport();
  }

  public void setTargetLocation(float x, float y) {
    this._cameraX = Math.min(Math.max(x, this._minX), this._maxX);
    this._cameraY = Math.min(Math.max(y, this._minY), this._maxY);

    // View
    Matrix.setLookAtM(this._viewMatrix, 0, this._cameraX, this._cameraY, 3.0f,
        this._cameraX, this._cameraY, 0.0f, 0.0f, 1.0f, 0.0f);

    // Projection
    Matrix.multiplyMM(this._viewProjectionMatrix, 0, this._projectionMatrix,
        0, this._viewMatrix, 0);
  }

  public int[] getViewBounds() {
    this._viewBounds[0] = (int)Math.max(0, Math.floor(this._cameraX - _ratio * _zoom));
    this._viewBounds[1] = (int)Math.min(World.WORLD_SIZE - 1, Math.ceil(this._cameraX + _ratio * _zoom));
    this._viewBounds[2] = (int)Math.max(0, Math.floor(this._cameraY - _zoom));
    this._viewBounds[3] = (int)Math.min(World.WORLD_SIZE - 1, Math.ceil(this._cameraY + _zoom));
    return this._viewBounds;
  }

  public float[] getViewProjectionMatrix() {
    return this._viewProjectionMatrix;
  }

  public float[] getViewMatrix() {
    return this._viewMatrix;
  }
}
