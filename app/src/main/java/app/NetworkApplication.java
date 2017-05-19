package app;

import android.app.Application;

import com.zhoujf.news.network.NetworkManager;

/**
 * Created by ZhouJF on 2017-05-18.
 */

public class NetworkApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NetworkManager.init(getApplicationContext());
    }

}
