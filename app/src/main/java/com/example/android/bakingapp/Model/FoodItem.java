package com.example.android.bakingapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by dell on 19/02/2018.
 */

public class FoodItem implements Parcelable {

    int id;
    String name;
    ArrayList<String> ingredients;
    ArrayList<Step> steps;
    ArrayList<String> ingredientsPart;
    String imageUrl;


    public FoodItem(int id, String name, ArrayList<String> ingredients, ArrayList<Step> steps, ArrayList<String> ingredientsPart, String imageUrl) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.ingredientsPart = ingredientsPart;
        this.steps = steps;
        this.imageUrl=imageUrl;
    }

    protected FoodItem(Parcel in) {
        id = in.readInt();
        name = in.readString();
        ingredients = in.createStringArrayList();
        ingredientsPart=in.createStringArrayList();
        steps = in.createTypedArrayList(Step.CREATOR);
        imageUrl = in.readString();

    }

    public static final Creator<FoodItem> CREATOR = new Creator<FoodItem>() {
        @Override
        public FoodItem createFromParcel(Parcel in) {
            return new FoodItem(in);
        }

        @Override
        public FoodItem[] newArray(int size) {
            return new FoodItem[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<String> getingredientsPart() {
        return ingredientsPart;
    }

    public void setingredientsPart(ArrayList<String> ingredientsPart) {
        this.ingredientsPart = ingredientsPart;
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeStringList(ingredients);
        parcel.writeStringList(ingredientsPart);
        parcel.writeTypedList(steps);
        parcel.writeString(imageUrl);
    }
}
