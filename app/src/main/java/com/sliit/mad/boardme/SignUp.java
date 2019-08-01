package com.sliit.mad.boardme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    Button btSignUp;
    EditText et_fname, et_lname, et_email, et_password, et_telephone,et_confirmPas;
    FirebaseDatabase dataBase;
    DatabaseReference dataRef;
    FirebaseAuth firebaseAuthe;
    Spinner sp;
    ProgressBar pgb;

    String fname,lname,type,pass,email,confPass,tele;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

         sp = (Spinner) findViewById(R.id.spiUsers);
        et_email = (EditText) findViewById(R.id.etEmailSignIN);
        et_fname = (EditText) findViewById(R.id.etFirstNameSignUp);
        et_lname = (EditText) findViewById(R.id.etLastNameSignUp);
        et_password = (EditText) findViewById(R.id.etPasswordSignIn);
        et_confirmPas = (EditText) findViewById(R.id.etConfirmPassword);
        et_telephone = (EditText) findViewById(R.id.etTele);
        pgb = (ProgressBar) findViewById(R.id.progressBar1);

        pgb.setVisibility(View.GONE);

        dataRef = FirebaseDatabase.getInstance().getReference("Users");
        firebaseAuthe = FirebaseAuth.getInstance();

        ArrayAdapter<String> userAdapter = new ArrayAdapter<String>(SignUp.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.users));
        userAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        sp.setAdapter(userAdapter);

        btSignUp = (Button) findViewById(R.id.btSignUp);

        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 fname = et_fname.getText().toString();
                 lname = et_lname.getText().toString();
                  tele = et_telephone.getText().toString();
                 pass = et_password.getText().toString();
                 confPass = et_confirmPas.getText().toString();
                  email = et_email.getText().toString();
                 type =  sp.getSelectedItem().toString();

                if (TextUtils.isEmpty(fname)){
                    Toast.makeText(SignUp.this, "Enter First Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(lname)){
                    Toast.makeText(SignUp.this, "Enter Last Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(tele)){
                    Toast.makeText(SignUp.this, "Enter Telephone no", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(pass)){
                    Toast.makeText(SignUp.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(confPass)){
                    Toast.makeText(SignUp.this, "Enter Password Again", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(SignUp.this, "Enter Email ", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(type)){
                    Toast.makeText(SignUp.this, "Enter Type", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (! (pass.equals(confPass)) ){
                    Toast.makeText(SignUp.this, "Check both passwords", Toast.LENGTH_SHORT).show();
                    return;
                }




                pgb.setVisibility(View.VISIBLE);
                firebaseAuthe.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Users user = new Users(
                                            type,
                                            fname,
                                            lname,
                                            email,
                                            tele
                                    );

                                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).
                                            setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            pgb.setVisibility(View.GONE);

                                            firebaseAuthe.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                    if ( !(task.isSuccessful())){
                                                        Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                    }
                                                    else{
                                                        Toast.makeText(SignUp.this, "Registration Successfull and Email Verification Sent", Toast.LENGTH_LONG).show();
                                                        Intent goActitviyVerify = new Intent(SignUp.this, VerifySignUp.class);
                                                        startActivity(goActitviyVerify);
                                                    }

                                                }
                                            });





                                        }
                                    });

                                } else {
                                    pgb.setVisibility(View.GONE);
                                    Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                }

                                // ...
                            }
                        });





            }
        });



    }
}
