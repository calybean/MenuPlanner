package com.youravgjoe.apps.menuplanner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class MealActivity extends AppCompatActivity {

    List<String> breakfastList = new ArrayList<>(Arrays.asList("Breakfast 1", "Breakfast 2", "Breakfast 3", "Breakfast 4", "Breakfast 5", "Breakfast 6", "Breakfast 7", "Breakfast 8", "Breakfast 9", "Breakfast 10"));
    List<String> lunchList = new ArrayList<>(Arrays.asList("Lunch 1", "Lunch 2", "Lunch 3", "Lunch 4", "Lunch 5", "Lunch 6", "Lunch 7", "Lunch 8", "Lunch 9", "Lunch 10"));
    List<String> dinnerList = new ArrayList<>(Arrays.asList("Dinner 1", "Dinner 2", "Dinner 3", "Dinner 4", "Dinner 5", "Dinner 6", "Dinner 7", "Dinner 8", "Dinner 9", "Dinner 10"));

    List<List<String>> mealListList = new ArrayList<>(Arrays.asList(breakfastList, lunchList, dinnerList));

    ArrayAdapter<String> myAdapter;
    ListView chooseMealListView;

    int meal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            meal = extras.getInt("meal"); //then depending on the meal, load a different list.
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //show back button in action bar

        chooseMealListView = (ListView) this.findViewById(R.id.choose_meal_listview);

        if(meal == 0) //load bfast list
        {
            //inflate the mealsList
            myAdapter = new ArrayAdapter<>(this, R.layout.content_day_view, R.id.meals_textview, breakfastList);
            chooseMealListView.setAdapter(myAdapter);
        }
        else if(meal == 1)
        {
            //inflate the mealsList
            myAdapter = new ArrayAdapter<>(this, R.layout.content_day_view, R.id.meals_textview, lunchList);
            chooseMealListView.setAdapter(myAdapter);
        }
        else if(meal == 2)
        {
            //inflate the mealsList
            myAdapter = new ArrayAdapter<>(this, R.layout.content_day_view, R.id.meals_textview, dinnerList);
            chooseMealListView.setAdapter(myAdapter);
        }
        else
        {
            Toast.makeText(this, "Could not inflate list of meals", Toast.LENGTH_SHORT).show();
        }

        //mealsList itemClickListener
        chooseMealListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int mealPosition, long id) {
                int position = 0;
                Bundle extras = getIntent().getExtras();
                if (extras != null)
                {
                    position = extras.getInt("position");
                }

                Intent mealIntent = new Intent(MealActivity.this, DayViewActivity.class);
                mealIntent.putExtra("position", position);
                mealIntent.putExtra("meal", mealListList.get(meal).get(mealPosition));
                MealActivity.this.startActivity(mealIntent);
            }
        });
    }


    //fileOut not used here. Only in DayViewActivity.
    public void fileOut(String prefName, List<String> values)
    {
//        Toast.makeText(this, "out: " + values.toString(), Toast.LENGTH_LONG).show();

        HashSet<String> hashSet = new HashSet<>(values);

        SharedPreferences sharedPreferences = getSharedPreferences(prefName, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(prefName, hashSet);
        editor.apply();
    }
}
