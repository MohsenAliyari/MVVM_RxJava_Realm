package com.example.mvvm_rxjava_realm.utils;

import android.app.Application;
import android.content.Context;
import com.example.mvvm_rxjava_realm.event.RxBus;
import io.realm.Realm;
import io.realm.RealmConfiguration;


public class MyApplication extends Application {

    private RxBus bus;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        this.bus = new RxBus();
        MyApplication.context = getApplicationContext();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("myrealm.realm")
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);

    }
    public static Context getAppContext() {
        return MyApplication.context;
    }
    public RxBus bus() {
        return bus;
    }


}