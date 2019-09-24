package com.sliit.mad.boardme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateUserDetails extends AppCompatActivity {


    Button btUpdate;
    EditText et_fname, et_lname, et_telephone,etAddressUp;
    FirebaseDatabase dataBase;
    DatabaseReference dataRef;
    FirebaseAuth firebaseAuthe;
    FirebaseUser firebaseAutheUser;
    ProgressBar pgb;
    String fname,lname,tele;
    String ofname, olname, otele,oemail,type,address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_details);

        et_telephone = (EditText) findViewById(R.id.etTeleUp);
        et_fname = (EditText) findViewById(R.id.etFirstNameUpSignUp);
        et_lname = (EditText) findViewById(R.id.etLastNameUpSignUp);
        etAddressUp = (EditText) findViewById(R.id.etAddressUp);
        pgb = (ProgressBar) findViewById(R.id.progressBar1Up);
        btUpdate = (Button) findViewById(R.id.btUpdateDetails);
        pgb.setVisibility(View.GONE);


        firebaseAuthe = FirebaseAuth.getInstance();
        firebaseAutheUser = firebaseAuthe.getCurrentUser();
        dataRef = FirebaseDatabase.getInstance().getReference("Users").child(firebaseAutheUser.getUid());

        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                 ofname = (String) dataSnapshot.child("firstName").getValue();
                 olname = (String) dataSnapshot.child("lastName").getValue();
//                String oaddress = (String) dataSnapshot.child("address").getValue();
                 otele = (String) dataSnapshot.child("telephone").getValue();
                 oemail = (String) dataSnapshot.child("email").getValue();
                 type = (String) dataSnapshot.child("type").getValue();
                 address = (String) dataSnapshot.child("address").getValue();

                et_lname.setText(olname);
                et_telephone.setText(otele);
                et_fname.setText(ofname);
                etAddressUp.setText(address);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fname = et_fname.getText().toString();
                lname = et_lname.getText().toString();
                tele = et_telephone.getText().toString();
                address =  etAddressUp.getText().toString();


                if (TextUtils.isEmpty(fname)){
                    Toast.makeText(UpdateUserDetails.this, "Enter First Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(lname)){
                    Toast.makeText(UpdateUserDetails.this, "Enter Last Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(tele)){
                    Toast.makeText(UpdateUserDetails.this, "Enter Telephone no", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(address)){
                    Toast.makeText(UpdateUserDetails.this, "Enter Telephone no", Toast.LENGTH_SHORT).show();
                    return;
                }

                pgb.setVisibility(View.VISIBLE);

                Users user = new Users(
                        type,
                        fname,
                        lname,
                        oemail,
                        tele,
                        address
                );


                dataRef.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        pgb.setVisibility(View.GONE);
                        if (task.isSuccessful()){

                            Toast.makeText(UpdateUserDetails.this, "Updated Successful", Toast.LENGTH_SHORT).show();

                            if (type.equals("Owner")){
                                Intent in = new Intent(UpdateUserDetails.this, OwnerProfile.class);
                                startActivity(in);
                            }
                            else{
                                Intent in = new Intent(UpdateUserDetails.this, StuParentHome.class);
                                startActivity(in);
                            }


                        }else {
                            Toast.makeText(UpdateUserDetails.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}
