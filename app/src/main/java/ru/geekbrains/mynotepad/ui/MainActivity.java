package ru.geekbrains.mynotepad.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ru.geekbrains.mynotepad.R;
import ru.geekbrains.mynotepad.publisher.Publisher;
import ru.geekbrains.mynotepad.ui.main.NotepadFragment;

public class MainActivity extends AppCompatActivity {

    private Publisher publisher;
    private Navigation navigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        publisher = new Publisher();
        navigation = new Navigation(getSupportFragmentManager());
        if(savedInstanceState==null){
            navigation.replaceFragment(NotepadFragment.newInstance(),false);
        }
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public Navigation getNavigation() {
        return navigation;
    }
}