package com.example.easychoices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CommentsActivity extends AppCompatActivity {
    EditText Comments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        Comments = (EditText) findViewById(R.id.editText5);

    }
    public void clickWrite(View v) {
        // check that both the fields are not empty
        if (Comments.getText().toString().length() != 0) {
            // To write data to the Firebase
            FirebaseFirestore mDatabase = FirebaseFirestore.getInstance();
            Map<String, Object> user = new HashMap<>();
            user.put("Comments", Comments.getText().toString());
            // To write to the database one item
            mDatabase.collection("Comments").add(user)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(getBaseContext(), "Details added: " +
                                    Comments.getText().toString() , Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getBaseContext(), "Error adding to DB",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
        }
        else
        {
            Toast.makeText(getApplicationContext(), "ERROR: Name and email cannot be empty.", Toast.LENGTH_LONG).show();
        }

        }
    public void clickRead(View v)
    {
        Intent contactList = new Intent(v.getContext(), CommentsDetails.class);
        startActivity(contactList);
    }
    }

