package cn.hugo.android.scanner.foodfinder;

public class BaseNutritionData {

/*
The total nutrition data from the server likes the following.
{"old_api_id":null,
"item_id":"51d37f0ccc9bff5553aaa993",
"item_name":"Sweetened Condensed Milk",
"leg_loc_id":null,
"brand_id":"51db37c3176fe9790a899241",
"brand_name":"Eagle Brand",
"item_description":"Sweetened Condensed",
"updated_at":"2014-08-20T17:44:19.000Z",
"nf_ingredient_statement":"Whole Milk, Sugar.</p><p>Do not heat in can.",
"nf_water_grams":null,
"nf_calories":130,
"nf_calories_from_fat":30,
"nf_total_fat":3,
"nf_saturated_fat":2,
"nf_trans_fatty_acid":0,
"nf_polyunsaturated_fat":null,
"nf_monounsaturated_fat":null,
"nf_cholesterol":10,"nf_sodium":35,
"nf_total_carbohydrate":22,
"nf_dietary_fiber":0,
"nf_sugars":22,
"nf_protein":3,
"nf_vitamin_a_dv":2,
"nf_vitamin_c_dv":0,
"nf_calcium_dv":10,
"nf_iron_dv":0,
"nf_refuse_pct":null,
"nf_servings_per_container":10,
"nf_serving_size_qty":2,
"nf_serving_size_unit":"tbsp",
"nf_serving_weight_grams":39,
"allergen_contains_milk":null,
"allergen_contains_eggs":null,
"allergen_contains_fish":null,
"allergen_contains_shellfish":null,
"allergen_contains_tree_nuts":null,
"allergen_contains_peanuts":null,
"allergen_contains_wheat":null,
"allergen_contains_soybeans":null,
"allergen_contains_gluten":null,
"usda_fields":null}
*/
	
	private String item_name = "";
	private String brand_name = "";
	private String item_description = "";

	private String nf_ingredient_statement = "";
	private String nf_water_grams = "";
	private String nf_calories = "";   // calories
	private String nf_calories_from_fat = "";
	private String nf_total_fat = "";  // total fat
	private String nf_saturated_fat = ""; //saturated fat (饱和脂肪)
	private String nf_trans_fatty_acid = ""; //反式脂肪酸
	private String nf_polyunsaturated_fat = "";//polyunsaturated fat (多元不饱和脂肪)
	private String nf_monounsaturated_fat = "";//monounsaturated fat 单不饱和脂肪

	private String nf_cholesterol = "";//cholesterol 胆固醇
	private String nf_sodium = ""; //sodium 钠
	private String nf_total_carbohydrate = ""; //total carbohydrate
	private String nf_dietary_fiber= "";//dietary fiber 食用纤维
	private String nf_sugars = "";  //sugars 糖
	private String nf_protein = ""; //protein 蛋白质
	private String nf_vitamin_a_dv=""; //vitamin A
	private String nf_vitamin_c_dv = ""; //vitamin C
	private String nf_calcium_dv = ""; // calcium 钙
	private String nf_iron_dv = ""; //iron 铁含量
	private String nf_servings_per_container = "";//total grams of container = nf_servings_per_container * nf_serving_weight_grams
	private String nf_serving_size_qty = "";//
	private String nf_serving_size_unit = ""; //tbsp 一汤匙 （基础单位）
	private String nf_serving_weight_grams = "";//一汤匙 克 nf_serving_weight_grams = nf_serving_size_qty * nf_serving_size_unit
	
	public String toString (){
		return item_name +", "+
				brand_name +", "+
				item_description +", "+

				nf_ingredient_statement +", "+
				nf_water_grams +", "+
				nf_calories +", "+   // calories
				nf_calories_from_fat +", "+
				nf_total_fat +", "+  // total fat
				nf_saturated_fat +", "+ //saturated fat (饱和脂肪)
				nf_trans_fatty_acid +", "+ //反式脂肪酸
				nf_polyunsaturated_fat +", "+//polyunsaturated fat (多元不饱和脂肪)
				nf_monounsaturated_fat +", "+//monounsaturated fat 单不饱和脂肪

				nf_cholesterol +", "+//cholesterol 胆固醇
				nf_sodium +", "+ //sodium 钠
				nf_total_carbohydrate +", "+ //total carbohydrate
				nf_dietary_fiber+", "+//dietary fiber 食用纤维
				nf_sugars +", "+  //sugars 糖
				nf_protein +", "+ //protein 蛋白质
				nf_vitamin_a_dv+", " + //vitamin A
				nf_vitamin_c_dv +", "+ //vitamin C
				nf_calcium_dv +", "+ // calcium 钙
				nf_iron_dv +", "+ //iron 铁含量
				nf_servings_per_container +", "+//total grams of container = nf_servings_per_container * nf_serving_weight_grams
				nf_serving_size_qty +", "+//
				nf_serving_size_unit +", "+ //tbsp 一汤匙 （基础单位）
				nf_serving_weight_grams ;//一汤匙 克 nf_serving_weight_grams = nf_serving_size_qty * nf_serving_size_unit
	}
	
	public String getFoodName (){
		return item_name;

	}
	public String getFoodBrand (){
		return brand_name;
		
	}
	public String getFoodDescription (){
		return item_description;
	}
}
