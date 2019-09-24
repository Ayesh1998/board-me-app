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

public class StuParentHome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //drawer
    DrawerLayout drawer;
    ActionBarDrawerToggle drawerToggle;
    //drawer

    DatabaseReference ownerReferance;
    RecyclerView boardingList;
    TextView fname,address,email,tele,stduParentType;
    FirebaseAuth firebaseAuthe;
    FirebaseUser firebaseAutheUser;
    ImageView ivs;
    Button stdbtn123;
    String id = "1AoaPXeb8IV8hA3g0a1TSueXWV82";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_parent_home);

        email = (TextView)findViewById(R.id.stdProf_email) ;
        fname = (TextView)findViewById(R.id.stdProf_tvstudentFname) ;
      stduParentType = (TextView)findViewById(R.id.stduParentType) ;
        tele = (TextView)findViewById(R.id.stdProf_tele) ;
        address = (TextView)findViewById(R.id.stdProf_address) ;
//        ivs = (ImageView) findViewById(R.id.ownerProfIvUpdate12);
        stdbtn123 = findViewById(R.id.stdbtn123);



        firebaseAuthe = FirebaseAuth.getInstance();
        firebaseAutheUser = firebaseAuthe.getCurrentUser();
        id = firebaseAutheUser.getUid();

        //drawer
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        drawerToggle = new ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close);
        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navVieww);
        navigationView.setNavigationItemSelectedListener(this);


        stdbtn123.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in = new Intent(StuParentHome.this, UpdateUserDetails.class);
                startActivity(in);
            }
        });

//
//        ivs.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(StuParentHome.this, "lol", Toast.LENGTH_SHORT).show();
//                Intent in = new Intent(StuParentHome.this, UpdateUserDetails.class);
//                startActivity(in);
//            }
//        });



        ownerReferance = FirebaseDatabase.getInstance().getReference().child("Users").child(id);

        ownerReferance.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String ofname = (String) dataSnapshot.child("firstName").getValue();
                String olname = (String) dataSnapshot.child("lastName").getValue();
                String  stduParentTypes = (String) dataSnapshot.child("type").getValue();
                String oaddress = (String) dataSnapshot.child("address").getValue();
                String otele = (String) dataSnapshot.child("telephone").getValue();
                String oemail = (String) dataSnapshot.child("email").getValue();

                email.setText(oemail);
//                lname.setText(olname);
                address.setText(oaddress);
                stduParentType.setText(stduParentTypes);
                tele.setText(otele);
                fname.setText(ofname + " " + olname);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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

    public void Cl(View view) {
        Intent in = new Intent(StuParentHome.this, UpdateUserDetails.class);
        startActivity(in);
    }

}
