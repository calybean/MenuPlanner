package com.youravgjoe.apps.menuplanner;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MealActivity extends AppCompatActivity {

    List<String> mealsList = new ArrayList<>(Arrays.asList("Meal 1", "Meal 2", "Meal 3", "Meal 4", "Meal 5", "Meal 6", "Meal 7", "Meal 8", "Meal 9", "Meal 10", "Meal 11", "Meal 12", "Meal 13", "Meal 14", "Meal 15"));

    ArrayAdapter<String> myAdapter;
    ListView myListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myListView = (ListView) this.findViewById(R.id.choose_meal_listview);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        Integer position = 0;
        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            position = extras.getInt("position");
        }

        switch (position)
        {
            case 0:
                myAdapter = new ArrayAdapter<>(this, R.layout.content_day_view, R.id.meals_textview, mealsList);
                myListView.setAdapter(myAdapter);
                //add meal to Sunday
                break;
            case 1:
                myAdapter = new ArrayAdapter<>(this, R.layout.content_day_view, R.id.meals_textview, mealsList);
                myListView.setAdapter(myAdapter);
                break;
            case 2:
                myAdapter = new ArrayAdapter<>(this, R.layout.content_day_view, R.id.meals_textview, mealsList);
                myListView.setAdapter(myAdapter);
                break;
            case 3:
                myAdapter = new ArrayAdapter<>(this, R.layout.content_day_view, R.id.meals_textview, mealsList);
                myListView.setAdapter(myAdapter);
                break;
            case 4:
                myAdapter = new ArrayAdapter<>(this, R.layout.content_day_view, R.id.meals_textview, mealsList);
                myListView.setAdapter(myAdapter);
                break;
            case 5:
                myAdapter = new ArrayAdapter<>(this, R.layout.content_day_view, R.id.meals_textview, mealsList);
                myListView.setAdapter(myAdapter);
                break;
            case 6:
                myAdapter = new ArrayAdapter<>(this, R.layout.content_day_view, R.id.meals_textview, mealsList);
                myListView.setAdapter(myAdapter);
                break;
            default:
                break;
        }
    }

}
