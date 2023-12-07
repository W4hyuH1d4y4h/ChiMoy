package com.example.chimoy.ui.keranjang;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.chimoy.R;
import com.example.chimoy.ui.produk.ProdukViewModel;

public class KeranjangFragment extends Fragment {

    private ProdukViewModel produkViewModel;
    private TextView totalHargaTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_keranjang, container, false);

        totalHargaTextView = view.findViewById(R.id.totalHarga);

        // Inisialisasi ViewModel
        produkViewModel = new ViewModelProvider(requireActivity()).get(ProdukViewModel.class);

        // Mengamati perubahan pada ViewModel (pilihan kartu)
        produkViewModel.getSelectedCard().observe(getViewLifecycleOwner(), new Observer<ProdukViewModel.CardSelection>() {
            @Override
            public void onChanged(ProdukViewModel.CardSelection cardSelection) {
                // Mengupdate tampilan sesuai dengan pilihan kartu yang dipilih
                updateTotalHarga(cardSelection);
            }
        });

        // Handling button click event
        Button masukkanButton = view.findViewById(R.id.btn_masukkan);
        masukkanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tambahkan logika untuk menangani masukkan produk
            }
        });

        return view;
    }

    private void updateTotalHarga(ProdukViewModel.CardSelection cardSelection) {
        // Tambahkan logika untuk menghitung total harga berdasarkan pilihan kartu
        // Update TextView totalHargaTextView dengan total yang dihitung
        totalHargaTextView.setText("Total : ..."); // Gantilah dengan total yang dihitung
    }
}
