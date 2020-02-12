package com.ryan.ctf.drawers;

import android.opengl.GLES30;

import com.ryan.ctf.graphics.Texture;
import com.ryan.ctf.graphics.TexturedVerticesProgram;

import java.nio.ByteOrder;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

/*
 * Draws a one by one texture square in the z = 0 plane
 */
public class TextureDrawer implements IDrawable {

  private static final int FLOAT_SIZE = 4;
  private static final int VERTEX_COUNT = 4;
  private static final int COORDS_PER_VERTEX = 3;

  private static final float[] textureCoordinates = {
      0f, 1f,
      0f, 0f,
      1f, 1f,
      1f, 0f
  };

  // Same buffer for all textures
  private static final FloatBuffer textureCoordinateBuffer;

  static {
    // Setup texture coordinate buffer
    ByteBuffer bb = ByteBuffer.allocateDirect(textureCoordinates.length * FLOAT_SIZE);
    bb.order(ByteOrder.nativeOrder());
    textureCoordinateBuffer = bb.asFloatBuffer();

    // Put the default array of coordinates
    textureCoordinateBuffer.put(textureCoordinates);
    textureCoordinateBuffer.position(0);
  }

  private TexturedVerticesProgram _program;
  private FloatBuffer _positionBuffer;
  private Texture _texture;

  public TextureDrawer(float x, float y, Texture texture) {
    // Use the textured vertices program
    this._program = TexturedVerticesProgram.getInstance();
    this._texture = texture;

    // Setup position buffer
    ByteBuffer bb = ByteBuffer.allocateDirect(VERTEX_COUNT * COORDS_PER_VERTEX * FLOAT_SIZE);
    bb.order(ByteOrder.nativeOrder());
    this._positionBuffer = bb.asFloatBuffer();

    // Build vertices
    setPosition(x, y);
  }

  public void setPosition(float x, float y) {
    this._positionBuffer.position(0);
    this._positionBuffer.put(new float[] {
        x, y, 0.0f,
        x, y + 1, 0.0f,
        x + 1, y, 0.0f,
        x + 1, y + 1, 0.0f
    });
    this._positionBuffer.position(0);
  }

  public void draw(float[] viewProjectionMatrix) {
    // Use the textured vertices program
    this._program.use();

    // Set the position buffer
    this._program.setPositionPointer(this._positionBuffer);

    // Set the texture coordinates buffer
    this._program.setTextureCoordinatePointer(textureCoordinateBuffer);

    // Set the current texture
    this._program.setTexture(this._texture);

    // Set the current view projection matrix
    this._program.setViewProjectionMatrix(viewProjectionMatrix);

    // Draw two triangles as a strip
    GLES30.glDrawArrays(GLES30.GL_TRIANGLE_STRIP, 0, VERTEX_COUNT);

    // Disable program attributes
    this._program.disableAttributes();
  }
}
