package com.sliit.mad.boardme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminHome extends AppCompatActivity implements View.OnClickListener {

    private CardView property,request,user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
       request = (CardView)findViewById(R.id.card00001);
        user = (CardView)findViewById(R.id.card0002);
        property = (CardView) findViewById(R.id.card0003);

        request.setOnClickListener(this);
        user.setOnClickListener(this);
        property.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()) {
           case R.id.card00001 : i = new Intent(this,propertyManagement.class);startActivity(i);
                break;

            case R.id.card0002 : i = new Intent(this,UserManagement.class);startActivity(i);
                break;

            case R.id.card0003:
                i = new Intent(this, QuestioAnAnz.class);
                startActivity(i);
                break;

            default:
                break;
        }

    }
}
