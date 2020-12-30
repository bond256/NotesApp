package com.example.notesapp.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Notes.class}, version = 2)
public abstract class DataBaseApp extends RoomDatabase {
    public abstract NotesDao notesDao();
}
