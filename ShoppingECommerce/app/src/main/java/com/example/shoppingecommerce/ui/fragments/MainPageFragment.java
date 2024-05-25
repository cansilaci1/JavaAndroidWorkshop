package com.example.shoppingecommerce.ui.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.shoppingecommerce.R;
import com.example.shoppingecommerce.data.entity.Product;
import com.example.shoppingecommerce.databinding.ActivityMainBinding;
import com.example.shoppingecommerce.databinding.FragmentMainPageBinding;
import com.example.shoppingecommerce.ui.adapter.MainPageAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainPageFragment extends Fragment {
    FragmentMainPageBinding binding;
    private RecyclerView recyclerView;
    private MainPageAdapter adapter;
    private List<Product> productList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMainPageBinding.inflate(inflater, container, false);

        recyclerView = binding.rvItems;
        productList = new ArrayList<>();

        loadProducts(); // productList'in initialize edilmesinden sonra ürünleri yükle

        adapter = new MainPageAdapter(productList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        binding.fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
                navController.navigate(R.id.action_mainPageFragment_to_addProductFragment);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadProducts(); // Fragment yeniden aktif olduğunda ürünleri tekrar yükle
        adapter.notifyDataSetChanged(); // Adapter'a veri değişikliğini bildir
    }

    private void loadProducts() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("PRODUCTS", Context.MODE_PRIVATE);
        int count = sharedPreferences.getInt("COUNT", 0);
        productList.clear();
        for (int i = 0; i < count; i++) {
            String productName = sharedPreferences.getString("PRODUCT_NAME_" + i, "");
            float productPrice = sharedPreferences.getFloat("PRODUCT_PRICE_" + i, 0);
            productList.add(new Product(productName, productPrice, R.drawable.ic_launcher_background));
        }
    }
}
