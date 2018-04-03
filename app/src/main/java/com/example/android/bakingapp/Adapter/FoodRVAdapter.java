package com.example.android.bakingapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.bakingapp.Model.FoodItem;
import com.example.android.bakingapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dell on 21/02/2018.
 */

public class FoodRVAdapter extends RecyclerView.Adapter<FoodRVAdapter.FoodViewHolder> {


    ArrayList<FoodItem> foodItems;
    Context context;

    public FoodRVAdapter(ArrayList<FoodItem> foodItems, Context context){
        this.context=context;
        this.foodItems = foodItems;

    }


    @Override
    public FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.food_item,parent,false);
        FoodViewHolder mainViewHolder = new FoodViewHolder(view);

        return mainViewHolder;
    }

    @Override
    public void onBindViewHolder(FoodViewHolder holder, int position) {

        holder.foodNameText.setText(foodItems.get(position).getName());
        if (!foodItems.get(position).getImageUrl().equals("")){
            Picasso.with(context).load(foodItems.get(position).getImageUrl()).into(holder.foodImageView);

        }
    }


    @Override
    public int getItemCount() {
        if(foodItems==null)
            return 0;
        else
            return foodItems.size();
    }


    class FoodViewHolder extends RecyclerView.ViewHolder{

        TextView foodNameText;
        ImageView foodImageView;

        public FoodViewHolder(View itemView) {
            super(itemView);

            foodNameText = (TextView) itemView.findViewById(R.id.food_name);
            foodImageView = (ImageView) itemView.findViewById(R.id.food_image);
        }

    }
}
