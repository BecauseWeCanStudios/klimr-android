package ru.raaas.klimr.activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
//import android.support.v7.app.ActionBarActivity;
import android.app.Fragment;
import android.content.res.Configuration;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;

import ru.raaas.klimr.R;
import ru.raaas.klimr.Utils;
import ru.raaas.klimr.fragments.Dashboard;
import ru.raaas.klimr.fragments.Group;
import ru.raaas.klimr.fragments.Teachers;

// TODO Save/load activity state
// TODO Tint DisastersActivity button red when there are known disasters

public class Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // navigation drawer title// used to store app title
    private CharSequence myTitle;
    DrawerLayout drawer;
    NavigationView navigationView;

    private String[] viewsNames;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(ru.raaas.klimr.R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.open_menu, R.string.close_menu);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(ru.raaas.klimr.R.id.content_frame, new Dashboard()).commit();
        setTitle(R.string.klimr);
    }

    @Override
    protected void onStart() {
        super.onStart();
        TextView name = (TextView) navigationView.getHeaderView(0).findViewById(R.id.drawer_name);
        TextView mail = (TextView) navigationView.getHeaderView(0).findViewById(R.id.drawer_mail);
        name.setText("Игорь Неизвестный");
        mail.setText("brovtcin.is@students.dvfu.ru");
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        boolean action = false;
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        int header = -1;
        if (id == R.id.nav_dashboard) {
            fragment = new Dashboard();
        } else if (id == R.id.nav_schedule) {
            fragment = new Group();
        } else if (id == R.id.nav_group) {
            fragment = new Group();
        } else if (id == R.id.nav_subjects) {
            fragment = new Group();
        } else if (id == R.id.nav_teachers) {
            fragment = new Teachers();
        } else if (id == R.id.nav_settings) {
            action = true;
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
        } else if (id == R.id.nav_feedback) {
            action = true;
            Intent intent = new Intent(
                    Intent.ACTION_SENDTO,
                    Uri.fromParts("mailto", getString(R.string.klimr_feedback_email), null)
            );

            try {
                startActivityForResult(intent, 1);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(
                        this,
                        R.string.klimr_feedback_no_email_app,
                        Toast.LENGTH_SHORT
                ).show();
            }
        }

        if (fragment != null) {
            android.app.FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(ru.raaas.klimr.R.id.content_frame, fragment).commit();
            if (id == R.id.nav_dashboard) {
                setTitle(R.string.klimr);
            } else {
                setTitle(item.getTitle());
            }
            drawer.closeDrawer(GravityCompat.START);
        } else if (action) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            // error in creating fragment
            Log.e("Main", "Error in creating fragment");
        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(ru.raaas.klimr.R.menu.main, menu);
        Utils.tintMenuItemIcons(
                menu,
                ContextCompat.getColor(this, R.color.klimr_topMenuTint),
                255
        );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    /**
     * Called when invalidateOptionsMenu() is triggered
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if navigation drawer is opened, hide the action items
        //boolean drawerOpen = myDrawerLayout.isDrawerOpen(myDrawerList);
        /*menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);*/
        return true;
    }

    @Override
    public void setTitle(CharSequence title) {
        myTitle = title;
        getSupportActionBar().setTitle(myTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        //myDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        //myDrawerToggle.onConfigurationChanged(newConfig);
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

}
