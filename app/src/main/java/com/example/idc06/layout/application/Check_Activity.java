package com.example.idc06.layout.application;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by idc06 on 2017-01-03.
 */

public class Check_Activity extends Activity {
    ArrayList list_id = new ArrayList();
    ArrayList list_pw = new ArrayList();
    String id,pw;
    String check = null;
    //login check
    public String check_login(String id, String pw) {
        this.id = id;
        this.pw = pw;
        class loginTask extends AsyncTask<String, String, String> {

            @Override
            protected String doInBackground(String... params) {
                String link = "http://1.224.44.55/login.php";
                StringBuilder json = new StringBuilder();
                String result = "";
                try {
                    URL url = new URL(link);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
                    while (true) {
                        String line = br.readLine();
                        if (line == null) break;
                        json.append(line + "\n");
                    }
                br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                result = check(json.toString());
                Log.d("asd",result);
                return result;
            }
        }

        try {
            check = new loginTask().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
            return check;

    }

    public String check(String json) {
        String result="";
        try {
            JSONArray array = new JSONArray(json);

            for (int i = 0; i < array.length(); i++) {
                JSONObject jo = array.getJSONObject(i);
                Log.d("asdasd",jo.getString("id"));
                list_id.add(jo.getString("id"));
                Log.d("xxxxxxx",jo.getString("password"));
                list_pw.add(jo.getString("password"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
            Log.d("id",pw);
        Log.d("pw",pw);

        for (int i = 0; i<list_id.size();i++ ){
            Log.d("asd",list_id.get(i).toString());
            Log.d("sad",list_pw.get(i).toString());
            if(id.equals(list_id.get(i).toString()) && pw.equals(list_pw.get(i).toString())){
                if(id.equals("admin")) result = "A"; // admin
                result = "S";
            }else result = "F"; // fail

        }
            return result;
    }


}
