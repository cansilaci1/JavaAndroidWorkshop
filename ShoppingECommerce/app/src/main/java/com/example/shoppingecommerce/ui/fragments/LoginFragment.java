package com.example.shoppingecommerce.ui.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.shoppingecommerce.R;
import com.example.shoppingecommerce.databinding.FragmentLoginBinding;


public class LoginFragment extends Fragment {
    FragmentLoginBinding binding;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        sharedPreferences = getActivity().getSharedPreferences("USER_CREDENTIALS", Context.MODE_PRIVATE);

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String savedUsername = sharedPreferences.getString("USERNAME", "");
                String savedPassword = sharedPreferences.getString("PASSWORD", "");

                String enteredUsername = binding.editTextLoginName.getText().toString();
                String enteredPassword = binding.editTextLoginPassword.getText().toString();

                if (savedUsername.equals(enteredUsername) && savedPassword.equals(enteredPassword)) {
                    Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_mainPageFragment);
                    Toast.makeText(getActivity(), "Login successful!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }
}