package com.gokisoft.c1812l;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FruitActivity extends AppCompatActivity {
    ListView listView;
    Button btnAdd;
    List<String> dataList = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);

        listView = findViewById(R.id.af_listview);
        btnAdd = findViewById(R.id.af_add);

        dataList.add("Apple");
        dataList.add("Banana");
        dataList.add("Coconut");


        adapter = new ArrayAdapter<String>(this,
                R.layout.item_fruit, R.id.if_title, dataList);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String value = dataList.get(position);

                Toast.makeText(FruitActivity.this, value, Toast.LENGTH_SHORT).show();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogAdd();
            }
        });
    }

    private void showDialogAdd() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.dialog_add_fruit, null);


//        Toast.makeText(this, "new item", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);

        final EditText txtTitle = view.findViewById(R.id.daf_title);

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String title = txtTitle.getText().toString();

                dataList.add(title);

                adapter.notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(FruitActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
            }
        });

        builder.show();
    }
}
