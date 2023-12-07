package com.example.chimoy.ui.produk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.chimoy.R;

public class ProdukFragment extends Fragment {
    private CardView cardViewHariIni;
    private CardView cardViewMingguIni;
    private CardView cardViewBulanIni;
    private ProdukViewModel produkViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_produk, container, false);

        cardViewHariIni = view.findViewById(R.id.cardViewHariIni);
        cardViewMingguIni = view.findViewById(R.id.cardViewMingguIni);
        cardViewBulanIni = view.findViewById(R.id.cardViewBulanIni);

        // Inisialisasi ViewModel
        produkViewModel = new ViewModelProvider(this).get(ProdukViewModel.class);

        // Mengamati perubahan status dari ViewModel
        produkViewModel.getSelectedCard().observe(getViewLifecycleOwner(), selectedCard -> {
            // Mengubah warna card sesuai dengan pilihan yang dipilih di ViewModel
            cardViewHariIni.setCardBackgroundColor(
                    getResources().getColor(selectedCard == ProdukViewModel.CardSelection.HARI_INI ?
                            R.color.kuningJadi : R.color.kuningPucat));
            cardViewMingguIni.setCardBackgroundColor(
                    getResources().getColor(selectedCard == ProdukViewModel.CardSelection.MINGGU_INI ?
                            R.color.kuningJadi : R.color.kuningPucat));
            cardViewBulanIni.setCardBackgroundColor(
                    getResources().getColor(selectedCard == ProdukViewModel.CardSelection.BULAN_INI ?
                            R.color.kuningJadi : R.color.kuningPucat));
        });

        // Menanggapi klik pada CardViews dan memberi tahu ViewModel
        cardViewHariIni.setOnClickListener(v -> produkViewModel.selectCard(ProdukViewModel.CardSelection.HARI_INI));
        cardViewMingguIni.setOnClickListener(v -> produkViewModel.selectCard(ProdukViewModel.CardSelection.MINGGU_INI));
        cardViewBulanIni.setOnClickListener(v -> produkViewModel.selectCard(ProdukViewModel.CardSelection.BULAN_INI));

        return view;
    }
}
