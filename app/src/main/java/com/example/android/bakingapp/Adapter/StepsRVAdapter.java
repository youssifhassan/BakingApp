package com.example.android.bakingapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.Model.Step;

import java.util.ArrayList;


public class StepsRVAdapter extends RecyclerView.Adapter<StepsRVAdapter.StepsViewHolder> {


    ArrayList<Step> steps;

    public StepsRVAdapter(ArrayList<Step> steps){
        this.steps = steps;
    }


    @Override
    public StepsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.stepitem, parent, false);
        StepsViewHolder mainViewHolder = new StepsViewHolder(itemView);

        return mainViewHolder;
    }

    @Override
    public void onBindViewHolder(StepsViewHolder holder, int position) {

        holder.shortDescriptionText.setText(steps.get(position).getShortDesc());

    }



    @Override
    public int getItemCount() {
        if(steps==null)
            return 0;
        else
            return steps.size();
    }


    class StepsViewHolder extends RecyclerView.ViewHolder{

        TextView shortDescriptionText;

        public StepsViewHolder(View itemView) {
            super(itemView);
            shortDescriptionText = (TextView) itemView.findViewById(R.id.step_short_desc);
        }

    }
}

