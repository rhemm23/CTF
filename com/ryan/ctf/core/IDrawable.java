package com.ryan.ctf.core;

/*
 * Represents an item that can be rendered
 */
public interface IDrawable {
  void draw(float[] viewProjectionMatrix);
}
