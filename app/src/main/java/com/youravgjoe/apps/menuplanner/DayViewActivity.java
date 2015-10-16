package com.youravgjoe.apps.menuplanner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.AddFloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class DayViewActivity extends AppCompatActivity {

    ActionBar dayActionBar;

    ListView mealsListView;

    ArrayAdapter<String> myAdapter;

    List<String> sundayMeals = new ArrayList<>();
    List<String> mondayMeals = new ArrayList<>();
    List<String> tuesdayMeals = new ArrayList<>();
    List<String> wednesdayMeals = new ArrayList<>();
    List<String> thursdayMeals = new ArrayList<>();
    List<String> fridayMeals = new ArrayList<>();
    List<String> saturdayMeals = new ArrayList<>();

    List<String> actionBarTitles = new ArrayList<>(Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"));

    List<List<String>> dayMealList = new ArrayList<>(Arrays.asList(sundayMeals, mondayMeals, tuesdayMeals, wednesdayMeals, thursdayMeals, fridayMeals, saturdayMeals));

    public String[] dayPrefNames = {"sundayMeals", "mondayMeals", "tuesdayMeals", "wednesdayMeals", "thursdayMeals", "fridayMeals", "saturdayMeals"};

    int mealToDelete;
    boolean add = true;

    FloatingActionsMenu fab_menu;
    AddFloatingActionButton fab_bfast;
    AddFloatingActionButton fab_lunch;
    AddFloatingActionButton fab_dinner;
    com.getbase.floatingactionbutton.FloatingActionButton fab_delete_meal;

    int meal; // 0 = Breakfast, 1 = Lunch, 2 = Dinner

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dayActionBar = this.getSupportActionBar();
        dayActionBar.setDisplayHomeAsUpEnabled(true); //show back button in action bar (hasn't ever produced NullPointerException)
        mealsListView = (ListView) this.findViewById(R.id.meals_listview);

        //here are all of the floating action buttons:
        fab_menu = (FloatingActionsMenu) findViewById(R.id.fab_menu);
        fab_bfast = (AddFloatingActionButton) findViewById(R.id.fab_breakfast);
        fab_lunch = (AddFloatingActionButton) findViewById(R.id.fab_lunch);
        fab_dinner = (AddFloatingActionButton) findViewById(R.id.fab_dinner);
        fab_delete_meal = (com.getbase.floatingactionbutton.FloatingActionButton) findViewById(R.id.fab_delete_meal);
        fab_delete_meal.setVisibility(View.GONE); //delete button starts out invisible.

        fab_delete_meal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //delete list item here
                dayMealList.get(position).remove(mealToDelete);
                fileOut(dayPrefNames[position], dayMealList.get(position)); //output the updated list to the day's shared pref
                fileIn(dayPrefNames[position]);
                populateMealList(position);

                fab_delete_meal.setVisibility(View.GONE);
                fab_menu.setVisibility(View.VISIBLE);

                add = true; //reset boolean
            }

        });

        fab_bfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                meal = 0;
                Intent mealIntent = new Intent(DayViewActivity.this, MealActivity.class);
                mealIntent.putExtra("position", position);
                mealIntent.putExtra("meal", meal);
                DayViewActivity.this.startActivity(mealIntent);
            }
        });

        fab_lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                meal = 1;
                Intent mealIntent = new Intent(DayViewActivity.this, MealActivity.class);
                mealIntent.putExtra("position", position);
                mealIntent.putExtra("meal", meal);
                DayViewActivity.this.startActivity(mealIntent);
            }
        });

        fab_dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                meal = 2;
                Intent mealIntent = new Intent(DayViewActivity.this, MealActivity.class);
                mealIntent.putExtra("position", position);
                mealIntent.putExtra("meal", meal);
                DayViewActivity.this.startActivity(mealIntent);
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
                    v.setBackgroundColor(getResources().getColor(R.color.redHighlight)); //highlight the long pressed list item
                    mealToDelete = mealPosition;
                    add = false; //fab is now a delete button, not an add button
                    fab_menu.setVisibility(View.GONE);
                    fab_delete_meal.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });

        // This is for when we come to this activity from MealActivity:
        String newMeal;
        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            position = extras.getInt("position");
            fileIn(dayPrefNames[position]);
            dayMealList.get(position).clear();
            dayMealList.get(position).addAll(fileIn(dayPrefNames[position]));
            populateMealList(position);

            if(extras.getString("meal") != null) //this is for when we've come back from MealActivity, not from MainActivity
            {
                dayMealList.get(position).clear();
                dayMealList.get(position).addAll(fileIn(dayPrefNames[position]));
                newMeal = extras.getString("meal");
                dayMealList.get(position).add(newMeal); //add the new meal to the day's List of meals
                fileOut(dayPrefNames[position], dayMealList.get(position)); //output the updated list to the day's shared pref
                populateMealList(position); //repopulate the day's list of meals
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
            fab_menu.setVisibility(View.VISIBLE);
            fab_delete_meal.setVisibility(View.GONE);
            populateMealList(position); //unhighlight the selected list item
            add = true; //reset add bool
        }
    }

    public List<String> fileIn(String prefName)
    {
        SharedPreferences sharedPreferences = getSharedPreferences(prefName, MODE_PRIVATE);
        HashSet<String> hashSet = (HashSet<String>) sharedPreferences.getStringSet(prefName, new HashSet<String>());
        return new ArrayList<>(hashSet);
    }

    public void fileOut(String prefName, List<String> values)
    {
        HashSet<String> hashSet = new HashSet<>(values);
        SharedPreferences sharedPreferences = getSharedPreferences(prefName, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(prefName, hashSet);
        editor.apply();
    }

    // This is where we populate and/or update the list of meals for the specified day.
    public void populateMealList(int day)
    {
        dayActionBar.setTitle(actionBarTitles.get(day));
        myAdapter = new ArrayAdapter<>(this, R.layout.content_day_view, R.id.meals_textview, dayMealList.get(day));
        mealsListView.setAdapter(myAdapter);
    }
}