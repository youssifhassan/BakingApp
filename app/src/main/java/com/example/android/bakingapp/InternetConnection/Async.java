package com.example.android.bakingapp.InternetConnection;

import android.net.Uri;
import android.os.AsyncTask;

import com.example.android.bakingapp.Model.FoodItem;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by youss on 2/20/2018.
 */

public class Async extends AsyncTask<String,Void,String> {
    public AsyncResponse delegate = null;

    public Async(AsyncResponse asyncResponse) {
        delegate = asyncResponse;//Assigning call back interface through constructor
    }

    @Override
    protected String doInBackground(String... strings) {
        String json = null;
        try {
            Uri.Builder builder = new Uri.Builder();
            builder.scheme("http")
                    .authority("d17h27t6h515a5.cloudfront.net")
                    .appendPath("topher")
                    .appendPath("2017")
                    .appendPath("May")
                    .appendPath("59121517_baking")
                    .appendPath("baking.json");
            URL url = new URL(builder.toString());

            HttpConnection httpConnection = new HttpConnection(url);
            json = httpConnection.getConnection();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override
    protected void onPostExecute(String s) {
        ArrayList<FoodItem> foodItems;
        JsonParser jsonParser = new JsonParser();
        foodItems=jsonParser.parseJson(s);
        delegate.ProcessFinish(foodItems);
    }
}
