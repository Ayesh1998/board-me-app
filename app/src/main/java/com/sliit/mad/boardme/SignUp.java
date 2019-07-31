package com.sliit.mad.boardme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SignUp extends AppCompatActivity {

    Button btSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Spinner sp = (Spinner) findViewById(R.id.spiUsers);

        ArrayAdapter<String> userAdapter = new ArrayAdapter<String>(SignUp.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.users));
        userAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        sp.setAdapter(userAdapter);

        btSignUp = (Button) findViewById(R.id.btSignUp);

        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goActitviyVerify = new Intent(SignUp.this, com.sliit.mad.boardme.VerifySignUp.class);
                startActivity(goActitviyVerify);
            }
        });



    }
}
