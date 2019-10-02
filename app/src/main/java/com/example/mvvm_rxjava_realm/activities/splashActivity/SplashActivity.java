package com.example.mvvm_rxjava_realm.activities.splashActivity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import com.example.mvvm_rxjava_realm.R;
import com.example.mvvm_rxjava_realm.activities.splashActivity.viewModel.SplashViewModel;
import com.example.mvvm_rxjava_realm.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ActivitySplashBinding activitySplashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        SplashViewModel splashViewModel = new SplashViewModel(this);
        activitySplashBinding.setViewModel(splashViewModel);

    }
}
