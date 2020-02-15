package com.ryan.ctf.models;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface GameObjectDao {

  @Insert
  void insertAll(GameObjectModel... gameObjectModels);

  @Delete
  void delete(GameObjectModel gameObjectModel);

  @Update
  void update(GameObjectModel gameObjectModel);

  @Query("SELECT * FROM game_object WHERE world_id = :worldId")
  List<GameObjectModel> getWorldGameObjects(long worldId);
}
