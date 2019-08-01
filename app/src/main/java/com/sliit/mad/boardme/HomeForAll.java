package com.sliit.mad.boardme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class HomeForAll extends AppCompatActivity {

    RecyclerView boardingList;
    DatabaseReference proprtiesReferance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_for_all);

        proprtiesReferance = FirebaseDatabase.getInstance().getReference().child("Properties");
        proprtiesReferance.keepSynced(true);

        boardingList = (RecyclerView) findViewById(R.id.rvHomeList);
        boardingList.setHasFixedSize(true);
        boardingList.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Properties,PropertyHolder1>firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Properties, PropertyHolder1>
                (Properties.class,R.layout.home_row,PropertyHolder1.class,proprtiesReferance) {
            @Override
            protected void populateViewHolder(PropertyHolder1 propertyHolder1, Properties properties, int i) {

                propertyHolder1.setTitle(properties.getTitle());
                propertyHolder1.setBathrooms(properties.getBathrooms());
                propertyHolder1.setPrice(properties.getPrice());
                propertyHolder1.setRooms(properties.getRooms());
                propertyHolder1.setImages(getApplicationContext(),properties.getImages());

            }
        };

        boardingList.setAdapter(firebaseRecyclerAdapter);


    }
}

class  PropertyHolder1 extends  RecyclerView.ViewHolder{

    View mView;

    public PropertyHolder1(View v){
        super(v);
        mView=v;
    }

    public void setTitle(String t){
        TextView homeCardTitle = (TextView) mView.findViewById(R.id.homeCardTitle);
        homeCardTitle.setText(t);
    }
    public void setRooms(String t){
        TextView homeCardRooms = (TextView) mView.findViewById(R.id.homeCardRomms2);
        homeCardRooms.setText("Rooms : " + t);
    }
    public void setBathrooms(String t){
        TextView homeCardBathrooms = (TextView) mView.findViewById(R.id.homeCardBathrooms);
        homeCardBathrooms.setText("Bathrooms : " +t);
    }
    public void setPrice(String t){
        TextView homeCardPrice = (TextView) mView.findViewById(R.id.homeCardPriceView);
        homeCardPrice.setText("Price : "+ t + "/monthly");
    }

    public void setImages(Context ctx,String t){
        ImageView homeCardImage1 = (ImageView) mView.findViewById(R.id.homeCardImage);
//        Picasso.with(ctx).load(t).into(homeCardImage1);
        Picasso.with(ctx)
                .load(t)
                .into(homeCardImage1);
    }


}