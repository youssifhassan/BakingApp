package com.example.android.bakingapp.InternetConnection;

import com.example.android.bakingapp.Model.FoodItem;

import java.util.ArrayList;

/**
 * Created by youss on 2/20/2018.
 */

public interface AsyncResponse {
    void ProcessFinish(ArrayList<FoodItem> result);
}
