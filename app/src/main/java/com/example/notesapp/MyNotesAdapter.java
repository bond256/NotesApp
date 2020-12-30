package com.example.notesapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapp.DB.Notes;

import java.util.List;

public class MyNotesAdapter extends RecyclerView.Adapter<MyNotesAdapter.ViewHolder> {
    private List<Notes> m_notesList;
    private View.OnLongClickListener m_onLongClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        private final TextView textView;
        OnLongClickListener onLongClickListener;

        public ViewHolder(View view, OnLongClickListener longClickListener) {
            super(view);
            // Define click listener for the ViewHolder's View
            this.onLongClickListener = longClickListener;
            textView = (TextView) view.findViewById(R.id.textView);
            view.setOnLongClickListener(this);
        }

        public TextView getTextView() {
            return textView;
        }

        @Override
        public boolean onLongClick(View v) {
            onLongClickListener.onLongClick(getAdapterPosition());
            return true;
        }
    }

    public MyNotesAdapter(List<Notes> notesList, View.OnLongClickListener onLongClickListener) {
        m_notesList = notesList;
        m_onLongClickListener = onLongClickListener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.note_item, viewGroup, false);

        return new ViewHolder(view, (OnLongClickListener) m_onLongClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getTextView().setText(m_notesList.get(position).name);
    }

    @Override
    public int getItemCount() {
        return m_notesList.size();
    }

    public interface OnLongClickListener {
        void onLongClick(int positon);
    }
}
