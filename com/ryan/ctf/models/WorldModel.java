package com.ryan.ctf.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

@Entity(tableName = "world")
public class WorldModel {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "world_id")
  public long WorldId;

  @ColumnInfo(name = "width")
  public float Width;

  @ColumnInfo(name = "height")
  public float Height;
}
