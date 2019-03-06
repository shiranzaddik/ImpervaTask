import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

public class SearchResultsCollector {
	
	List<SearchResult> SearchResultsList = new ArrayList<>();

	public SearchResultsCollector(List<SearchResult> searchResultsList) {
		super();
		SearchResultsList = searchResultsList;
	}
    
	public String createFileContainJsons() {
		List<JSONObject> jsonResultList = createJsonList();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd, HH.mm").format(new Date());
		String fileName = "SecurityResultGitHub-" + timeStamp + ".json";
        try (FileWriter file = new FileWriter(fileName)) {
        	for (JSONObject jsonObject : jsonResultList) {
        		file.write(jsonObject.toString());
        		file.write(System.lineSeparator());
			}
        	return fileName;
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}   
	}

	private List<JSONObject> createJsonList() {
		List<JSONObject> jsonResultList = new ArrayList<>();
        for (SearchResult searchResult : this.SearchResultsList) {
        	try {
        		jsonResultList.add(searchResult.createJsonObj());
			} catch (JSONException e) {
				e.printStackTrace();
			}		
		}
        return jsonResultList;
	}
    

}
