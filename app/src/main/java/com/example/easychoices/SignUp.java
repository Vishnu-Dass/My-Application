package com.example.easychoices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    EditText email;
    EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        email = (EditText) findViewById(R.id.editText3);
        password = (EditText) findViewById(R.id.editText4);
    }
    public void Main(View view ){
        Intent myIntent =  new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }
    public void clickSign(View v) {

        //gets user info and converts it to string
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();
        //When one field is empty
        if (userEmail.length() != 0 && userPassword.length()!= 0) {
            // Signup an account using email and password from EditTexts
            mAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                // Sign up success
                                Toast.makeText(getBaseContext(), "Signup Successful.",
                                        Toast.LENGTH_SHORT).show();
                                Intent successActivity = new Intent(getBaseContext(), Login.class);
                                startActivity(successActivity);


                            } else {
                                // If sign up fails, display a message to the user.
                                Toast.makeText(getBaseContext(), "Signup failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        else {
            Toast.makeText(getApplicationContext(), "ERROR: Email and Password cannot be empty.", Toast.LENGTH_SHORT).show();
        }

    }
}
