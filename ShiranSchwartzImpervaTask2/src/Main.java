import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class Main {
	
	public static void main(String[] args) {
		if(args.length==0) {
			System.out.println("Use: ShiranSchwartzImpervaTask2.jar <JsonFileName>");
			return;
		}
		String nameFileContainjsons = args[0];
		try (BufferedReader br = new BufferedReader(new FileReader(nameFileContainjsons))) {
		    String jsonLine;
		    int fileNumber = 1;
		    Map<JSONObject, Integer> jsons = new HashMap<JSONObject, Integer>();
		    
		    
		    while ((jsonLine = br.readLine()) != null) {
		    	JSONObject jsonLineObject = new JSONObject();
				try {
					jsonLineObject = new JSONObject(jsonLine);
				} catch (JSONException e2) {
					e2.printStackTrace();
				}        
		        JSONObject newJsonLineObject = new JSONObject();
		 		try {
					newJsonLineObject.put("Title", jsonLineObject.get("Title"));
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
		 		try {
					newJsonLineObject.put("Tags", jsonLineObject.get("Tags"));
				} catch (JSONException e) {
					e.printStackTrace();
				}
		 		try {
					newJsonLineObject.put("Language", jsonLineObject.get("Language"));
				} catch (JSONException e) {
					e.printStackTrace();
				}
		 		jsons.put(newJsonLineObject, fileNumber);
		 		fileNumber++;
		    }
		    
		    jsons.entrySet().parallelStream().forEach(
		    		entry ->
		    			createFileForJsonObject(entry.getKey(), entry.getValue()));
		    
		    
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}



	private static String createFileForJsonObject(JSONObject jsonObject, int fileNumber) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd, HH.mm.ss.SSS").format(new Date());
		String fileName = "SecurityResultGitHub-" + fileNumber + "-" + timeStamp + ".json";
	    try (FileWriter file = new FileWriter(fileName)) {
			file.write(jsonObject.toString());
	    	return fileName;
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}   
	}

}
