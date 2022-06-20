package com.example.Bank_Sampah.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.Bank_Sampah.dao.DatabaseDao;
import com.example.Bank_Sampah.model.ModelDatabase;

@Database(entities = {ModelDatabase.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DatabaseDao databaseDao();
}
