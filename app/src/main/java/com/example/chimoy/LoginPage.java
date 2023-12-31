package com.example.chimoy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chimoy.ui.home.HomeFragment;

public class LoginPage extends AppCompatActivity {
    // Deklarasikan elemen-elemen UI, seperti outletEditText dan karyawanEditText, di sini
    private EditText outletEditText;
    private EditText karyawanEditText;
    private Button btn_Masuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        // Inisialisasi elemen UI, seperti outletEditText dan karyawanEditText, di sini
        outletEditText = findViewById(R.id.outlet);
        karyawanEditText = findViewById(R.id.karyawan);

        btn_Masuk = findViewById(R.id.btn_Masuk);
        btn_Masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dapatkan nilai outlet dan karyawan dari input pengguna
                String outlet = outletEditText.getText().toString().trim();
                String karyawan = karyawanEditText.getText().toString().trim();



                Intent intent = new Intent(LoginPage.this, MainActivity.class);
                intent.putExtra("outlet", outlet);
                intent.putExtra("karyawan", karyawan);
                startActivity(intent);
            }
        });
    }
}
