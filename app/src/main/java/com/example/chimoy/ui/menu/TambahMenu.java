package com.example.chimoy.ui.menu;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.chimoy.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TambahMenu extends AppCompatActivity {
    TextInputEditText harga, nama;

    Button btn_tambahdata;

    FirebaseDatabase database;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_menu);

        harga = findViewById(R.id.ip_addharga);
        nama = findViewById(R.id.ip_addmenu);
        btn_tambahdata = findViewById(R.id.btn_menubaru);

        btn_tambahdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("pemesanan");

                String namabarang = nama.getText().toString();
                String hargabarang = harga.getText().toString();

                System.out.println(namabarang);
                System.out.println(hargabarang);

                String id = RandomId(10);

                ModelMenu modelMenu = new ModelMenu(namabarang, hargabarang);
                reference.child(id).setValue(modelMenu);

                Toast.makeText(getApplicationContext(), "Data Menu Telah Berhasil Bertambah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static String RandomId(int length) {
        String character = "abcdefghijklmnopqrstuvwxyz123456789";
        char[] random = new char[length];

        for (int i = 0; i < length; i++) {
            random[i] = character.charAt((int) Math.floor(Math.random() * character.length()));
        }

        return new String(random);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}