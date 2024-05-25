package com.example.shoppingecommerce.ui.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.shoppingecommerce.databinding.FragmentUpdateBinding;

public class UpdateFragment extends Fragment {
    FragmentUpdateBinding binding;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentUpdateBinding.inflate(inflater, container, false);
        sharedPreferences = requireActivity().getSharedPreferences("PRODUCTS", Context.MODE_PRIVATE);

        // Önceki fragment'ten gelen verileri al
        Bundle bundle = getArguments();
        if (bundle != null) {
            String productName = bundle.getString("productName", "");
            float productPrice = bundle.getFloat("productPrice", 0);
            binding.editTextUpdateName.setText(productName);
            binding.editTextUpdatePrice.setText(String.valueOf(productPrice));
        }

        // Kaydet düğmesine tıklandığında
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUpdatedProduct();
                requireActivity().onBackPressed(); // Fragmentı geri al
            }
        });

        return binding.getRoot();
    }

    // Güncellenmiş ürünü kaydetme
    private void saveUpdatedProduct() {
        String productName = binding.editTextUpdateName.getText().toString();
        float productPrice = Float.parseFloat(binding.editTextUpdatePrice.getText().toString());

        // Önceki fragment'ten gelen verileri güncelle
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("PRODUCT_NAME_" + getArguments().getInt("position"), productName);
        editor.putFloat("PRODUCT_PRICE_" + getArguments().getInt("position"), productPrice);
        editor.apply();
    }
}
