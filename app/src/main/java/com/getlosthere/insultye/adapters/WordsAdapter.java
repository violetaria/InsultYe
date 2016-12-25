package com.getlosthere.insultye.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.getlosthere.insultye.R;
import com.getlosthere.insultye.models.Word;

import java.util.List;

/**
 * Created by violetaria on 12/17/16.
 */

public class WordsAdapter extends RecyclerView.Adapter<WordsAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvValue;

        public ViewHolder(View itemView) {
            super(itemView);

            tvValue = (TextView) itemView.findViewById(R.id.tvValue);
        }
    }

    private List<Word>words;
    private Context context;

    public WordsAdapter(Context context, List<Word> words) {
        this.words = words;
        this.context = context;
    }

    private Context getContext() {
        return context;
    }

    @Override
    public WordsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_word, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WordsAdapter.ViewHolder viewHolder, int position) {
        Word word = words.get(position);
        TextView tvValue = viewHolder.tvValue;
        tvValue.setText(word.getValue());
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    public void addItem(String value, Integer type) {
        Word newWord = new Word();
        newWord.value = value;
        newWord.type = type;
        newWord.save();
        words.add(newWord);
        notifyItemInserted(words.size());
    }

    public void removeItem(int position) {
        Word word = words.get(position);
        Word.delete(Word.class, word.getId());
        words.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, words.size());
    }

    public void editItem(int position, String value) {
        Word word = words.get(position);
        word.value = value;
        word.save();
    }
}