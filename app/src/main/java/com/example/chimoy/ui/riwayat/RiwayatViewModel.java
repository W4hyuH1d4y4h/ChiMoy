package com.example.chimoy.ui.riwayat;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class RiwayatViewModel extends ViewModel {
    // Transaction.java
    public static class Transaction {
        private String date;
        private int transactionCount;
        private double totalAmount;

        public void setDate(String s) {
        }

        public void setTransactionCount(int i) {
        }

        public void setTotalAmount(double v) {
        }

        // Constructors, getters, and setters
    }

    private final MutableLiveData<String> mText;
    private final MutableLiveData<List<Transaction>> transactions;

    public RiwayatViewModel() {
        mText = new MutableLiveData<>();

        transactions = new MutableLiveData<>();
        transactions.setValue(new ArrayList<>());
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<Transaction>> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        List<Transaction> currentTransactions = transactions.getValue();
        if (currentTransactions != null) {
            currentTransactions.add(transaction);
            transactions.setValue(currentTransactions);
        }
    }
}