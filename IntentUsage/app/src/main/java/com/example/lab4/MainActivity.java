package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editTextKilo;
    private EditText editTextBoy;
    private TextView textViewSonuc;
    private Button buttonHesapla;
    private Button buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextKilo = findViewById(R.id.editTextKilo);
        editTextBoy = findViewById(R.id.editTextBoy);
        textViewSonuc = findViewById(R.id.textViewOutput);
        buttonHesapla = findViewById(R.id.buttonHesapla);
        buttonNext = findViewById(R.id.buttonNext);

        buttonHesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float kilo = getKilo(editTextKilo);
                float boy = getBoy(editTextBoy);
                float endex = (10000* kilo) / (boy * boy);

                textViewSonuc.setText(String.valueOf(endex));
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float kilo = getKilo(editTextKilo);
                float boy = getBoy(editTextBoy);
                float endex = (10000* kilo) / (boy * boy);

                // Intent'i oluştur ve endex değerini ekleyerek DetailActivity'ye geçiş yap
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("endex", endex);
                startActivity(intent);
            }
        });


    }

    private int getKilo(EditText editText) {
        String numberString = editText.getText().toString();
        return Integer.parseInt(numberString);
    }

    private int getBoy(EditText editText) {
        String numberString = editText.getText().toString();
        return Integer.parseInt(numberString);
    }
}
