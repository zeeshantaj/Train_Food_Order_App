package com.example.train_food_order_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.CursorJoiner;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.train_food_order_app.Models.ChildText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuSelection_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuSelection_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MenuSelection_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuSelection_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuSelection_Fragment newInstance(String param1, String param2) {
        MenuSelection_Fragment fragment = new MenuSelection_Fragment();
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

    private CardView lunch;
    private CardView breakFast;
    private CardView snack;
    private CardView dinner;
    private TextView din;
    private Intent intent;
    private FrameLayout frameLayout;
    private FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_selection_, container, false);

        dinner = view.findViewById(R.id.dinnerView);
        lunch = view.findViewById(R.id.cardViewLunch);
        breakFast = view.findViewById(R.id.cardViewBreakFast);
        snack = view.findViewById(R.id.cardViewSnack);
        frameLayout = getActivity().findViewById(R.id.loginFrameLayout);

        fragmentManager = getActivity().getSupportFragmentManager();


        dinner.setOnClickListener((View)->{


            String name = "dinner";
            SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("data_key",name);
            editor.apply();
            getFragment();
        });
        lunch.setOnClickListener((View)->{
            String name = "lunch";
            SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("data_key",name);
            editor.apply();
            getFragment();

        });
        breakFast.setOnClickListener((View)->{
            String name = "breakFast";
            SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("data_key",name);
            editor.apply();
            getFragment();
        });
        snack.setOnClickListener((View)->{
            String name = "snack";
            SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("data_key",name);
            editor.apply();
            getFragment();

        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void getFragment(){
        FragmentUtils.setFragment(fragmentManager,frameLayout.getId(),new MenuShow_Fragment());
    }
    public void onBackPressed() {
        // Handle back button press in MenuShowFragment
        requireActivity().finish();
    }

//    private void setFragment(Fragment fragment){
//        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//        //fragmentTransaction.setCustomAnimations(R.anim.slide_from_left,R.anim.slideout_from_right);
//        fragmentTransaction.replace(frameLayout.getId(),fragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
//    }

}