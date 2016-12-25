package com.getlosthere.insultye.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by violetaria on 12/18/16.
 */

@Table(name = "Word", id = "_id")
public class Word extends Model {
    @Column(name = "Value")
    public String value;
    @Column(name = "Word_Type")
    public Integer type;

    public Word() {
        super();
    }

    public Word(String value, Integer type) {
        super();
        this.value = value;
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public static Word getRandom(int type) {
        return new Select()
                .from(Word.class)
                .where("Word_Type = ?",type)
                .orderBy("RANDOM()")
                .executeSingle();
    }

    public static ArrayList<Word> getAll(int type) {
        List<Word> words = new Select()
                .distinct()
                .from(Word.class)
                .where("Word_Type = ?",type)
                .groupBy("Value")
                .orderBy("Value ASC")
                .execute();
        ArrayList<Word> wordArrayList = new ArrayList<>();
        for(int i = 0; i < words.size(); i++){
            wordArrayList.add(i,words.get(i));
        }
        return wordArrayList;
    }

    public static ArrayList<String> getAllValues(int type){
        List<Word> words = getAll(type);
        ArrayList<String> values = new ArrayList<>();
        for(int i = 0; i < words.size(); i++){
            values.add(i,words.get(i).getValue());
        }
        return values;
    }
}