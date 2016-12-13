package com.getlosthere.insultye.models;

import com.getlosthere.insultye.R;
import com.getlosthere.insultye.applications.InsultYeApplication;

/**
 * Created by violetaria on 12/11/16.
 */

public class Insult {
    public String text;

    public Insult() {
        super();
        this.text = InsultYeApplication.resources.getString(R.string.intro_text);

    }

    public Insult(Salutation salutation,
                  SingleAdjective singleAdj,
                  DoubleAdjective doubleAdj,
                  Noun noun) {
            super();
            this.text = salutation.getValue() + " " +
                        singleAdj.getValue() + " " +
                        doubleAdj.getValue() + " " +
                        noun.getValue();
    }

    public void setText(String text) { this.text = text; }

    public void setText(Salutation salutation,
            SingleAdjective singleAdj,
            DoubleAdjective doubleAdj,
            Noun noun){
        this.text = salutation.getValue() + " " +
                singleAdj.getValue() + " " +
                doubleAdj.getValue() + " " +
                noun.getValue();
    }

    public String getText() { return text; }
}
