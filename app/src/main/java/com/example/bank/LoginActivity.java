package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button loginBtn = findViewById(R.id.login_btn);
        EditText emailEt = findViewById(R.id.email_et);
        EditText passwdEt = findViewById(R.id.password_et);
        Button goToRegisterBtn = findViewById(R.id.go_to_register_activity);

        loginBtn.setOnClickListener(v -> {
            if (emailEt.getText().toString().isEmpty() || passwdEt.getText().toString().isEmpty()){
                Toast.makeText(LoginActivity.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
            }
            else{
                FirebaseAuth.getInstance().signInWithEmailAndPassword(emailEt.getText().toString(), passwdEt.getText().toString())
                        .addOnCompleteListener(task -> {
                            if(task.isSuccessful()){
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            }
                        });
            }
        });

        goToRegisterBtn.setOnClickListener(v1 -> startActivity(
                new Intent(LoginActivity.this, RegisterActivity.class))
        );
    }
}