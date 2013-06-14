package com.haui.android.sns;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.haui.android.sns.base.HauiSNSBase;
import com.haui.android.sns.entity.UserInfo;
import com.haui.android.sns.utils.DevUtils;
import com.haui.android.sns.utils.HttpUtils;
import com.haui.android.sns.utils.JSONParser;
import com.haui.android.sns.utils.JsonHelper;
import com.haui.android.sns.utils.UrlJsonAsyncTask;

public class RegisterActivity extends Activity {

	private SharedPreferences mPreferences;
	private String mUserEmail;
	private String mUserEmailConfirm;
	private String mUserName;
	private String mUserPassword;
	private String mUserPasswordConfirmation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// XXX Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);
	}

	public void registerNewAccount(View button) {

		this.collectInputInfo();
		// if everything is ok!
		if (checkInput()) {
			RegisterTask registerTask = new RegisterTask(RegisterActivity.this);
			registerTask.setMessageLoading("Registering new account...");
			registerTask.execute(HauiSNSBase.REG_URL);
		}

	}

	private void collectInputInfo() {
		EditText usernameField = (EditText) findViewById(R.id.userName);
		mUserName = usernameField.getText().toString();

		EditText userEmailField = (EditText) findViewById(R.id.userEmail);
		mUserEmail = userEmailField.getText().toString();

		EditText userEmailConfirm = (EditText) findViewById(R.id.userEmailConfirm);
		mUserEmailConfirm = userEmailConfirm.getText().toString();

		EditText userPasswordField = (EditText) findViewById(R.id.userPassword);
		mUserPassword = userPasswordField.getText().toString();

		EditText userPasswordConfirmationField = (EditText) findViewById(R.id.userPasswordConfirmation);
		mUserPasswordConfirmation = userPasswordConfirmationField.getText()
				.toString();
	}

	private boolean checkInput() {
		if (mUserEmail.length() == 0 || mUserEmailConfirm.length() == 0
				|| mUserName.length() == 0 || mUserPassword.length() == 0
				|| mUserPasswordConfirmation.length() == 0) {
			// input fields are empty
			DevUtils.showToast(RegisterActivity.this,
					"Please complete all the fields");
			return false;
		} else if (!mUserEmail.equals(mUserEmailConfirm)) {
			// emails not match
			DevUtils.showToast(RegisterActivity.this,
					"Your email adress doesn't match confirmation, check again!");
			return false;
		} else if (!mUserPassword.equals(mUserPasswordConfirmation)) {
			// password not match
			DevUtils.showToast(RegisterActivity.this,
					"Your password doesn't match confirmation, check again!");
			return false;
		}

		return true;
	}

	private class RegisterTask extends UrlJsonAsyncTask {

		public RegisterTask(Context context) {
			super(context);
		}

		@Override
		protected JSONObject doInBackground(String... urls) {

			JSONObject params = JsonHelper.makeRegisterJsonParam(new UserInfo(
					mUserName, mUserEmail, mUserPassword));
			DevUtils.printLog("Params to send " + params);
			return HttpUtils.postRequest(params, urls[0]);
		}

		@Override
		protected void onPostExecute(JSONObject json) {

			try {
				String info = "Unknow error!";
				if (json.getBoolean(JSONParser.SUCCESS_TAG)) {
					String auth = json.getJSONObject(JSONParser.DATA_TAG)
							.getString(UserInfo.AUTH_TAG);

					SharedPreferences.Editor editor = mPreferences.edit();
					// save the returned auth_token into
					// the SharedPreferences
					editor.putString("AuthToken", auth);
					editor.commit();
					DevUtils.showToast(RegisterActivity.this,
							"Welcome! You have signed up successfully!\nNow get started by Login.");
					DevUtils.printLog("Welcome! You have signed up successfully");
					Intent intent = new Intent(RegisterActivity.this,
							LoginActivity.class);
					startActivityForResult(intent, 0);

				} else {
					info = json.getString(JSONParser.INFO_TAG);
					DevUtils.showToast(RegisterActivity.this,
							"Registion not success!\n");
					DevUtils.printLog(String.valueOf(json));
					DevUtils.showAlert(RegisterActivity.this,
							"Register failed", info);
					// ProgressDialog.show(this.context, "Registion failed",
					// info,
					// true, true);
				}

			} catch (Exception e) {
				DevUtils.printLog(e.getMessage());
			} finally {
				super.onPostExecute(json);
			}
		}
	}
}
