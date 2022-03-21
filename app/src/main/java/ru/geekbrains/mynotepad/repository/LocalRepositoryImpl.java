package ru.geekbrains.mynotepad.repository;

import android.content.res.Resources;
import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.List;

import ru.geekbrains.mynotepad.R;

public class LocalRepositoryImpl implements NoteSource {

    private List<NoteData> dataSource;
    private Resources resources;

    public LocalRepositoryImpl(Resources resources) {
        dataSource = new ArrayList<NoteData>();
        this.resources = resources;
    }

    public LocalRepositoryImpl init() {
        String[] titles = resources.getStringArray(R.array.note_name);
        String[] descriptions = resources.getStringArray(R.array.note_body);
        TypedArray backgroundColor = resources.obtainTypedArray(R.array.color_background);

        for (int i = 0; i < titles.length; i++) {
            dataSource.add(new NoteData(titles[i], descriptions[i], backgroundColor.getColor(i, 0)));
        }
        return this;
    }

    @Override
    public int size() {
        return dataSource.size();
    }

    @Override
    public List<NoteData> getAllNotesData() {
        return dataSource;
    }

    @Override
    public NoteData getNoteData(int position) {
        return dataSource.get(position);
    }

    @Override
    public void clearNotesData() {
        dataSource.clear();
    }

    @Override
    public void addNoteData(NoteData noteData) {
        dataSource.add(noteData);
    }

    @Override
    public void deleteNoteData(int position) {
        dataSource.remove(position);
    }

    @Override
    public void updateNoteData(int position, NoteData newNoteData) {
        dataSource.set(position, newNoteData);
    }
}
