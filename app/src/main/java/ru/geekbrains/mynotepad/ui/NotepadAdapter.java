package ru.geekbrains.mynotepad.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.geekbrains.mynotepad.R;
import ru.geekbrains.mynotepad.repository.NoteData;
import ru.geekbrains.mynotepad.repository.NoteSource;

public class NotepadAdapter extends RecyclerView.Adapter<NotepadAdapter.MyViewHolder> {

    private NoteSource noteSource;

    public void setData(NoteSource noteSource) {
        this.noteSource = noteSource;
        notifyDataSetChanged();
    }
    NotepadAdapter(){

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new MyViewHolder(layoutInflater.inflate(R.layout.fragment_notepad_cardview_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindContentWithLayout(noteSource.getCardData(position));
    }

    @Override
    public int getItemCount() {
        return noteSource.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDescription;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.title);
            textViewDescription = (TextView) itemView.findViewById(R.id.description);
        }

        public void bindContentWithLayout(NoteData content){
            textViewTitle.setText(content.getTitle());
            textViewTitle.setBackgroundColor(content.getBackgroundColor());
            textViewDescription.setText(content.getDescription());
        }
    }
}
