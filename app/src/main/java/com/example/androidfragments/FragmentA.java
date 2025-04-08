package com.example.androidfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;

public class FragmentA extends Fragment {
    private EditText fragmentAEditText;
    private SharedViewModel sharedViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);

        fragmentAEditText = view.findViewById(R.id.fragmentAEditText);
        Button sendToActivityButton = view.findViewById(R.id.sendToActivityButton);
        Button sendToFragmentButton = view.findViewById(R.id.sendToFragmentButton);

        // Get SharedViewModel
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        // Send data to Activity
        sendToActivityButton.setOnClickListener(v -> {
            String message = fragmentAEditText.getText().toString();
            Bundle result = new Bundle();
            result.putString("message", message);
            getParentFragmentManager().setFragmentResult("dataFromFragmentA", result);
        });

        // Send data to Fragment B
        sendToFragmentButton.setOnClickListener(v -> {
            String message = fragmentAEditText.getText().toString();
            sharedViewModel.sendMessage(message);
        });

        return view;
    }
}