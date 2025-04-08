package com.example.androidfragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView activityTextView;
    private EditText activityEditText;
    private Button sendToFragmentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityTextView = findViewById(R.id.activityTextView);
        activityEditText = findViewById(R.id.activityEditText);
        sendToFragmentButton = findViewById(R.id.sendToFragmentButton);

        Button fragmentToActivityButton = findViewById(R.id.fragmentToActivityButton);
        Button activityToFragmentButton = findViewById(R.id.activityToFragmentButton);
        Button fragmentToFragmentButton = findViewById(R.id.fragmentToFragmentButton);

        // Receive data from Fragment A
        getSupportFragmentManager().setFragmentResultListener("dataFromFragmentA", this, (requestKey, result) -> {
            String message = result.getString("message");
            activityTextView.setText("Message from Fragment A: " + message);
            activityTextView.setVisibility(View.VISIBLE);
        });

        //send data to Fragment C
        sendToFragmentButton.setOnClickListener(v -> {
            String message = activityEditText.getText().toString();
            Bundle result = new Bundle();
            result.putString("message", message);
            getSupportFragmentManager().setFragmentResult("dataFromActivity", result);
        });

        // Navigate to Fragment A
        fragmentToActivityButton.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new FragmentA())
                    .commit();
            activityTextView.setVisibility(View.GONE);
            sendToFragmentButton.setVisibility(View.GONE);
            activityEditText.setVisibility(View.GONE);

        });

        // Navigate to Fragment C
        activityToFragmentButton.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new FragmentC())
                    .commit();
            sendToFragmentButton.setVisibility(View.VISIBLE);
            activityEditText.setVisibility(View.VISIBLE);
        });

        // Navigate to Fragment B
        fragmentToFragmentButton.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new FragmentB())
                    .commit();
            sendToFragmentButton.setVisibility(View.GONE);
            activityEditText.setVisibility(View.GONE);
            activityTextView.setVisibility(View.GONE);
        });
    }
}