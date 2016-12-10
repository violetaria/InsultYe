package com.getlosthere.insultye.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by violetaria on 12/10/16.
 */

@Table(name = "SingleAdjectives")
public class SingleAdjective extends Model {
    @Column(name = "Value")
    public String value;

    public SingleAdjective() {
        super();
    }

    public SingleAdjective(String singleAdjective) {
        super();
        this.value = singleAdjective;
    }

    public String getValue() {
        return value;
    }

    public static SingleAdjective getRandom() {
        return new Select().from(com.getlosthere.insultye.models.SingleAdjective.class).orderBy(
                "RANDOM()").executeSingle();
    }

    public static List<Noun> getAll() {
        return new Select()
                .from(com.getlosthere.insultye.models.SingleAdjective.class)
                .orderBy("Value ASC")
                .execute();
    }

}