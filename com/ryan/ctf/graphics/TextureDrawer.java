package com.ryan.ctf.graphics;

import android.opengl.GLES30;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class TextureDrawer {

  private static final int FLOAT_SIZE = 4;
  private static final int VERTEX_COUNT = 4;
  private static final int COORDS_PER_VERTEX = 3;

  private static final float[] textureCoordinates = {
      0f, 1f,
      0f, 0f,
      1f, 1f,
      1f, 0f
  };

  private static final FloatBuffer textureCoordinateBuffer;

  static {
    ByteBuffer bb = ByteBuffer.allocateDirect(textureCoordinates.length * FLOAT_SIZE);
    bb.order(ByteOrder.nativeOrder());
    textureCoordinateBuffer = bb.asFloatBuffer();
    textureCoordinateBuffer.put(textureCoordinates);
    textureCoordinateBuffer.position(0);
  }

  private TexturedVerticesProgram _program;
  private FloatBuffer _positionBuffer;

  public TextureDrawer() {
    this._program = TexturedVerticesProgram.getInstance();

    // Setup position buffer
    ByteBuffer bb = ByteBuffer.allocateDirect(VERTEX_COUNT * COORDS_PER_VERTEX * FLOAT_SIZE);
    bb.order(ByteOrder.nativeOrder());
    this._positionBuffer = bb.asFloatBuffer();
  }

  private void updatePositionBuffer(float x, float y) {
    this._positionBuffer.position(0);
    this._positionBuffer.put(new float[] {
        x, y, 0.0f,
        x, y + 1, 0.0f,
        x + 1, y, 0.0f,
        x + 1, y + 1, 0.0f
    });
    this._positionBuffer.position(0);
  }

  public void draw(float x, float y, Texture texture, float[] vpMatrix) {
    this._program.use();
    updatePositionBuffer(x, y);
    this._program.setPositionPointer(this._positionBuffer);
    this._program.setTextureCoordinatePointer(textureCoordinateBuffer);
    this._program.setTexture(texture);
    this._program.setViewProjectionMatrix(vpMatrix);
    GLES30.glDrawArrays(GLES30.GL_TRIANGLE_STRIP, 0, VERTEX_COUNT);
    this._program.disableAttributes();
  }
}
