package com.getlosthere.insultye.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.getlosthere.insultye.R;
import com.getlosthere.insultye.adapters.WordsAdapter;
import com.getlosthere.insultye.databinding.ActivityEditBinding;
import com.getlosthere.insultye.models.DoubleAdjective;
import com.getlosthere.insultye.models.Noun;
import com.getlosthere.insultye.models.Salutation;
import com.getlosthere.insultye.models.SingleAdjective;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {
    ListView lvWords;
    ImageButton ibSalutation;
    ImageButton ibNoun;
    ImageButton ibSingleAdjective;
    ImageButton ibDoubleAdjective;
    private ActivityEditBinding binding;
    ArrayList<String> words;
    ArrayAdapter<String> wordsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit);
        lvWords = binding.lvWords;
        ibSalutation = binding.ibSalutation;
        ibSalutation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                words = Salutation.getAllValues();
                wordsAdapter.clear();
                wordsAdapter.addAll(words);
                wordsAdapter.notifyDataSetChanged();
            }
        });
        ibNoun = binding.ibNoun;
        ibNoun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                words = Noun.getAllValues();
                wordsAdapter.clear();
                wordsAdapter.addAll(words);
                wordsAdapter.notifyDataSetChanged();
            }
        });
        ibSingleAdjective = binding.ibAdjective1;
        ibSingleAdjective.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                words = SingleAdjective.getAllValues();
                wordsAdapter.clear();
                wordsAdapter.addAll(words);
                wordsAdapter.notifyDataSetChanged();
            }
        });
        ibDoubleAdjective = binding.ibAdjective2;
        ibDoubleAdjective.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                words = DoubleAdjective.getAllValues();
                wordsAdapter.clear();
                wordsAdapter.addAll(words);
                wordsAdapter.notifyDataSetChanged();
            }
        });
        words = new ArrayList<String>();
        populateWords();
    }

    private void populateWords() {
        words = Noun.getAllValues();
        wordsAdapter = new WordsAdapter(this, words);
        lvWords.setAdapter(wordsAdapter);
    }

}
