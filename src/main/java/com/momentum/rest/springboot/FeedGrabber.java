package com.momentum.rest.springboot;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class FeedGrabber {

    @Scheduled(fixedRate = 1000) // this is in milliseconds
    public void grabFeedPrices() throws IOException {

        String[] stocksToCheck = {"AAPL", "GOOG", "BRK-A", "NSC", "MSFT", "OTHER"};
        String requestURL = "http://feed.conygre.com:8080/MockYahoo/quotes.csv?s=%s&f=p0";
        String joinedStocksString = String.join(",", stocksToCheck);
        String finalrequestURL = String.format(requestURL, joinedStocksString);

        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String currentTime = sdf.format(dt);

        List<Double> priceResults = makeFeedPricesRequest(finalrequestURL);

        for (int i = 0; i < stocksToCheck.length; i++) {
            // add this to the database
            // COLUMNS:
            // STOCK | PRICE | DATETIME

            System.out.println(stocksToCheck[i] + "\t" + priceResults.get(i) + "\t" + currentTime);
        }

    }

    private static List<Double> makeFeedPricesRequest(String requestURL) throws IOException {
        List<Double> results = new ArrayList<>();
        URL url = new URL(requestURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            results.add(Double.parseDouble(line));
        }
        rd.close();

        return results;
    }

}
