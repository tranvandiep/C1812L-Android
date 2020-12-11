package com.gokisoft.c1812l.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gokisoft.c1812l.R;
import com.gokisoft.c1812l.models.Book;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Diep.Tran on 12/11/20.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    LayoutInflater inflater = null;
    List<Book> dataList;

    public BookAdapter(Context context, List<Book> dataList) {
        this.dataList = dataList;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_food, null);

        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder bookViewHolder, int position) {
        //set du lieu
        Book book = dataList.get(position);

        bookViewHolder.txtTitle.setText(book.getTitle());
        bookViewHolder.txtContent.setText(book.getContent());

        //Hien thi hinh anh
        Picasso.get()
                .load(book.getThumbnail())
                .into(bookViewHolder.thumbnailImg);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnailImg;
        TextView txtTitle, txtContent, txtPrice;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            thumbnailImg = itemView.findViewById(R.id.ifo_thumbnail);
            txtTitle = itemView.findViewById(R.id.ifo_title);
            txtContent = itemView.findViewById(R.id.ifo_content);
            txtPrice = itemView.findViewById(R.id.ifo_price);
        }
    }
}
