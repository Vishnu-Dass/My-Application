package com.example.easychoices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class CommentsDetails extends AppCompatActivity {
    ListView commentsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments_details);
        commentsList = (ListView) findViewById(R.id.listComments);
// To read values from the Firebase
        FirebaseFirestore mDatabase = FirebaseFirestore.getInstance();
        mDatabase.collection("Comments")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<String> names = new ArrayList<String>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                names.add(document.getId() + ": " +
                                        document.getData().get("Comments").toString() + " - " );
                            }
                            ArrayAdapter adapter = new ArrayAdapter(CommentsDetails.this,
                                    android.R.layout.simple_list_item_1, names);
                            commentsList.setAdapter(adapter);
                        } else {
                            Toast.makeText(getBaseContext(), "Error getting documents",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
