package com.sliit.mad.boardme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class StuParentHome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //drawer
    DrawerLayout drawer;
    ActionBarDrawerToggle drawerToggle;
    //drawer

    RecyclerView boardingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_parent_home);

        //drawer
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        drawerToggle = new ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close);
        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navVieww);
        navigationView.setNavigationItemSelectedListener(this);

        //drawer

        boardingList = (RecyclerView) findViewById(R.id.stu_par_bookigRecycle);
        boardingList.setHasFixedSize(true);
        boardingList.setLayoutManager(new LinearLayoutManager(this));
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


            Intent navMenu = new Intent(StuParentHome.this, OwnerProfile.class);
            startActivity(navMenu);

        } else if (id == R.id.nav_enter_bookigs) {


//            Intent navMenu2 = new Intent(HomeForAll.this, StuParentPropertySingleView.class);
//            startActivity(navMenu2);
        }

        return false;
    }
    //drawer

}
