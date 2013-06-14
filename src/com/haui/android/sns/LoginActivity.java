package com.haui.android.sns;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.haui.android.sns.base.HauiSNSBase;
import com.haui.android.sns.entity.UserInfo;
import com.haui.android.sns.utils.DevUtils;
import com.haui.android.sns.utils.HttpUtils;
import com.haui.android.sns.utils.JSONParser;
import com.haui.android.sns.utils.UrlJsonAsyncTask;

public class LoginActivity extends Activity {

	private SharedPreferences mPreferences;
	private String mUsername;
	private String mUserPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// XXX Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);
	}

	public void doLogin(View view) {
		EditText userEmailField = (EditText) findViewById(R.id.userEmail);
		mUsername = userEmailField.getText().toString();
		EditText userPasswordField = (EditText) findViewById(R.id.userPassword);
		mUserPassword = userPasswordField.getText().toString();

		if (mUsername.length() == 0 || mUserPassword.length() == 0) {
			// input fields are empty
			DevUtils.showToast(LoginActivity.this,
					"Please complete all the fields");
			return;
		} else {

			LoginTask loginTask = new LoginTask(LoginActivity.this);
			loginTask.setMessageLoading("Logging in...");
			loginTask.execute(HauiSNSBase.LOGIN_URL);
		}
	}

	private class LoginTask extends UrlJsonAsyncTask {

		public LoginTask(Context context) {
			super(context);
		}

		@Override
		protected JSONObject doInBackground(String... params) {

			String url = params[0];
			JSONObject loginParams = new JSONObject();
			try {
				loginParams.put("user",
						UserInfo.getLoginJson(mUsername, mUserPassword));

				return HttpUtils.postRequest(loginParams, url);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;

		}

		@Override
		protected void onPostExecute(JSONObject result) {
			try {
				if (result.getBoolean(JSONParser.SUCCESS_TAG)) {
					String auth = result.getJSONObject(JSONParser.DATA_TAG)
							.getString(UserInfo.AUTH_TAG);
					DevUtils.showToast(LoginActivity.this,
							"You has logged in successful! Ur authToken is "
									+ auth);

					SharedPreferences.Editor editor = mPreferences.edit();
					// save the returned auth_token into
					// the SharedPreferences
					editor.putString("AuthToken", auth);
					editor.commit();

				} else {
					// DevUtils.showToast(LoginActivity.this, "Cannot login!");
					String error = result.getString(JSONParser.INFO_TAG);
					DevUtils.showAlert(LoginActivity.this, "Login failed",
							error);
					DevUtils.printLog("Cannot login because: " + error);
					DevUtils.printLog("" + result);
				}

			} catch (Exception e) {
				DevUtils.printLog(e.getMessage());
			} finally {
				super.onPostExecute(result);
			}

		}

	}
}