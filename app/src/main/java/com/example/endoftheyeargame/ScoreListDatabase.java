package com.example.endoftheyeargame;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ScoreList.class}, version = 1)
public abstract class ScoreListDatabase extends RoomDatabase {
    private static ScoreListDatabase instance;

    public abstract ScoreListDAO scoreListDAO();

    public static synchronized ScoreListDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    ScoreListDatabase.class,
                    "score_database")
                    .build();
        }
        return instance;
    }
}
