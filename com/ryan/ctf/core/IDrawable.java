package com.ryan.ctf.core;

import com.ryan.ctf.math.Rectanglef;

/**
 * Represents an item that can be rendered
 */
public interface IDrawable {

  /**
   * Draws the current object
   *
   * @param viewProjectionMatrix is the current view projection matrix
   */
  void draw(float[] viewProjectionMatrix);
}
