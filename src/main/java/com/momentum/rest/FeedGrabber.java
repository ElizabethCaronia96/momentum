package com.momentum.rest;

import com.momentum.rest.entities.Price;
import com.momentum.rest.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FeedGrabber {

    @Autowired
    private PriceService ps;

    @Scheduled(fixedRate = 1000) // this is in milliseconds
    public void grabFeedPrices() throws IOException {

        Date dt = new Date();
        Timestamp timestamp = new Timestamp(dt.getTime());
        System.out.println(String.format("[%s]: Adding new prices from feed.", timestamp));

        String[] stocksToCheck = {"AAPL", "GOOG", "BRK-A", "NSC", "MSFT", "OTHER"};
        String requestURL = "http://feed.conygre.com:8080/MockYahoo/quotes.csv?s=%s&f=p0";
        String joinedStocksString = String.join(",", stocksToCheck);
        String finalrequestURL = String.format(requestURL, joinedStocksString);


        List<Double> priceResults = makeFeedPricesRequest(finalrequestURL);


        for (int i = 0; i < stocksToCheck.length; i++) {
            Price newPrice = new Price(stocksToCheck[i], priceResults.get(i), timestamp);
            ps.addNewPrice(newPrice);
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
