package cn.hugo.android.scanner.foodfinder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.json.JSONObject;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

/*
 * https://api.nutritionix.com/v1_1/item?upc=652729101133&appId=627c7b12&appKey=cd5439dd8daf61d57ed2418be0e869a5
 * barcode = 652729101133
 * appId = 627c7b12;
 * appKey = cd5439dd8daf61d57ed2418be0e869a5;
 * 
 * if the upc is wrong or there is no the result for this upc. Returning this data.
 * {"error_message":"Item ID or UPC was invalid","error_code":"item_not_found","status_code":404}
 */

public class FoodNutritionFinder {
	protected String appId = "627c7b12";
	protected String appKey = "cd5439dd8daf61d57ed2418be0e869a5";
	protected Context context = null;
	protected FoodNutritionFinderListener mListener = null;
	
	public FoodNutritionFinder (Context c, FoodNutritionFinderListener cb){
		context = c;
		mListener = cb;
	}
	
	private String getFoodRequestUrl (String upc, String Id, String Key){
		
		return "https://api.nutritionix.com/v1_1/item?upc=" + upc + "&appId=" + Id + "&appKey=" + Key;
	}
	
	public interface FoodNutritionFinderListener{
		public void findFoodSuccess (BaseNutritionData bnd);
		public void findFoodFailed (String error_message, String error_code, int status_code);
	}
	
	public void getFoodNutritionFromUPC (String upc){

		HttpRequestTask hr = new HttpRequestTask (context);
		hr.addListener(new GETRequestListener());
		hr.setTaskProperty("GET");
		hr.execute(getFoodRequestUrl (upc, appId, appKey));
	}
	
	private class GETRequestListener implements HttpRequestTask.HttpRequestTaskListener{
		@Override
		public void ServerResponse(String jsonStr) {

			if (!isErrorMessage (jsonStr)){
				try {
		        	Gson sGson = new Gson();
		        	BaseNutritionData object = sGson.fromJson(jsonStr, BaseNutritionData.class);
		        	if (mListener != null){
		        		mListener.findFoodSuccess(object);
		        	}
		        } catch(Exception e){
		            e.printStackTrace();
		        }
			}			
		}		
	}
	
	private boolean isErrorMessage (String jsonStr){
		try{
			JSONObject json = new JSONObject (jsonStr);
			if (json.has("error_message") && json.has("error_code")){

				if (mListener != null){
					String error_message = json.getString("error_message");
					String error_code =  json.getString("error_code");
					int status_code = json.getInt("status_code");
					mListener.findFoodFailed(error_message, error_code, status_code);
				}
				return true;
			}else{
				return false;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return true;
	}
}


