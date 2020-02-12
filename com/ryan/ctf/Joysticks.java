package com.ryan.ctf;

import android.opengl.Matrix;
import android.view.MotionEvent;

import com.ryan.ctf.graphics.CircleDrawer;

public class Joysticks {

  private float[] _viewProjectionMatrix;

  private Player _player;

  private CircleDrawer _rightOutlineDrawer;
  private CircleDrawer _leftOutlineDrawer;

  private CircleDrawer _rightFocusDrawer;
  private CircleDrawer _leftFocusDrawer;

  public Joysticks(int width, int height, Player player) {
    this._viewProjectionMatrix = new float[16];
    this._player = player;

    // Temp matrices
    float[] projectionMatrix = new float[16];
    float[] viewMatrix = new float[16];

    // Look at center
    Matrix.setLookAtM(viewMatrix, 0, 0f, 0f, 3f,
        0f, 0f, 0f, 0f, 1f, 0f);

    // Setup custom projection
    float ratio = (float)width / height;
    Matrix.frustumM(projectionMatrix, 0, -ratio,
        ratio, -1, 1, 3, 4);

    Matrix.multiplyMM(this._viewProjectionMatrix,0, projectionMatrix,
        0, viewMatrix, 0);

    // Calc
    float focusRadius = 0.15f;
    float outlineRadius = 0.3f;
    float x = (outlineRadius / 2) - 0.9f * ratio;
    float y = -0.5f;

    // Setup drawers
    this._leftFocusDrawer = new CircleDrawer(x, y, 0f, focusRadius);
    this._leftOutlineDrawer = new CircleDrawer(x, y, 0f, outlineRadius);
    this._rightFocusDrawer = new CircleDrawer(-x, y, 0f, focusRadius);
    this._rightOutlineDrawer = new CircleDrawer(-x, y, 0f, outlineRadius);

    // Set colors
    this._leftOutlineDrawer.setColor(0f, 0f, 0f, 0.5f);
    this._rightOutlineDrawer.setColor(0f, 0f, 0f, 0.5f);
    this._leftFocusDrawer.setColor(0f, 0f, 0f, 0.75f);
    this._rightFocusDrawer.setColor(0f, 0f, 0f, 0.75f);
  }

  public boolean processTouchEvent(MotionEvent event) {
    switch(event.getAction()) {
      case MotionEvent.ACTION_MOVE:


    }
    return false;
  }

  public void draw() {
    this._leftOutlineDrawer.draw(this._viewProjectionMatrix);
    this._rightOutlineDrawer.draw(this._viewProjectionMatrix);
    this._leftFocusDrawer.draw(this._viewProjectionMatrix);
    this._rightFocusDrawer.draw(this._viewProjectionMatrix);
  }
}
