package com.example.lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.lab6.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPreferences = this.getSharedPreferences("com.example.lab6", Context.MODE_PRIVATE);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kullaniciAdi = binding.editTextText.getText().toString();
                String adres = binding.editTextText.getText().toString();
                sharedPreferences.edit().putString("kullaniciAdi",kullaniciAdi).apply();
                sharedPreferences.edit().putString("adres",adres).apply();
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });


    }
}