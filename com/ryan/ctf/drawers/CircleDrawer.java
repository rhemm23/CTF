package com.ryan.ctf.drawers;

import android.opengl.GLES30;

import com.ryan.ctf.core.IDrawable;
import com.ryan.ctf.graphics.ColoredVerticesProgram;
import com.ryan.ctf.math.Color4;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/*
 * Draws a circle on the z = 0 plane
 */
public class CircleDrawer implements IDrawable {

  private static final int VERTEX_COUNT = 4;
  private static final int COORDS_PER_VERTEX = 3;
  private static final int CIRCLE_VERTEX_COUNT = 66;

  private ColoredVerticesProgram _program;
  private FloatBuffer _vertexBuffer;

  private float[] _color;

  private float _radius;
  private float _x;
  private float _y;

  public CircleDrawer() {
    // Build float buffer
    ByteBuffer bb = ByteBuffer.allocateDirect(VERTEX_COUNT *
            COORDS_PER_VERTEX * CIRCLE_VERTEX_COUNT);
    bb.order(ByteOrder.nativeOrder());
    this._vertexBuffer = bb.asFloatBuffer();

    // Get program
    this._program = ColoredVerticesProgram.getInstance();
  }

  public CircleDrawer(float x, float y, float radius, Color4 color) {
    // Setup buffer and program
    this();

    // Set values
    this._radius = radius;
    this._x = x;
    this._y = y;

    // Calculate vertices
    calculateVertices();
  }

  /*
   * Sets the radius of the circle
   */
  public void setRadius(float radius) {
    this._radius = radius;
    calculateVertices();
  }

  /*
   * Sets the position of the circle
   */
  public void setPosition(float x, float y) {
    this._x = x;
    this._y = y;
    calculateVertices();
  }

  /*
   * Calculates all vertices of circle given starting
   * position and radius
   */
  private void calculateVertices() {
    float[] vertices = new float[CIRCLE_VERTEX_COUNT * 3];

    // Central vertex
    vertices[0] = this._x;
    vertices[1] = this._y;
    vertices[2] = 0f;

    // Fanned vertices
    float angle = 0;
    float angleStep = (float)(2 * Math.PI) / (CIRCLE_VERTEX_COUNT - 2);
    for(int i = 3; i < CIRCLE_VERTEX_COUNT * 3; i+=3) {
      vertices[i] = this._x + (this._radius * (float)Math.cos(angle));
      vertices[i + 1] = this._y + (this._radius * (float)Math.sin(angle));
      vertices[i + 2] = 0f;
      angle += angleStep;
    }

    // Setup buffer
    this._vertexBuffer.position(0);
    this._vertexBuffer.put(vertices);
    this._vertexBuffer.position(0);
  }

  /*
   * Set the color of the circle to be drawn
   */
  public void setColor(Color4 color) {
    this._color = color.asFloatArray();
  }

  /*
   * Draws the circle to the screen
   */
  public void draw(float[] vpMatrix) {
    // Use colored vertices program
    this._program.use();

    // Set circle color
    this._program.setColor(this._color);

    // Set circle position
    this._program.setPositionPointer(this._vertexBuffer);

    // Set view projection matrix
    this._program.setViewProjectionMatrix(vpMatrix);

    // Draw circle as fan of triangles
    GLES30.glDrawArrays(GLES30.GL_TRIANGLE_FAN, 0, CIRCLE_VERTEX_COUNT);

    // Disable program attributes
    this._program.disableAttributes();
  }
}
