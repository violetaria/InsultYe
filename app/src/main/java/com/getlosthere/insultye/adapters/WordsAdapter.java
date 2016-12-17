package com.getlosthere.insultye.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.getlosthere.insultye.R;

import java.util.ArrayList;

/**
 * Created by violetaria on 12/17/16.
 */

public class WordsAdapter extends ArrayAdapter<String> {

    public WordsAdapter(Context context, ArrayList<String> words) {
        super(context, 0, words);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            String word = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_word, parent, false);
            }
            // Lookup view for data population
            TextView tvValue = (TextView) convertView.findViewById(R.id.tvValue);
            // Populate the data into the template view using the data object
            tvValue.setText(word);
            // Return the completed view to render on screen
            return convertView;
    }
}