package com.ryan.ctf.drawers;

/*
 * Represents an item that can be rendered
 */
public interface IDrawable {
  void draw(float[] viewProjectionMatrix);
}
