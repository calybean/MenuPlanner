package com.youravgjoe.apps.menuplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DayViewActivity extends AppCompatActivity {

    ActionBar dayActionBar;

    ListView myListView;

    ArrayAdapter<String> myAdapter;

    List<String> sundayMeals = new ArrayList<>(Arrays.asList("Meal 1", "Meal 2", "Meal 3", "Meal 4"));
    List<String> mondayMeals = new ArrayList<>(Arrays.asList("Meal 1", "Meal 2", "Meal 3", "Meal 4"));
    List<String> tuesdayMeals = new ArrayList<>(Arrays.asList("Meal 1", "Meal 2", "Meal 3", "Meal 4"));
    List<String> wednesdayMeals = new ArrayList<>(Arrays.asList("Meal 1", "Meal 2", "Meal 3", "Meal 4"));
    List<String> thursdayMeals = new ArrayList<>(Arrays.asList("Meal 1", "Meal 2", "Meal 3", "Meal 4"));
    List<String> fridayMeals = new ArrayList<>(Arrays.asList("Meal 1", "Meal 2", "Meal 3", "Meal 4"));
    List<String> saturdayMeals = new ArrayList<>(Arrays.asList("Meal 1", "Meal 2", "Meal 3", "Meal 4"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dayActionBar = this.getSupportActionBar();
        myListView = (ListView) this.findViewById(R.id.meals_listview);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

//                Toast.makeText(DayViewActivity.this, "Add a meal.", Toast.LENGTH_SHORT).show();

                Integer position = 0;
                Bundle extras = getIntent().getExtras();
                if(extras != null)
                {
                    position = extras.getInt("position");
                }

                Intent mealIntent = new Intent(DayViewActivity.this, MealActivity.class);
                mealIntent.putExtra("position", position);
                DayViewActivity.this.startActivity(mealIntent);
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Integer day = extras.getInt("position");

//            Toast.makeText(DayViewActivity.this, day.toString(), Toast.LENGTH_SHORT).show();


            updateMealList(day);
        }
    }

    public void addMeal(int position)
    {
        switch (position) {
            case 0:
                sundayMeals.add(sundayMeals.size(), "New meal");
                myAdapter = new ArrayAdapter<>(this, R.layout.content_day_view, R.id.meals_textview, sundayMeals);
                myListView.setAdapter(myAdapter);
                break;
            case 1:
                sundayMeals.add(mondayMeals.size(), "New meal");
                myAdapter = new ArrayAdapter<>(this, R.layout.content_day_view, R.id.meals_textview, mondayMeals);
                myListView.setAdapter(myAdapter);
                break;
            case 2:
                sundayMeals.add(tuesdayMeals.size(), "New meal");
                myAdapter = new ArrayAdapter<>(this, R.layout.content_day_view, R.id.meals_textview, tuesdayMeals);
                myListView.setAdapter(myAdapter);
                break;
            case 3:
                sundayMeals.add(wednesdayMeals.size(), "New meal");
                myAdapter = new ArrayAdapter<>(this, R.layout.content_day_view, R.id.meals_textview, wednesdayMeals);
                myListView.setAdapter(myAdapter);
                break;
            case 4:
                sundayMeals.add(thursdayMeals.size(), "New meal");
                myAdapter = new ArrayAdapter<>(this, R.layout.content_day_view, R.id.meals_textview, thursdayMeals);
                myListView.setAdapter(myAdapter);
                break;
            case 5:
                sundayMeals.add(fridayMeals.size(), "New meal");
                myAdapter = new ArrayAdapter<>(this, R.layout.content_day_view, R.id.meals_textview, fridayMeals);
                myListView.setAdapter(myAdapter);
                break;
            case 6:
                sundayMeals.add(saturdayMeals.size(), "New meal");
                myAdapter = new ArrayAdapter<>(this, R.layout.content_day_view, R.id.meals_textview, saturdayMeals);
                myListView.setAdapter(myAdapter);
                break;
            default:
                break;
        }
    }

    public void updateMealList(int day)
    {
        switch (day)
        {
            case 0:
                dayActionBar.setTitle(R.string.sunday);
                myAdapter = new ArrayAdapter<>(this, R.layout.content_day_view, R.id.meals_textview, sundayMeals);
                myListView.setAdapter(myAdapter);
                break;
            case 1:
                dayActionBar.setTitle(R.string.monday);
                myAdapter = new ArrayAdapter<>(this, R.layout.content_day_view, R.id.meals_textview, mondayMeals);
                myListView.setAdapter(myAdapter);
                break;
            case 2:
                dayActionBar.setTitle(R.string.tuesday);
                myAdapter = new ArrayAdapter<>(this, R.layout.content_day_view, R.id.meals_textview, tuesdayMeals);
                myListView.setAdapter(myAdapter);
                break;
            case 3:
                dayActionBar.setTitle(R.string.wednesday);
                myAdapter = new ArrayAdapter<>(this, R.layout.content_day_view, R.id.meals_textview, wednesdayMeals);
                myListView.setAdapter(myAdapter);
                break;
            case 4:
                dayActionBar.setTitle(R.string.thursday);
                myAdapter = new ArrayAdapter<>(this, R.layout.content_day_view, R.id.meals_textview, thursdayMeals);
                myListView.setAdapter(myAdapter);
                break;
            case 5:
                dayActionBar.setTitle(R.string.friday);
                myAdapter = new ArrayAdapter<>(this, R.layout.content_day_view, R.id.meals_textview, fridayMeals);
                myListView.setAdapter(myAdapter);
                break;
            case 6:
                dayActionBar.setTitle(R.string.saturday);
                myAdapter = new ArrayAdapter<>(this, R.layout.content_day_view, R.id.meals_textview, saturdayMeals);
                myListView.setAdapter(myAdapter);
                break;
            default:
                break;
        }
    }
}