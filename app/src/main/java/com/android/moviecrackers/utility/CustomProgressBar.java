package com.android.moviecrackers.utility;

import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AlertDialog;

import com.android.moviecrackers.R;


public class CustomProgressBar {

    private AlertDialog dialog;
    private boolean mProgress = true;

    public CustomProgressBar(Context context) {
        setProgressDialog(context);
    }

    private void setProgressDialog(Context context) {
        int llPadding = 30;
        LinearLayout ll = new LinearLayout(context);
//        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setPadding(llPadding, llPadding, llPadding, llPadding);
        ll.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams llParam = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        llParam.gravity = Gravity.CENTER;
        ll.setLayoutParams(llParam);

        ProgressBar progressBar = new ProgressBar(context);
        progressBar.setIndeterminate(true);
        progressBar.setBackground(context.getResources().getDrawable(R.drawable.white_round_corner));
        progressBar.setScaleX(0.8f);
        progressBar.setScaleY(0.8f);
        progressBar.setPadding(llPadding, llPadding, llPadding, llPadding);

        ll.addView(progressBar);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setCancelable(false);
        builder.setView(ll);
        dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        if (!dialog.isShowing()) {
//            dialog.show();
            Window window = dialog.getWindow();
            if (window != null) {
                window.setBackgroundDrawableResource(android.R.color.transparent);
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(dialog.getWindow().getAttributes());
                layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT;
                layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setAttributes(layoutParams);
            }
        }

    }

    public void show() {
        if (mProgress) {
            mProgress = false;
            dialog.show();
        }

    }

    public void hide() {
        if (!mProgress) {
            mProgress = true;
            dialog.hide();
        }

    }
}
