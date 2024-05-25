package com.example.mobilprogramlamahafta15app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mobilprogramlamahafta15app.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    private int clickCount = 0;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnGizle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.btnGizle.setVisibility(View.GONE);
            }
        });

        binding.btnGoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.btnGizle.setVisibility(View.VISIBLE);
            }
        });

        binding.chip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Chip'e Tıkladın!!", Toast.LENGTH_SHORT).show();
            }
        });

        binding.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.gazi.edu.tr"));
                startActivity(intent);
            }
        });

        binding.floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCount++;
                Snackbar.make(binding.getRoot(),"faba"+ clickCount + "kez tıklandı", Snackbar.LENGTH_SHORT).show();
            }
        });

        binding.switch1.setOnCheckedChangeListener(((buttonView, isChecked) -> {
            binding.imageButton.setEnabled(isChecked);
        }));

        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(binding.getRoot(), "http://www.gazi.edu.tr",Snackbar.LENGTH_SHORT).show();
            }
        });

        }
    }