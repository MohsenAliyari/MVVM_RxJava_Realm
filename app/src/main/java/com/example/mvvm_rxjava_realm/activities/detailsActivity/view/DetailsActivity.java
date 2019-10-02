package com.example.mvvm_rxjava_realm.activities.detailsActivity.view;

import android.app.Activity;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import com.example.mvvm_rxjava_realm.R;
import com.example.mvvm_rxjava_realm.activities.detailsActivity.viewModel.DetailsViewModel;
import com.example.mvvm_rxjava_realm.databinding.ActivityDetailsBinding;

public class DetailsActivity extends AppCompatActivity {

    public static void StartDeTailsActivity(Activity activity) {
        Intent intent = new Intent(activity, DetailsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    private DetailsViewModel detailsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ActivityDetailsBinding activityDetailsBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_details);
        detailsViewModel = new DetailsViewModel(this);
        activityDetailsBinding.setItem(detailsViewModel);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detailsViewModel.detailsModelCompositeDisposable.dispose();
        detailsViewModel.detailsModelCompositeDisposable.clear();
    }
}
