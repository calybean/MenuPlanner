package com.youravgjoe.apps.menuplanner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class DayViewActivity extends AppCompatActivity {

    ActionBar dayActionBar;

    ListView mealsListView;

    ArrayAdapter<String> myAdapter;

    //Lists for testing:

//    List<String> sundayMeals = new ArrayList<>(Arrays.asList("Meal 1", "Meal 2", "Meal 3", "Meal 4"));
//    List<String> mondayMeals = new ArrayList<>(Arrays.asList("Meal 1", "Meal 2", "Meal 3", "Meal 4"));
//    List<String> tuesdayMeals = new ArrayList<>(Arrays.asList("Meal 1", "Meal 2", "Meal 3", "Meal 4"));
//    List<String> wednesdayMeals = new ArrayList<>(Arrays.asList("Meal 1", "Meal 2", "Meal 3", "Meal 4"));
//    List<String> thursdayMeals = new ArrayList<>(Arrays.asList("Meal 1", "Meal 2", "Meal 3", "Meal 4"));
//    List<String> fridayMeals = new ArrayList<>(Arrays.asList("Meal 1", "Meal 2", "Meal 3", "Meal 4"));
//    List<String> saturdayMeals = new ArrayList<>(Arrays.asList("Meal 1", "Meal 2", "Meal 3", "Meal 4"));

    List<String> sundayMeals = new ArrayList<>();
    List<String> mondayMeals = new ArrayList<>();
    List<String> tuesdayMeals = new ArrayList<>();
    List<String> wednesdayMeals = new ArrayList<>();
    List<String> thursdayMeals = new ArrayList<>();
    List<String> fridayMeals = new ArrayList<>();
    List<String> saturdayMeals = new ArrayList<>();

    //find where I'm outputting to a file, and make sure I'm saving the new meal in the file, and when I add ANOTHER new meal, make sure I'm saving both.

    List<List<String>> dayMealList = new ArrayList<>(Arrays.asList(sundayMeals, mondayMeals, tuesdayMeals, wednesdayMeals, thursdayMeals, fridayMeals, saturdayMeals));

    public String[] dayPrefNames = {"sundayMeals", "mondayMeals", "tuesdayMeals", "wednesdayMeals", "thursdayMeals", "fridayMeals", "saturdayMeals"};
//    final String meal = "meal";

    int mealToDelete;
    boolean add = true;

    FloatingActionButton fab;

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //show back button in action bar

        dayActionBar = this.getSupportActionBar();
        mealsListView = (ListView) this.findViewById(R.id.meals_listview);

        //FAB onClick

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                position = 0;
                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    position = extras.getInt("position");
                }

                if(add)
                {
                    Intent mealIntent = new Intent(DayViewActivity.this, MealActivity.class);
                    mealIntent.putExtra("position", position);
                    DayViewActivity.this.startActivity(mealIntent);
                }
                else
                {
                    //delete list item here
                    dayMealList.get(position).remove(mealToDelete);
                    fileOut(dayPrefNames[position], dayMealList.get(position)); //output the updated list to the day's shared pref
                    fileIn(dayPrefNames[position]);
                    populateMealList(position);

                    fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
                    fab.setImageResource(R.drawable.ic_add);

                    add = true; //reset boolean
                }
            }
        });

        //ListView Item onClick

        mealsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int mealPosition, long id) {
                if(add)
                {
                    Bundle extras = getIntent().getExtras();
                    if (extras != null) {
                        position = extras.getInt("position");
                    }
                    Toast.makeText(getApplicationContext(), "You clicked on " + dayMealList.get(position).get(mealPosition), Toast.LENGTH_SHORT).show();
                }
            }
        });

        //ListView Item onLongClick

        mealsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> av, View v, int mealPosition, long id) {
                if(add)
                {
                    v.setBackgroundColor(getResources().getColor(R.color.redHighlight));

                    mealToDelete = mealPosition;

                    //make an if else in the back press to check for add bool and reset the button and the onclick onBackPress

                    add = false; //fab is now a delete button, not an add button

                    fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorRed)));
                    fab.setImageResource(R.drawable.ic_delete);
                }
                return true;
            }
        });

        // This is for when we come back to this activity from MealActivity:
        String newMeal;
        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            position = extras.getInt("position");
            fileIn(dayPrefNames[position]);
            dayMealList.get(position).clear();
            dayMealList.get(position).addAll(fileIn(dayPrefNames[position]));
            populateMealList(position);

            if(extras.getString("meal") != null) //this is the code we run when we've come back from MealActivity, not from MainActivity
            {
                dayMealList.get(position).clear();
                dayMealList.get(position).addAll(fileIn(dayPrefNames[position]));
                newMeal = extras.getString("meal");
                dayMealList.get(position).add(newMeal); //add the new meal to the day's List of meals (this will probably be done with file input, not with an intent.
                fileOut(dayPrefNames[position], dayMealList.get(position)); //output the updated list to the day's shared pref
                populateMealList(position);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if(add) //if we haven't long pressed anything, and the add button is showing...
        {
            NavUtils.navigateUpFromSameTask(this); //this brings us back to the week view when back is pressed, instead of back to MealActivity
            super.onBackPressed();
        }
        else
        {
            //reset the fab color and image
            fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
            fab.setImageResource(R.drawable.ic_add);
            //unhighlight the selected list item
            populateMealList(position);
            //reset add bool
            add = true;
        }
    }

    // Input from file using SharedPrefs

    public List<String> fileIn(String prefName)
    {
        SharedPreferences sharedPreferences = getSharedPreferences(prefName, MODE_PRIVATE);
        HashSet<String> hashSet = (HashSet<String>) sharedPreferences.getStringSet(prefName, new HashSet<String>());

        return new ArrayList<>(hashSet);
    }

    public void fileOut(String prefName, List<String> values) //the List that we pass in should already be updated with the new meal inside it.
    {
//        Toast.makeText(this, "out: " + values.toString(), Toast.LENGTH_LONG).show();

        HashSet<String> hashSet = new HashSet<>(values);

        SharedPreferences sharedPreferences = getSharedPreferences(prefName, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(prefName, hashSet);
        editor.apply();
    }

    // This is where we populate AND update the list of meals for the specified day.
    public void populateMealList(int day)
    {
        switch (day)
        {
            case 0:
                dayActionBar.setTitle(R.string.sunday);
                myAdapter = new ArrayAdapter<>(this, R.layout.content_day_view, R.id.meals_textview, sundayMeals);
                mealsListView.setAdapter(myAdapter);
                break;
            case 1:
                dayActionBar.setTitle(R.string.monday);
                myAdapter = new ArrayAdapter<>(this, R.layout.content_day_view, R.id.meals_textview, mondayMeals);
                mealsListView.setAdapter(myAdapter);
                break;
            case 2:
                dayActionBar.setTitle(R.string.tuesday);
                myAdapter = new ArrayAdapter<>(this, R.layout.content_day_view, R.id.meals_textview, tuesdayMeals);
                mealsListView.setAdapter(myAdapter);
                break;
            case 3:
                dayActionBar.setTitle(R.string.wednesday);
                myAdapter = new ArrayAdapter<>(this, R.layout.content_day_view, R.id.meals_textview, wednesdayMeals);
                mealsListView.setAdapter(myAdapter);
                break;
            case 4:
                dayActionBar.setTitle(R.string.thursday);
                myAdapter = new ArrayAdapter<>(this, R.layout.content_day_view, R.id.meals_textview, thursdayMeals);
                mealsListView.setAdapter(myAdapter);
                break;
            case 5:
                dayActionBar.setTitle(R.string.friday);
                myAdapter = new ArrayAdapter<>(this, R.layout.content_day_view, R.id.meals_textview, fridayMeals);
                mealsListView.setAdapter(myAdapter);
                break;
            case 6:
                dayActionBar.setTitle(R.string.saturday);
                myAdapter = new ArrayAdapter<>(this, R.layout.content_day_view, R.id.meals_textview, saturdayMeals);
                mealsListView.setAdapter(myAdapter);
                break;
            default:
                break;
        }
    }
}

/*
*
*         List<String> tempList = fileIn(dayFileNames[position]); //input the list of meals from the day's shared pref

//        Toast.makeText(this, "in: " + tempList.toString(), Toast.LENGTH_LONG).show();

        if(!newMeal.equals("")) //ignore warning
        {
            dayMealList.get(position).clear(); //clear the array only if we're going to read in the old data and a new meal from shared pref
        }
        dayMealList.get(position).addAll(tempList);

        populateMealList(position);

//        dayMealList.get(position).add(newMeal); //add the meal to the day's list of meals
*
* */