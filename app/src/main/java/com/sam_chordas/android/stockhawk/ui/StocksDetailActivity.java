package com.sam_chordas.android.stockhawk.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.model.Quote;
import com.sam_chordas.android.stockhawk.model.Results;
import com.sam_chordas.android.stockhawk.model.StockHistory;
import com.sam_chordas.android.stockhawk.service.StockHistoryService;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class StocksDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stocks_detail);

    }

    @Override
    protected void onResume() {
        super.onResume();
        String symbol = getIntent().getStringExtra("symbol");
        getSupportActionBar().setTitle(symbol);
        String startDate = "2015-10-10";
        String endDate = "2016-04-10";
        String yqlQuery = "select * from yahoo.finance.historicaldata where symbol = \"" + symbol + "\" and startDate = \"" + startDate + "\" and endDate = \"" + endDate + "\"";
        String format = "json";
        String diagnostic = "true";
        String env = "http://datatables.org/alltables.env";

        Call<StockHistory> historicalData = StockHistoryService.getHistoricalData(yqlQuery, format, diagnostic, env);
        historicalData.enqueue(new Callback<StockHistory>() {
            @Override
            public void onResponse(Response<StockHistory> response, Retrofit retrofit) {
                StockHistory stockHistory = response.body();
                Results results = stockHistory.getQuery().getResults();
                LineChart lineChart = (LineChart) findViewById(R.id.chart);
                ArrayList<Entry> entries = new ArrayList<>();
                int index = 0;
                int nextMonth = 0;
                int quoteSize = results.getQuote().size();
                while (index < 6 && nextMonth <= quoteSize ) {
                    Quote quote = results.getQuote().get(nextMonth);
                    Entry entry = new Entry(Float.parseFloat(quote.getHigh()), index);
                    entries.add(entry);
                    index++;
                    nextMonth = nextMonth + 20;

                }


                LineDataSet dataset = new LineDataSet(entries, "High Value");
                ArrayList<String> labels = new ArrayList<>();
                labels.add("October");
                labels.add("November");
                labels.add("December");
                labels.add("January");
                labels.add("February");
                labels.add("March");

                LineData data = new LineData(labels, dataset);
                lineChart.setData(data); // set the data and list of lables into chart
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS);
                lineChart.setDescription("Stock History");  // set the description
                lineChart.invalidate();
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
