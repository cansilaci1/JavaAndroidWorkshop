package com.example.shoppingecommerce.ui.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoppingecommerce.R;
import com.example.shoppingecommerce.databinding.FragmentAddProductBinding;


public class AddProductFragment extends Fragment {
    FragmentAddProductBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddProductBinding.inflate(inflater, container, false);

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProduct();
                navigateToMainPageFragment();
            }
        });

        return binding.getRoot();
    }

    private void saveProduct() {
        String productName = binding.editTextProductName.getText().toString();
        double productPrice = Double.parseDouble(binding.editTextPrice.getText().toString());

        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("PRODUCTS", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int count = sharedPreferences.getInt("COUNT", 0);
        editor.putString("PRODUCT_NAME_" + count, productName);
        editor.putFloat("PRODUCT_PRICE_" + count, (float) productPrice);
        editor.putInt("COUNT", count + 1);
        editor.apply();
    }

    private void navigateToMainPageFragment() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        navController.popBackStack(); // AddProductFragment'tan çık
    }
}
