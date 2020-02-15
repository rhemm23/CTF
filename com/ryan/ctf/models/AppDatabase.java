package com.ryan.ctf.models;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(
    entities = {
        WorldModel.class,
        GameObjectModel.class,
    },
    version = 1,
    exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

  public abstract WorldDao worldDao();

  public abstract GameObjectDao gameObjectDao();

}
