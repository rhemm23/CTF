package com.ryan.ctf;

import android.opengl.Matrix;

import com.ryan.ctf.math.Rectanglef;

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

  // What the camera sees
  private Rectanglef _viewBounds;

  // Ratio of width/height
  private float _ratio;
  private float _zoom;

  // Camera position
  private float _x;
  private float _y;

  public Camera() {
    this._viewProjectionMatrix = new float[16];
    this._projectionMatrix = new float[16];
    this._viewBounds = new Rectanglef();
    this._viewMatrix = new float[16];
    this._zoom = DEFAULT_CAMERA_ZOOM;
  }

  public Camera(int width, int height) {
    this();
    updateProjectionMatrix((float)width / height, this._zoom);
  }

  public Camera(float x, float y, int width, int height) {
    this(width, height);
    updateCameraPosition(x, y);
  }

  /*
   * Updates the bounds of the camera's view
   */
  private void updateViewBounds() {
    this._viewBounds.X1 = -this._ratio * this._zoom + this._x;
    this._viewBounds.X2 = this._ratio * this._zoom + this._x;
    this._viewBounds.Y1 = -this._zoom + this._y;
    this._viewBounds.Y2 = this._zoom + this._y;
  }

  /*
   * Update the projection matrix
   */
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

    // Find new bounds
    updateViewBounds();
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

    // Find new bounds
    updateViewBounds();
  }

  public Rectanglef getViewBounds() {
    return this._viewBounds;
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
    updateProjectionMatrix((float)width / height, this._zoom);
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