package com.zhoujf.news.network;

import android.util.Log;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * Created by ZhouJF on 2017-05-18.
 */

public class JsonBeanRequest<T> extends JsonRequest {
    private static final String TAG = "JsonBeanRequest";
    Class<T> mClass;

    public JsonBeanRequest(int method, Class<T> classz, String url, String requestBody, Response.Listener listener, Response.ErrorListener errorListener) {
        super(method, url, requestBody, listener, errorListener);
        mClass = classz;
    }

    public JsonBeanRequest(Class<T> classz, String url, Response.Listener listener, Response.ErrorListener errorListener) {
        this(Method.GET, classz, url, null, listener, errorListener);
    }

    public JsonBeanRequest(Class<T> classz, String url, JsonBeanRequestListener listener) {
        this(classz, url, listener, listener);
    }

    @Override
    protected Response parseNetworkResponse(NetworkResponse response) {
        Log.d(TAG, "parseNetworkResponse: ");

        byte[] data = response.data;
        try {
            String result = new String(data, PROTOCOL_CHARSET);
            Gson mGson = new Gson();
            T t = mGson.fromJson(result, mClass);
            Cache.Entry entry = HttpHeaderParser.parseCacheHeaders(response);
            return Response.success(t, entry);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
