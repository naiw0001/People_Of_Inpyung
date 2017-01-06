package com.example.idc06.layout.application;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

/**
 * Created by idc06 on 2016-12-29.
 */
public class Menu1_Activity extends ActionBarActivity {
    private Toolbar toolbar;
    private RadioButton r1,r2,r3;
    private EditText ed_name,ed_address,ed_phone;
    private String name,address,phone;
    private int count;
    private String menu;
    private ImageView menu_img;
    private String id;
    private ListView list;
    Db_insert db_Insert = new Db_insert();
    ActionBarDrawerToggle drawerToggle;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu1);
        toolbar = (Toolbar)findViewById(R.id.toolbar_menu1);
        menu_img = (ImageView)findViewById(R.id.menu);
        Intent intent = getIntent();
        int val = intent.getIntExtra("menu",1);
        id = intent.getStringExtra("id");
        switch (val){
            case 1:
                menu_img.setImageResource(R.drawable.menu1);
                menu = "menu1";
                toolbar.setTitle("급식 1");
                break;
            case 2:
                menu_img.setImageResource(R.drawable.menu2);
                menu = "menu2";
                toolbar.setTitle("급식 2");
                break;
            case 3:
                menu_img.setImageResource(R.drawable.menu3);
                menu = "menu3";
                toolbar.setTitle("급식 3");
                break;
            case 4:
                menu_img.setImageResource(R.drawable.menu4);
                menu = "menu4";
                toolbar.setTitle("급식 4");
                break;
            case 5:
                menu_img.setImageResource(R.drawable.menu5);
                menu = "menu5";
                toolbar.setTitle("급식 5");
                break;
            case 6:
                menu_img.setImageResource(R.drawable.menu6);
                menu = "menu6";
                toolbar.setTitle("급식 6");
                break;
            case 7:
                menu_img.setImageResource(R.drawable.menu7);
                menu = "menu7";
                toolbar.setTitle("급식 7");
                break;
            case 8:
                menu_img.setImageResource(R.drawable.menu8);
                menu = "menu8";
                toolbar.setTitle("급식 8");
                break;
            case 9:
                menu_img.setImageResource(R.drawable.menu9);
                menu = "menu9";
                toolbar.setTitle("급식 9");
                break;
            case 10:
                menu_img.setImageResource(R.drawable.menu10);
                menu = "menu10";
                toolbar.setTitle("급식 10");
                break;
        }
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        toolbar.setBackgroundColor(Color.parseColor("#fd331d"));


        Button btn_id = (Button) findViewById(R.id.btn_login_menu);

        if(id.equals("anonymous")){
            btn_id.setText("비회원");
        }else btn_id.setText("ID: "+id);

        setSupportActionBar(toolbar);

        r1 = (RadioButton)findViewById(R.id.one);
        r2 = (RadioButton)findViewById(R.id.two);
        r3 = (RadioButton)findViewById(R.id.three);

        ed_name = (EditText)findViewById(R.id.user_name);
        ed_address = (EditText)findViewById(R.id.user_address);
        ed_phone = (EditText)findViewById(R.id.user_phone);
        drawerLayout  = (DrawerLayout)findViewById(R.id.drawer_layout);

        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.app_name,R.string.app_name);
        drawerLayout.setDrawerListener(drawerToggle);

        ListViewAdapter adapter = new ListViewAdapter();
        list = (ListView) findViewById(R.id.list_menu);

        list.setAdapter(adapter);

        adapter.addItem(ContextCompat.getDrawable(this, R.mipmap.icon), "1");
        adapter.addItem(ContextCompat.getDrawable(this, R.mipmap.icon), "2");
        adapter.addItem(ContextCompat.getDrawable(this, R.mipmap.icon), "3");
        adapter.addItem(ContextCompat.getDrawable(this, R.mipmap.icon), "4");
        adapter.addItem(ContextCompat.getDrawable(this, R.mipmap.icon), "5");
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListData item = (ListData) parent.getItemAtPosition(position);
                String text = item.getText();
                Intent intent = new Intent(Menu1_Activity.this, Explanation.class);
                intent.putExtra("text", text);
                startActivity(intent);
            }
        });


    }

    public void okay(View v){
        name = ed_name.getText().toString();
        address=ed_address.getText().toString();
        phone = ed_phone.getText().toString();

        if(r1.isChecked()==true){
            count=1;
        }else if(r2.isChecked()==true){
            count=2;
        }else if(r3.isChecked()==true){
            count=3;
        }
        db_Insert.insert(name,address,phone,count,menu);
        Toast.makeText(getApplicationContext(),"주문이 완료되었습니다.",Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(Menu1_Activity.this, MainActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);

    }
    public void cancel(View v){
        Intent intent = new Intent(Menu1_Activity.this, MainActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);

        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

}
