package com.example.idc06.layout.application;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private ImageView[] menu;
    private int imgId[] = {R.id.image1, R.id.image2, R.id.image3, R.id.image4, R.id.image5, R.id.image6, R.id.image7, R.id.image8, R.id.image9, R.id.image10};
    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;
    private Button btn_id;
    private String id;
    private ListView list;
    private TextView text;
    Intent intent_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("인평의 민족");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        toolbar.setBackgroundColor(Color.parseColor("#fd331d"));
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name);
        drawerLayout.setDrawerListener(drawerToggle);
        btn_id = (Button) findViewById(R.id.btn_login);

            if (id.equals("anonymous")) {
                btn_id.setText("비회원");
            } else btn_id.setText("ID: " + id);

        text = (TextView) findViewById(R.id.text_view);
        text.setText("인평의민족");

        menu = new ImageView[10];
        for (int i = 0; i < 10; i++) {
            menu[i] = (ImageView) findViewById(imgId[i]);
            menu[i].setOnClickListener(this);
        }

        ListViewAdapter adapter = new ListViewAdapter();
        list = (ListView) findViewById(R.id.list);

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
                Intent intent = new Intent(MainActivity.this, Explanation.class);
                intent.putExtra("text", text);
                startActivity(intent);
            }
        });
        intent_menu = new Intent(MainActivity.this, Menu1_Activity.class);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.image1:
                intent_menu.putExtra("menu", 1);
                break;
            case R.id.image2:
                intent_menu.putExtra("menu", 2);
                break;
            case R.id.image3:
                intent_menu.putExtra("menu", 3);
                break;
            case R.id.image4:
                intent_menu.putExtra("menu", 4);
                break;
            case R.id.image5:
                intent_menu.putExtra("menu", 5);
                break;
            case R.id.image6:
                intent_menu.putExtra("menu", 6);
                break;
            case R.id.image7:
                intent_menu.putExtra("menu", 7);
                break;
            case R.id.image8:
                intent_menu.putExtra("menu", 8);
                break;
            case R.id.image9:
                intent_menu.putExtra("menu", 9);
                break;
            case R.id.image10:
                intent_menu.putExtra("menu", 10);
                break;
        }
        intent_menu.putExtra("id", id);
        startActivity(intent_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
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
