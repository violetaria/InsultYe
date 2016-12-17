package com.getlosthere.insultye.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by violetaria on 12/1/16.
 */
@Table(name = "Salutations")
public class Salutation extends Model{
    @Column(name = "Value")
    public String value;

    public Salutation(){
        super();
    }

    public Salutation(String salutation){
        super();
        this.value = salutation;
    }

    public String getValue(){
        return value;
    }

    public static Salutation getRandom() {
        return new Select().from(Salutation.class).orderBy("RANDOM()").executeSingle();
    }

    public static List<Salutation> getAll() {
        return new  Select()
                .from(Salutation.class)
                .orderBy("Value ASC")
                .execute();
    }

    public static ArrayList<String> getAllValues(){
        List<Salutation> salutations;
        ArrayList<String> values = new ArrayList<>();
        salutations = new  Select()
                .from(Salutation.class)
                .orderBy("Value ASC")
                .execute();
        for(int i = 0; i < salutations.size(); i++){
            values.add(i,salutations.get(i).getValue());
        }
        return values;
    }

}
