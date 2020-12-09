package com.gokisoft.c1812l;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChatActivity extends AppCompatActivity {
    EditText editText;
    Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        editText = findViewById(R.id.ac_title);
        sendBtn = findViewById(R.id.ac_send_btn);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //send data.
                Intent intent = new Intent();
                intent.setAction(MessageActivity.ACTION_MESSAGE);
                intent.putExtra("msg", editText.getText().toString());

                sendBroadcast(intent);
            }
        });
    }
}
