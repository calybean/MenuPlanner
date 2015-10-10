package com.youravgjoe.apps.menuplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView weekListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        weekListView = (ListView) this.findViewById(R.id.week_listview);

        Ingredient beefyFeet = new Ingredient(this.getApplicationContext(), "Beef", 56.75, 14.0);
        beefyFeet.SaveToFile(beefyFeet.name);
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//
//                Toast.makeText(MainActivity.this, "You clicked the floating action button.", Toast.LENGTH_SHORT).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        weekListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent dayIntent;
                switch(position)
                {
                    case 0:
//                        Toast.makeText(MainActivity.this, "Sunday", Toast.LENGTH_SHORT).show();
                        dayIntent = new Intent(MainActivity.this, DayViewActivity.class);
                        dayIntent.putExtra("position", position);
                        MainActivity.this.startActivity(dayIntent);
                        break;
                    case 1:
//                        Toast.makeText(MainActivity.this, "Monday", Toast.LENGTH_SHORT).show();
                        dayIntent = new Intent(MainActivity.this, DayViewActivity.class);
                        dayIntent.putExtra("position", position);
                        MainActivity.this.startActivity(dayIntent);
                        break;
                    case 2:
//                        Toast.makeText(MainActivity.this, "Tuesday", Toast.LENGTH_SHORT).show();
                        dayIntent = new Intent(MainActivity.this, DayViewActivity.class);
                        dayIntent.putExtra("position", position);
                        MainActivity.this.startActivity(dayIntent);
                        break;
                    case 3:
//                        Toast.makeText(MainActivity.this, "Wednesday", Toast.LENGTH_SHORT).show();
                        dayIntent = new Intent(MainActivity.this, DayViewActivity.class);
                        dayIntent.putExtra("position", position);
                        MainActivity.this.startActivity(dayIntent);
                        break;
                    case 4:
//                        Toast.makeText(MainActivity.this, "Thursday", Toast.LENGTH_SHORT).show();
                        dayIntent = new Intent(MainActivity.this, DayViewActivity.class);
                        dayIntent.putExtra("position", position);
                        MainActivity.this.startActivity(dayIntent);
                        break;
                    case 5:
//                        Toast.makeText(MainActivity.this, "Friday", Toast.LENGTH_SHORT).show();
                        dayIntent = new Intent(MainActivity.this, DayViewActivity.class);
                        dayIntent.putExtra("position", position);
                        MainActivity.this.startActivity(dayIntent);
                        break;
                    case 6:
//                        Toast.makeText(MainActivity.this, "Saturday", Toast.LENGTH_SHORT).show();
                        dayIntent = new Intent(MainActivity.this, DayViewActivity.class);
                        dayIntent.putExtra("position", position);
                        MainActivity.this.startActivity(dayIntent);
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "Default case", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
            // Handle the camera action
        } else if (id == R.id.nav_inventory) {

        } else if (id == R.id.nav_shopping_list) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    public void floatingButtonClick(View view)
//    {
//        Toast.makeText(this, "You clicked the floating action button.", Toast.LENGTH_SHORT).show();
//
//    }
}
