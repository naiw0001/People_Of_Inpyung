package com.example.idc06.layout.application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by idc06 on 2017-01-05.
 */
public class Explanation extends Activity{

    TextView text;
    String s_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp);
        Intent intent = getIntent();
        s_text = intent.getStringExtra("text");

        text = (TextView)findViewById(R.id.ex_text);
        text.setText(s_text);


    }
}
