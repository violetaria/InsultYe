package com.getlosthere.insultye.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.getlosthere.insultye.R;
import com.getlosthere.insultye.models.DoubleAdjective;
import com.getlosthere.insultye.models.Noun;
import com.getlosthere.insultye.models.Salutation;
import com.getlosthere.insultye.models.SingleAdjective;

public class MainActivity extends AppCompatActivity {
    Button btnThrowOne;
    TextView tvInsult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.dragon_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_main);

        btnThrowOne = (Button) findViewById(R.id.btnThrow);
        tvInsult = (TextView) findViewById(R.id.tvInsult);

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
}
