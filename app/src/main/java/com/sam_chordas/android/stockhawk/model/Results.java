package com.sam_chordas.android.stockhawk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Results {

    @SerializedName("quote")
    @Expose
    private List<Quote> quote = new ArrayList<Quote>();

    /**
     *
     * @return
     * The quote
     */
    public List<Quote> getQuote() {
        return quote;
    }

    /**
     *
     * @param quote
     * The quote
     */
    public void setQuote(List<Quote> quote) {
        this.quote = quote;
    }

}