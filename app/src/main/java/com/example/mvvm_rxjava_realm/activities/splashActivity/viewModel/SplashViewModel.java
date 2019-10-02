package com.example.mvvm_rxjava_realm.activities.splashActivity.viewModel;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import androidx.databinding.BaseObservable;
import com.example.mvvm_rxjava_realm.activities.baseActivity.view.BaseActivity;

public class SplashViewModel extends BaseObservable {

    private Context context;
    private int SPLASH_DISPLAY_LENGTH=3000;

    public SplashViewModel(Context context) {
        this.context = context;
        splashTimer();
    }

    private void splashTimer(){
        Handler handler=new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                BaseActivity.StartBaseActivity(context);
            }
        },SPLASH_DISPLAY_LENGTH);
    }
}
