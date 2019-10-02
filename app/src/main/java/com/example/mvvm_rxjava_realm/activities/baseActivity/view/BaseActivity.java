package com.example.mvvm_rxjava_realm.activities.baseActivity.view;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import com.example.mvvm_rxjava_realm.R;
import com.example.mvvm_rxjava_realm.activities.baseActivity.viewModel.MovieViewModel;
import com.example.mvvm_rxjava_realm.databinding.ActivityBaseBinding;

public class BaseActivity extends AppCompatActivity {

    public static void StartBaseActivity(Context context) {
        Intent intent = new Intent(context, BaseActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    private MovieViewModel movieViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityBaseBinding activityBaseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base);
         movieViewModel = new MovieViewModel(this);
        activityBaseBinding.setItem(movieViewModel);

    }
    @Override
    protected void onResume() {
        super.onResume();
        movieViewModel.onResume();
    }

    @Override
    public void onBackPressed() {
        if (movieViewModel.onBackKeyPress()) {
            super.onBackPressed();
        }else {
            movieViewModel.onBackKeyPress();
        }
    }
}
