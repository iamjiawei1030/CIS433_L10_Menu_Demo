package com.example.cis433_l10_menu_demo;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FloatingActionButton fab;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //enable the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // drawer layout instance to toggle the menu icon to open drawer and back button to close drawer
        drawer = findViewById(R.id.drawer_layout_main_activity);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, R.string.drawer_open, R.string.drawer_cloase);

        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        //setup onitem select listener for the navigation drawer
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_main);
        navigationView.setNavigationItemSelectedListener(this);
        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //setup FAB
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(fabOnClickListener);
    }

    //method to inflating the overflow menu for the actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_appbar,menu);
        return true;
    }

    //onclick listener for the FAB
    private View.OnClickListener fabOnClickListener = new View.OnClickListener() {
        //when clicked, a popupMenu will be created and inflated
        @Override
        public void onClick(View v) {
            PopupMenu popupMenu = new PopupMenu(MainActivity.this,fab);
            popupMenu.getMenuInflater().inflate(R.menu.menu_fab,popupMenu.getMenu());
            popupMenu.show();
            //handle item selection activity for the popupMenu
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Toast.makeText(MainActivity.this,"You clicked "+item.getTitle(),Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
        }
    };

    //method to handle item selection activities on the action bar,
    // including the navigation drawer icon
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch(item.getItemId()){
            case R.id.action_settings:
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
                return true;
            case R.id.likes:
                Intent intentNavi = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intentNavi);
                return true;
            case R.id.logout:
                //do something
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    //method for OnNavigationItemSelectedListener to handle navigation drawer item selection
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                drawer.closeDrawer(GravityCompat.START);
                //do something
                return true;
            case R.id.nav_gallery:
                drawer.closeDrawer(GravityCompat.START);
                //do something
                return true;
            case R.id.nav_slideshow:
                drawer.closeDrawer(GravityCompat.START);
                Toast.makeText(MainActivity.this, "you have been logged out", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }
}