package com.pub.projects.activities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.pub.projects.R;
import com.pub.projects.fragments.DetailFragment;
import com.pub.projects.fragments.HomeFragment;
import com.pub.projects.fragments.TaskFragment;
import com.pub.projects.model.NavItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    Toolbar toolbar;
    private static long back_pressed;
    DrawerLayout Drawer;
    ActionBarDrawerToggle mDrawerToggle;

    SharedPreferences pref;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/webfont.ttf");

        TextView title = (TextView) findViewById(R.id.toolbar_title);

        title.setText(getResources().getString(R.string.app_name));
        title.setTypeface(face);

        displayView(0);
        loadDraws();
    }

    public void loadDraws() {

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);


        Drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, Drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

            }

        };

        Drawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    public void loadDrawsDisplay() {
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerToggle.syncState();
    }

    public void loadDrawsHide() {
        mDrawerToggle.setDrawerIndicatorEnabled(false);
        mDrawerToggle.syncState();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (id == R.id.nav_projects) {
            displayView(0);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.main_profile, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        NavItem nav = new NavItem();
        // Handle action bar actions click
        switch (item.getItemId()) {
            case android.R.id.home:
                String navi = nav.getPage();
                switch (navi) {
                    case "Home":
                        displayView(0);
                        return true;
                }
                return true;
            case R.id.action_projects:

                return true;
            case R.id.action_logout:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    /**
     * Displaying fragment view for selected nav drawer list item
     */
    public void displayView(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        String Tag = "";
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                Tag = "HomeFragment";
                break;
            case 1:
                fragment = new TaskFragment();
                Tag = "TaskFragment";
                break;
            case 2:
                fragment = new DetailFragment();
                Tag = "DetailFragment";
                break;

            default:
                break;
        }

        if (fragment != null) {

            try {
                InputMethodManager input = (InputMethodManager) this
                        .getSystemService(Activity.INPUT_METHOD_SERVICE);
                input.hideSoftInputFromWindow(new View(this).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            } catch (Exception e) {
                e.printStackTrace();
            }
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment, Tag).commit();
        } else {
            Log.e("HomeActivity", "Error in creating fragment");
        }
    }


    @Override
    public void onBackPressed() {
        NavItem nav = new NavItem();
        String navi = nav.getPage();
        switch (navi) {
            case "HomePage":
                if (back_pressed + 2000 > System.currentTimeMillis()) {
                    super.onBackPressed();
                } else {
                    Toast toast = Toast.makeText(getBaseContext(), "Press back once again to exit application!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    back_pressed = System.currentTimeMillis();
                }
                return;
            case "Home":
                displayView(0);
                return;
            case "Task":
                displayView(1);
                return;

        }

    }

}
