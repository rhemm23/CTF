package com.ryan.ctf;

import com.ryan.ctf.blocks.Block;

public class World {

  public static final int WORLD_SIZE = 256;

  public Block[][] Blocks;

  public World() {
    this.Blocks = new Block[WORLD_SIZE][WORLD_SIZE];
  }


}
