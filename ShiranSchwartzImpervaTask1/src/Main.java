import org.json.JSONException;


public class Main {
	
	public static void main(String[] args) throws JSONException {
		
		GitHubParser gitHubPerser = new GitHubParser();
		
		SearchResultsCollector searchResultsCollector = gitHubPerser.parseGibHubSearchResults(5, "security");	 
        
		searchResultsCollector.createFileContainJsons();
		
	}
}
