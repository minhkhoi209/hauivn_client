package com.haui.android.sns.base;


public class TimShotRequest {
	/*
	 * public static int curDownImg = 0; public static int curDownAva = 0;
	 * private static final String TAG = "Download"; private static
	 * DownloadImageTask dlit; private static DownloadAvatarTask dlat; private
	 * static ArrayList<TimShotPhoto> timpho; private static
	 * ArrayList<TimShotUser> timus; private static BaseAdapter listImageAdap =
	 * null; private static BaseAdapter listAvaAdap = null; private static
	 * BaseAdapter coverImageAdapter = null; public static boolean
	 * isDownloadImageComplete = false; public static boolean
	 * isDownloadAvaComplete = false; private static File checkExist;
	 * 
	 * public static void setImageAdapter(BaseAdapter liA) { listImageAdap =
	 * liA; }
	 * 
	 * public static void setDownListImage(ArrayList<TimShotPhoto> setPho,
	 * BaseAdapter lAdap) { timpho = setPho; listImageAdap = lAdap; curDownImg =
	 * 0; isDownloadImageComplete = false; }
	 * 
	 * public static void setDownListImage(ArrayList<TimShotPhoto> setPho,
	 * BaseAdapter lAdap, BaseAdapter cover) { // remove all picture have
	 * already download // for(int i=0;i<setPho.size();i++){ // if(timpho!=null)
	 * // for(int j = 0;j<timpho.size();j++){ //
	 * if(setPho.get(i).getPhotoID()==timpho.get(j).getPhotoID()){ //
	 * setPho.remove(i); // timpho.remove(j); // i--; // break; // } // } // }
	 * timpho = setPho; listImageAdap = lAdap; coverImageAdapter = cover;
	 * curDownImg = 0; isDownloadImageComplete = false; }
	 * 
	 * public static void setDownListAvatar(ArrayList<TimShotUser> timu,
	 * BaseAdapter lAdap) { timus = timu; listAvaAdap = lAdap; curDownAva = 0;
	 * isDownloadAvaComplete = false; }
	 * 
	 * public static void startDownloadAva(Activity acA) { int size = 0; try {
	 * size = timus.size(); } catch (Exception e) { Log.e("abc",
	 * "error get size of timus"); isDownloadAvaComplete = true; return; } if
	 * (curDownAva < size) { // Log.d("should", //
	 * "downloaded"+" "+String.valueOf
	 * (curDownImg)+"  "+String.valueOf(timpho.get
	 * (curDownImg).getPhotoID()+timpho.get(curDownImg).getPhotoTitle()));
	 * downloadAvaTo(timus.get(curDownAva).getAvatarUrl(),
	 * String.valueOf(timus.get(curDownAva).getId()) + ".jpeg", acA); if
	 * (listAvaAdap != null) listAvaAdap.notifyDataSetChanged(); } else { if
	 * (listAvaAdap != null) listAvaAdap.notifyDataSetChanged(); curDownAva = 0;
	 * listAvaAdap = null; timus = null; isDownloadAvaComplete = true; } }
	 * 
	 * public static void startDownloadImg(Activity acI) { int size = 0; try {
	 * size = timpho.size(); } catch (Exception e) { Log.e("abc",
	 * "error get size of timpho"); isDownloadImageComplete = true; return; } if
	 * (curDownImg < size) { Log.d("should", "downloaded" + " " +
	 * String.valueOf(curDownImg) + "  " + String.valueOf(timpho.get(curDownImg)
	 * .getPhotoID() + timpho.get(curDownImg).getPhotoTitle()));
	 * downloadImageTo(timpho.get(curDownImg).getPhotoUrl(),
	 * String.valueOf(timpho.get(curDownImg).getPhotoID()) + ".jpeg", acI); if
	 * (listImageAdap != null) listImageAdap.notifyDataSetChanged(); if
	 * (coverImageAdapter != null) coverImageAdapter.notifyDataSetChanged(); }
	 * else { if (listImageAdap != null) listImageAdap.notifyDataSetChanged();
	 * if (coverImageAdapter != null) coverImageAdapter.notifyDataSetChanged();
	 * curDownImg = 0; if (acI.getClass() != HomePage.class) timpho = null;
	 * listImageAdap = null; isDownloadImageComplete = true; } }
	 * 
	 * public static void downloadAvaTo(String imgUrl, String imgName, Activity
	 * ac) { URL reviewImageURL; try { reviewImageURL = new URL(imgUrl); String
	 * filleName = Environment.getExternalStorageDirectory() .getAbsolutePath()
	 * + "/" + TimShotCode.avaFolderName + "/" + imgName; checkExist = new
	 * File(filleName); if (!checkExist.exists()) { dlat = new
	 * DownloadAvatarTask(); dlat.setActivity(ac); dlat.setImageName(imgName);
	 * dlat.execute(reviewImageURL); Log.v("log_tag", "downloaded ava no"); File
	 * sdImageMainDirectory = new File(
	 * Environment.getExternalStorageDirectory(), TimShotCode.avaFolderName); if
	 * (sdImageMainDirectory.mkdirs()) Log.d("log_tag", "Directory ava created "
	 * + sdImageMainDirectory.getAbsolutePath()); else Log.d("log_tag",
	 * " ava fail create" + sdImageMainDirectory.getAbsolutePath()); } else {
	 * Log.d(TAG, "downloaded ava " + imgName); curDownAva++;
	 * startDownloadAva(ac); }
	 * 
	 * } catch (MalformedURLException e) { Log.v(TAG, e.toString());
	 * curDownAva++; startDownloadAva(ac); } }
	 * 
	 * public static void downloadImageTo(String imgUrl, String imgName,
	 * Activity ac) { try { String filleName =
	 * Environment.getExternalStorageDirectory() .getAbsolutePath() + "/" +
	 * TimShotCode.folderName + "/" + imgName; try { checkExist = new
	 * File(filleName); } catch (Exception e) {
	 * 
	 * } if (!checkExist.exists()) { checkExist = null; dlit = new
	 * DownloadImageTask(); dlit.setActivity(ac); dlit.setImageName(imgName);
	 * dlit.execute(new URL(imgUrl)); Log.v("log_tag", "downloaded no"); File
	 * sdImageMainDirectory = new File(
	 * Environment.getExternalStorageDirectory(), TimShotCode.folderName); if
	 * (sdImageMainDirectory.mkdirs()) Log.d("log_tag", "Directory created " +
	 * sdImageMainDirectory.getAbsolutePath()); else Log.d("log_tag",
	 * "fail create" + sdImageMainDirectory.getAbsolutePath()); } else {
	 * Log.d(TAG, "downloaded " + imgName); curDownImg++; startDownloadImg(ac);
	 * }
	 * 
	 * } catch (MalformedURLException e) { Log.v(TAG, e.toString());
	 * curDownImg++; startDownloadImg(ac); } }
	 * 
	 * public static RequestData UploadAvarta(String postUrl, String fileName) {
	 * RequestData res = new RequestData(); try { HttpClient httpClient = new
	 * DefaultHttpClient(); HttpPost postRequest = new HttpPost(postUrl);
	 * 
	 * MultipartEntity reqEntity = new MultipartEntity(
	 * HttpMultipartMode.BROWSER_COMPATIBLE); reqEntity.addPart("file", new
	 * FileBody(new File(fileName), "image/jpeg"));
	 * postRequest.setEntity(reqEntity); HttpResponse response =
	 * httpClient.execute(postRequest); HttpEntity resEntity =
	 * response.getEntity(); res = new
	 * RequestData(EntityUtils.toString(resEntity), response
	 * .getStatusLine().getStatusCode()); Log.i("RESPONSE", res.getReponse());
	 * Log.i("status code", String.valueOf(res.getStatusCode())); } catch
	 * (Exception e) { } return res; }
	 * 
	 * public static RequestData UploadImage(String postUrl, String fileName,
	 * String title) { RequestData res = new RequestData(); try { HttpClient
	 * httpClient = new DefaultHttpClient(); HttpPost postRequest = new
	 * HttpPost(postUrl);
	 * 
	 * MultipartEntity reqEntity = new MultipartEntity(
	 * HttpMultipartMode.BROWSER_COMPATIBLE); reqEntity.addPart("title", new
	 * StringBody(title, "text/plain", Charset.forName("UTF-8")));
	 * reqEntity.addPart("description", new StringBody(""));
	 * reqEntity.addPart("file", new FileBody(new File(fileName),
	 * "image/jpeg")); reqEntity.addPart("width", new StringBody("100"));
	 * reqEntity.addPart("height", new StringBody("100"));
	 * 
	 * postRequest.setEntity(reqEntity); HttpResponse response =
	 * httpClient.execute(postRequest); HttpEntity resEntity =
	 * response.getEntity(); res = new
	 * RequestData(EntityUtils.toString(resEntity), response
	 * .getStatusLine().getStatusCode()); Log.i("RESPONSE", res.getReponse());
	 * Log.i("status code", String.valueOf(res.getStatusCode())); } catch
	 * (Exception e) { } return res; }
	 * 
	 * public static RequestData PostRequest(ArrayList<NameValuePair> params,
	 * String postUrl) { RequestData res = null; try { HttpClient client = new
	 * DefaultHttpClient(); HttpPost post = new HttpPost(postUrl);
	 * UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params, HTTP.UTF_8);
	 * post.setEntity(ent); HttpResponse responsePOST = client.execute(post);
	 * HttpEntity resEntity = responsePOST.getEntity(); if (resEntity != null) {
	 * res = new RequestData(EntityUtils.toString(resEntity),
	 * responsePOST.getStatusLine().getStatusCode()); Log.i("RESPONSE",
	 * res.getReponse()); Log.i("status code",
	 * String.valueOf(res.getStatusCode())); } } catch (Exception e) {
	 * e.printStackTrace(); } return res;
	 * 
	 * }
	 * 
	 * public static RequestData PutRequest(ArrayList<NameValuePair> params,
	 * String putUrl) { RequestData res = null; try { HttpClient client = new
	 * DefaultHttpClient(); HttpPut put = new HttpPut(putUrl);
	 * UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params, HTTP.UTF_8);
	 * put.setEntity(ent); HttpResponse responsePOST = client.execute(put);
	 * HttpEntity resEntity = responsePOST.getEntity(); if (resEntity != null) {
	 * res = new RequestData(EntityUtils.toString(resEntity),
	 * responsePOST.getStatusLine().getStatusCode()); Log.i("RESPONSE",
	 * res.getReponse()); Log.i("status code",
	 * String.valueOf(res.getStatusCode())); } } catch (Exception e) {
	 * e.printStackTrace(); } return res;
	 * 
	 * }
	 * 
	 * public static String GetDataRequest(String getUrl) throws Exception {
	 * String res = null; BufferedReader in = null; try { HttpClient client =
	 * new DefaultHttpClient(); HttpGet request = new HttpGet();
	 * request.setURI(new URI(getUrl)); HttpResponse response =
	 * client.execute(request); in = new BufferedReader(new
	 * InputStreamReader(response.getEntity() .getContent())); StringBuffer sb =
	 * new StringBuffer(""); String line = ""; String NL =
	 * System.getProperty("line.separator"); while ((line = in.readLine()) !=
	 * null) { sb.append(line + NL); } in.close(); res = sb.toString(); }
	 * finally { if (in != null) { try { in.close(); } catch (IOException e) {
	 * e.printStackTrace(); } } } return res; }
	 */
}
