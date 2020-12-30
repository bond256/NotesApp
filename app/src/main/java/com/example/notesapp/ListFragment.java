package com.example.notesapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapp.DB.DataBaseApp;
import com.example.notesapp.DB.Notes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListFragment extends Fragment implements MyNotesAdapter.OnLongClickListener, View.OnLongClickListener, AddFragmentDialog.NoticeDialogListener {
    private List<Notes> data;
    private MyNotesAdapter myNotesAdapter;
    private Queries queries;
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.example.notesapp";
    private int count_insert = 0;
    private int count_delete = 0;
    private TextView countData;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPreferences = getActivity().getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE);
        count_insert = mPreferences.getInt("count_insert", 0);
        count_delete = mPreferences.getInt("count_delete", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);
        countData = view.findViewById(R.id.countView);
        queries = new Queries(getActivity());
        countData.setText("Insert: " + count_insert + " " + "Delete: " + count_delete);
        RecyclerView notesRecycler = view.findViewById(R.id.rescyclerView);
        FloatingActionButton addButton = view.findViewById(R.id.addNotes);
        addButton.setOnClickListener(v -> {
            AddFragmentDialog addFragmentDialog = new AddFragmentDialog();
            addFragmentDialog.setListener(this);
            addFragmentDialog.show(getChildFragmentManager(), "myDialog");
        });
        data = queries.getData();
        myNotesAdapter = new MyNotesAdapter(data, ListFragment.this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        notesRecycler.setLayoutManager(layoutManager);
        notesRecycler.setAdapter(myNotesAdapter);
        return view;
    }

    @Override
    public void onLongClick(int positon) {
        queries.delete(data.get(positon));
        count_delete++;
        data.remove(positon);
        myNotesAdapter.notifyItemRemoved(positon);
        countData.setText("Insert: " + count_insert + " " + "Delete: " + count_delete);
    }


    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    @Override
    public void onDialogAddClick(DialogFragment dialog) {
        EditText nameNote = dialog.getDialog().findViewById(R.id.nameNote);
        Toast.makeText(getActivity(), nameNote.getText(), Toast.LENGTH_SHORT).show();
        count_insert++;
        String name = nameNote.getText().toString();
        data.add(addNote(name));
        countData.setText("Insert: " + count_insert + " " + "Delete: " + count_delete);
    }

    Notes addNote(String nameNote) {
        Notes note = new Notes();
        note.name = nameNote;
        return note;
    }

    @Override
    public void onStop() {
        super.onStop();
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putInt("count_insert", count_insert);
        preferencesEditor.putInt("count_delete", count_delete);
        preferencesEditor.apply();
        queries.insert(data);
    }
}