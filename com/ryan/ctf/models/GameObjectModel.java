package com.ryan.ctf.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

@Entity(tableName = "game_object")
public class GameObjectModel {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "game_object_id")
  public long GameObjectId;

  @ForeignKey(
      entity = WorldModel.class,
      parentColumns = { "world_id" },
      childColumns = { "world_id" },
      onDelete = ForeignKey.CASCADE
  )
  @ColumnInfo(name = "world_id")
  public long WorldId;

  @ColumnInfo(name = "x")
  public float X;

  @ColumnInfo(name = "y")
  public float Y;

  @ColumnInfo(name = "width")
  public float Width;

  @ColumnInfo(name = "height")
  public float Height;

  @ColumnInfo(name = "x_velocity")
  public float XVelocity;

  @ColumnInfo(name = "y_velocity")
  public float YVelocity;

  @ColumnInfo(name = "object_type")
  public String ObjectType;
}