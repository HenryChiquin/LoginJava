package com.example.loginjava.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.loginjava.database.dao.universoDAO;
import com.example.loginjava.database.entity.tUniverso;


@Database(entities = {tUniverso.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public static AppDatabase INSTANCE;


    public abstract universoDAO uniiversoDao();

    public static AppDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class,"dbUniverso.db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
