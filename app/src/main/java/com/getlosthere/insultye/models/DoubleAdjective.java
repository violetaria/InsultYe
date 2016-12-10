package com.getlosthere.insultye.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by violetaria on 12/10/16.
 */

@Table(name = "DoubleAdjectives")
public class DoubleAdjective extends Model {
    @Column(name = "Value")
    public String value;

    public DoubleAdjective() {
        super();
    }

    public DoubleAdjective(String doubleAdjective) {
        super();
        this.value = doubleAdjective;
    }

    public String getValue() {
        return value;
    }

    public static DoubleAdjective getRandom() {
        return new Select().from(DoubleAdjective.class).orderBy(
                "RANDOM()").executeSingle();
    }

    public static List<Noun> getAll() {
        return new Select()
                .from(DoubleAdjective.class)
                .orderBy("Value ASC")
                .execute();
    }

}