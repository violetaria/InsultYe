package com.getlosthere.insultye.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.getlosthere.insultye.R;
import com.getlosthere.insultye.databinding.ActivityMainBinding;
import com.getlosthere.insultye.models.DoubleAdjective;
import com.getlosthere.insultye.models.Noun;
import com.getlosthere.insultye.models.Salutation;
import com.getlosthere.insultye.models.SingleAdjective;

public class MainActivity extends AppCompatActivity {
    Button btnThrowOne;
    TextView tvInsult;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.dragon_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        btnThrowOne = binding.btnThrow;
        tvInsult = binding.tvInsult;

        btnThrowOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Salutation salutation = Salutation.getRandom();
                Noun noun = Noun.getRandom();
                SingleAdjective singleAdjective = SingleAdjective.getRandom();
                DoubleAdjective doubleAdjective = DoubleAdjective.getRandom();
                String insultText = salutation.getValue() + " "
                        + singleAdjective.getValue() + " "
                        + doubleAdjective.getValue() + " "
                        + noun.getValue();
                tvInsult.setText(insultText);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
