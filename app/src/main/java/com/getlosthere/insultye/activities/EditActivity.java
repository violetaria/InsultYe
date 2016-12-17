package com.getlosthere.insultye.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;

import com.getlosthere.insultye.R;
import com.getlosthere.insultye.adapters.WordsAdapter;
import com.getlosthere.insultye.databinding.ActivityEditBinding;
import com.getlosthere.insultye.models.DoubleAdjective;
import com.getlosthere.insultye.models.Noun;
import com.getlosthere.insultye.models.Salutation;
import com.getlosthere.insultye.models.SingleAdjective;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {
    RecyclerView lvWords;
    ImageButton ibSalutation;
    ImageButton ibNoun;
    ImageButton ibSingleAdjective;
    ImageButton ibDoubleAdjective;
    private ActivityEditBinding binding;
    ArrayList<String> words;
    WordsAdapter wordsAdapter;
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
                replaceWords(Salutation.getAllValues());
            }
        });
        ibNoun = binding.ibNoun;
        ibNoun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceWords(Noun.getAllValues());
            }
        });
        ibSingleAdjective = binding.ibAdjective1;
        ibSingleAdjective.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceWords(SingleAdjective.getAllValues());
            }
        });
        ibDoubleAdjective = binding.ibAdjective2;
        ibDoubleAdjective.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceWords(DoubleAdjective.getAllValues());
            }
        });
        words = new ArrayList<String>();
        populateWords();
    }

    private void populateWords() {
        wordsAdapter = new WordsAdapter(this, words);
        lvWords.setAdapter(wordsAdapter);
        lvWords.setLayoutManager(new LinearLayoutManager(this));
        replaceWords(Salutation.getAllValues());
    }

    private void replaceWords(ArrayList<String> newWords){
        int oldSize = words.size();
        words.clear();
        wordsAdapter.notifyItemRangeRemoved(0,oldSize);
        words.addAll(newWords);
        wordsAdapter.notifyItemRangeInserted(0, newWords.size());
    }
}
