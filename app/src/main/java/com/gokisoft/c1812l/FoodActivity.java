package com.gokisoft.c1812l;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
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

import com.gokisoft.c1812l.adapters.FoodCursorAdapter;
import com.gokisoft.c1812l.db.DBHelper;
import com.gokisoft.c1812l.entity.FoodModify;
import com.gokisoft.c1812l.models.Food;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FoodActivity extends AppCompatActivity {
    ListView listView;
    List<Food> dataList = new ArrayList<>();
//    FoodAdapter foodAdapter;
    FoodCursorAdapter foodAdapter;
    int currentIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        //kich hoat database
        DBHelper.getInstance(this);

        listView = findViewById(R.id.afo_listview);

//        //fake data
//        dataList.add(new Food(R.drawable.food01, "Pizza 01", "Ok Ngon 01", 100000));
//        dataList.add(new Food(R.drawable.food02, "Pizza 02", "Ok Ngon 02", 120000));
//        dataList.add(new Food(R.drawable.food03, "Pizza 03", "Ok Ngon 03", 160000));
//        readFile();
        readSharedPreferences();

        //create adapter
//        foodAdapter = new FoodAdapter(this, dataList);
        Cursor cursor = FoodModify.findAll();
        foodAdapter = new FoodCursorAdapter(this, cursor);
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
//                dataList.remove(index);
//                foodAdapter.notifyDataSetChanged();
                Cursor cursor = foodAdapter.getCursor();
                cursor.moveToPosition(index);
                int id = cursor.getInt(cursor.getColumnIndex("_id"));

                FoodModify.delete(id);

                cursor = FoodModify.findAll();
                foodAdapter.changeCursor(cursor);

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

    private void readFile() {
        //code save File o day
        ObjectInputStream ois = null;
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(getFileStreamPath("food.dat"));
            ois = new ObjectInputStream(fis);

            //save file
            dataList = (List<Food>) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void saveFile() {
        //code save File o day
        ObjectOutputStream oos = null;
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(getFileStreamPath("food.dat"));
            oos = new ObjectOutputStream(fos);

            //save file
            oos.writeObject(dataList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void saveSharedPreferences() {
        //Save dataList => Object Array => convert Object Array => String (json string)
        //Su dung thu vien gson => convert object/array => json string va nguoc lai
        String json = new Gson().toJson(dataList);
        //save json to SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("C1808G", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //save
        editor.putString("dataList", json);

        editor.commit();
    }

    private void readSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("C1808G", MODE_PRIVATE);

        String json = sharedPreferences.getString("dataList", "[]");

        //convert json string => object array
        Gson gson = new Gson();
        dataList = gson.fromJson(json, new TypeToken<List<Food>>(){}.getType());

        if(dataList == null) {
            dataList = new ArrayList<>();
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
            Cursor cursor = foodAdapter.getCursor();
            cursor.moveToPosition(currentIndex);

            String title = cursor.getString(cursor.getColumnIndex("title"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            float price = cursor.getFloat(cursor.getColumnIndex("price"));

            edTitle.setText(title);
            edContent.setText(content);
            edPrice.setText(String.valueOf(price));

//            edTitle.setText(dataList.get(currentIndex).getTitle());
//            edContent.setText(dataList.get(currentIndex).getContent());
//            edPrice.setText(String.valueOf(dataList.get(currentIndex).getPrice()));
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
//                    dataList.get(currentIndex).setTitle(title);
//                    dataList.get(currentIndex).setContent(content);
//                    dataList.get(currentIndex).setPrice(price);
                    Cursor cursor = foodAdapter.getCursor();
                    cursor.moveToPosition(currentIndex);
                    int id = cursor.getInt(cursor.getColumnIndex("_id"));

                    Food food = new Food(title, content, price);
                    food.set_id(id);
                    FoodModify.save(food);
                    currentIndex = -1;
                } else {
                    Food food = new Food(R.drawable.food01, title, content, price);
//                    dataList.add(food);
                    //Save database
                    FoodModify.insert(food);
                }
                Cursor cursor = FoodModify.findAll();
                foodAdapter.changeCursor(cursor);
//                saveFile();
//                saveSharedPreferences();

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
