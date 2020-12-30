package com.example.notesapp.DB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Notes {
    @PrimaryKey(autoGenerate = true)
    public Integer _id;

    public String name;
}
