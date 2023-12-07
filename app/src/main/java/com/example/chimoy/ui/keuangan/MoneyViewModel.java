package com.example.chimoy.ui.keuangan;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MoneyViewModel extends ViewModel {

    public enum CardSelection {
        HARI_INI,
        MINGGU_INI,
        BULAN_INI
    }

    private MutableLiveData<String> mText;
    private MutableLiveData<CardSelection> selectedCard;

    public MoneyViewModel() {
        mText = new MutableLiveData<>();

        selectedCard = new MutableLiveData<>();
        selectedCard.setValue(CardSelection.HARI_INI); // Mengatur nilai awal
    }

    public LiveData<String> getText() {
        return mText;
    }

    // Getter untuk LiveData<CardSelection>
    public LiveData<CardSelection> getSelectedCard() {
        return selectedCard;
    }

    // Metode untuk mengatur pilihan kartu yang dipilih
    public void selectCard(CardSelection selection) {
        selectedCard.setValue(selection);
    }
}
