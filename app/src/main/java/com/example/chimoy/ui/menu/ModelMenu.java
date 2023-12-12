package com.example.chimoy.ui.menu;

public class ModelMenu {
    private String nama_menu, harga_menu;

    public String getNama_menu() {
        return nama_menu;
    }

    public void setNama_menu(String nama_menu) {
        this.nama_menu = nama_menu;
    }

    public String getHarga_menu() {
        return harga_menu;
    }

    public void setHarga_menu(String harga_menu) {
        this.harga_menu = harga_menu;
    }

    public ModelMenu(String nama_menu, String harga_menu) {
        this.nama_menu = nama_menu;
        this.harga_menu = harga_menu;
    }
}
