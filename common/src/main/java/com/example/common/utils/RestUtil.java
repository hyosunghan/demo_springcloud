package com.example.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author hyosunghan
 * @since 2020-09-04
 */

public class RestUtil {

    //get
    public static String getMethod(String url) throws IOException {
        URL restURL = new URL(url);

        HttpURLConnection conn = (HttpURLConnection) restURL.openConnection();

        conn.setRequestMethod("GET"); // POST GET PUT DELETE
        conn.setRequestProperty("Accept", "application/json");

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuffer sb = new StringBuffer();
        String line;
        while((line = br.readLine()) != null ){
            sb.append(line);
        }

        br.close();
        return sb.toString();
    }

    //post
    public static String postMethod(String url, String query) throws IOException {
        URL restURL = new URL(url);

        HttpURLConnection conn = (HttpURLConnection) restURL.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        PrintStream ps = new PrintStream(conn.getOutputStream());
        ps.print(query);
        ps.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuffer sb = new StringBuffer();
        String line;
        while((line = br.readLine()) != null ){
            sb.append(line);
        }

        br.close();
        return sb.toString();
    }

    public static void main(String[] args) {
        try {
            getMethod("http://www.baidu.com");

            String url = "";
            String query = ""; //json格式
            postMethod(url, query);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}

