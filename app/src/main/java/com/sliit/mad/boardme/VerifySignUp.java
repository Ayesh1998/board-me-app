package com.sliit.mad.boardme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VerifySignUp extends AppCompatActivity {

    Button btVerify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_sign_up);

        btVerify = (Button) findViewById(R.id.btSignin);

        btVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goActitviyLogin = new Intent(VerifySignUp.this, com.sliit.mad.boardme.MainActivity.class);
                startActivity(goActitviyLogin);
            }
        });
    }
}
