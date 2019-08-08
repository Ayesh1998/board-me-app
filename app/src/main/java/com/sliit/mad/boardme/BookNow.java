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

public class BookNow extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //drawer
    DrawerLayout drawer;
    ActionBarDrawerToggle drawerToggle;
    //drawer

    Button btBookNow;
    EditText et_advance, et_descriptionj;
    FirebaseDatabase dataBase;
    DatabaseReference dataRef;
    FirebaseAuth firebaseAuthe;
    ProgressBar pgb;
//    Spinner sp;

    String advance,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_now);


        btBookNow = (Button)findViewById(R.id.btnbooknow);
        et_advance = (EditText) findViewById(R.id.ptadvance);
        et_descriptionj = (EditText) findViewById(R.id.ptDescription);
        pgb = (ProgressBar) findViewById(R.id.bookProgressBar);


        pgb.setVisibility(View.GONE);

        dataRef = FirebaseDatabase.getInstance().getReference("Booking");
        firebaseAuthe = FirebaseAuth.getInstance();

//        ArrayAdapter<String> userAdapter = new ArrayAdapter<String>(BookNow.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.users));
//        userAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
//        sp.setAdapter(userAdapter);

        btBookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                advance = et_advance.getText().toString();
                description = et_descriptionj.getText().toString();

                if (TextUtils.isEmpty(advance)){
                    Toast.makeText(BookNow.this, "Enter Advance", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(description)){
                    Toast.makeText(BookNow.this, "Enter Description", Toast.LENGTH_SHORT).show();
                    return;
                }

                  pgb.setVisibility(View.VISIBLE);

//                firebaseAuthe.createUserWithEmailAndPassword(email, pass)
//                        .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if (task.isSuccessful()) {
//
//                                    Users user = new Users(
//                                            type,
//                                            fname,
//                                            lname,
//                                            email,
//                                            tele
//                                    );
//
//                                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).
//                                            setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<Void> task) {
//
//                                            pgb.setVisibility(View.GONE);
//
//                                            firebaseAuthe.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
//                                                @Override
//                                                public void onComplete(@NonNull Task<Void> task) {
//
//                                                    if ( !(task.isSuccessful())){
//                                                        Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                                                    }
//                                                    else{
//                                                        Toast.makeText(SignUp.this, "Registration Successfull and Email Verification Sent", Toast.LENGTH_LONG).show();
//                                                        Intent goActitviyVerify = new Intent(SignUp.this, VerifySignUp.class);
//                                                        startActivity(goActitviyVerify);
//                                                    }
//
//                                                }
//                                            });
//
//
//
//
//
//                                        }
//                                    });
//
//                                } else {
//                                    pgb.setVisibility(View.GONE);
//                                    Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//
//                                }
//
//                                // ...
//                            }
//                        });





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
