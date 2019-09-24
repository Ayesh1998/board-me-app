package com.sliit.mad.boardme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class UpdatePasswod extends AppCompatActivity {

    Button update;
    EditText email;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_passwod);

        update = findViewById(R.id.btUpdatePass);
        email = findViewById(R.id.updateEmail);
        auth = FirebaseAuth.getInstance();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.sendPasswordResetEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isComplete()){
                            Toast.makeText(UpdatePasswod.this, "Password Reset Sent to your email", Toast.LENGTH_LONG).show();
                            Intent navMenu = new Intent(UpdatePasswod.this, MainActivity.class);
                            startActivity(navMenu);
                        }else{
                            Toast.makeText(UpdatePasswod.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
