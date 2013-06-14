package com.haui.android.sns.utils;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.haui.android.sns.entity.Post;
import com.haui.android.sns.entity.UserInfo;

public class JSONParser {

	public static final String SUCCESS_TAG = "success";
	public static final String INFO_TAG = "info";
	public static final String DATA_TAG = "data";

	public static String getLoginRespon(String jsonString) throws JSONException {

		JSONObject jsonObject = new JSONObject(jsonString);
		String success = jsonObject.getString(JSONParser.SUCCESS_TAG);

		if (Boolean.parseBoolean(success)) {
			String info = jsonObject.getString(JSONParser.INFO_TAG);
			if (info.equals(UserInfo.LOGIN_OK_CODE)) {
				JSONObject data = jsonObject.getJSONObject(JSONParser.DATA_TAG);
				if (!data.isNull(UserInfo.AUTH_TAG)) {
					String auth = data.getString(UserInfo.AUTH_TAG);
					DevUtils.printLog("Success " + success + " info: " + info
							+ "auth: " + auth);
					return auth;

				} else {
					DevUtils.printLog("Problem with user's auth_token!");
				}
			} else {
				DevUtils.printLog("Login not OK!");
			}
		} else {
			DevUtils.printLog("Server say: " + success);
		}

		return null;
	}

	public static Post getPostFromJson(String json) {

		try {
			JSONObject jsonObject = new JSONObject(json);
			String content = jsonObject.getString(Post.CONTENT_TAG);
			int user = jsonObject.getInt(Post.USER_TAG);
			String postAt = jsonObject.getString(Post.POST_AT_TAG);
			DateTime postTime = DateTime.parse(postAt);
			return new Post(user, content, postTime);
		} catch (JSONException e) {

			return null;
		}
	}

	public static ArrayList<Post> getPosts(String json) {
		ArrayList<Post> allPosts = new ArrayList<Post>();
		try {
			JSONArray postArray = new JSONArray(json);
			for (int i = 0; i < postArray.length(); i++) {
				String postJson = postArray.getJSONObject(i).toString();
				Post post = getPostFromJson(postJson);
				allPosts.add(post);
			}

			return allPosts;

		} catch (JSONException e) {
			return null;
		}

	}
}
