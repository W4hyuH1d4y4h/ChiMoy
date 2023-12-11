package com.example.chimoy.ui.menu;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.chimoy.R;
import com.example.chimoy.ui.configurasi.Configurasi;
import com.google.android.material.textfield.TextInputEditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Date;
import java.net.URLEncoder;

public class TambahMenu extends AppCompatActivity {
    private static final int REQUEST_PICK_IMAGE = 1;
    Toolbar toolbar;
    TextInputEditText ip_addmenu, ip_addharga;
    ImageView iv_addimage;
    Button btn_choseimage;
    private Configurasi configurasi;
    private Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_menu);
        configurasi = new Configurasi();

        toolbar = findViewById(R.id.add_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        iv_addimage = findViewById(R.id.iv_addimage);
        btn_choseimage = findViewById(R.id.btn_choseimage);
        ip_addmenu = findViewById(R.id.ip_addmenu);
        ip_addharga = findViewById(R.id.ip_addharga);

        Button btnTambahMenu = findViewById(R.id.btn_menubaru);
        btn_choseimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Panggil metode untuk memilih gambar
                pilihGambar();
            }
        });

        btnTambahMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidForm()) {
                    // Lakukan tindakan tambah menu
                    try {
                        tambahMenu();
                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    private boolean isValidForm() {
        boolean isValid = true;

        String namaMenu = ip_addmenu.getText().toString().trim();
        String hargaMenu = ip_addharga.getText().toString().trim();

        if (namaMenu.isEmpty()) {
            isValid = false;
            ip_addmenu.setError("Nama menu harus diisi");
        }

        if (hargaMenu.isEmpty()) {
            isValid = false;
            ip_addharga.setError("Harga menu harus diisi");
        }

        return isValid;
    }
    private void pilihGambar() {
        // Implementasikan logika untuk memilih gambar dari galeri
        // Anda dapat menggunakan Intent untuk membuka galeri dan memilih gambar
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_PICK_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();

            // Set gambar ke ImageView
            iv_addimage.setImageURI(selectedImageUri);
        }
    }

    private void tambahMenu() throws UnsupportedEncodingException {
        String namaMenu = ip_addmenu.getText().toString().trim();
        String hargaMenu = ip_addharga.getText().toString().trim();
        String idGambar = Long.toString(new Date().getTime());

        // Mendapatkan baseUrl dari objek configurasi
        String baseUrl = configurasi.baseUrl() + "simpan.php";

        // Lakukan operasi jaringan di thread latar belakang
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Buat URL untuk permintaan HTTP
                    URL url = new URL(baseUrl);

                    // Buka koneksi HTTP
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                    try {
                        // Set metode permintaan ke POST
                        urlConnection.setRequestMethod("POST");
                        urlConnection.setDoOutput(true);

                        // Format data yang akan dikirimkan
                        String postData = "nama_produk=" + URLEncoder.encode(namaMenu, "UTF-8") +
                                "&harga_produk=" + URLEncoder.encode(hargaMenu, "UTF-8") +
                                "&id_gambar=" + idGambar;

                        // Kirim data ke server
                        OutputStream outputStream = urlConnection.getOutputStream();
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                        writer.write(postData);
                        writer.flush();
                        writer.close();
                        outputStream.close();

                        // Baca respons dari server
                        InputStream inputStream = urlConnection.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                        StringBuilder response = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }
                        reader.close();

                        // Tampilkan respons di thread antarmuka pengguna (UI)
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(TambahMenu.this, "Respons dari server: " + response.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    } finally {
                        // Tutup koneksi
                        urlConnection.disconnect();
                    }
                } catch (ProtocolException | MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
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