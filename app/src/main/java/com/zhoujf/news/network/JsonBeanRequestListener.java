package com.zhoujf.news.network;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by ZhouJF on 2017-05-18.
 */

public class JsonBeanRequestListener<T> implements Response.Listener<T>, Response.ErrorListener {
    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(T response) {

    }
}
