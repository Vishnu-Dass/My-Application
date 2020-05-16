package Adapter;

import android.content.Intent;
import android.os.Bundle;

import com.example.easychoices.MainActivity;
import com.example.easychoices.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import Model.Restaurant;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import Adapter.ui.main.SectionsPagerAdapter;

public class Main2Activity extends AppCompatActivity {


    String mTitle = "";
    String mUrl = "";
    CollectionReference restaurants;
    List<Restaurant> mRestaurantList = new ArrayList<>();
    MyAdapter myAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        restaurants = FirebaseFirestore.getInstance().collection("Halal");


        Intent intent = getIntent();

        if (intent != null) {

            if (intent.getStringExtra(MainActivity.ARG_TITLE) != null) {
                mTitle = intent.getStringExtra(MainActivity.ARG_TITLE);
            }

            if (intent.getStringExtra(MainActivity.ARG_URL) != null) {
                mUrl = intent.getStringExtra(MainActivity.ARG_URL);
            }


        }


        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), mTitle, mUrl);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}