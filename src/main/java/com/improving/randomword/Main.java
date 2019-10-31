package com.improving.randomword;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
        public static void main(String args[]) throws IOException {
            URL url = new URL("https://randomword.com");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            Scanner scanner = new Scanner(in);
            String html = scanner.useDelimiter("\\A").next();
            var word = getWord(html);
            System.out.println(word);
            in.close();
        }

        public static String getWord(String html){
            Pattern pattern = Pattern.compile("<div id=\"random_word\">(.*)</div>");
            Matcher matcher = pattern.matcher(html);
            while (matcher.find()) {
                return(matcher.group(1));
            }
            return "No match found.";
        }
}