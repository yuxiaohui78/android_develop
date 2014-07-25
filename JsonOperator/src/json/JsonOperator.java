package json;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
public class JsonOperator {

	public static void main(String[] args) {
		PlugInConfigurationParser ();
		System.out.println();
		ExerciseListParser ();		
	}
	
	public static void PlugInConfigurationParser (){
		try {
			InputStream is = new FileInputStream("plugin_configuration.txt");

			PlugInConfiguration obj = JsonUtils.objectFromJson (is, PlugInConfiguration.class);
			
			is.close();
			
			System.out.println ("-------------------json to object--------------------");
			obj.print();
			
			System.out.println ("-------------------object to json --------------------");
			System.out.println (JsonUtils.jsonFromObject(obj));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void ExerciseListParser (){
		try {
			InputStream is = new FileInputStream("exercise_json.txt");

			ExerciseData obj = JsonUtils.objectFromJson (is, ExerciseData.class);
			
			is.close();
			
			System.out.println ("-------------------json to object--------------------");
			obj.print();
			
			System.out.println ("-------------------object to json --------------------");
			System.out.println (JsonUtils.jsonFromObject(obj));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
