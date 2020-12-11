package com.gokisoft.c1812l;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gokisoft.c1812l.adapters.BookAdapter;
import com.gokisoft.c1812l.models.Book;

import java.util.ArrayList;
import java.util.List;

public class BookActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Book> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        recyclerView = findViewById(R.id.ab_recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //fake data
        dataList.add(new Book("https://product.hstatic.net/1000217031/product/dac_nhan_tam_1ad146f4adc7443ea98c0636f6cba476.jpg", "Sach 1", "Noi dung 1"));
        dataList.add(new Book("https://product.hstatic.net/1000217031/product/dac_nhan_tam_1ad146f4adc7443ea98c0636f6cba476.jpg", "Sach 2", "Noi dung 2"));
        dataList.add(new Book("https://sachvui.com/cover/2015/Quang-ganh-lo-di-va-vui-song.jpg", "Sach 3", "Noi dung 3"));
        dataList.add(new Book("https://product.hstatic.net/1000217031/product/dac_nhan_tam_1ad146f4adc7443ea98c0636f6cba476.jpg", "Sach 4", "Noi dung 4"));
        dataList.add(new Book("https://product.hstatic.net/1000217031/product/dac_nhan_tam_1ad146f4adc7443ea98c0636f6cba476.jpg", "Sach 5", "Noi dung 5"));

        //Thiet ke Adapter
        BookAdapter adapter = new BookAdapter(this, dataList);

        recyclerView.setAdapter(adapter);
    }
}
