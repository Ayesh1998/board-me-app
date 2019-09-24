package com.sliit.mad.boardme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OwnerProfile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    DatabaseReference ownerReferance;
    FirebaseAuth ffAuth;
    //drawer
    DrawerLayout drawer;
    ActionBarDrawerToggle drawerToggle;
    TextView fname,address,email,tele,lname;
    FirebaseAuth firebaseAuthe;
    FirebaseUser firebaseAutheUser;
    ImageView iv;

    String id = "1AoaPXeb8IV8hA3g0a1TSueXWV82";

//    String id =  ffAuth.getCurrentUser().getUid().toString();
    Button myBookingReq,addPlace,myPlaces;

    //drawer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_profile);

        email = (TextView)findViewById(R.id.ownerProfileemail) ;
        fname = (TextView)findViewById(R.id.ownerProfiletvstudentname) ;
        lname = (TextView)findViewById(R.id.ownerProfiletvstudentLastname) ;
        tele = (TextView)findViewById(R.id.ownerProfiletele) ;
        address = (TextView)findViewById(R.id.ownerProfileaddress) ;
        myBookingReq = (Button) findViewById(R.id.ownerProfilebtBookingRequs) ;
        addPlace = (Button) findViewById(R.id.ownerProfilebtAddBoarding) ;
        myPlaces = (Button) findViewById(R.id.ownerProfilebtMyBoardingPlaces) ;
        iv = findViewById(R.id.ownerProfIvUpdate);

        firebaseAuthe = FirebaseAuth.getInstance();
        firebaseAutheUser = firebaseAuthe.getCurrentUser();
        id = firebaseAutheUser.getUid();

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in = new Intent(OwnerProfile.this, UpdateUserDetails.class);
                startActivity(in);
            }
        });

        ownerReferance = FirebaseDatabase.getInstance().getReference().child("Users").child(id);

        myPlaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(OwnerProfile.this, HomeForAll.class);
                in.putExtra("owners","yes");
                startActivity(in);
            }
        });

        ownerReferance.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String ofname = (String) dataSnapshot.child("firstName").getValue();
                String olname = (String) dataSnapshot.child("lastName").getValue();
                String oaddress = (String) dataSnapshot.child("address").getValue();
                String otele = (String) dataSnapshot.child("telephone").getValue();
                String oemail = (String) dataSnapshot.child("email").getValue();
                String type = (String) dataSnapshot.child("type").getValue();

                email.setText(oemail);
                lname.setText(type);
                address.setText(oaddress);
                tele.setText(otele);
                fname.setText(ofname + " " + olname);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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


            Intent navMenu = new Intent(OwnerProfile.this, StuParentHome.class);
            startActivity(navMenu);

        } else if (id == R.id.nav_enter_bookigs) {


//            Intent navMenu2 = new Intent(HomeForAll.this, StuParentPropertySingleView.class);
//            startActivity(navMenu2);
        } else if (id == R.id.nav_enter_aboutus) {


            Intent navMenu = new Intent(OwnerProfile.this, AboutUs.class);
            startActivity(navMenu);
        }

        return false;
    }
    //drawer

}
