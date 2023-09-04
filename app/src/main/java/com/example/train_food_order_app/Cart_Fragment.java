package com.example.train_food_order_app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.train_food_order_app.Adapter.OrderAdapter;
import com.example.train_food_order_app.Models.OrderInfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Cart_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Cart_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Cart_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Cart_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Cart_Fragment newInstance(String param1, String param2) {
        Cart_Fragment fragment = new Cart_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private RecyclerView recyclerView;
    private List<OrderInfo> orderInfoList;
    private OrderAdapter adapter;
    private Toolbar toolbar;

    // Find the specific menu item by its ID
    private MenuItem menuItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart_, container, false);
        toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Order Details");
        menuItem = toolbar.getMenu().findItem(R.id.orderCart);
        recyclerView = view.findViewById(R.id.orderRecycler);
        orderInfoList = new ArrayList<>();

        setData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        adapter = new OrderAdapter(orderInfoList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        hideMenuItem();

        return view;
    }


    private void setData(){

        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uid = auth.getCurrentUser().getUid();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Orders").child(uid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    orderInfoList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                        String name = dataSnapshot.child("itemName").getValue(String.class);
                        String imageUrl = dataSnapshot.child("imageUrl").getValue(String.class);
                        String orderNumber = dataSnapshot.child("orderNumber").getValue(String.class);
                        String isAccepted = dataSnapshot.child("isAccepted").getValue(String.class);
                        double totalAmount = dataSnapshot.child("totalAmount").getValue(Double.class);
                        int quantity = dataSnapshot.child("quantity").getValue(Integer.class);
                       // int qtr = dataSnapshot.child("quantity").getValue(Integer.class);
                        OrderInfo orderInfo = new OrderInfo(name, imageUrl, orderNumber, isAccepted, totalAmount,quantity);
                        orderInfoList.add(orderInfo);

                    }
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(getActivity(), "You didn't order anything yet\nwhen you make order\nyour order history will be shown here", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("MyApp", error.getMessage());
            }
        });
    }
    private void hideMenuItem() {
        // Access the MainActivity's Toolbar

        // Hide the menu item
        if (menuItem != null) {
            menuItem.setVisible(false);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        menuItem.setVisible(true);

    }

}