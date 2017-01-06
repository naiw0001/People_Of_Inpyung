package com.example.idc06.layout.application;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by idc06 on 2016-12-29.
 */

public class Db_insert extends Activity {


    public void insert(String name,String address,String phone,int count_,String dv){

        String count = String.valueOf(count_);

        class Task extends AsyncTask<String,String,String>{
                URL url;

            @Override
            protected String doInBackground(String... params) {
                String _name = params[0];
                String _address = params[1];
                String _phone = params[2];
                String _count = params[3];
                String _dv = params[4];
                String link = "http://1.224.44.55/insert.php";
                String data="";

                try {

                    data = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(_name,"UTF-8");
                    data += "&"+URLEncoder.encode("address","UTF-8")+"="+URLEncoder.encode(_address,"UTF-8");
                    data += "&"+URLEncoder.encode("phone","UTF-8")+"="+URLEncoder.encode(_phone,"UTF-8");
                    data += "&"+URLEncoder.encode("count","UTF-8")+"="+URLEncoder.encode(_count,"UTF-8");
                    data += "&"+URLEncoder.encode("dv","UTF-8")+"="+URLEncoder.encode(_dv,"UTF-8");

//                    data += "?name="+URLEncoder.encode(_name,"UTF-8");
//                    data += "&address="+URLEncoder.encode(_address,"UTF-8");
//                    data += "&phone="+URLEncoder.encode(_phone,"UTF-8");
//                    data += "&count="+URLEncoder.encode(_count,"UTF-8");
//                    data += "&dv="+URLEncoder.encode(_dv,"UTF-8");

                    Log.d("vvvvvvvvvvvvv",link);
                    url = new URL(link);
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.setRequestMethod("POST");
                    conn.connect();
//                    OutputStreamWriter ow = new OutputStreamWriter(conn.getOutputStream());
//                    ow.write(data);
//                    ow.flush();
//                    ow.close();
                    PrintWriter pw = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()));
                    pw.write(data);
                    pw.flush();

                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line=null;
                    String text="";

                    while(true){
                        if((line=br.readLine())==null) break;
                        text += line;
                        Log.d("xzczxc",text);


                    }

         } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }
        }

        new Task().execute(name,address,phone,count,dv);
    }


}
