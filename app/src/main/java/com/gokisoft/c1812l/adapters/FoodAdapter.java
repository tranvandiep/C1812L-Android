package com.gokisoft.c1812l.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gokisoft.c1812l.R;
import com.gokisoft.c1812l.models.Food;

import java.util.List;

/**
 * Created by Diep.Tran on 11/27/20.
 */

public class FoodAdapter extends BaseAdapter {
    Activity activity;
    List<Food> dataList;

    public FoodAdapter(Activity activity, List<Food> dataList) {
        this.activity = activity;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = activity.getLayoutInflater();
        view = inflater.inflate(R.layout.item_food, null);

        ImageView thumbnail = view.findViewById(R.id.ifo_thumbnail);
        TextView title = view.findViewById(R.id.ifo_title);
        TextView content = view.findViewById(R.id.ifo_content);
        TextView price = view.findViewById(R.id.ifo_price);

        Food food = dataList.get(position);

        thumbnail.setImageResource(food.getResId());
        title.setText(food.getTitle());
        content.setText(food.getContent());
        price.setText(String.valueOf(food.getPrice()));

        return view;
    }
}
