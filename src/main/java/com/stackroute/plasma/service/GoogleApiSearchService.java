package com.stackroute.plasma.service;

import org.springframework.stereotype.Service;

import javax.json.Json;
import javax.json.stream.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class GoogleApiSearchService {
    final static String apiKey = "AIzaSyB-93tpPyxrK76l6iw-mFnsvDiUJCLpFw8";
    final static String customSearchEngineKey = "006477474756235376421:nz2modhy5qa";
    public String newResult ;
    private int initial;
   // String[] link = new String[10];
    String[] link;
   // List<String> newList = new ArrayList<>();
    List<String[]> newList;
    //int i=0;
    private int finall;
    //String result;
    // base url for the search query
    final static String searchURL = "https://www.googleapis.com/customsearch/v1?";
    //https://cse.google.com/cse/setup/basic?cx=006477474756235376421%3Anz2modhy5qa
    //https://console.developers.google.com/apis/credentials?project=plasma-231517

    public String[] read(String qSearch, int start, int numOfResults) {
        try {
            int i = 0;
            link = new String[10];
            //String[] link = new String[10];
           // newList = new ArrayList<>();
            String toSearch = searchURL + "key=" + apiKey + "&cx="
                    + customSearchEngineKey + "&q=";

            toSearch += qSearch;

            toSearch += "&alt=json";

            toSearch += "&start=" + start;

            toSearch += "&num=" + numOfResults;

            URL url = new URL(toSearch);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            StringBuffer buffer = new StringBuffer();
            while ((line = br.readLine()) != null) {
                buffer.append(line);
            }
            newResult = buffer.toString();
            JsonParser parser = Json.createParser(new StringReader(newResult));
            while (parser.hasNext()) {
                JsonParser.Event event = parser.next();

                if (event == JsonParser.Event.KEY_NAME) {

                    if (parser.getString().equals("htmlTitle")) {
                        JsonParser.Event value = parser.next();
                        if (value == JsonParser.Event.VALUE_STRING)
                            System.out.println("Title (HTML): "
                                    + parser.getString());
                    }

                    if (parser.getString().equals("link")) {

                        JsonParser.Event value = parser.next();

                        if (value == JsonParser.Event.VALUE_STRING)
                            link[i++] = parser.getString();
                            //link = link + parser.getString();
                            //link[0][i++] = parser.getString();
                            //newList[] = parser.getString();
                            System.out.println("Link: " + parser.getString());
                      //  System.out.println("helloooooo"+ link[i]);
                    }

                }

            }

            initial = initial + 10;

            finall++;

            System.out
                    .println("**************************************************");

            //return result;
           // return link;
            //return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
       // return null;
        return link;
    }

}
