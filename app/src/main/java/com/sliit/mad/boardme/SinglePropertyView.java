package com.sliit.mad.boardme;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class SinglePropertyView extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //drawer
    DrawerLayout drawer;
    ActionBarDrawerToggle drawerToggle;
    //drawer

    DatabaseReference proprtiesReferance;
    String propertyKey;
    TextView singleTitle, singleRooms, singleBathrooms, singleAdress, singlePrice, singleTele, deleteProperty;
    ImageView singleImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_property_view);

        singleTitle = (TextView) findViewById(R.id.singlePropertyViewTitle);
        singleAdress = (TextView) findViewById(R.id.singlePropertyViewAddress);
        singlePrice = (TextView) findViewById(R.id.singlePropertyViewPrice);
        singleRooms = (TextView) findViewById(R.id.singlePropertyViewRooms);
        singleBathrooms = (TextView) findViewById(R.id.singlePropertyViewBathrooms);
        singleTele = (TextView) findViewById(R.id.singlePropertyViewPhone);
        singleImage = (ImageView) findViewById(R.id.singlePropertyImageView);
        deleteProperty = (TextView) findViewById(R.id.single_property_delete);


        proprtiesReferance = FirebaseDatabase.getInstance().getReference().child("Properties");
        propertyKey = getIntent().getExtras().getString("keyValue");
//        Toast.makeText(this, propertyKey, Toast.LENGTH_SHORT).show();
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

                singleBathrooms.setText("Bathrooms : " + propertyBathrooms);
                singlePrice.setText(propertyPrice);
                singleRooms.setText("Rooms : " + propertyRooms);
                singleTitle.setText(propertyTitle);
                singleAdress.setText(propertyAddress);
                singleTele.setText(propertyTele);
//
//                Picasso.with(SinglePropertyView.this).load(propertyImage).into(singleImage);
                Picasso.with(SinglePropertyView.this).load(propertyImage).into(singleImage);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        deleteProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(SinglePropertyView.this, "lol", Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder
                        = new AlertDialog
                        .Builder(SinglePropertyView.this);

                builder.setMessage("Confirm Delete ?");
                builder.setTitle("Alert !");
                builder.setCancelable(false);

                builder.setNegativeButton(
                        "No",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.cancel();
                            }
                        });

                builder
                        .setPositiveButton(
                                "Yes",
                                new DialogInterface
                                        .OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        Toast.makeText(SinglePropertyView.this, "Success", Toast.LENGTH_SHORT).show();

                                        proprtiesReferance.child(propertyKey).removeValue();

                                        finish();
                                    }
                                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

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


            Intent goActitviyVerify = new Intent(SinglePropertyView.this, VerifySignUp.class);
            startActivity(goActitviyVerify);
        }
        return false;
    }
    //drawer
}
