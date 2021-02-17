package com.brithbroadcast.communication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String BUNDLE_KEY = "MESSAGE_KEY";
    // static value used for intent tracking
    public static int INTENT_MESSAGE_CODE = 111;
    private TextView conversationTextView;
    private EditText messageEditText;
    private Button sendButton;
    private String message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Binding views to variables
        conversationTextView = findViewById(R.id.conversation1_textview);
        messageEditText = findViewById(R.id.message1_edittext);
        sendButton = findViewById(R.id.send1_button);

        // Setting up initial UI
        conversationTextView.setText(getString(R.string.conversation1_text, "", ""));


        // <- onClick Section ->


        // When clicked, the app will navigate to main_activity2
        // passing the information(message) typed on messageEditText inside the intent bundle
        // to be accessible and later on manipulated on main_activity2
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Instantiating the intent
                // intent takes in the initial activity context and the activity class from
                // the next activity to be displayed
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                // Grabbing type message from editText
                String message = messageEditText.getText().toString().trim();
                // Staging intent bundle with type message
                intent.putExtra(BUNDLE_KEY, message);
                // Starting activity from intent
                startActivityForResult(intent, INTENT_MESSAGE_CODE);


            }
        });


    }

    // Method used to check if any data was sent back from the result intent
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        message = data.getStringExtra(BUNDLE_KEY);
        messageEditText.setText("");
        if (message != null)
            conversationTextView.setText(getString(R.string.conversation2_text, "Message from MA2: ", message));

    }
}

