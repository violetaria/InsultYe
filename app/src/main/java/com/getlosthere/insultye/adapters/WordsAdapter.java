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
    private static class ViewHolder {
        TextView tvValue;
    }

    public WordsAdapter(Context context, ArrayList<String> words) {
        super(context, 0, words);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String word = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_word, parent, false);
            viewHolder.tvValue = (TextView) convertView.findViewById(R.id.tvValue);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvValue.setText(word);

        return convertView;
    }
}