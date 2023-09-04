package com.example.train_food_order_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.train_food_order_app.Adapter.MenuAdapter;
import com.example.train_food_order_app.Models.MenuItems;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FrameLayout frameLayout;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CheckInternetAccess();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.orderCart) {
            // Handle search item click
            FragmentUtils.setFragment(fragmentManager,frameLayout.getId(),new Cart_Fragment());
            Toast.makeText(this, "cartClicked", Toast.LENGTH_SHORT).show();

            return true;
        }
        // Handle other menu item clicks if needed
        return super.onOptionsItemSelected(item);
    }

//    private void setDefaultFragment(Fragment fragment){
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_in, R.anim.fragment_slide_out);
//        fragmentTransaction.replace(frameLayout.getId(),fragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
//    }

    @Override
    public void onBackPressed() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.loginFrameLayout);
        if (currentFragment instanceof MenuSelection_Fragment) {
            // Handle back button press in MenuShowFragment
            ((MenuSelection_Fragment) currentFragment).onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
    private void CheckInternetAccess(){
        if (NetworkUtils.isNetworkAvailable(this)){
            NetworkUtils.hasInternetAccess(new NetworkUtils.InternetAccessCallback() {
                @Override
                public void onInternetAccessResult(boolean hasInternetAccess) {
                    if (hasInternetAccess){

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setContentView(R.layout.activity_main);
                                toolbar =  findViewById(R.id.toolbar);
                                setSupportActionBar(toolbar);
                                fragmentManager = getSupportFragmentManager();

                                frameLayout = findViewById(R.id.loginFrameLayout);
                                // setDefaultFragment(new MenuSelection_Fragment());
                                FragmentUtils.setFragment(fragmentManager,frameLayout.getId(),new MenuSelection_Fragment());

                            }
                        });
                    }
                    else {
                        //no internet connection
                        setContentView(R.layout.no_internet_connection);
                        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "You have no internet connection", Snackbar.LENGTH_INDEFINITE);
                        snackbar.setActionTextColor(getResources().getColor(R.color.red));
                        snackbar.setAction("Check Again", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                CheckInternetAccess();
                            }
                        });
                        snackbar.show();
                    }
                }
            });
        }
        else {
            //not connected to internet
            setContentView(R.layout.not_connected_to_internet);
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Make sure you are connected to the internet", Snackbar.LENGTH_INDEFINITE);
            snackbar.setActionTextColor(getResources().getColor(R.color.red));
            snackbar.setAction("DISMISS", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    snackbar.dismiss();
                }
            });
            snackbar.show();
        }
    }
}