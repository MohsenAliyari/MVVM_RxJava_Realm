package com.example.mvvm_rxjava_realm.utils;

/**
 * Created by Hishki on 22/02/2019.
 */

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import com.example.mvvm_rxjava_realm.R;

public class GlobalThings {

    private static GlobalThings mInstance;



    public static GlobalThings getInstance() {
        if (mInstance == null)
            mInstance = new GlobalThings();
        return mInstance;
    }




    public static void showToast(Context context, String message, int duration) {
        Toast.makeText(context, message, duration).show();
    }

    public static void showToast(Context context, String message) {
        showToast(context, message, Toast.LENGTH_LONG);
    }



    public AlertDialog createDialog(final Activity activity, @Nullable String title, CharSequence message, String positiveButtonTitle, @Nullable DialogInterface.OnClickListener positiveButtonListener, String negativeButtonTitle, @Nullable DialogInterface.OnClickListener negetiveButtonListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        if (title != null) {
            builder.setTitle(title);
            TextView titleView = new TextView(activity);
            titleView.setText(title);
            builder.setCustomTitle(titleView);
        }
        builder.setMessage(message);
        builder.setPositiveButton(positiveButtonTitle, positiveButtonListener);
        if (negativeButtonTitle != null)
            builder.setNegativeButton(negativeButtonTitle, negetiveButtonListener);
        final AlertDialog dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dlg) {
                TextView textView = (TextView) dialog.findViewById(android.R.id.message);
                Typeface face = Typeface.createFromAsset(activity.getAssets(), "fonts/iransansmobile_medium.ttf");
                textView.setTypeface(face);
                Button button = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                if (button != null)
                    button.setTypeface(face);
                button = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                if (button != null)
                    button.setTypeface(face);
            }
        });
        return dialog;
    }

    public AlertDialog showDialog(final Activity activity, @Nullable String title, CharSequence message, String positiveButtonTitle, @Nullable DialogInterface.OnClickListener positiveButtonListener, String negativeButtonTitle, @Nullable DialogInterface.OnClickListener negetiveButtonListener) {
        AlertDialog dialog = createDialog(activity, title, message, positiveButtonTitle, positiveButtonListener, negativeButtonTitle, negetiveButtonListener);
        dialog.show();
        dialog.setCancelable(false);
        return dialog;
    }

    public AlertDialog showOkDialog(Activity activity, @Nullable String title, CharSequence message, @Nullable DialogInterface.OnClickListener positiveButtonListener) {
        return showDialog(activity, title, message, activity.getResources().getString(R.string.dialog_ok), positiveButtonListener, null, null);
    }

}
