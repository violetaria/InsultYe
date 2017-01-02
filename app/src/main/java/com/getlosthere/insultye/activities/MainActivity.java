package com.getlosthere.insultye.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.getlosthere.insultye.R;
import com.getlosthere.insultye.databinding.ActivityMainBinding;
import com.getlosthere.insultye.helpers.DatabaseHelper;
import com.getlosthere.insultye.models.Insult;
import com.getlosthere.insultye.models.Word;
import com.plattysoft.leonids.ParticleSystem;

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

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.dragon_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        btnThrowOne = binding.btnThrow;
        tvInsult = binding.tvInsult;
        binding.setInsult(insult);

        btnThrowOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer numberOfParticles = 50;
                new ParticleSystem(MainActivity.this, numberOfParticles, R.drawable.fire, 600)
                        .setSpeedRange(0.1f, 0.5f)
                        .oneShot(tvInsult, numberOfParticles);
                binding.setInsult(null);

                Word salutation = Word.getRandom(SALUTATION);
                Word noun = Word.getRandom(NOUN);
                Word singleAdjective = Word.getRandom(SINGLE_ADJ);
                Word doubleAdjective = Word.getRandom(DOUBLE_ADJ);

                insult.setText(salutation,singleAdjective,doubleAdjective,noun);
                binding.setInsult(insult);
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
