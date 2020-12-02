package com.gokisoft.c1812l.adapters;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gokisoft.c1812l.R;

/**
 * Created by Diep.Tran on 12/2/20.
 */

public class FoodCursorAdapter extends CursorAdapter {
    Activity activity;

    public FoodCursorAdapter(Activity activity, Cursor c) {
        super(activity, c);
        this.activity = activity;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.item_food, null);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ImageView thumbnailImg = view.findViewById(R.id.ifo_thumbnail);
        TextView titleTxt = view.findViewById(R.id.ifo_title);
        TextView contentTxt = view.findViewById(R.id.ifo_content);
        TextView priceTxt = view.findViewById(R.id.ifo_price);

        String title = cursor.getString(cursor.getColumnIndex("title"));
        String content = cursor.getString(cursor.getColumnIndex("content"));
        float price = cursor.getFloat(cursor.getColumnIndex("price"));

        titleTxt.setText(title);
        contentTxt.setText(content);
        priceTxt.setText(String.valueOf(price));
    }
}
