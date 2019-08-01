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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button signIn,signUp;
    EditText email1,password1;
    String email,password11;
    FirebaseAuth fireSignInAuth;
    ProgressBar pr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signUp = (Button)findViewById(R.id.bSignUp);
        signIn = (Button)findViewById(R.id.btSignin);
        email1 = (EditText)findViewById(R.id.etEmailSignIN) ;
        password1 = (EditText)findViewById(R.id.etPasswordSignIn) ;
        fireSignInAuth = FirebaseAuth.getInstance();
        pr = (ProgressBar) findViewById(R.id.progressBar2) ;
        pr.setVisibility(View.GONE);


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = email1.getText().toString();
                password11 = password1.getText().toString();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(MainActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password11)){
                    Toast.makeText(MainActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                pr.setVisibility(View.VISIBLE);

                fireSignInAuth.signInWithEmailAndPassword(email1.getText().toString(), password1.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()){
                                    pr.setVisibility(View.GONE);
                                    Toast.makeText(MainActivity.this, "Login Succefull", Toast.LENGTH_LONG).show();
                                    Intent goActitviyHome = new Intent(MainActivity.this, HomeForAll.class);
                                    startActivity(goActitviyHome);
                                }
                                else{
                                    pr.setVisibility(View.GONE);
                                    Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goActitviySignUp = new Intent(MainActivity.this, com.sliit.mad.boardme.SignUp.class);
                startActivity(goActitviySignUp);
            }
        });

    }



}
