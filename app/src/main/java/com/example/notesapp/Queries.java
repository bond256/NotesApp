package com.example.notesapp;

import android.content.Context;
import android.widget.Toast;

import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.notesapp.DB.DataBaseApp;
import com.example.notesapp.DB.Notes;
import com.example.notesapp.DB.NotesDao;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public class Queries {
    private DataBaseApp dataBaseApp;
    private NotesDao notesDao;

    public Queries(Context context) {
        dataBaseApp = Room.databaseBuilder(context, DataBaseApp.class, "Data")
                .build();
        notesDao = dataBaseApp.notesDao();
    }

    List<Notes> getData() {
        return notesDao.getNotes()
                .subscribeOn(Schedulers.io())
                .blockingGet();
    }

    void insert(List<Notes> notes) {
        notesDao.insert(notes)
                .subscribeOn(Schedulers.io())
                //.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }


    void delete(Notes notes) {
        notesDao.delete(notes)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

    }
}
