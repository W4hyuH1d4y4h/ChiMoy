package com.example.chimoy.ui.adapter;

//import static android.os.Build.VERSION_CODES.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.chimoy.R;
import java.util.ArrayList;
import java.util.List;

public class AdapterMenu extends ArrayAdapter<GetDataProduk> {
    private Context mContext;
    private int mResource;

    public AdapterMenu(@NonNull Context context, int resource, @NonNull List<GetDataProduk> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource, parent, false);
        }

        // Dapatkan data produk pada posisi tertentu
        GetDataProduk produk = getItem(position);

        // Setel nilai TextViews atau elemen lainnya sesuai dengan data produk
        ImageView ivMakanan = convertView.findViewById(R.id.iv_makanan);
        TextView tvNamaMakanan = convertView.findViewById(R.id.tv_namaMakanan);
        TextView tvHargaMakanan = convertView.findViewById(R.id.tv_hargaMakanan);
        Button btnMakanan = convertView.findViewById(R.id.btn_Makanan);

        // Setel nilai sesuai dengan data produk
        ivMakanan.setImageResource(produk.getGambar());
        tvNamaMakanan.setText(produk.getNama_produk());
        tvHargaMakanan.setText(produk.getHarga_produk());

        // Tambahkan kode untuk elemen lain sesuai kebutuhan

        return convertView;
    }
}