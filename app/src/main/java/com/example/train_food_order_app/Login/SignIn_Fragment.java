package com.example.train_food_order_app.Login;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.train_food_order_app.Admin_Activity.AdminActivity;
import com.example.train_food_order_app.FragmentUtils;
import com.example.train_food_order_app.MainActivity;
import com.example.train_food_order_app.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignIn_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignIn_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignIn_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignIn_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignIn_Fragment newInstance(String param1, String param2) {
        SignIn_Fragment fragment = new SignIn_Fragment();
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
    private Button signUpBtn;
    private FrameLayout parentLayout;
    private TextInputEditText loginEmail, password;
    private Button loginBtn;
    private FirebaseAuth auth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in_, container, false);
        signUpBtn = view.findViewById(R.id.dontHaveAccount);
        loginBtn = view.findViewById(R.id.loginBtn);
        loginEmail = view.findViewById(R.id.loginEmail);
        password = view.findViewById(R.id.loginPassword);
        auth = FirebaseAuth.getInstance();
        parentLayout = getActivity().findViewById(R.id.parentFrameLayout);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        signUpBtn.setOnClickListener((View)->{
            FragmentUtils.setFragment(fragmentManager,parentLayout.getId(),new SignUpFragment());
        });

        setData();
        return view;

    }

    private void setData() {
        loginBtn.setOnClickListener((View) -> {
            String email = loginEmail.getText().toString();
            String pass = password.getText().toString();
            if (email.isEmpty()) {
                loginEmail.setError("Email field is empty");
                return;
            }
            if (pass.isEmpty()) {
                password.setError("Password field is empty");
                return;
            }

            if (email.equals("admin")&&pass.equals("12345")){
                Intent intent = new Intent(getActivity(), AdminActivity.class);
                startActivity(intent);
            }

                auth.signInWithEmailAndPassword(email, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Error Occur " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        });
    }
}