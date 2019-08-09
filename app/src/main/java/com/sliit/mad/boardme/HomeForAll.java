package com.sliit.mad.boardme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class HomeForAll extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //drawer
    DrawerLayout drawer;
    ActionBarDrawerToggle drawerToggle;
    //drawer

    RecyclerView boardingList;

    DatabaseReference proprtiesReferance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_for_all);

        //drawer
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        drawerToggle = new ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close);
        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navVieww);
        navigationView.setNavigationItemSelectedListener(this);

        //drawer


        proprtiesReferance = FirebaseDatabase.getInstance().getReference().child("Properties");
        proprtiesReferance.keepSynced(true);

        boardingList = (RecyclerView) findViewById(R.id.rvHomeList);
        boardingList.setHasFixedSize(true);
        boardingList.setLayoutManager(new LinearLayoutManager(this));


    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Properties, PropertyHolder1> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Properties, PropertyHolder1>
                (Properties.class, R.layout.home_row, PropertyHolder1.class, proprtiesReferance) {

            String property_key;

            @Override
            protected void populateViewHolder(PropertyHolder1 propertyHolder1, Properties properties, int i) {

                property_key = getRef(i).getKey();

                propertyHolder1.setTitle(properties.getTitle());
                propertyHolder1.setBathrooms(properties.getBathrooms());
                propertyHolder1.setPrice(properties.getPrice());
                propertyHolder1.setRooms(properties.getRooms());
                propertyHolder1.setImages(getApplicationContext(), properties.getImages());

                propertyHolder1.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                       Toast.makeText(HomeForAll.this, property_key, Toast.LENGTH_SHORT).show();

                        Intent sigleProperty = new Intent(HomeForAll.this, SinglePropertyView.class);
                        sigleProperty.putExtra("keyValue", property_key);
                        startActivity(sigleProperty);
                    }
                });

            }
        };

        boardingList.setAdapter(firebaseRecyclerAdapter);


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


            Intent navMenu = new Intent(HomeForAll.this, OwnerProfile.class);
            startActivity(navMenu);

        } else if (id == R.id.nav_enter_bookigs) {


//            Intent navMenu2 = new Intent(HomeForAll.this, StuParentPropertySingleView.class);
//            startActivity(navMenu2);
        } else if (id == R.id.nav_enter_aboutus) {


            Intent navMenu = new Intent(HomeForAll.this, AboutUs.class);
            startActivity(navMenu);
        }

        return false;
    }
    //drawer

}

class PropertyHolder1 extends RecyclerView.ViewHolder {

    View mView;

    public PropertyHolder1(View v) {
        super(v);
        mView = v;
    }

    public void setTitle(String t) {
        TextView homeCardTitle = (TextView) mView.findViewById(R.id.homeCardTitle);
        homeCardTitle.setText(t);
    }

    public void setRooms(String t) {
        TextView homeCardRooms = (TextView) mView.findViewById(R.id.homeCardRomms2);
        homeCardRooms.setText("Rooms : " + t);
    }

    public void setBathrooms(String t) {
        TextView homeCardBathrooms = (TextView) mView.findViewById(R.id.homeCardBathrooms);
        homeCardBathrooms.setText("Bathrooms : " + t);
    }

    public void setPrice(String t) {
        TextView homeCardPrice = (TextView) mView.findViewById(R.id.homeCardPriceView);
        homeCardPrice.setText("Price : " + t + "/monthly");
    }

    public void setImages(Context ctx, String t) {
        ImageView homeCardImage1 = (ImageView) mView.findViewById(R.id.homeCardImage);
//        Picasso.with(ctx).load(t).into(homeCardImage1);
        Picasso.with(ctx)
                .load(t)
                .into(homeCardImage1);
    }


}
