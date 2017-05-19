package com.zhoujf.news.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by ZhouJF on 2017-05-18.
 */

public class NetworkManager {
    public static RequestQueue sRequestQueue;
    private static ImageLoader sImageLoader;
    private static final int CACHE_SIZE = 5*1024*1024;

    public static void init(Context context) {
        sRequestQueue = Volley.newRequestQueue(context);
        sImageLoader = new ImageLoader(sRequestQueue, new BitmapLruCache(CACHE_SIZE));
    }

    ;

    private static class BitmapLruCache extends LruCache<String, Bitmap> implements ImageLoader.ImageCache {

        /**
         * @param maxSize for caches that do not override {@link #sizeOf}, this is
         *                the maximum number of entries in the cache. For all other caches,
         *                this is the maximum sum of the sizes of the entries in this cache.
         */
        public BitmapLruCache(int maxSize) {
            super(maxSize);
        }

        @Override
        protected int sizeOf(String key, Bitmap value) {
            return value.getByteCount();//返回图片的字节数
        }

        @Override
        public Bitmap getBitmap(String url) {
            return get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            put(url, bitmap);
        }
    }

    public static ImageLoader getImageLoader() {
        return sImageLoader;
    }
}
