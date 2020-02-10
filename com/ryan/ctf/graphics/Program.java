package com.ryan.ctf.graphics;

import android.content.Context;
import android.opengl.GLES30;

public abstract class Program {

  protected int _program;

  protected Program() {
    this._program = GLES30.glCreateProgram();
  }

  public int getId() {
    return this._program;
  }

  public void use() {
    GLES30.glUseProgram(this._program);
  }

  protected void buildProgramFromAssets(Context context,
                                     String vertexShaderPath,
                                     String fragmentShaderPath) {
    // Load shaders
    Shader vertexShader = Shader.loadFromAsset(context,
        vertexShaderPath, GLES30.GL_VERTEX_SHADER);
    Shader fragmentShader = Shader.loadFromAsset(context,
        fragmentShaderPath, GLES30.GL_FRAGMENT_SHADER);

    // Attach shaders
    GLES30.glAttachShader(this._program, vertexShader.getId());
    GLES30.glAttachShader(this._program, fragmentShader.getId());

    // Link
    GLES30.glLinkProgram(this._program);

    // Detach shaders
    GLES30.glDetachShader(this._program, vertexShader.getId());
    GLES30.glDetachShader(this._program, fragmentShader.getId());

    // Delete shaders
    GLES30.glDeleteShader(vertexShader.getId());
    GLES30.glDeleteShader(fragmentShader.getId());
  }
}
