package com.example.android.bakingapp.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.Adapter.StepsRVAdapter;
import com.example.android.bakingapp.Model.FoodItem;
import com.example.android.bakingapp.Model.Step;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.Adapter.RecyclerItemClickListener;

/**
 * Created by youss on 3/27/2018.
 */

public class IngredientsDetails extends Fragment {

    TextView ingredients;
    TextView viewDetailedText;
    RecyclerView recyclerView;
    String ingredientsString;
    OnStepSelectionChangeListener listener;
    FoodItem foodItem;

    public interface OnStepSelectionChangeListener {
        public void OnSelectionChanged(Step step);
    }

    public IngredientsDetails() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (OnStepSelectionChangeListener) context;
        } catch (ClassCastException e) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {((AppCompatActivity) getActivity()).getSupportActionBar().show();

        View view =inflater.inflate(R.layout.ingredients_details_fragment, container, false);

        ingredients = (TextView) view.findViewById(R.id.ingredients);
        recyclerView = (RecyclerView) view.findViewById(R.id.steps);
        viewDetailedText = (TextView) view.findViewById(R.id.txtbtn);

        if (getArguments() != null && getArguments().containsKey("single")){
            foodItem = (FoodItem) getArguments().getParcelable("single");
            setData(foodItem);
        }


        return view;
    }

    public void setFoodItem(FoodItem foodItem) {
        this.foodItem = foodItem;
    }

    public void setData(final FoodItem foodItem){
        ingredientsString = "";

        for (String s : foodItem.getIngredients()){
            ingredientsString = ingredientsString+s+" \n\n";
        }

        String ingsPart = "";
        for (String s : foodItem.getingredientsPart()){
            ingsPart =ingsPart+s+",";
        }

        ingredients.setText(ingsPart);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new StepsRVAdapter(foodItem.getSteps()));

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        listener.OnSelectionChanged(foodItem.getSteps().get(position));
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                })
        );

        viewDetailedText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setTitle("More Details")
                        .setMessage(ingredientsString)
                        .show();
            }
        });
    }
}
