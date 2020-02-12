package com.ryan.ctf;

import android.opengl.Matrix;

/*
 * Camera that looks at the z = 0 plane
 */
public class Camera {

  private static final float DEFAULT_CAMERA_ZOOM = 1f;
  private static final float CAMERA_Z_NEAR = 3f;
  private static final float CAMERA_Z_FAR = 4f;

  // View matrices
  private float[] _viewProjectionMatrix;
  private float[] _projectionMatrix;
  private float[] _viewMatrix;

  // Dimensions of viewport
  private float _height;
  private float _width;

  // Ratio of width/height
  private float _ratio;
  private float _zoom;

  // Camera position
  private float _x;
  private float _y;

  public Camera() {
    this._viewProjectionMatrix = new float[16];
    this._projectionMatrix = new float[16];
    this._viewMatrix = new float[16];
    this._zoom = DEFAULT_CAMERA_ZOOM;
  }

  public Camera(int width, int height) {
    // Variable defaults
    this();

    // Set initial viewport
    updateViewport(width, height);
  }

  public Camera(float x, float y, int width, int height) {
    // Variable defaults
    this();

    // Initial viewport / position
    updateViewport(width, height);
    updateCameraPosition(x, y);
  }

  private void updateProjectionMatrix(float ratio, float zoom) {
    // Update normalized variables
    this._ratio = ratio;
    this._zoom = zoom;

    // Calc sizes
    float xSize = this._ratio * this._zoom;
    float ySize = this._zoom;

    // Calc the new projection matrix
    Matrix.frustumM(this._projectionMatrix, 0, -xSize,
        xSize, -ySize, ySize, CAMERA_Z_NEAR, CAMERA_Z_FAR);
  }

  /*
   * Performs calculations for new viewport
   */
  private void updateViewport(int width, int height) {
    // Update viewport dimensions
    this._height = height;
    this._width = width;

    // Update projection
    updateProjectionMatrix((float)width / height, this._zoom);
  }

  /*
   * Performs calculations for new camera position
   */
  private void updateCameraPosition(float x, float y) {
    // Position updates
    this._x = x;
    this._y = y;

    // Update view matrix
    Matrix.setLookAtM(this._viewMatrix, 0, this._x, this._y, CAMERA_Z_NEAR,
        this._x, this._y, 0f, 0f, 1f, 0f);

    // Update view projection matrix
    Matrix.multiplyMM(this._viewProjectionMatrix, 0, this._projectionMatrix,
        0, this._viewMatrix, 0);
  }

  /*
   * Sets the position of the camera
   */
  public void setPosition(float x, float y) {
    updateCameraPosition(x, y);
  }

  /*
   * Sets the dimensions of the viewport
   */
  public void setViewport(int width, int height) {
    updateViewport(width, height);
  }

  /*
   * Sets the camera's zoom
   */
  public void setZoom(float zoom) {
    updateProjectionMatrix(this._ratio, zoom);
  }

  /*
   * Gets the camera's view projection matrix
   */
  public float[] getViewProjectionMatrix() {
    return this._viewProjectionMatrix;
  }
}