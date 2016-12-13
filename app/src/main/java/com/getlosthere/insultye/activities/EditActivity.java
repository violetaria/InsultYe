package com.getlosthere.insultye.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.getlosthere.insultye.R;
import com.getlosthere.insultye.databinding.ActivityEditBinding;

public class EditActivity extends AppCompatActivity {
    ListView lvWords;
    private ActivityEditBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit);
        lvWords = binding.lvWords;
    }
}
