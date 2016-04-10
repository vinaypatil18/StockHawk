package com.sam_chordas.android.stockhawk.rest;

import com.sam_chordas.android.stockhawk.model.StockHistory;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;


public interface StockHistoryEndPoints {
    @GET("public/yql")
    Call<StockHistory> getHistoricalData(@Query("q") String yqlQuery,
                                         @Query("format") String responseFormat,
                                         @Query("diagnostics") String diagnostics,
                                         @Query("env") String env);
}
