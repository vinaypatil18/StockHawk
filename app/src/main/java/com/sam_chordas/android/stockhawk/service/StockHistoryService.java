package com.sam_chordas.android.stockhawk.service;


import com.sam_chordas.android.stockhawk.model.StockHistory;
import com.sam_chordas.android.stockhawk.rest.StockHistoryEndPoints;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class StockHistoryService {
    private static Retrofit retrofit;
    private static StockHistoryEndPoints service;
    private static final String BASE_URI = "https://query.yahooapis.com/v1/";

    static {
        System.out.println("Building Retrofit");
        retrofit = new Retrofit.Builder().baseUrl(BASE_URI).addConverterFactory(GsonConverterFactory.create()).build();
        service = retrofit.create(StockHistoryEndPoints.class);
    }

    public static Call<StockHistory> getHistoricalData(String yqlQuery, String responseFormat, String diagnostics, String env) {
        Call<StockHistory> stockHistoryCall = service.getHistoricalData(yqlQuery, responseFormat, diagnostics, env);
        return stockHistoryCall;
    }
}
