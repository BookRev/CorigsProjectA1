package com.br.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import com.br.object.Books;
 
public class JavaGetRequest {
 
    private static HttpURLConnection con;
 
    public static void findisbn(Books book) throws MalformedURLException,
            ProtocolException, IOException {

    	long isbn = book.getIsbn();
        String url = "https://api2.isbndb.com/book/"+isbn;
        try {
            URL myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization","44230_24e69e2352639863e6a38b18cbeb9a64");
            con.setRequestMethod("GET");
            StringBuilder content;
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {
                String line;
                content = new StringBuilder();
                while ((line = in.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }
            String ts = content.toString();
            ts = ts.substring(ts.indexOf("<b>")+3);
          //  String title = ts.substring(0,ts.indexOf("</b>"));
         //   String story = ts.substring(ts.indexOf("</b>")+4,ts.indexOf("</p>"));
            String cover = ts.substring(ts.indexOf("image\":\"")+8,ts.indexOf(".jpg"))+".jpg";          
              book.setBookpic(cover);
        } finally {
 
            con.disconnect();
        }
    }
}