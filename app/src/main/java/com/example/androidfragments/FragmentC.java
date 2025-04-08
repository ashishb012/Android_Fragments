package com.example.androidfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

public class FragmentC extends Fragment {
    private TextView messageFromActivityTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_c, container, false);
        messageFromActivityTextView = view.findViewById(R.id.messageFromActivityTextView);

        // Receive data from Activity
        getParentFragmentManager().setFragmentResultListener("dataFromActivity", this, (requestKey, result) -> {
            String message = result.getString("message");
            messageFromActivityTextView.setText("Message from Activity: " + message);
        });

        return view;
    }
}