package com.example.idc06.layout.application;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by idc06 on 2017-01-05.
 */

public class ListViewAdapter extends BaseAdapter {

    private ArrayList<ListData> list = new ArrayList<ListData>();

    public ListViewAdapter(){
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item,parent,false);
        }

        ImageView iconImage = (ImageView)convertView.findViewById(R.id.img);
        TextView text = (TextView)convertView.findViewById(R.id.textView);

        ListData listData = list.get(position);

        iconImage.setImageDrawable(listData.getIcon());
        text.setText(listData.getText());

        return convertView;
    }

    public void addItem(Drawable icon, String text){
        ListData item = new ListData();
        item.setIcon(icon);
        item.setText(text);

        list.add(item);
    }

}
