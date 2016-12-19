package com.youravgjoe.apps.menuplanner;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import com.getbase.floatingactionbutton.AddFloatingActionButton;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ArrayAdapter<String> myAdapter;

    List<String> shoppingList = new ArrayList<>();
    String[] testShoppingArray = {"Bacon", "Orange Juice", "Yogurt", "Avocado", "Tortilla Chips", "Salsa", "Lemon Juice"};
    final String shoppingListPref = "shoppingList";

    List<String> inventoryList = new ArrayList<>();
    String[] testInventoryArray = {"Cheese", "Milk", "Bread", "Eggs", "Flour", "Beans", "Rice", "Sugar", "Apples", "Popcorn"};
    final String   inventoryPref = "inventory";

    ActionBar actionBar;

    ListView weekListView;
    ListView inventoryListView;
    ListView shoppingListView;
    AddFloatingActionButton addToInventoryFab;
    boolean inventoryBool;
    boolean shoppingListBool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DataManager.Init(this);

        //this will eventually be replaced with a read from file, not from the dummy array:
        for(int i = 0; i < testShoppingArray.length; i++)
        {
            shoppingList.add(i, testShoppingArray[i]);
        }

        for(int i = 0; i < testInventoryArray.length; i++)
        {
            inventoryList.add(i, testInventoryArray[i]);
        }

        //start these out as false, because we're not in inventory or shopping list views.
        inventoryBool = false;
        shoppingListBool = false;

        addToInventoryFab = (AddFloatingActionButton) findViewById(R.id.addToInventory);
        addToInventoryFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //show an alert dialog (Do you really want to add everything from list to inventory?)
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Add all?")
                        .setMessage("Are you sure you'd like to add everything from your shopping list to your inventory?")
                        .setPositiveButton(R.string.yes_add, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //this is where we add all to inventory
                            }
                        })
                        .setNegativeButton(R.string.no_add, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(R.drawable.ic_add_white_24dp)
                        .show();
            }
        });

        actionBar = this.getSupportActionBar();
        actionBar.setTitle(R.string.menu); //Ignore warning. This doesn't produce java.lang.NullPointerException (yet?)

        weekListView = (ListView) this.findViewById(R.id.week_listview);
        inventoryListView = (ListView) this.findViewById(R.id.inventory_listview);
        shoppingListView = (ListView) this.findViewById(R.id.shopping_list);

        inventoryListView.setVisibility(View.GONE);
        shoppingListView.setVisibility(View.GONE);

        //start with addToInventory fab not showing
        addToInventoryFab.setVisibility(View.GONE);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        weekListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //code to start DayViewActivity:
                Intent dayIntent;
                dayIntent = new Intent(MainActivity.this, DayViewActivity.class);
                dayIntent.putExtra("position", position);
                MainActivity.this.startActivity(dayIntent);
            }
        });

        inventoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(), "You clicked on " + testInventoryArray[position], Toast.LENGTH_SHORT).show();
            }
        });

        shoppingListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(), "You clicked on " + testShoppingArray[position], Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(inventoryBool || shoppingListBool)
            {
                //run all the code we run when weekly list is selected from pull out menu
                inventoryBool = false;
                shoppingListBool = false;

                // Handle the weekly menu action
                actionBar.setTitle(R.string.menu);

                //make this list visible, and all others gone
                weekListView.setVisibility(View.VISIBLE);
                inventoryListView.setVisibility(View.GONE);
                shoppingListView.setVisibility(View.GONE);

                //hide of floating action button
                addToInventoryFab.setVisibility(View.GONE);
            }
            else
            {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this, "No settings yet", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_weekly_menu) {
            inventoryBool = false;
            shoppingListBool = false;

            // Handle the weekly menu action
            actionBar.setTitle(R.string.menu);

            //make this list visible, and all others gone
            weekListView.setVisibility(View.VISIBLE);
            inventoryListView.setVisibility(View.GONE);
            shoppingListView.setVisibility(View.GONE);

            //hide of floating action button
            addToInventoryFab.setVisibility(View.GONE);
        } else if (id == R.id.nav_inventory) {
            inventoryBool = true;

            actionBar.setTitle(R.string.inventory);

            inventoryListView.setVisibility(View.VISIBLE);
            weekListView.setVisibility(View.GONE);
            shoppingListView.setVisibility(View.GONE);

            addToInventoryFab.setVisibility(View.GONE);

            myAdapter = new ArrayAdapter<>(this, R.layout.content_day_view, R.id.meals_textview, testInventoryArray);
            inventoryListView.setAdapter(myAdapter);
        } else if (id == R.id.nav_shopping_list) {
            shoppingListBool = true;

            actionBar.setTitle(R.string.shopping_list);

            shoppingListView.setVisibility(View.VISIBLE);
            inventoryListView.setVisibility(View.GONE);
            weekListView.setVisibility(View.GONE);

            //show floating action button
            addToInventoryFab.setVisibility(View.VISIBLE);

            myAdapter = new ArrayAdapter<>(this, R.layout.content_day_view, R.id.meals_textview, testShoppingArray);
            shoppingListView.setAdapter(myAdapter);

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}