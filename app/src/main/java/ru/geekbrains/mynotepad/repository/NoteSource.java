package ru.geekbrains.mynotepad.repository;

import java.util.List;

public interface NoteSource {
    int size();
    List<NoteData> getAllCardsData();
    NoteData getCardData(int position);

    void clearNotesData();
    void addNoteData(NoteData noteData);

    void deleteNoteData(int position);
    void updateNoteData(int position, NoteData newNoteData);
}
