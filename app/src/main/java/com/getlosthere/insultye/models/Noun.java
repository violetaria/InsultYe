package com.getlosthere.insultye.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by violetaria on 12/10/16.
 */

@Table(name = "Nouns")
public class Noun extends Model {
    @Column(name = "Value")
    public String value;

    public Noun() {
        super();
    }

    public Noun(String noun) {
        super();
        this.value = noun;
    }

    public String getValue() {
        return value;
    }

    public static Noun getRandom() {
        return new Select().from(Noun.class).orderBy(
                "RANDOM()").executeSingle();
    }

    public static List<Noun> getAll() {
        return new Select()
                .from(Noun.class)
                .orderBy("Value ASC")
                .execute();
    }

}