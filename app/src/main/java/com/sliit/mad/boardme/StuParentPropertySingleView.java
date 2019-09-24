package com.sliit.mad.boardme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class StuParentPropertySingleView extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    //drawer
    DrawerLayout drawer;
    ActionBarDrawerToggle drawerToggle;
    //drawer

    DatabaseReference proprtiesReferance;
    String propertyKey;
    TextView studentSingleTitle, studentSingleRooms, studentSingleBathrooms, studentSingleAdress, studentSinglePrice, studentSingleTele;
    ImageView studentSingleImage;
    Button studentSingleBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_parent_property_single_view);


        //drawer
        drawer = (DrawerLayout)findViewById(R.id.drawer);
        drawerToggle = new ActionBarDrawerToggle(this,drawer,R.string.open,R.string.close);
        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = (NavigationView)findViewById(R.id.navVieww);
        navigationView.setNavigationItemSelectedListener(this);

        //drawer

        studentSingleTitle = (TextView) findViewById(R.id.studentSinglePropertyViewTitle);
        studentSingleAdress = (TextView) findViewById(R.id.studentSinglePropertyViewAddress);
        studentSinglePrice = (TextView) findViewById(R.id.studentSinglePropertyViewPrice);
        studentSingleRooms = (TextView) findViewById(R.id.studentSinglePropertyViewRooms);
        studentSingleBathrooms = (TextView) findViewById(R.id.studentSinglePropertyViewBathrooms);
        studentSingleTele = (TextView) findViewById(R.id.studentSinglePropertyViewPhone);
        studentSingleImage = (ImageView) findViewById(R.id.studentSinglePropertyImageView);
        studentSingleBooking = (Button) findViewById(R.id.studentStuParentbtBookNow);

        proprtiesReferance = FirebaseDatabase.getInstance().getReference().child("Properties");
        propertyKey = getIntent().getExtras().getString("keyValue");

        proprtiesReferance.child(propertyKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String propertyTitle = (String) dataSnapshot.child("Title").getValue();
                String propertyRooms = (String) dataSnapshot.child("Rooms").getValue();
                String propertyBathrooms = (String) dataSnapshot.child("Bathrooms").getValue();
                String propertyAddress = (String) dataSnapshot.child("Address").getValue();
                String propertyPrice = (String) dataSnapshot.child("Price").getValue();
                String propertyTele = (String) dataSnapshot.child("Telephone").getValue();
                String propertyImage = (String) dataSnapshot.child("Images").getValue();

                studentSingleBathrooms.setText("Bathrooms : " + propertyBathrooms);
                studentSinglePrice.setText(propertyPrice);
                studentSingleRooms.setText("Rooms : " + propertyRooms);
                studentSingleTitle.setText(propertyTitle);
                studentSingleAdress.setText(propertyAddress);
                studentSingleTele.setText(propertyTele);
//
//                Picasso.with(SinglePropertyView.this).load(propertyImage).into(singleImage);
                Picasso.with(StuParentPropertySingleView.this).load(propertyImage).into(studentSingleImage);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        studentSingleBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goBookNow= new Intent(StuParentPropertySingleView.this, BookNow.class);
                goBookNow.putExtra("propertyID",propertyKey);
                startActivity(goBookNow);
            }
        });

    }

    //drawer
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();

        if(id == R.id.nav_enter_profile){


            Intent goActitviyVerify = new Intent(StuParentPropertySingleView.this, SinglePropertyView.class);
            startActivity(goActitviyVerify);
        }
        return false;
    }
    //drawer

}
