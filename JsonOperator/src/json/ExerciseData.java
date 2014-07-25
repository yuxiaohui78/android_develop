package json;

import java.util.ArrayList;

public class ExerciseData {
	public String username = "";
	public int status = 0;
	public int totalCount = 0;
	public ArrayList <DiaryItem> itemArray;
	
	public class DiaryItem{
		public String type;
		public String description;
		public int complete_value;
		public String unit;
		public int calories;
	}
	
	public void print (){
    	System.out.println("username 	= " + username);
    	System.out.println("status 		= " + status);
    	System.out.println("totolCount	= " + totalCount);
    	
    	for (DiaryItem i  : itemArray){
    		System.out.println("type =" + i.type + ", description=" + i.description + 
    				", description=" + i.complete_value + ", unit=" + i.unit + ", calories=" + i.calories);
    	}
	}
}
