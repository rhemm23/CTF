package com.ryan.ctf.core;

import com.ryan.ctf.math.Rectanglef;

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

  /*
   * Removes a game object from the spatial map
   */
  public void removeGameObject(GameObject obj) {
    for(String cell : getObjectCells(obj)) {
      HashSet<GameObject> cellObjects = this._data.get(cell);
      if(cellObjects != null) {
        cellObjects.remove(obj);
      }
    }
  }

  /*
   * Updates the cells the game object occupies, useful
   * if the position of the object is changed
   */
  public void updateGameObject(GameObject obj) {
    this.removeGameObject(obj);
    this.addGameObject(obj);
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
   * Finds all game objects located within or nearby a rectangular bounds
   */
  public HashSet<GameObject> getGameObjectsWithinBounds(Rectanglef bounds) {
    // Casting to int w/ overcompensating
    int x1 = (int)Math.floor(bounds.X1 / this._cellSize);
    int y1 = (int)Math.floor(bounds.Y1 / this._cellSize);
    int x2 = (int)Math.ceil(bounds.X2 / this._cellSize);
    int y2 = (int)Math.ceil(bounds.Y2 / this._cellSize);

    // Find objects in cells
    HashSet<GameObject> objects = new HashSet<>();
    for(int x = x1; x < x2; x++) {
      for(int y = y1; y < y2; y++) {
        HashSet<GameObject> cellObjects = this._data.get(x + ":" + y);
        if(cellObjects != null) {
          objects.addAll(cellObjects);
        }
      }
    }
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
    return (x / this._cellSize) + ":" + (y / this._cellSize);
  }
}
