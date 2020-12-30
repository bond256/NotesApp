package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListFragment listFragment = new ListFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flContent, listFragment, null)
                .commit();
    }
}