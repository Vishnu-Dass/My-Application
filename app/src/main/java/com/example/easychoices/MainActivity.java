package com.example.easychoices;

import Adapter.Main2Activity;
import Adapter.MyAdapter;
import Interface.FirebaseLoader;
import Model.Restaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FirebaseLoader {
    ViewPager viewPager;
    MyAdapter myAdapter;
    FirebaseLoader firebaseLoader;


    CollectionReference restaurants;

    List<Restaurant> mRestaurantList = new ArrayList<>();


    public static final String ARG_TITLE = "ARG_TITLE";
    public static final String ARG_URL = "ARG_URL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        firebaseLoader = this;
        restaurants = FirebaseFirestore.getInstance().collection("Restaurant");

        viewPager = (ViewPager) findViewById(R.id.viewPager);

        getData();
    }


    private void getData() {
        restaurants.get()
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        firebaseLoader.onFirestoreLoadFailed(e.getMessage());
                    }
                })
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Restaurant> restaurants = new ArrayList<>();
                            for (QueryDocumentSnapshot restaurantSnapshot : task.getResult()) {
                                Restaurant restaurant = restaurantSnapshot.toObject(Restaurant.class);
                                restaurants.add(restaurant);
                            }
                            firebaseLoader.onFireStoreLoadSuccess(restaurants);

                        }
                    }
                });
    }

    @Override
    public void onFireStoreLoadSuccess(List<Restaurant> restaurants) {

        mRestaurantList = restaurants;

        myAdapter = new MyAdapter(this, restaurants);
        viewPager.setAdapter(myAdapter);
    }

    @Override
    public void onFirestoreLoadFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }
    public void clickSNext(View view){
        Intent myIntent = new Intent(this, CommentsActivity.class);
        startActivity(myIntent);
    }

    public void Go(View view){
        Intent myIntent = new Intent(this, CommentsActivity.class);
        startActivity(myIntent);
    }

    public void clickNext(View view) {


        int selectedPageNo = viewPager.getCurrentItem();


        if (mRestaurantList.size() > 0) {


            String title = mRestaurantList.get(selectedPageNo).getTitle();
            String url = mRestaurantList.get(selectedPageNo).getImage();

            Intent intent = new Intent(this, Main2Activity.class);

            intent.putExtra(ARG_TITLE, title);
            intent.putExtra(ARG_URL, url);

            startActivity(intent);

        }
    }
}
