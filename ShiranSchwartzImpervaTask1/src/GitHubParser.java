import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GitHubParser {
	
	public SearchResultsCollector parseGibHubSearchResults(int amuontPagesSearch, String searchWords){
		String link = "";
		try {
			link = "https://github.com/search?q=" + URLEncoder.encode(searchWords, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		List<SearchResult> resultList = new ArrayList<>();
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        WebDriver browser = new ChromeDriver();
        browser.get(link);
            
        int pageNumber = 1;
        while(pageNumber<=amuontPagesSearch) {    	
	        List<WebElement> repoListItems = browser.findElements(By.className("repo-list-item"));
	        for (WebElement repoItem : repoListItems) {  
	        	
	        	String title = repoItem.findElement(By.className("v-align-middle")).getText();
	        	String description = findDecriptionResult(repoItem);
	        	List<String> tagsList = findTagsListResult(repoItem);
	        	String time = repoItem.findElement(By.cssSelector("relative-time")).getAttribute("datetime");
	        	String language = repoItem.findElement(By.className("min-width-0")).getText();
	        	String starsStr = repoItem.findElement(By.className("pl-2")).findElement(By.className("muted-link")).getText();
	        	int stars = (int) (starsStr.contains("k") ? Double.parseDouble(starsStr.replace("k", ""))*1000 :  Double.parseDouble(starsStr));
	        	
	        	resultList.add(new SearchResult(title, description, tagsList, time, language, stars));
			}
	        pageNumber++;
	        browser.get(link + "&p=" + pageNumber);
        }
        browser.close();
        return new SearchResultsCollector(resultList);
	}
	



	private static List<String> findTagsListResult(WebElement repoItem) {
		List<String> tagsList = new ArrayList<>();
		List<WebElement> tagElementList = repoItem.findElements(By.className("topic-tag"));
		for (WebElement tagElement : tagElementList) {
			tagsList.add(tagElement.getText());
		}
		return tagsList;
	}

	private static String findDecriptionResult(WebElement repoItem) {
		String decription = "";
		try {
			decription = repoItem.findElement(By.cssSelector("p.col-12")).getText();
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			//e.printStackTrace();
		}
		return decription;
	}

}
