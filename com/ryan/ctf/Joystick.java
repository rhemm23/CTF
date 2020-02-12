package com.ryan.ctf;

import com.ryan.ctf.graphics.CircleDrawer;

public class Joystick {

  private static final float DEFAULT_OUTLINE_OPACITY = 0.25f;
  private static final float DEFAULT_FOCUS_OPACITY = 0.5f;

  private CircleDrawer _outlineDrawer;
  private CircleDrawer _focusDrawer;

  public Joystick() { }

  public Joystick(float x, float y, float size) {
    this._outlineDrawer = new CircleDrawer();
  }

  /*
   * Sets the radius of the outer circle
   */
  public void setRadius(float radius) {

  }

  /*
   * Sets the position of the joystick
   */
  public void setPosition(float x, float y) {

  }

  private void calculateVertices() {


  }

}
