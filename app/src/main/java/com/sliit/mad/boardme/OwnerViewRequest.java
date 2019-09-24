package com.sliit.mad.boardme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class OwnerViewRequest extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    //drawer
    DrawerLayout drawer;
    ActionBarDrawerToggle drawerToggle;
    //drawer

    String id,bookingID,des,status,advance,proprtyID,user;
    Long time;
    TextView description,ownerViewReqAdvance;
    DatabaseReference ownerReferance;
    Button accept,delete;
    FirebaseAuth firebaseAuthe;
    FirebaseUser firebaseAutheUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_view_request);

        description  = (TextView) findViewById(R.id.ownerRequestDescription);
        ownerViewReqAdvance =  (TextView) findViewById(R.id.ownerViewReqAdvance);
        accept = (Button) findViewById(R.id.btnAcceptRequest);
        delete = (Button) findViewById(R.id.btnDeleteRequest);


        bookingID = "-LpWNCzLhldsa1GhDWkV";

        firebaseAuthe = FirebaseAuth.getInstance();
        firebaseAutheUser = firebaseAuthe.getCurrentUser();
        ownerReferance = FirebaseDatabase.getInstance().getReference("Bookings").child(firebaseAutheUser.getUid()).child(bookingID);





        ownerReferance.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                des =  (String) dataSnapshot.child("description").getValue();
                status = (String) dataSnapshot.child("status").getValue();
                advance = (String) dataSnapshot.child("advance").getValue();
                proprtyID = (String) dataSnapshot.child("propertyID").getValue();
                time =(long) dataSnapshot.child("time").getValue();
                user = (String) dataSnapshot.child("userID").getValue();

                ownerViewReqAdvance.setText("Advance :- " + advance);
                description.setText("Description :- " +des);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                status = "Accepted";
                Map bookingMapping = new HashMap();
                bookingMapping.put("advance", advance);
                bookingMapping.put("propertyID", proprtyID);
                bookingMapping.put("description", des);
                bookingMapping.put("status", status);
                bookingMapping.put("user", user);
                bookingMapping.put("time", time);

                ownerReferance.setValue(bookingMapping).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(OwnerViewRequest.this, "Accepted Successfully", Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(OwnerViewRequest.this,  task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                status = "Rejected";
                Map bookingMapping = new HashMap();
                bookingMapping.put("advance", advance);
                bookingMapping.put("propertyID", proprtyID);
                bookingMapping.put("description", des);
                bookingMapping.put("status", status);
                bookingMapping.put("user", user);
                bookingMapping.put("time", time);

                ownerReferance.setValue(bookingMapping).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(OwnerViewRequest.this, "Rejected Successfully", Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(OwnerViewRequest.this,  task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


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


            Intent navMenu = new Intent(OwnerViewRequest.this, OwnerProfile.class);
            startActivity(navMenu);

        } else if (id == R.id.nav_enter_bookigs) {


//            Intent navMenu2 = new Intent(HomeForAll.this, StuParentPropertySingleView.class);
//            startActivity(navMenu2);
        } else if (id == R.id.nav_enter_aboutus) {


            Intent navMenu = new Intent(OwnerViewRequest.this, AboutUs.class);
            startActivity(navMenu);
        }

        return false;
    }
    //drawer
}
