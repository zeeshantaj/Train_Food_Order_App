package com.example.train_food_order_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.train_food_order_app.Adapter.MenuAdapter;
import com.example.train_food_order_app.Models.ChildText;
import com.example.train_food_order_app.Models.MenuItems;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class MenuShow_Fragment extends Fragment {



    public MenuShow_Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private RecyclerView foodRecycler;
    private MenuAdapter adapter;
    private List<MenuItems> list;
    private View view;
    private SharedPreferences sharedPreferences;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_menu_show_, container, false);
        foodRecycler = view.findViewById(R.id.foodRecyclerView);
        list = new ArrayList<>();
//        list.add(new MenuItems("pizza1","pizza with extra chezz and olives",10.88,R.drawable.done));
//        list.add(new MenuItems("pizza2","pizza with extra chezz and olives",16.88,R.drawable.done));
//        list.add(new MenuItems("pizza3","pizza with extra chezz and olives",15.88,R.drawable.done));
//        list.add(new MenuItems("pizza4","pizza with extra chezz and olives",41.88,R.drawable.done));
//        list.add(new MenuItems("pizza5","pizza with extra chezz and olives",31.88,R.drawable.done));
//        list.add(new MenuItems("pizza6","pizza with extra chezz and olives",1.88,R.drawable.done));
//        list.add(new MenuItems("pizza7","pizza with extra chezz and olives",12.88,R.drawable.done));
//        list.add(new MenuItems("pizza8","pizza with extra chezz and olives",12.88,R.drawable.done));
//        list.add(new MenuItems("pizza9","pizza with extra chezz and olives",18.88,R.drawable.done));
//        list.add(new MenuItems("pizza10","pizza with extra chezz and olives",16.88,R.drawable.done));
//        list.add(new MenuItems("pizza11","pizza with extra chezz and olives",14.88,R.drawable.done));
//        list.add(new MenuItems("pizza12","pizza with extra chezz and olives",12.88,R.drawable.done));
//        list.add(new MenuItems("pizza13","pizza with extra chezz and olives",13.88,R.drawable.done));


        adapter = new MenuAdapter(list, new MenuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MenuItems items) {
                Toast.makeText(getActivity(), "Clicked: " + items.getItemName(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(),FoodDetailsActivity.class);
                intent.putExtra("item_name", items.getItemName());
                intent.putExtra("item_description", items.getDescription());
                intent.putExtra("item_price", items.getPrice());
                intent.putExtra("item_Image", items.getImageUrl());


                startActivity(intent);

            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        foodRecycler.setLayoutManager(gridLayoutManager);

        foodRecycler.setAdapter(adapter);
        getDatabaseMenu();
        return view;
    }

    private void getDatabaseMenu(){

        sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String receivedData = sharedPreferences.getString("data_key", "");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("MenuDetails").child(receivedData);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    list.clear();
                    for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                        // Assuming each menu detail is stored as a MenuItem object in Firebase
                        MenuItems menuItem = itemSnapshot.getValue(MenuItems.class);
                        list.add(menuItem);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        }

    @Override
    public void onStop() {
        super.onStop();
        //Log.e("onStop", "Called");
    }


}