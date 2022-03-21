package ru.geekbrains.mynotepad.ui.editor;

import android.os.Bundle;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.geekbrains.mynotepad.R;
import ru.geekbrains.mynotepad.repository.NoteData;
import ru.geekbrains.mynotepad.ui.MainActivity;

public class NoteFragment extends Fragment {

    NoteData noteData;


    public static NoteFragment newInstance(NoteData noteData) {
        NoteFragment fragment = new NoteFragment();
        Bundle args = new Bundle();
        args.putParcelable("noteData", noteData);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            noteData = getArguments().getParcelable("noteData");
            ((EditText) view.findViewById(R.id.inputTitle)).setText(noteData.getTitle());
            ((EditText) view.findViewById(R.id.inputDescription)).setText(noteData.getDescription());
        }
        view.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View it) {
                noteData.setTitle(((EditText) view.findViewById(R.id.inputTitle)).getText().toString());
                noteData.setDescription(((EditText) view.findViewById(R.id.inputDescription)).getText().toString());
                ((MainActivity) requireActivity()).getPublisher().sendMessage(noteData);
                ((MainActivity) requireActivity()).getSupportFragmentManager().popBackStack();
            }
        });
    }
}
