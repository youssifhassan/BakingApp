package com.example.android.bakingapp.InternetConnection;

import com.example.android.bakingapp.Model.FoodItem;
import com.example.android.bakingapp.Model.Step;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by youss on 2/20/2018.
 */

public class JsonParser {

    public ArrayList<FoodItem> parseJson(String json){
        ArrayList<FoodItem> foodItems = new ArrayList<FoodItem>();

        try {
            JSONArray foods = new JSONArray(json);
            for (int i=0; i < foods.length(); i++){
                JSONObject foodData = foods.getJSONObject(i);
                Integer id = foodData.getInt("id");
                String name = foodData.getString("name");
                String image = foodData.getString("image");

                ArrayList<String> ingredientsArray = new ArrayList<String>();
                ArrayList<String> ingredientsPartArray = new ArrayList<String>();
                JSONArray ingredients = foodData.getJSONArray("ingredients");
                for(int j=0;j<ingredients.length();j++){
                    JSONObject ing = ingredients.getJSONObject(j);
                    String string = ing.getInt("quantity")+" "+ing.getString("measure")+" "+ing.getString("ingredient");
                    ingredientsArray.add(string);
                    ingredientsPartArray.add(ing.getString("ingredient"));
                }

                JSONArray steps = foodData.getJSONArray("steps");
                ArrayList<Step>stepsArr = new ArrayList<Step>();

                for(int k=0;k<steps.length();k++){
                    JSONObject step = steps.getJSONObject(k);
                    int stepId =step.getInt("id");
                    String stepShortDescription = step.getString("shortDescription");
                    String stepDesc = step.getString("description");
                    String stepVideo = step.getString("videoURL");
                    String stepThumb = step.getString("thumbnailURL");
                    stepsArr.add(new Step(stepId,stepShortDescription,stepDesc,stepVideo,stepThumb));
                }

                foodItems.add(new FoodItem(id,name,ingredientsArray,stepsArr,ingredientsPartArray,image));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return foodItems;
    }
}
