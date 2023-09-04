package com.example.train_food_order_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class SetupActivity extends AppCompatActivity {
    private static final String PREF_SETUP_COMPLETE = "setup_complete";
    private int currentStep = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_setup);

//        // Load the appropriate layout for the current step
//        loadStepLayout(currentStep);
//
//        Button nextButton = findViewById(R.id.nextButton);
//        nextButton.setOnClickListener(v -> {
//            if (currentStep <= 3) {
//                // Proceed to the next step
//                currentStep++;
//                Log.e("MyApp", String.valueOf(currentStep));
//                loadStepLayout(currentStep);
//            } else {
//                // Complete the setup process
//                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//                preferences.edit().putBoolean(PREF_SETUP_COMPLETE, true).apply();
//
//                // Navigate to the main activity or wherever needed
//                // ...
//                finish();
//            }
//        });
//    }
//
//    private void loadStepLayout(int step) {
//        int layoutResId = 0;
//        switch (step) {
//            case 1:
//                layoutResId = R.layout.setup1_layout;
//                break;
//            case 2:
//                layoutResId = R.layout.setup2_layout;
//                break;
//            case 3:
////                TextView textViewDebug = findViewById(R.id.textViewDebug);
////                textViewDebug.setText("Debug text");
//                layoutResId = R.layout.setup3_layout;
//                break;
//            default:
//                break;
//        }
//
//        if (layoutResId != 0) {
//            Log.d("SetupActivity", "Loading layout: " + getResources().getResourceEntryName(layoutResId));
//            //setContentView(layoutResId);
//            setContentView(layoutResId);
//        }
    }
}