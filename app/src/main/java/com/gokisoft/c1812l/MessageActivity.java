package com.gokisoft.c1812l;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends AppCompatActivity {
    public static final String ACTION_MESSAGE = "message";

    List<String> dataList = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ListView listView;
    Button chattingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        listView = findViewById(R.id.am_listview);
        chattingBtn = findViewById(R.id.am_chatting);

        dataList.add("testing...");

        adapter = new ArrayAdapter<String>(this, R.layout.item_fruit, R.id.if_title, dataList);
        listView.setAdapter(adapter);

        chattingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MessageActivity.this, ChatActivity.class);
                startActivity(intent);
            }
        });

        MessageReceiver receiver = new MessageReceiver();

        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_MESSAGE);

        registerReceiver(receiver, filter);
    }

    class MessageReceiver extends BroadcastReceiver {

            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();

                switch (action) {
                    case ACTION_MESSAGE:
                        String msg = intent.getStringExtra("msg");

                        dataList.add(msg);

                        adapter.notifyDataSetChanged();
                        break;
                }
            }
    }
}
