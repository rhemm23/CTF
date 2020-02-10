package com.ryan.ctf.graphics;

import android.content.Context;
import android.opengl.GLES30;

import java.nio.FloatBuffer;

public class ColoredVerticesProgram extends Program {

  private static ColoredVerticesProgram instance;

  public static void assureInstance(Context context) {
    if(instance == null) {
      instance = new ColoredVerticesProgram(context);
    }
  }

  public static ColoredVerticesProgram getInstance() {
    return instance;
  }

  private int _viewProjectionMatrixHandle;
  private int _positionHandle;
  private int _colorHandle;

  private ColoredVerticesProgram(Context context) {
    // Load program
    super.buildProgramFromAssets(context,
        "shaders/ColoredVertexShader.txt",
        "shaders/ColoredFragmentShader.txt"
    );

    // Set handles
    this._viewProjectionMatrixHandle = GLES30.glGetUniformLocation(
        this._program, "viewProjectionMatrix");
    this._positionHandle = GLES30.glGetAttribLocation(
        this._program, "position");
    this._colorHandle = GLES30.glGetUniformLocation(
        this._program, "color");
  }

  public void setPositionPointer(FloatBuffer pointer) {
    GLES30.glEnableVertexAttribArray(this._positionHandle);
    GLES30.glVertexAttribPointer(this._positionHandle, 3,
        GLES30.GL_FLOAT, false, 12, pointer);
  }

  public void setViewProjectionMatrix(float[] vpMatrix) {
    GLES30.glUniformMatrix4fv(this._viewProjectionMatrixHandle,
        1, false, vpMatrix, 0);
  }

  public void setColor(float[] color) {
    GLES30.glUniform4fv(this._colorHandle, 1, color, 0);
  }

  public void disableAttributes() {
    GLES30.glDisableVertexAttribArray(this._positionHandle);
  }
}
