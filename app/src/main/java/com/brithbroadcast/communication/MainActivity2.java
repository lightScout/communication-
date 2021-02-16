package com.brithbroadcast.communication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static com.brithbroadcast.communication.MainActivity.BUNDLE_KEY;
import static com.brithbroadcast.communication.MainActivity.INTENT_MESSAGE_CODE;

public class MainActivity2 extends AppCompatActivity {

    private TextView conversationTextView;
    private EditText messageEditText;
    private Button sendButton;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        conversationTextView = findViewById(R.id.conversation2_textview);
        messageEditText = findViewById(R.id.message2_edittext);
        sendButton = findViewById(R.id.send2_button);

        // Extracting date coming from intent bundle
        message = getIntent().getStringExtra(BUNDLE_KEY);
        if (message != null) {
            conversationTextView.setText(getString(R.string.conversation2_text, "Message from MA1: ", message));
            messageEditText.setText("");
        }

        // <- onClick Section ->

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                String message = messageEditText.getText().toString().trim();
                // Staging result intent bundle with message
                resultIntent.putExtra(BUNDLE_KEY, message);
                // Setting result intent
                setResult(INTENT_MESSAGE_CODE, resultIntent);
                finish(); // OnDestroy will be called without the OnStop - onStop is skipped


            }
        });
    }
}