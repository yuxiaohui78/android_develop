package cn.hugo.android.scanner.foodfinder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class JSONUtil {
    
    @SuppressWarnings("unused")
    private static final String TAG = "JSONUtil";
    private static Gson sGson = new Gson();

    public static String jsonFromObject(Object object) {
        return sGson.toJson(object);
    }

    public static <T> T objectFromJson(String json, Class<T> clz) {
        return sGson.fromJson(json, clz);
    }

    //used for collections
    // FIXME Type seems have bug on some devices, like HTC Sensation XL with Beats Audio X315e
    //    public static <T> T objectFromJson(String json, Type typeOfT) {
//        return sGson.fromJson(json, typeOfT);
//    }
//
//    public static<T> T objectFromJson(InputStream in,Type typeOfT) {
//        try {
//            JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
//            T object = sGson.fromJson(reader, typeOfT);
//            reader.close();
//            return object;
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static<T> T objectFromJson(InputStream in, Class<T> clz) {
        try {
            JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
            T object = sGson.fromJson(reader, clz);
            reader.close();
            return object;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
