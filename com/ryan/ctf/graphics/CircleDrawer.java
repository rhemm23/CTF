package com.ryan.ctf.graphics;

import android.opengl.GLES30;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class CircleDrawer {

  private static final int CIRCLE_VERTEX_COUNT = 66;

  private ColoredVerticesProgram _program;
  private FloatBuffer _vertexBuffer;

  private float[] _color;

  public CircleDrawer(float x, float y, float z, float radius) {
    ByteBuffer bb = ByteBuffer.allocateDirect(12 * CIRCLE_VERTEX_COUNT);
    bb.order(ByteOrder.nativeOrder());
    this._vertexBuffer = bb.asFloatBuffer();

    // Set circle
    setCircle(x, y, z, radius);

    // Get program
    this._program = ColoredVerticesProgram.getInstance();
  }

  public void setCircle(float x, float y, float z, float radius) {
    float[] vertices = new float[CIRCLE_VERTEX_COUNT * 3];

    vertices[0] = x;
    vertices[1] = y;
    vertices[2] = z;

    float angle = 0;
    float angleStep = (float)(2 * Math.PI) / (CIRCLE_VERTEX_COUNT - 2);
    for(int i = 3; i < CIRCLE_VERTEX_COUNT * 3; i+=3) {
      vertices[i] = x + (radius * (float)Math.cos(angle));
      vertices[i + 1] = y + (radius * (float)Math.sin(angle));
      vertices[i + 2] = z;
      angle += angleStep;
    }

    // Setup buffer
    this._vertexBuffer.position(0);
    this._vertexBuffer.put(vertices);
    this._vertexBuffer.position(0);
  }

  public void setColor(float r, float g, float b, float a) {
    this._color = new float[] {
        r, g, b, a
    };
  }

  public void draw(float[] vpMatrix) {
    this._program.use();
    this._program.setColor(this._color);
    this._program.setPositionPointer(this._vertexBuffer);
    this._program.setViewProjectionMatrix(vpMatrix);
    GLES30.glDrawArrays(GLES30.GL_TRIANGLE_FAN, 0, CIRCLE_VERTEX_COUNT);
    this._program.disableAttributes();
  }
}
