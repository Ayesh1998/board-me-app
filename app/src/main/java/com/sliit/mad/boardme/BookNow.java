package com.sliit.mad.boardme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;
import java.util.Map;

public class BookNow extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //drawer
    DrawerLayout drawer;
    ActionBarDrawerToggle drawerToggle;
    //drawer

    Button btBookNow;
    EditText et_advance, et_descriptionj;
    FirebaseDatabase dataBase;

    FirebaseAuth ffAuth;
    DatabaseReference db;
    ProgressBar pgb;
//    Spinner sp;

    String advance,description,propertyIDs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_now);


        btBookNow = (Button)findViewById(R.id.btnbooknow);
        et_advance = (EditText) findViewById(R.id.ptadvance);
        et_descriptionj = (EditText) findViewById(R.id.ptDescription);
        pgb = (ProgressBar) findViewById(R.id.bookProgressBar);
        propertyIDs = getIntent().getExtras().getString("propertyID");


        pgb.setVisibility(View.GONE);

        ffAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance().getReference().child("Bookings").child(ffAuth.getCurrentUser().getUid());


        btBookNow.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {

                                             advance = et_advance.getText().toString();
                                             description = et_descriptionj.getText().toString();
                                             String status = "Pending";
                                             String propertyID = propertyIDs;
                                             String user = ffAuth.getCurrentUser().getUid();

                                             DatabaseReference db2 = db.push();

                                             if (((TextUtils.isEmpty(advance)) && (TextUtils.isEmpty(description)))) {
                                                 Toast.makeText(BookNow.this, "Fill Empty Fields", Toast.LENGTH_SHORT).show();
                                                 return;
                                             } else {

                                                 Map bookingMapping = new HashMap();
                                                 bookingMapping.put("advance", advance);
                                                 bookingMapping.put("propertyID", propertyID);
                                                 bookingMapping.put("description", description);
                                                 bookingMapping.put("status", status);
                                                 bookingMapping.put("user", user);
                                                 bookingMapping.put("time", ServerValue.TIMESTAMP);

                                                 db2.setValue(bookingMapping).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                     @Override
                                                     public void onComplete(@NonNull Task<Void> task) {
                                                         if (task.isSuccessful()) {
                                                             Intent ins = new Intent(BookNow.this,StuParentPropertySingleView.class);
                                                            ins.putExtra("keyValue",propertyIDs) ;
                                                            startActivity(ins);
                                                             Toast.makeText(BookNow.this, "Booking added successfully", Toast.LENGTH_SHORT).show();
                                                         } else {
                                                             Toast.makeText(BookNow.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                         }
                                                     }
                                                 });


                                             }
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
