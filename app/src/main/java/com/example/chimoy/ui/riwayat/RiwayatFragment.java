package com.example.chimoy.ui.riwayat;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chimoy.R;
import com.example.chimoy.databinding.FragmentHomeBinding;
import com.example.chimoy.databinding.FragmentRiwayatBinding;
import com.example.chimoy.ui.home.HomeViewModel;

// RiwayatFragment.java
public class RiwayatFragment extends Fragment {

    private FragmentRiwayatBinding binding;
        public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_riwayat, container, false);
        RiwayatViewModel riwayatViewModel =
                new ViewModelProvider(this).get(RiwayatViewModel.class);

        binding = FragmentRiwayatBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textRiwayat;
        riwayatViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // Tambahkan Observer untuk riwayat transaksi
        riwayatViewModel.getTransactions().observe(getViewLifecycleOwner(), transactions -> {
            // Gunakan data transactions untuk meng-update tampilan riwayat
            // Misalnya, gunakan RecyclerView atau TextView sesuai kebutuhan Anda
        });

        // Contoh menambahkan transaksi
        RiwayatViewModel.Transaction sampleTransaction = new RiwayatViewModel.Transaction();
        sampleTransaction.setDate("2023-12-07");
        sampleTransaction.setTransactionCount(2);
        sampleTransaction.setTotalAmount(60000.0);

        riwayatViewModel.addTransaction(sampleTransaction);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
