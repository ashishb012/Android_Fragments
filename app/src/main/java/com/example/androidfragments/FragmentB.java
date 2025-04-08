package com.example.androidfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class FragmentB extends Fragment {
    private TextView messageFromFragmentATextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, container, false);

        messageFromFragmentATextView = view.findViewById(R.id.messageFromFragmentATextView);

        // Get SharedViewModel
        SharedViewModel sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        // Observe messages from Fragment A
        sharedViewModel.getMessage().observe(getViewLifecycleOwner(), message -> {
            messageFromFragmentATextView.setText("Message from Fragment A: " + message);
        });

        return view;
    }
}