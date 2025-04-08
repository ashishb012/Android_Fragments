package com.example.androidfragments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<String> message = new MutableLiveData<>();

    public void sendMessage(String text) {
        message.setValue(text);
    }

    public LiveData<String> getMessage() {
        return message;
    }
}