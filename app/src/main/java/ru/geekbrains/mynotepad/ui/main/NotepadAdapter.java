package ru.geekbrains.mynotepad.ui.main;

import android.view.LayoutInflater;
import android.widget.ToggleButton;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import ru.geekbrains.mynotepad.R;
import ru.geekbrains.mynotepad.repository.NoteData;
import ru.geekbrains.mynotepad.repository.NoteSource;

public class NotepadAdapter extends RecyclerView.Adapter<NotepadAdapter.MyViewHolder> {

    private NoteSource notesSource;

    OnItemClickListener onItemClickListener;

    Fragment fragment;

    private int menuPosition;

    public int getMenuPosition(){
        return menuPosition;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public void setData(NoteSource notesSource) {
        this.notesSource = notesSource;
        notifyDataSetChanged();
    }

    NotepadAdapter(NoteSource notesSource){
        this.notesSource = notesSource;
    }

    NotepadAdapter(){
    }

    NotepadAdapter(Fragment fragment){
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new MyViewHolder(layoutInflater.inflate(R.layout.fragment_notepad_cardview_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindContentWithLayout(notesSource.getNoteData(position));
    }

    @Override
    public int getItemCount() {
        return notesSource.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewTitle;
        private final TextView textViewDescription;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.title);
            textViewDescription = (TextView) itemView.findViewById(R.id.description);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    menuPosition = getLayoutPosition();
                    return false;
                }
            });
            fragment.registerForContextMenu(itemView);
        }

        public void bindContentWithLayout(NoteData content){
            textViewTitle.setText(content.getTitle());
            textViewTitle.setBackgroundColor(content.getBackgroundColor());
            textViewDescription.setText(content.getDescription());
        }
    }
}
