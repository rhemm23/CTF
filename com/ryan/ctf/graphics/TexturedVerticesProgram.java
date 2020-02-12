package com.ryan.ctf.graphics;

import android.content.Context;
import android.opengl.GLES30;

import java.nio.FloatBuffer;

/*
 * A shader program that draws vertices using a texture
 */
public class TexturedVerticesProgram extends Program {

  private static TexturedVerticesProgram instance;

  public static void assureInstance(Context context) {
    if(instance == null) {
      instance = new TexturedVerticesProgram(context);
    }
  }

  public static TexturedVerticesProgram getInstance() {
    return instance;
  }

  private int _viewProjectionMatrixHandle;
  private int _textureCoordinateHandle;
  private int _positionHandle;
  private int _textureHandle;

  private TexturedVerticesProgram(Context context) {
    // Load shaders
    super.buildProgramFromAssets(
        context,
        "shaders/TextureVertexShader.txt",
        "shaders/TextureFragmentShader.txt"
    );

    // Load handles
    this._viewProjectionMatrixHandle = GLES30.glGetUniformLocation(
        this._program, "viewProjectionMatrix");
    this._positionHandle = GLES30.glGetAttribLocation(
        this._program, "position");
    this._textureHandle = GLES30.glGetUniformLocation(
        this._program, "image");
    this._textureCoordinateHandle = GLES30.glGetAttribLocation(
        this._program, "inputTextureCoordinate");
  }

  /*
   * Set the shape vertices
   */
  public void setPositionPointer(FloatBuffer pointer) {
    GLES30.glEnableVertexAttribArray(this._positionHandle);
    GLES30.glVertexAttribPointer(this._positionHandle, 3,
        GLES30.GL_FLOAT, false, 12, pointer);
  }

  /*
   * Set the texture coordinates
   */
  public void setTextureCoordinatePointer(FloatBuffer pointer) {
    GLES30.glEnableVertexAttribArray(this._textureCoordinateHandle);
    GLES30.glVertexAttribPointer(this._textureCoordinateHandle, 2,
        GLES30.GL_FLOAT, false, 8, pointer);
  }

  /*
   * Set the texture to draw
   */
  public void setTexture(Texture texture) {
    GLES30.glActiveTexture(GLES30.GL_TEXTURE0);
    GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, texture.getId());
    GLES30.glUniform1i(this._textureHandle, 0);
  }

  /*
   * Disable the program attributes
   */
  public void disableAttributes() {
    GLES30.glDisableVertexAttribArray(this._positionHandle);
    GLES30.glDisableVertexAttribArray(this._textureCoordinateHandle);
  }

  /*
   * Set the view projection matrix
   */
  public void setViewProjectionMatrix(float[] vpMatrix) {
    GLES30.glUniformMatrix4fv(this._viewProjectionMatrixHandle,
        1, false, vpMatrix, 0);
  }
}
