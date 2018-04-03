package com.example.android.bakingapp.InternetConnection;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by youss on 2/20/2018.
 */

public class HttpConnection {

    String json = null;
    URL url;

    public HttpConnection(URL url) {
        this.url = url;
    }

    public String getConnection(){
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

            json = stringBuilder.toString();
            inputStream.close();
            bufferedReader.close();
            httpURLConnection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }
}
