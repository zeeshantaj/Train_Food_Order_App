package com.example.train_food_order_app.Login;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.train_food_order_app.MainActivity;
import com.example.train_food_order_app.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignUpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUpFragment newInstance(String param1, String param2) {
        SignUpFragment fragment = new SignUpFragment();
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

    private TextInputEditText userName,email,pass, conPass;
    private Button signUpBtn;
    private View view;
    private FirebaseAuth auth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view =inflater.inflate(R.layout.fragment_sign_up, container, false);

        userName = view.findViewById(R.id.signUpUserName);
        email = view.findViewById(R.id.singupEmail);
        pass = view.findViewById(R.id.signUpPass);
        conPass = view.findViewById(R.id.signUpConPass);
        signUpBtn = view.findViewById(R.id.signUpBtn);
        auth = FirebaseAuth.getInstance();

        signUpBtn.setOnClickListener((View)->{
            String name = userName.getText().toString();
            String email1 = email.getText().toString();
            String pass1 = pass.getText().toString();
            String conPass1 = conPass.getText().toString();
            if (name.isEmpty()){
                userName.setError("UserName field is empty");
                return;
            }if (email1.isEmpty()){
                email.setError("UserName field is empty");
                return;
            }if (pass1.isEmpty()){
                pass.setError("UserName field is empty");
                return;
            }if (conPass1.isEmpty()){
                conPass.setError("UserName field is empty");
                return;
            }
            if (!pass1.equals(conPass1)){
                Toast.makeText(getActivity(), "Password and Confirm Password should match", Toast.LENGTH_LONG).show();
                return;
            }
            else {
                auth.createUserWithEmailAndPassword(email1, pass1).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Error while Creating account\n"+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        return view;
    }
}