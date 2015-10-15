package com.youravgjoe.apps.menuplanner;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

//Example Toast:
//Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();

//<string-array name="shopping_list">
//        <item>Bacon</item>
//        <item>Orange Juice</item>
//        <item>Yogurt</item>
//        <item>Salt</item>
//        <item>Avocado</item>
//        <item>Tortilla Chips</item>
//        <item>Salsa</item>
//        <item>Lemon Juice</item>



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    List<String> shoppingList = new ArrayList<>();
    String[] testArray = {"Bacon", "Orange Juice", "Yogurt", "Avocado", "Tortilla Chips", "Salsa", "Lemon Juice"};

    List<String> inventoryList = new ArrayList<>();
    String[] testArray2 = {"Cheese", "Milk", "Bread", "Eggs", "Flour", "Beans", "Rice", "Sugar", "Apples", "Popcorn"};

    ActionBar actionBar;

    ListView weekListView;
    ListView inventoryListView;
    ListView shoppingListView;
    FloatingActionButton addToInventory;
    boolean inventoryBool;
    boolean shoppingListBool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //doesn't work. :(

//        MenuItem weeklyMenuNav = (MenuItem) findViewById(R.id.nav_weekly_menu);
//        MenuItem inventoryNav = (MenuItem) findViewById(R.id.nav_inventory);
//        MenuItem shoppingListNav = (MenuItem) findViewById(R.id.nav_shopping_list);
//
//        Menu navMenu = (Menu) findViewById(R.id.nav);
//
//        navMenu.getItem(0).setChecked(true);

        //this will eventually be replaced with a read from file, not from the dummy array:
        for(int i = 0; i < testArray.length; i++)
        {
            shoppingList.add(i, testArray[i]);
        }

        //start these out as false, because we're not in inventory or shopping list views.
        inventoryBool = false;
        shoppingListBool = false;

        addToInventory = (FloatingActionButton) findViewById(R.id.addToInventory);
        addToInventory.setOnClickListener(new View.OnClickListener() {
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
                        .setIcon(android.R.drawable.ic_menu_add)
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
        addToInventory.setVisibility(View.GONE);

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

                String[] inventoryArray = getResources().getStringArray(R.array.inventory);

                Toast.makeText(getApplicationContext(), "You clicked on " + inventoryArray[position], Toast.LENGTH_SHORT).show();
            }
        });

        shoppingListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String[] shoppingListArray = getResources().getStringArray(R.array.shopping_list);

                Toast.makeText(getApplicationContext(), "You clicked on " + shoppingListArray[position], Toast.LENGTH_SHORT).show();

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

                //get rid of floating action button
                addToInventory.setVisibility(View.GONE);
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

            //get rid of floating action button
            addToInventory.setVisibility(View.GONE);
        } else if (id == R.id.nav_inventory) {
            inventoryBool = true;

            actionBar.setTitle(R.string.inventory);

            inventoryListView.setVisibility(View.VISIBLE);
            weekListView.setVisibility(View.GONE);
            shoppingListView.setVisibility(View.GONE);

            addToInventory.setVisibility(View.GONE);
            //get rid of floating action button
        } else if (id == R.id.nav_shopping_list) {
            shoppingListBool = true;

            actionBar.setTitle(R.string.shopping_list);

            shoppingListView.setVisibility(View.VISIBLE);
            inventoryListView.setVisibility(View.GONE);
            weekListView.setVisibility(View.GONE);

            //add floating action button
            addToInventory.setVisibility(View.VISIBLE);
        }

//        else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}