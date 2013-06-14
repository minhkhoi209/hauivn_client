package com.haui.android.sns.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.haui.android.sns.entity.ResponData;
import com.haui.android.sns.entity.UserInfo;

public class HttpUtils {

	public static ResponData postRequest(ArrayList<NameValuePair> params,
			String postUrl) {

		ResponData res = null;
		try {
			res = getErrorResponData();
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(postUrl);
			UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params,
					HTTP.UTF_8);
			post.setEntity(ent);

			HttpResponse responsePOST = client.execute(post);
			HttpEntity resEntity = responsePOST.getEntity();
			if (resEntity != null) {

				res.setResponse(EntityUtils.toString(resEntity));
				res.setStatusCode(responsePOST.getStatusLine().getStatusCode());

				DevUtils.printLog("Stauscode: " + res.getStatusCode()
						+ ", Respond string: " + res.getReponse());

				return res;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			DevUtils.printLog("Cannot encode params!");
		} catch (ClientProtocolException e) {

			e.printStackTrace();
			DevUtils.printLog("Cannot connect to server!");
		} catch (ParseException e) {

			e.printStackTrace();
			DevUtils.printLog("Cannot read response");
		} catch (IOException e) {
			// XXX Auto-generated catch block
			e.printStackTrace();
			DevUtils.printLog("Cannot connect to server!");
		}

		return res;

	}

	public static JSONObject postRequest(JSONObject params, String postUrl) {

		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(postUrl);
		JSONObject json = new JSONObject();
		try {

			StringEntity entity = new StringEntity(params.toString(), "UTF-8");
			post.setEntity(entity);
			post.setHeader("Accept", "application/json");
			post.setHeader("Content-Type", "application/json");
			HttpResponse response = client.execute(post);
			HttpEntity reEntity = response.getEntity();
			json = new JSONObject(EntityUtils.toString(reEntity));
			return json;

		} catch (HttpResponseException e) {

			DevUtils.printLog("Response Code: " + e.getStatusCode());
			json = JsonHelper.getErrorJsonObject("Invalid params");

		} catch (IOException e) {

			e.printStackTrace();
			json = JsonHelper.getErrorJsonObject("Connection failed!");
		} catch (JSONException e) {

			e.printStackTrace();
			json = JsonHelper.getErrorJsonObject();
		}

		return json;
	}

	private static ResponData getErrorResponData() {
		try {
			JSONObject errorJson = new JSONObject();
			errorJson.put(JSONParser.SUCCESS_TAG, false);
			errorJson.put(JSONParser.INFO_TAG, "Cannot load data from server!");
			return new ResponData(errorJson.toString(), 400);
		} catch (JSONException e) {
			DevUtils.printLog(e.getMessage());
		}
		return null;
	}

	public static ArrayList<NameValuePair> createRegParams(UserInfo userInfo) {

		ArrayList<NameValuePair> ulrParams = new ArrayList<NameValuePair>();
		ulrParams.add(new BasicNameValuePair(UserInfo.USER_NAME_PARAM, userInfo
				.getUsername()));
		ulrParams.add(new BasicNameValuePair(UserInfo.USER_EMAIL_PARAM,
				userInfo.getEmail()));
		ulrParams.add(new BasicNameValuePair(UserInfo.USER_EMAIL_PARAM,
				userInfo.getEmail()));
		ulrParams.add(new BasicNameValuePair(UserInfo.USER_PASSWOD_PARAM,
				userInfo.getPassword()));
		ulrParams.add(new BasicNameValuePair(UserInfo.USER_EMAIL_PARAM,
				userInfo.getPassword()));
		return ulrParams;

	}
}