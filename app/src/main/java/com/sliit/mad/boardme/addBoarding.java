package com.sliit.mad.boardme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addBoarding extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_boarding);

        EditText etName,etAddress,etphone,etPrice,editroom, etDescription;
        Button btnImage, btnAdd;

        etName = (EditText) findViewById(R.id.etName);
         etAddress = (EditText) findViewById(R.id.etName);
         etphone = (EditText) findViewById(R.id.etName);
         etPrice = (EditText) findViewById(R.id.etName);
         editroom = (EditText) findViewById(R.id.etName);
        etDescription= (EditText) findViewById(R.id.etName);
        btnImage= (Button) findViewById(R.id.btnAdd);
         btnAdd = (Button) findViewById(R.id.btnAdd);

         btnAdd.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Toast.makeText(addBoarding.this, "Addedd Successfully", Toast.LENGTH_SHORT).show();
             }
         });
    }
}

