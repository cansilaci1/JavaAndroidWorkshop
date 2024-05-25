package com.example.shoppingecommerce.ui.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import com.example.shoppingecommerce.R;
import com.example.shoppingecommerce.data.entity.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class MainPageAdapter extends RecyclerView.Adapter<MainPageAdapter.ViewHolder> {
    private List<Product> productList;

    public MainPageAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public MainPageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_page_card_design, parent, false);
        return new ViewHolder(view, productList);
    }

    @Override
    public void onBindViewHolder(@NonNull MainPageAdapter.ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageItem;
        private TextView tvPrice;
        private TextView name;
        private FloatingActionButton floatingActionButton;
        private FloatingActionButton btnDelete;
        private List<Product> productList;

        public ViewHolder(@NonNull View itemView, List<Product> productList) {
            super(itemView);
            this.productList = productList;
            imageItem = itemView.findViewById(R.id.imageItem);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            name = itemView.findViewById(R.id.tvDescription);
            floatingActionButton = itemView.findViewById(R.id.btnUpdateFragment);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }

        public void bind(Product product) {
            imageItem.setImageResource(product.getImageResId());
            tvPrice.setText(String.valueOf(product.getPrice()) + "₺");
            name.setText(product.getName());
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Product product = productList.get(getAdapterPosition());
                    Bundle bundle = new Bundle();
                    bundle.putString("productName", product.getName());
                    bundle.putFloat("productPrice", product.getPrice());
                    Navigation.findNavController(v).navigate(R.id.action_mainPageFragment_to_updateFragment, bundle);
                }
            });
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        // Seçilen ürünü al
                        Product product = productList.get(position);
                        // SharedPreferences'i al
                        SharedPreferences sharedPreferences = v.getContext().getSharedPreferences("PRODUCTS", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        // Ürünü sharedPreferences'ten sil
                        int count = sharedPreferences.getInt("COUNT", 0);
                        for (int i = 0; i < count; i++) {
                            if (sharedPreferences.getString("PRODUCT_NAME_" + i, "").equals(product.getName()) &&
                                    sharedPreferences.getFloat("PRODUCT_PRICE_" + i, 0) == product.getPrice()) {
                                editor.remove("PRODUCT_NAME_" + i);
                                editor.remove("PRODUCT_PRICE_" + i);
                                break;
                            }
                        }
                        // COUNT'u güncelle
                        editor.putInt("COUNT", count - 1);
                        editor.apply();

                        // RecyclerView'i güncelle
                        productList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, productList.size());
                    }
                }
            });

        }
    }
}