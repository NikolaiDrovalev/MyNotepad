package ru.geekbrains.mynotepad.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.geekbrains.mynotepad.R;
import ru.geekbrains.mynotepad.repository.LocalRepositoryImpl;


public class NotepadFragment extends Fragment {

    NotepadAdapter notepadAdapter;

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
    }

    void initAdapter(){
        notepadAdapter = new NotepadAdapter();
        LocalRepositoryImpl localRepository = new LocalRepositoryImpl(requireContext().getResources());
        notepadAdapter.setData(localRepository.init());
    }
    void initRecycler(View view){
        RecyclerView recyclerView = view.findViewById(R.id.recycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(notepadAdapter);
    }
    String[] getData(){
        String[] data = getResources().getStringArray(R.array.note_name);
        return data;
    }
}