package com.haui.android.sns.entity;

import org.json.JSONException;
import org.json.JSONObject;

public class UserInfo {

	public static final String LOGIN_OK_CODE = "Logged in";
	public static final String LOGIN_FAILED_CODE = "Login Failed";

	public static final String USER_LOGIN_PARAM = "login";
	public static final String USER_NAME_PARAM = "user[username]";
	public static final String USER_EMAIL_PARAM = "user[email]";
	public static final String USER_PASSWOD_PARAM = "user[password]";
	public static final String USER_PASSWOD_CONFIRM_PARAM = "user[password_confirmation]";
	public static final String AUTH_TAG = "auth_token";

	private String email, username, password, auth_token;

	public UserInfo(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public UserInfo(String mUserName, String mUserEmail, String mUserPassword) {
		this(mUserName, mUserPassword);
		this.email = mUserEmail;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuthToken() {
		return auth_token;
	}

	public void setAuthToken(String auth_token) {
		this.auth_token = auth_token;
	}

	public JSONObject getRegisterJson() throws JSONException {
		JSONObject user = new JSONObject();

		user.put("username", username);
		user.put("email", email);
		user.put("password", password);
		user.put("password_confirmation", password);
		return user;
	}

	public static JSONObject getLoginJson(String login, String password)
			throws JSONException {
		JSONObject user = new JSONObject();
		user.put(USER_LOGIN_PARAM, login);
		user.put("password", password);
		return user;
	}

	public static JSONObject getLoginJson(UserInfo userInfo)
			throws JSONException {
		JSONObject user = new JSONObject();
		user.put(USER_LOGIN_PARAM, userInfo.username);
		user.put("password", userInfo.username);
		return user;
	}

}
