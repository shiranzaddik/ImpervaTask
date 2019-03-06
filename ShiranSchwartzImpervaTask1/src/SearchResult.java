import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class SearchResult {
	
	String title;
	String description;
	List<String> tagsList;
	String time;
	String language;
	int stars;
	
	public SearchResult(String title, String description, List<String> tagsList, String time, String language,
			int stars) {
		super();
		this.title = title;
		this.description = description;
		this.tagsList = tagsList;
		this.time = time;
		this.language = language;
		this.stars = stars;
	}
	
	public JSONObject createJsonObj() throws JSONException {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("Title", title);  
		jsonObj.put("Description", description);
		jsonObj.put("Time", time);
		jsonObj.put("Language", language);
		jsonObj.put("Stars", stars);
		
		JSONArray tagsListJson = new JSONArray();
		for (String string : tagsList) {
			tagsListJson.put(string);
		}
		jsonObj.put("Tags", tagsListJson);
        return jsonObj;

	}

}
