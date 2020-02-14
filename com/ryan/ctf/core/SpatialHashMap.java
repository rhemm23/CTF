package com.ryan.ctf.core;

import java.util.HashMap;
import java.util.HashSet;

/*
 * Spatial map of game objects
 *
 * Cell size must be greater than or equal to
 * the largest game object possible
 */
public class SpatialHashMap {

  private HashMap<String, HashSet<GameObject>> _data;
  private int _cellSize;

  public SpatialHashMap(int cellSize) {
    this._data = new HashMap<>();
    this._cellSize = cellSize;
  }

  /*
   * Adds a game object to the spatial map
   */
  public void addGameObject(GameObject obj) {
    for(String cell : getObjectCells(obj)) {
      addGameObjectToCell(cell, obj);
    }
  }

  public void removeGameObject(GameObject obj) {
    for(String cell : getObjectCells(obj)) {
      HashSet<GameObject> cellObjects = this._data.get(cell);
      if(cellObjects != null) {
        cellObjects.remove(obj);
      }
    }
  }

  /*
   * Gets all game objects near a game object
   */
  public HashSet<GameObject> getNearbyGameObjects(GameObject obj) {
    HashSet<GameObject> objects = new HashSet<>();
    for(String cell : getObjectCells(obj)) {
      HashSet<GameObject> cellObjects = this._data.get(cell);
      if(cellObjects != null) {
        objects.addAll(cellObjects);
      }
    }
    objects.remove(obj);
    return objects;
  }

  /*
   * Adds a game object to a specific cell
   */
  private void addGameObjectToCell(String cell, GameObject obj) {
    if(!this._data.containsKey(cell)) {
      this._data.put(cell, new HashSet<GameObject>());
    }
    this._data.get(cell).add(obj);
  }

  /*
   * Gets all cells a game object is in
   */
  private HashSet<String> getObjectCells(GameObject obj) {
    HashSet<String> cells = new HashSet<>();
    cells.add(getCell(obj.getX(), obj.getY()));
    cells.add(getCell(obj.getX(), obj.getY() + obj.getHeight()));
    cells.add(getCell(obj.getX() + obj.getWidth(), obj.getY()));
    cells.add(getCell(obj.getX() + obj.getWidth(), obj.getY() + obj.getHeight()));
    return cells;
  }

  @SuppressWarnings("DefaultLocale")
  private String getCell(int x, int y) {
    return String.format("%d:%d",
        x / this._cellSize,
        y / this._cellSize);
  }
}
