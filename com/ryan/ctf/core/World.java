package com.ryan.ctf.core;

import java.util.ArrayList;
import java.util.HashSet;

public class World implements IDrawable {

  private static final float GRAVITY = -0.5f;

  private ArrayList<Entity> _entities;
  private SpatialHashMap _worldSpace;

  public World(int cellSize) {
    this._worldSpace = new SpatialHashMap(cellSize);
    this._entities = new ArrayList<>();
  }

  /**
   * Updates the entities within the world, applying velocity,
   * detecting collision, and applying gravity
   */
  public void update() {
    for(Entity entity : this._entities) {
      int xStep = Math.round(entity.getXVelocity());
      int yStep = Math.round(entity.getYVelocity());

      // Apply velocities
      if(xStep != 0) {
        tryApplyVelocity(entity, xStep, true);
      }
      if(yStep != 0) {
        tryApplyVelocity(entity, yStep, false);
      }

      // Apply gravity if possible
      if(canMove(entity, 0, -1)) {
        entity.setYVelocity(entity.getYVelocity() + GRAVITY);
      }
    }
  }

  private void tryApplyVelocity(Entity entity, int velocity, boolean isXAxis) {
    boolean success = false;

    // Try to move full velocity, if not possible, try
    // incrementely lower values until one works
    while(velocity != 0) {
      boolean couldMove = isXAxis
          ? tryMove(entity, velocity, 0)
          : tryMove(entity, 0, velocity);

      if(couldMove) {
        success = true;
        break;
      } else if(velocity > 0) {
        velocity--;
      } else {
        velocity++;
      }
    }

    // Could not move, clear velocity
    if(!success) {
      if(isXAxis) {
        entity.setXVelocity(0);
      } else {
        entity.setYVelocity(0);
      }
    }
  }

  private boolean tryMove(GameObject obj, int dx, int dy) {
    if(canMove(obj, dx, dy)) {
      obj.setX(obj.getX() + dx);
      obj.setY(obj.getY() + dy);
      this._worldSpace.updateGameObject(obj);
      return true;
    }
    return false;
  }

  private boolean canMove(GameObject obj, int dx, int dy) {
    boolean success = true;

    // Update positions
    obj.setX(obj.getX() + dx);
    obj.setY(obj.getY() + dy);

    // Check for collisions
    HashSet<GameObject> nearbyObjects = this._worldSpace.getNearbyGameObjects(obj);
    for(GameObject testObj : nearbyObjects) {
      if(obj.collidesWith(testObj)) {
        success = false;
        break;
      }
    }

    // Revert positions
    obj.setX(obj.getX() - dx);
    obj.setY(obj.getY() - dy);
    return success;
  }

  /**
   * Adds a game object to the world
   */
  public void addGameObject(GameObject obj) {
    this._worldSpace.addGameObject(obj);
    if(obj instanceof Entity) {
      this._entities.add((Entity)obj);
    }
  }

  public void draw(float[] viewProjectionMatrix) {
    /*HashSet<GameObject> visibleObjects =
        this._worldSpace.getGameObjectsWithinBounds(viewBounds);
    for(GameObject obj : visibleObjects) {
      obj.draw(viewProjectionMatrix, viewBounds);
    }*/
  }
}