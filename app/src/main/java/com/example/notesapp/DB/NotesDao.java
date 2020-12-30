package com.example.notesapp.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface NotesDao {
    @Query("Select * FROM Notes")
    Single<List<Notes>> getNotes();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(List<Notes> notes);

    @Delete
    Completable delete(Notes notes);
}
