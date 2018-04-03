package com.example.android.bakingapp.Activity;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.bakingapp.Fragment.IngredientsDetails;
import com.example.android.bakingapp.Fragment.StepDetails;
import com.example.android.bakingapp.Model.FoodItem;
import com.example.android.bakingapp.Model.Step;
import com.example.android.bakingapp.R;

public class FoodDetails extends AppCompatActivity implements IngredientsDetails.OnStepSelectionChangeListener {

    FoodItem foodItem;
    IngredientsDetails ingredientsDetails;
    StepDetails stepDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        ingredientsDetails = (IngredientsDetails) getFragmentManager().findFragmentById(R.id.ingredientsFragment);
        stepDetails = (StepDetails) getFragmentManager().findFragmentById(R.id.stepsFragment);

        foodItem = (FoodItem) getIntent().getParcelableExtra("foodItems");

        if(findViewById(R.id.larger) == null){
            android.app.Fragment check =  getFragmentManager().findFragmentById(R.id.foodIngredientsAndSteps);
            if(check == null){

                Bundle bundle = new Bundle();
                bundle.putParcelable("single",foodItem);
                IngredientsDetails foodDetailsList = new IngredientsDetails();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction().add(R.id.foodIngredientsAndSteps,foodDetailsList);
                foodDetailsList.setArguments(bundle);
                fragmentTransaction.commit();
            }
        }
        else{
            if (ingredientsDetails!= null){
                ingredientsDetails.setData(foodItem);
            }

        }
    }


    @Override
    public void OnSelectionChanged(Step step) {
        if(findViewById(R.id.larger) == null){

            Bundle bundle = new Bundle();
            bundle.putParcelable("step",step);
            StepDetails stepDetails = new StepDetails();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction().replace(R.id.foodIngredientsAndSteps,stepDetails);
            stepDetails.setArguments(bundle);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        else{
            StepDetails stepDetails = (StepDetails) getFragmentManager().findFragmentById(R.id.stepsFragment);
            if (step != null){
                stepDetails.setStep(step);
                stepDetails.setImage(step.getThumbUrl());
                stepDetails.setDesc(step.getDesc());
                stepDetails.reset();
                stepDetails.releasePlayer();
                stepDetails.initializePlayer(step);
            }
        }

    }

}
