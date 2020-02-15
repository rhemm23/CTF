package com.ryan.ctf.models;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface WorldDao {

  @Insert
  void insert(WorldModel world);

  @Delete
  void delete(WorldModel world);

  @Update
  void update(WorldModel world);

  @Query("SELECT * FROM world")
  WorldModel getFirstWorld();
}
