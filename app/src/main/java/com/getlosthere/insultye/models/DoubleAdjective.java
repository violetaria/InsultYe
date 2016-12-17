package com.getlosthere.insultye.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.ArrayList;
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

    public static List<DoubleAdjective> getAll() {
        return new Select()
                .distinct()
                .from(DoubleAdjective.class)
                .groupBy("Value")
                .orderBy("Value ASC")
                .execute();
    }

    public static ArrayList<String> getAllValues(){
        List<DoubleAdjective> doubleAdjectives;
        ArrayList<String> values = new ArrayList<>();
        doubleAdjectives = new  Select()
                .distinct()
                .from(DoubleAdjective.class)
                .groupBy("Value")
                .orderBy("Value ASC")
                .execute();
        for(int i = 0; i < doubleAdjectives.size(); i++){
            values.add(i,doubleAdjectives.get(i).getValue());
        }
        return values;
    }

}