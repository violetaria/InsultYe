package com.getlosthere.insultye.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.getlosthere.insultye.R;
import com.getlosthere.insultye.adapters.WordsAdapter;
import com.getlosthere.insultye.databinding.ActivityEditBinding;
import com.getlosthere.insultye.models.Noun;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {
    ListView lvWords;
    private ActivityEditBinding binding;
    ArrayList<String> words;
    ArrayAdapter<String> wordsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit);
        lvWords = binding.lvWords;
        words = new ArrayList<String>();
        populateWords();
    }

    private void populateWords() {
        words = Noun.getAllValues();
        wordsAdapter = new WordsAdapter(this, words);
        lvWords.setAdapter(wordsAdapter);
    }
}
