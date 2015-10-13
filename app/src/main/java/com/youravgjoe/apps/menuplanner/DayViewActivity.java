package com.youravgjoe.apps.menuplanner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class DayViewActivity extends AppCompatActivity {

    ActionBar dayActionBar;

    ListView myListView;

    ArrayAdapter<String> myAdapter;

    List<String> sundayMeals = new ArrayList<>(Arrays.asList("Sunday", "Meal 1", "Meal 2", "Meal 3", "Meal 4"));
    List<String> mondayMeals = new ArrayList<>(Arrays.asList("Monday", "Meal 1", "Meal 2", "Meal 3", "Meal 4"));
    List<String> tuesdayMeals = new ArrayList<>(Arrays.asList("Tuesday", "Meal 1", "Meal 2", "Meal 3", "Meal 4"));
    List<String> wednesdayMeals = new ArrayList<>(Arrays.asList("Wednesday", "Meal 1", "Meal 2", "Meal 3", "Meal 4"));
    List<String> thursdayMeals = new ArrayList<>(Arrays.asList("Thursday", "Meal 1", "Meal 2", "Meal 3", "Meal 4"));
    List<String> fridayMeals = new ArrayList<>(Arrays.asList("Friday", "Meal 1", "Meal 2", "Meal 3", "Meal 4"));
    List<String> saturdayMeals = new ArrayList<>(Arrays.asList("Saturday", "Meal 1", "Meal 2", "Meal 3", "Meal 4"));

    List<List<String>> dayMealList = new ArrayList<>(Arrays.asList(sundayMeals, mondayMeals, tuesdayMeals, wednesdayMeals, thursdayMeals, fridayMeals, saturdayMeals));

    public String[] dayFileNames = {"sunday.txt", "monday.txt", "tuesday.txt", "wednesday.txt", "thursday.txt", "friday.txt", "saturday.txt"};
    final String meal = "meal";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //show back button in action bar

        dayActionBar = this.getSupportActionBar();
        myListView = (ListView) this.findViewById(R.id.meals_listview);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position = 0;
                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    position = extras.getInt("position");
                }

                Intent mealIntent = new Intent(DayViewActivity.this, MealActivity.class);
                mealIntent.putExtra("position", position);
                DayViewActivity.this.startActivity(mealIntent);
            }
        });

        // This is for when we come back to this activity from MealActivity:
        int position = 0;
        String newMeal;
        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            position = extras.getInt("position");
            if(extras.getString("meal") != null)
            {
                newMeal = extras.getString("meal");
                dayMealList.get(position).add(newMeal); //add the new meal to the day's List of meals (this will probably be done with file input, not with an intent.
            }
        }


//        newMeal = fileIn(position, dayFileNames[position]);

        List<String> tempList = fileIn("mealsList"); //input the list of meals from the specified day file

        Toast.makeText(this, "in: " + tempList.toString(), Toast.LENGTH_LONG).show();

//        dayMealList.get(position).clear(); //clear the array, then fill it with the array read in from the file?
        dayMealList.get(position).addAll(tempList);

        populateMealList(position);

//        dayMealList.get(position).add(dayMealList.size(), newMeal); //add the meal to the day's list of meals
    }

    @Override
    public void onBackPressed() {
        NavUtils.navigateUpFromSameTask(this); //this brings us back to the week view when back is pressed, instead of back to MealActivity
        super.onBackPressed();
    }

    // Input from file using SharedPrefs

    public List<String> fileIn(String prefName)
    {
        SharedPreferences sharedPreferences = getSharedPreferences(prefName, MODE_PRIVATE);
        HashSet<String> hashSet = (HashSet<String>) sharedPreferences.getStringSet(prefName, new HashSet<String>());

        return new ArrayList<>(hashSet);
    }

    // This is where we populate AND update the list of meals for the specified day.
    public void populateMealList(int day)
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