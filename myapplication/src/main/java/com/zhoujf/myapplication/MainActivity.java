package com.zhoujf.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String url = "http://192.168.1.99:8080/zhbj/categories.json";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,null,)

    }
    private Response.Listener mListener = new Response.Listener(){

        @Override
        public void onResponse(Object response) {

        }
    };
}
