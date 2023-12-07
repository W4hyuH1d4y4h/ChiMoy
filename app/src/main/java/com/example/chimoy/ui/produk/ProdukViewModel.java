package com.example.chimoy.ui.produk;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProdukViewModel extends ViewModel {

    // Menambahkan enumerasi untuk merepresentasikan pilihan kartu
    public enum CardSelection {
        HARI_INI,
        MINGGU_INI,
        BULAN_INI
    }

    private final MutableLiveData<CardSelection> selectedCard;

    public ProdukViewModel() {
        selectedCard = new MutableLiveData<>();
        selectedCard.setValue(CardSelection.HARI_INI); // Kartu Hari Ini dipilih secara default
    }

    public LiveData<CardSelection> getSelectedCard() {
        return selectedCard;
    }

    // Metode untuk memilih kartu dan mengubah LiveData
    public void selectCard(CardSelection cardSelection) {
        selectedCard.setValue(cardSelection);
    }
}
