package json;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;


public class JsonUtils {

    private static Gson sGson = new Gson();

    public static String jsonFromObject(Object object) {
        return sGson.toJson(object);
    }

    public static <T> T objectFromJson(String json, Class<T> clz) {
        return sGson.fromJson(json, clz);
    }

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