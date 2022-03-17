package ru.geekbrains.mynotepad.publisher;

import ru.geekbrains.mynotepad.repository.NoteData;

public interface Observer {

    void receiveMessage(NoteData noteData);
}
