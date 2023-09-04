package com.example.train_food_order_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.train_food_order_app.Models.Order;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

public class FoodDetailsActivity extends AppCompatActivity {

    private DatabaseReference reference;

    private TextView quantity, cokeQtr, spriteQtr, fantaQtr, waterQtr;
    private ImageView add, minus, cokeAdd, spriteAdd, fantaAdd, cokeMinus, spriteMinus, fantaMinus, waterAdd, waterMinus;
    private int count = 1;
    private int cokeCount = 0;
    private double sum;

    private ImageView arrow;
    private ConstraintLayout expandable;
    private CardView cardView;
    private boolean isExpanded = false;

    private String itemName, imageUrl,itemDescription;
    private double itemPrice;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        reference = FirebaseDatabase.getInstance().getReference("Orders");

        Intent intent = getIntent();
        itemName = intent.getStringExtra("item_name");
        itemDescription = intent.getStringExtra("item_description");
        itemPrice = intent.getDoubleExtra("item_price", 0.0);
        imageUrl = intent.getStringExtra("item_Image");
        sum = itemPrice;


        TextView textViewItemName = findViewById(R.id.nameDetails);
        TextView textViewItemDescription = findViewById(R.id.descriptionDetails);
        TextView textViewItemPrice = findViewById(R.id.priceDetails);
        TextView txtTotalPrice = findViewById(R.id.totalPrice);
        TextView cokePrice = findViewById(R.id.cokePrice);
        TextView spritePrice = findViewById(R.id.spritePrice);
        TextView fantaPrice = findViewById(R.id.fantaPrice);
        TextView waterPrice = findViewById(R.id.waterPrice);
        ImageView imageView = findViewById(R.id.foodImageDetail);
        Button btnOrder = findViewById(R.id.orderBtn);
        quantity = findViewById(R.id.qtr);
        cokeQtr = findViewById(R.id.cokeQtr);
        spriteQtr = findViewById(R.id.sprintQtr);
        fantaQtr = findViewById(R.id.fantatQtr);
        waterQtr = findViewById(R.id.waterQtr);
        add = findViewById(R.id.add);
        cokeAdd = findViewById(R.id.cokeAdd);
        cokeMinus = findViewById(R.id.cokeMinus);
        spriteAdd = findViewById(R.id.spriteAdd);
        spriteMinus = findViewById(R.id.spritMinus);
        fantaAdd = findViewById(R.id.fantaAdd);
        fantaMinus = findViewById(R.id.fantaMinus);
        fantaMinus = findViewById(R.id.waterAdd);
        fantaMinus = findViewById(R.id.waterMinus);

        minus = findViewById(R.id.minus);
        cardView = findViewById(R.id.cardView);
        expandable = findViewById(R.id.expandableCons);
        arrow = findViewById(R.id.arrowImg);
        Picasso.get().load(imageUrl).into(imageView);

        textViewItemName.setText(itemName);
        textViewItemDescription.setText(itemDescription);
        textViewItemPrice.setText(String.format(Locale.US, "$ %.2f", itemPrice));
        txtTotalPrice.setText(String.format(Locale.US, "$ %.2f", itemPrice));


        minus.setOnClickListener((View) -> {
            if (count == 1) {
                return;
            }
            count--;
            quantity.setText(String.valueOf(count));
            quantity.setText(String.valueOf(count));
            sum = count * itemPrice;
            txtTotalPrice.setText(String.format(Locale.US, "$ %.2f", sum));
        });

        cokeAdd.setOnClickListener((View) -> {
            cokeCount++;
            cokeQtr.setText(String.valueOf(cokeCount));


//            double cokeAdd = cokeCount * 2.22;
//             sum = itemPrice + cokeAdd;
//            Log.e("cokePrice+itemPrice", String.valueOf(sum));
//            txtTotalPrice.setText(String.format(Locale.US,"$ %.2f", sum));
        });
        cokeMinus.setOnClickListener((View) -> {
            if (cokeCount == 0) {
                return;
            }
            cokeCount--;
            cokeQtr.setText(String.valueOf(cokeCount));
        });

        add.setOnClickListener((View) -> {
            count++;
            quantity.setText(String.valueOf(count));
            sum = count * itemPrice;
            txtTotalPrice.setText(String.format(Locale.US, "$ %.2f", sum));

        });


        btnOrder.setOnClickListener((View) -> {

            showEditDataAlertDialog();

        });


        arrow.setOnClickListener((View) -> {

            if (expandable.getVisibility() == android.view.View.GONE) {
                // The expandable section is currently hidden, so we need to show it
                TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                expandable.setVisibility(android.view.View.VISIBLE);
                arrow.setBackgroundResource(R.drawable.arrow_up);
                isExpanded = true; // Set the state to expanded
            } else {
                // The expandable section is currently visible, so we need to hide it
                TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                expandable.setVisibility(android.view.View.GONE);
                arrow.setBackgroundResource(R.drawable.arrow_down);
                isExpanded = false; // Set the state to collapsed
            }

        });


    }

    private void saveOrderToFirebase(Order order) {
        //String orderId = ordersRef.push().getKey(); // Generate a unique push ID for the order

        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uid = auth.getUid();

        if (uid != null && !uid.isEmpty()) {

            DatabaseReference ordersRef = FirebaseDatabase.getInstance().getReference().child("Orders").child(uid).child(order.getOrderNumber());
            ordersRef.setValue(order)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(this, "Order placed successfully!", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {

                        Toast.makeText(this, "Failed to place the order. Please try again.\n" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    });
        }
    }

    private void showEditDataAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Payment Options")
                .setMessage("Do you want to Pay Online or COD ?")
                .setPositiveButton("Pay Online", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        openEditDataFragment();
                    }
                })
                .setNegativeButton("COD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int minOrderNumber = 1000;
                        int maxOrderNumber = 9999;
                        Random random = new Random();
                        int digit = random.nextInt((maxOrderNumber - minOrderNumber) + 1) + minOrderNumber;

                        String orderNumber = String.valueOf(digit);
                        DecimalFormat decimalFormat = new DecimalFormat("#.00");
                        double formattedValue = Double.parseDouble(decimalFormat.format(sum));

                        String isAccepted = "not Accepted";
                        Order order = new Order(orderNumber, itemName, count, formattedValue, imageUrl, isAccepted);
                        Log.e("MyApp", order.getItemName() + order.getOrderNumber() + order.getIsAccepted());

                        saveOrderToFirebase(order);
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void openEditDataFragment() {
        // Create an instance of the transparent fragment
        Payment_Fragment paymentFragment = new Payment_Fragment();
        // Add the fragment to the fragment manager
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(android.R.id.content, paymentFragment); // Use android.R.id.content to add the fragment above all views
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}