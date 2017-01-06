package com.example.idc06.layout.application;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by idc06 on 2017-01-02.
 */

public class Login_Activity extends Activity {
    AlertDialog.Builder dialog;
    EditText ed_id,ed_pw;
    String id, pw;
    String result;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dialog = new AlertDialog.Builder(this);
        ed_id = (EditText)findViewById(R.id.ed_id);
        ed_pw = (EditText)findViewById(R.id.ed_pw);
        startActivity(new Intent(Login_Activity.this,Splash.class));

    }

    // login method
    public void login(View v){
        id = ed_id.getText().toString();
        pw=ed_pw.getText().toString();
         Check_Activity check = new Check_Activity();
        result = check.check_login(id,pw);
        if(result.equals("S")) {
            Intent intent = new Intent(Login_Activity.this,MainActivity.class);
            intent.putExtra("id",id);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "환영합니다.", Toast.LENGTH_SHORT).show();

            finish();
        }else if(result.equals("F")){
            Toast.makeText(getApplicationContext(),"아이디 또는 비밀번호가 틀렸습니다.",Toast.LENGTH_SHORT).show();
        }else if(result.equals("A")){
            // admin login
        }

    }
    // join method
    public void join(View v){
        final View dialog_join = getLayoutInflater().inflate(R.layout.dialog_join,null);

        dialog.setTitle("회원가입");
        dialog.setView(dialog_join);
        dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText name, passwd,passwd2, id, age;
                String s_name,s_passwd,s_id,s_age;

                name = (EditText)dialog_join.findViewById(R.id.join_name);
                passwd = (EditText)dialog_join.findViewById(R.id.join_passwd);
                passwd2=(EditText)dialog_join.findViewById(R.id.join_passwd2);
                id=(EditText)dialog_join.findViewById(R.id.join_id);
                age=(EditText)dialog_join.findViewById(R.id.join_age);

                s_name= name.getText().toString();
                s_passwd=passwd.getText().toString();
                s_id=id.getText().toString();
                s_age=age.getText().toString();

                if(s_passwd.equals(passwd2.getText().toString())){
                    new Join_Task().execute(s_name,s_passwd,s_id,s_age);
                }else Toast.makeText(getApplicationContext(),"비밀번호가 일치하지 않습니다.",Toast.LENGTH_SHORT).show();


            }
        });
        dialog.setNegativeButton("취소",null);
        dialog.show();
    }


    class Join_Task extends AsyncTask<String,String,String>{
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(getApplicationContext(),"회원가입이 완료되었습니다.",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... params) {
            String name = params[0];
            String passwd=params[1];
            String id=params[2];
            String age=params[3];
            String link = "http://1.224.44.55/join.php";
            String data;

            try{
                URL url = new URL(link);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                data = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8");
                data += "&"+URLEncoder.encode("age","UTF-8")+"="+URLEncoder.encode(age,"UTF-8");
                data += "&"+URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8");
                data += "&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(passwd,"UTF-8");

                PrintWriter pw = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()));
                pw.write(data);
                pw.close();

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String result = br.readLine();
                return result;

            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }
    }

    // login_anon
    public void login_anon(View v){
        String anon = "anonymous";
        Intent intent = new Intent(Login_Activity.this,MainActivity.class);
        intent.putExtra("id",anon);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "환영합니다.", Toast.LENGTH_SHORT).show();
        finish();
    }


}
