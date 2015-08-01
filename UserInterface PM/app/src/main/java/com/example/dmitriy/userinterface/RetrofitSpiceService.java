package com.example.dmitriy.userinterface;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.example.dmitriy.userinterface.Queries;
import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

public class RetrofitSpiceService extends RetrofitGsonSpiceService {
    public final static String BASE_URL = "https://10.51.110.13";

    public void onCreate() {
        super.onCreate();
        addRetrofitInterface(Queries.class);
    }

    protected String getServerUrl() {
        return BASE_URL;
    }

}
}
