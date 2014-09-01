package cn.hugo.android.scanner.foodfinder;

/*
 * POST testing website
 * http://www.hurl.it/ 
 */

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class HttpRequestTask extends AsyncTask <String, Void, String> {

	public final static String POST_MODE = "POST";
	public final static String GET_MODE = "GET";

	String  jsonBodyString = "";
	SSLContext  mSslContext = null;
	Context ctx = null;
	String mode = "GET";
	SSLManager sslMgr = null;
	private int AcceptedErrorCode = 404;

	ArrayList <HttpRequestTaskListener> listeners = new ArrayList <HttpRequestTaskListener>();

	private ProgressDialog dialog = null;
	private String dialogTip = "Request Data from Server...";
	private boolean enableWaittingDlg = false;

	public interface HttpRequestTaskListener {
		void ServerResponse (String jsonStr);
	}

	public HttpRequestTask (Context c){
		ctx = c;
		//		sslMgr = new SSLManager();
		//		try{
		//			sslMgr.setCertificate("ca", ctx.getAssets().open(CERTIFICATE_NAME));
		//		}catch(Exception e){
		//			e.printStackTrace();
		//		}

		dialog = new ProgressDialog(ctx);
	}

	public void enableWaittingDlg (boolean showDlg, String showText){
		enableWaittingDlg = showDlg;
		dialogTip = showText;
	}
	
	public void setAcceptedErrorCode (){
		
	}

	public void setTaskProperty (String jsonStr, String m){
		jsonBodyString = jsonStr;
		mode = m;
	}

	public void setTaskProperty (String m){
		mode = m;
	}

	public void addListener (HttpRequestTaskListener l){
		listeners.add(0, l);
	}

	private String HttpRequest(String url, String method, String jsonStr) {
		String result = null;
		try{
			URL dudeValidateUrl = new URL(url); 
			int responseCode = 0;
			HttpURLConnection conn = null;
			if (dudeValidateUrl.getProtocol().toLowerCase().equals("https")) {
				/*
				 * Disable SSL validation
				 * */
				SSLCertificateValidation.disable();
				conn = (HttpsURLConnection) dudeValidateUrl.openConnection();

				//				((HttpsURLConnection) conn).setSSLSocketFactory(sslMgr.getSSLContent().getSocketFactory());
				Log.i("HttpRequestTask", "Ignore certificate validation... " + dudeValidateUrl);

			} else {
				conn = (HttpURLConnection) dudeValidateUrl.openConnection();
			}

			if (method.equals(POST_MODE)){
				//This setting only use for post mode
				conn.setRequestMethod(method);
				conn.setDoOutput(true);
				/*Don't put json string to header in our APP*/
				//				conn.setRequestProperty(headName, URLEncoder.encode(jsonStr, "UTF-8"));
			}

//			conn.setRequestProperty("content-type", "application/json"); //don't set it for food searching.
			conn.setRequestProperty("accept", "application/json");
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);
			conn.connect();

			if (method.equals(POST_MODE)){
				/*put json string to body*/
				OutputStreamWriter out = new   OutputStreamWriter(conn.getOutputStream());
				out.write(jsonStr);
				out.close();  
			}

			responseCode = conn.getResponseCode();


			if( responseCode != 200 && responseCode != AcceptedErrorCode)
			{   
				String errorContent = parseSendMessageResponse(conn.getErrorStream());              
				throw new RuntimeException(String.format("ERROR: The enqueue request failed with a " +
						"%d response code, with the following message: %s",
						responseCode, errorContent));
			}
			else
			{
				if (responseCode == AcceptedErrorCode){
					result = parseSendMessageResponse(conn.getErrorStream());
				}else{
					result = parseSendMessageResponse(conn.getInputStream()); 
				}
			}
		}catch (java.net.SocketTimeoutException e) {
			Log.i("HttpRequestTask", "Server timeout!!!");
			result = null;
		}catch(Exception e){
			e.printStackTrace();
		}

		return result;
	}

	private  String parseSendMessageResponse(InputStream in) throws Exception
	{
		// Read from the input stream and convert into a String.
		InputStreamReader inputStream = new InputStreamReader(in);
		BufferedReader buff = new BufferedReader(inputStream);

		StringBuilder sb = new StringBuilder();
		String line = buff.readLine();
		while(line != null)
		{          
			sb.append(line);
			line = buff.readLine();
		}

		return sb.toString();
	}

	@Override
	protected void onPreExecute() {
		if ( enableWaittingDlg ){
			dialog.setMessage ( dialogTip );
			dialog.show();
		}

		super.onPreExecute();
	}

	@Override
	protected String doInBackground(String... params) {
		String url = params[0];

		return HttpRequest (url, mode, jsonBodyString);
	}

	@Override
	protected void onPostExecute(String result) {

		if (this.dialog.isShowing()) {
			this.dialog.dismiss();
		}

		if (result == null){			
			Toast.makeText(ctx, "Can not access to network, please check.",Toast.LENGTH_SHORT).show();
			return;
		}else{	
			Log.i("HttpRequestTask","Server Response:-->" + result);


			if (listeners.size() > 0){
				listeners.get(0).ServerResponse (result);
			}
		}
	}
}




