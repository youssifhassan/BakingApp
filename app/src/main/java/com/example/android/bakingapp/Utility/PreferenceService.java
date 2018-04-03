package com.example.android.bakingapp.Utility;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

public class PreferenceService extends IntentService {


    public static final String READ_PREFS="READ PREFS";

    public PreferenceService() {
        super("PreferenceService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(intent!=null){
            if (intent.getAction().equals(READ_PREFS)){
                readPreferences();
            }
        }
    }

    public static void startTheService(Context context){
        Intent intent = new Intent(context,PreferenceService.class);
        intent.setAction(READ_PREFS);
        context.startService(intent);
    }

    void readPreferences(){
        SharedPreferences sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        String name = sharedPref.getString("name", "empty");
        String ings = sharedPref.getString("ingredients", "empty");

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int appWidgetIds[] = appWidgetManager.getAppWidgetIds(new ComponentName(this,IngredientsWidget.class));
        IngredientsWidget.updateAll(this,appWidgetManager,appWidgetIds,name,ings);


    }
}
