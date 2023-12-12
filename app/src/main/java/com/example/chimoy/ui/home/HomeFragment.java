package com.example.chimoy.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chimoy.R;
import com.example.chimoy.databinding.FragmentHomeBinding;
import com.example.chimoy.ui.adapter.AdapterMenu;
import com.example.chimoy.ui.adapter.GetDataProduk;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private AdapterMenu adapter;
    private List<GetDataProduk> dataList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Inisialisasi ListView
        recyclerView = root.findViewById(R.id.tv_listmenu);

        // Inisialisasi Data dan Adapter
        dataList = new ArrayList<>();

        // Ambil data dari database dan tambahkan ke dataList
        fetchDataFromDatabase();

        adapter = new AdapterMenu(requireContext(), R.layout.activity_item_menu, dataList);

        // Set adapter ke ListView
//        recyclerView.setAdapter(adapter);

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    private void fetchDataFromDatabase() {
        // Lakukan permintaan ke server atau database untuk mengambil data produk
        // Gunakan kelas atau metode tertentu sesuai dengan implementasi Anda
        // Contoh pengisian data statis:
        dataList.add(new GetDataProduk("Item 1", R.drawable.ic_launcher_foreground, "Rp. 10.000"));
        dataList.add(new GetDataProduk("Item 2", R.drawable.ic_launcher_foreground, "Rp. 15.000"));
        // Tambahkan data produk sesuai dengan hasil permintaan dari server/database
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
