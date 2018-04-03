package com.example.android.bakingapp.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.android.bakingapp.Adapter.FoodRVAdapter;
import com.example.android.bakingapp.Adapter.RecyclerItemClickListener;
import com.example.android.bakingapp.InternetConnection.Async;
import com.example.android.bakingapp.InternetConnection.AsyncResponse;
import com.example.android.bakingapp.Model.FoodItem;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.Utility.PreferenceService;
import com.example.android.bakingapp.Utility.SimpleIdlingResource;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<FoodItem> foodItems;
    RecyclerView recyclerView;

    @Nullable
    private SimpleIdlingResource mIdlingResource;

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new SimpleIdlingResource();
        }
        return mIdlingResource;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.foodlist);
        getIdlingResource();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        Async async = new Async(new AsyncResponse() {
            @Override
            public void ProcessFinish(ArrayList<FoodItem> result) {
                foodItems = result;
                recyclerView.setAdapter(new FoodRVAdapter(result,MainActivity.this));
            }
        });
        async.execute();

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        String name = foodItems.get(position).getName();
                        String ingredients ="";
                        for (String x : foodItems.get(position).getIngredients()){
                            ingredients =ingredients+x+" \n";

                        }

                        SharedPreferences settings = getSharedPreferences("prefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("name", name);
                        editor.putString("ingredients", ingredients);
                        editor.apply();

                        PreferenceService.startTheService(MainActivity.this);

                        Intent intent = new Intent(MainActivity.this,FoodDetails.class);
                        intent.putExtra("foodItems",foodItems.get(position));
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                })
        );
    }


}
