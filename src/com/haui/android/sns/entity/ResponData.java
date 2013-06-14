package com.haui.android.sns.entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ResponData {
	private String response;
	private int statuscode;

	public ResponData() {
		// TODO Auto-generated constructor stub
		this.response = new String("");
		this.statuscode = 0;
	}

	public ResponData(String res, int sta) {
		this.response = res;
		this.statuscode = sta;
	}

	public String getReponse() {
		return response;
	}

	public int getStatusCode() {
		return statuscode;
	}

	public void setResponse(String r) {
		response = r;
	}

	public void setStatusCode(int s) {
		statuscode = s;
	}

	public JSONObject getJsonData() {
		try {
			return new JSONObject(response);
		} catch (JSONException e) {
			// XXX Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public JSONArray getJsonArray() {
		try {
			return new JSONArray(response);
		} catch (JSONException e) {
			// XXX Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
