package ru.geekbrains.mynotepad.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ru.geekbrains.mynotepad.R;
import ru.geekbrains.mynotepad.publisher.Observer;
import ru.geekbrains.mynotepad.repository.LocalRepositoryImpl;
import ru.geekbrains.mynotepad.repository.NoteData;
import ru.geekbrains.mynotepad.repository.NoteSource;
import ru.geekbrains.mynotepad.ui.MainActivity;
import ru.geekbrains.mynotepad.ui.editor.NoteFragment;


public class NotepadFragment extends Fragment implements OnItemClickListener {

    NotepadAdapter notepadAdapter;
    NoteSource data;
    RecyclerView recyclerView;

    public static NotepadFragment newInstance() {
        NotepadFragment fragment = new NotepadFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notepad, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
        initRecycler(view);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.notes_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                data.addNoteData(new NoteData("Новая заметка" + data.size(), "Описание новой заметки" + data.size(), R.array.color_background));
                notepadAdapter.notifyItemInserted(data.size() - 1);
                recyclerView.smoothScrollToPosition(data.size() - 1);
                return true;
            case R.id.action_clear:
                data.clearNotesData();
                notepadAdapter.notifyDataSetChanged();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        requireActivity().getMenuInflater().inflate(R.menu.notes_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int menuPosition = notepadAdapter.getMenuPosition();
        switch (item.getItemId()) {
            case R.id.action_update: {

                Observer observer = new Observer() {
                    @Override
                    public void receiveMessage(NoteData noteData) {
                        ((MainActivity) requireActivity()).getPublisher().unsubscribe(this);
                        data.updateNoteData(menuPosition, noteData);
                        notepadAdapter.notifyItemChanged(menuPosition);
                    }
                };
                ((MainActivity) requireActivity()).getPublisher().subscribe(observer);
                ((MainActivity) requireActivity()).getNavigation().addFragment(NoteFragment.newInstance(data.getCardData(menuPosition)), true);
                return true;
            }
            case R.id.action_delete: {
                data.deleteNoteData(menuPosition);
                notepadAdapter.notifyItemRemoved(menuPosition);
                return true;
            }
        }
        return super.onContextItemSelected(item);
    }

    void initAdapter() {
        notepadAdapter = new NotepadAdapter();
        data = new LocalRepositoryImpl(requireContext().getResources()).init();
        notepadAdapter.setData(data);
        notepadAdapter.setOnItemClickListener(NotepadFragment.this);
    }

    void initRecycler(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(notepadAdapter);

        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setChangeDuration(2000);
        animator.setRemoveDuration(2000);
        recyclerView.setItemAnimator(animator);

    }

    String[] getData() {
        String[] data = getResources().getStringArray(R.array.note_name);
        return data;
    }

    @Override
    public void onItemClick(int position) {
        String[] data = getData();
        Toast.makeText(requireContext(), " Нажали на " + data[position], Toast.LENGTH_SHORT).show();
    }
}