package com.ryan.ctf;

import com.ryan.ctf.drawers.CircleDrawer;
import com.ryan.ctf.drawers.IDrawable;
import com.ryan.ctf.math.Color4;

public class Joystick implements IDrawable {

  private CircleDrawer _outlineDrawer;
  private CircleDrawer _focusDrawer;

  private float _focusProportion;
  private float _radius;

  private Color4 _outlineColor;
  private Color4 _focusColor;

  private float _x;
  private float _y;

  public Joystick() {
    this._outlineDrawer = new CircleDrawer();
    this._focusDrawer = new CircleDrawer();
  }

  public Joystick(float x, float y, float radius,
      Color4 outlineColor, Color4 focusColor, float focusProportion) {
    // Position
    this._x = x;
    this._y = y;

    // Pad sizes
    this._focusProportion = focusProportion;
    this._radius = radius;

    // Pad colors
    this._outlineColor = outlineColor;
    this._focusColor = focusColor;

    // Pad drawers
    this._outlineDrawer = new CircleDrawer(x, y, radius, outlineColor);
    this._focusDrawer = new CircleDrawer(x, y, radius * focusProportion, focusColor);
  }

  /*
   * Set the color of the outline pad
   */
  public void setOutlineColor(Color4 color) {
    this._outlineDrawer.setColor(color);
  }

  /*
   * Set the color of the focus pad
   */
  public void setFocusColor(Color4 color) {
    this._focusDrawer.setColor(color);
  }

  /*
   * Sets the proportion of the outline pad the focus pad occupies
   */
  public void setFocusProportion(float focusProportion) {
    this._focusProportion = focusProportion;
    this._focusDrawer.setRadius(this._radius * focusProportion);
  }

  /*
   * Sets the radius of the outline pad
   */
  public void setRadius(float radius) {
    this._outlineDrawer.setRadius(radius);
    this._focusDrawer.setRadius(radius * this._focusProportion);
  }

  /*
   * Sets the position of the joystick
   */
  public void setPosition(float x, float y) {
    // Update position
    this._x = x;
    this._y = y;

    // Update pad drawer positions
    this._outlineDrawer.setPosition(x, y);
    this._focusDrawer.setPosition(x, y);
  }

  /*
   * Draws the outline and focus pad to the screen
   */
  public void draw(float[] viewProjectionMatrix) {
    this._outlineDrawer.draw(viewProjectionMatrix);
    this._focusDrawer.draw(viewProjectionMatrix);
  }
}
