package com.example.train_food_order_app.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.train_food_order_app.FragmentUtils;
import com.example.train_food_order_app.MainActivity;
import com.example.train_food_order_app.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FrameLayout parentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        checkUser();
    }
    private void checkUser(){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        if (user!=null){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            parentLayout = findViewById(R.id.parentFrameLayout);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentUtils.setFragment(fragmentManager,parentLayout.getId(),new SignIn_Fragment());
        }
    }
}