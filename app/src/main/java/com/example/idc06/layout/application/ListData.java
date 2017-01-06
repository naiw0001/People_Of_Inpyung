package com.example.idc06.layout.application;

import android.graphics.drawable.Drawable;

/**
 * Created by idc06 on 2017-01-05.
 */
public class ListData {

    private Drawable icon;
    private String text;

    public void setIcon(Drawable icon){
        this.icon = icon;
    }
    public void setText(String text){
        this.text=text;
    }
    public Drawable getIcon(){
        return this.icon;
    }
    public String getText(){
        return  this.text;
    }

}
