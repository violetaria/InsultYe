package com.getlosthere.insultye.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.getlosthere.insultye.R;
import com.getlosthere.insultye.databinding.ActivityMainBinding;
import com.getlosthere.insultye.helpers.DatabaseHelper;
import com.getlosthere.insultye.models.Insult;
import com.getlosthere.insultye.models.Word;

public class MainActivity extends AppCompatActivity {
    Button btnThrowOne;
    TextView tvInsult;
    Insult insult = new Insult();
    private final Integer SALUTATION = 1;
    private final Integer SINGLE_ADJ = 2;
    private final Integer DOUBLE_ADJ = 3;
    private final Integer NOUN = 4;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Kingthings_Calligraphica_2.ttf");
//        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Kingthings_Petrock.ttf");

        Toolbar toolbar = binding.toolbar;
        TextView tvToolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        tvToolbarTitle.setTypeface(font);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayUseLogoEnabled(false);

        btnThrowOne = binding.btnThrow;
        tvInsult = binding.tvInsult;
        binding.setInsult(insult);

        final Animation animScrollNewWord = AnimationUtils.loadAnimation(this, R.anim.scroll_new_word);
        final Animation animScrollOldWord = AnimationUtils.loadAnimation(this, R.anim.scroll_old_word);
        tvInsult.setTypeface(font);
        btnThrowOne.setTypeface(font);
        btnThrowOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                AnimationSet replaceAnimation = new AnimationSet(false);
//                replaceAnimation.setFillAfter(true);
//                replaceAnimation.addAnimation(animScrollOldWord);

                Word salutation = Word.getRandom(SALUTATION);
                Word noun = Word.getRandom(NOUN);
                Word singleAdjective = Word.getRandom(SINGLE_ADJ);
                Word doubleAdjective = Word.getRandom(DOUBLE_ADJ);

                insult.setText(salutation,singleAdjective,doubleAdjective,noun);
                binding.setInsult(insult);
                tvInsult.startAnimation(animScrollNewWord);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.miEditInsults:
                Intent i = new Intent(MainActivity.this, EditActivity.class);
                startActivity(i);
                return true;
            case R.id.miResultInsults:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.reset_db);
                builder.setMessage(R.string.are_you_sure);
                builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
                        databaseHelper.resetDatabase();
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
