package com.sliit.mad.boardme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class BookNow extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //drawer
    DrawerLayout drawer;
    ActionBarDrawerToggle drawerToggle;
    //drawer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_now);



        //drawer
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        drawerToggle = new ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close);
        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navVieww);
        navigationView.setNavigationItemSelectedListener(this);

        //drawer

    }


    //drawer
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();

        if (id == R.id.nav_enter_profile) {


//            Intent navMenu = new Intent(HomeForAll.this, SinglePropertyView.class);
//            startActivity(navMenu);

        } else if (id == R.id.nav_enter_bookigs) {


//            Intent navMenu2 = new Intent(HomeForAll.this, StuParentPropertySingleView.class);
//            startActivity(navMenu2);
        }

        return false;
    }
    //drawer
}
