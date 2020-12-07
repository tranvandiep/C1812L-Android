package com.gokisoft.c1812l;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.gokisoft.c1812l.adapters.FoodAdapter;
import com.gokisoft.c1812l.models.Food;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    ListView listView;
    List<Food> dataList = new ArrayList<>();
    FoodAdapter foodAdapter;
    int currentIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        listView = findViewById(R.id.afo_listview);

//        //fake data
        dataList.add(new Food(R.drawable.food01, "Pizza 01", "Ok Ngon 01", 100000));
        dataList.add(new Food(R.drawable.food02, "Pizza 02", "Ok Ngon 02", 120000));
        dataList.add(new Food(R.drawable.food03, "Pizza 03", "Ok Ngon 03", 160000));

        //create adapter
        foodAdapter = new FoodAdapter(this, dataList);
        listView.setAdapter(foodAdapter);

        //dang ky context menu
        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_content, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;

        switch (item.getItemId()) {
            case R.id.menu_edit_item:
                //Them item moi
                this.currentIndex = index;
                showDialogAdd();
                return true;
            case R.id.menu_delete_item:
                dataList.remove(index);
                foodAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_add_new_item:
                //Them item moi
                showDialogAdd();
                return true;
            case R.id.menu_exit:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showDialogAdd() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_food, null);

        final EditText edTitle = view.findViewById(R.id.df_title);
        final EditText edContent = view.findViewById(R.id.df_content);
        final EditText edPrice = view.findViewById(R.id.df_price);
        if(currentIndex >= 0) {
            edTitle.setText(dataList.get(currentIndex).getTitle());
            edContent.setText(dataList.get(currentIndex).getContent());
            edPrice.setText(String.valueOf(dataList.get(currentIndex).getPrice()));
        }

        builder.setView(view);
        builder.setTitle("Add/Update Item")
                .setPositiveButton("Save Item", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String title = edTitle.getText().toString();
                        String content = edContent.getText().toString();
                        float price = Float.parseFloat(edPrice.getText().toString());

                        if(currentIndex >= 0) {
                            dataList.get(currentIndex).setTitle(title);
                            dataList.get(currentIndex).setContent(content);
                            dataList.get(currentIndex).setPrice(price);
                            currentIndex = -1;
                        } else {
                            Food food = new Food(R.drawable.food01, title, content, price);
                            dataList.add(food);
                        }

                        foodAdapter.notifyDataSetChanged();
                    }
                }).setNegativeButton("Cencel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.show();
    }
}
