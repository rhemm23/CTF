package com.ryan.ctf.core;

public class World {

  private static final int WORLD_CELL_SIZE = 32;

  private SpatialHashMap _worldSpace;

  public World() {
    this._worldSpace = new SpatialHashMap(WORLD_CELL_SIZE);
  }

}
