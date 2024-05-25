package com.example.lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.lab6.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    ActivitySecondBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPreferences = this.getSharedPreferences("com.example.lab6", Context.MODE_PRIVATE);

        String kullaniciAdi = sharedPreferences.getString("kullaniciAdi", "");
        String adres = sharedPreferences.getString("adres", "");

        binding.textView.setText("Kullanıcı Adı: " + kullaniciAdi + "\nAdres: " + adres);

        binding.buttonSil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().clear().apply();
                Toast.makeText(SecondActivity.this, "Tüm veriler silindi", Toast.LENGTH_SHORT).show();
                binding.textView.setText("");

            }
        });

    }

}