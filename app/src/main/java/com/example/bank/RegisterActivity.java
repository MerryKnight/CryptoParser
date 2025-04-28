package com.example.bank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button signupBtn = findViewById(R.id.signup_btn);
        EditText emailEt = findViewById(R.id.email_et);
        EditText passwdEt = findViewById(R.id.password_et);
        EditText usernameEt = findViewById(R.id.username_et);
        ImageButton backBtn = findViewById(R.id.back_btn);


        signupBtn.setOnClickListener(v -> {
            if (emailEt.getText().toString().isEmpty() || passwdEt.getText().toString().isEmpty() ||
                    usernameEt.getText().toString().isEmpty()){
                Toast.makeText(RegisterActivity.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
            }
            else{
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailEt.getText().toString(), passwdEt.getText().toString())
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                HashMap<String, String> userInfo = new HashMap<>();
                                userInfo.put("email", emailEt.getText().toString());
                                userInfo.put("username", usernameEt.getText().toString());
                                FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance()
                                        .getCurrentUser().getUid()).setValue(userInfo);
                                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                            }
                            else{
                                Toast.makeText(RegisterActivity.this, "you suck", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });

        backBtn.setOnClickListener(v ->
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class)));
    }
}