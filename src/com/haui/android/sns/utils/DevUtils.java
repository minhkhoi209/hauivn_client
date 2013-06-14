package com.haui.android.sns.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.Log;
import android.widget.Toast;

import com.haui.android.sns.R;
import com.haui.android.sns.base.HauiSNSBase;

public class DevUtils {

	public static void printLog(String log) {
		Log.i(HauiSNSBase.APP_NAME, log);
	}

	public static void showToast(Context context, String message) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}

	public static void showAlert(Context context, String title, String message) {

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title);
        builder.setMessage(message).setIcon(R.drawable.ic_dlg_warring);
        builder.setPositiveButton("OK", new OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.show();
	}
}
