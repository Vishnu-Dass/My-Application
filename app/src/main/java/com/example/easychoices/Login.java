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

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    EditText email;
    EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);
    }
    public void Next(View view){
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();

        // shows error when one field is empty
        if (userEmail.length() != 0 && userPassword.length()!= 0) {

            //signs in with singup info stored in firebase
            mAuth.signInWithEmailAndPassword(userEmail, userPassword)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign up success
                                Toast.makeText(getBaseContext(), "Login Successful.",
                                        Toast.LENGTH_SHORT).show();
                                Intent Home = new Intent(getBaseContext(), MainActivity.class);
                                startActivity(Home);

                                // Bring user to success activvity

                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(getBaseContext(), "Login failed.",
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
