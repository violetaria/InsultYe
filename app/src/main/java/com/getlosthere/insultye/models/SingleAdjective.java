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

    public static List<SingleAdjective> getAll() {
        return new Select()
                .distinct()
                .from(SingleAdjective.class)
                .groupBy("Value")
                .orderBy("Value ASC")
                .execute();
    }

    public static ArrayList<String> getAllValues(){
        List<SingleAdjective> singleAdjectives;
        ArrayList<String> values = new ArrayList<>();
        singleAdjectives = new  Select()
                .distinct()
                .from(SingleAdjective.class)
                .groupBy("Value")
                .orderBy("Value ASC")
                .execute();
        for(int i = 0; i < singleAdjectives.size(); i++){
            values.add(i,singleAdjectives.get(i).getValue());
        }
        return values;
    }
}