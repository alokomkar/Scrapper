package in.resultfetcher;

import java.util.Scanner;

public class BookResults {
	
	
	public static void main(String args[]){
		
		/**String variables to hold Website name and search keyword*/
		String WebSite;
		String Keyword;

		Scanner scanIn = new Scanner(System.in);
		
		/**Read Website*/
		System.out.println("Enter the Website : ");
		WebSite = scanIn.nextLine();
		
		/**Read Keyword*/
		System.out.println("Enter the Search Keyword : ");
		Keyword = scanIn.nextLine();
		
		scanIn.close();
		
		String URL = URLInitiator(WebSite, Keyword);
		if(WebSite.equalsIgnoreCase("Flipkart")){
			FlipkartBean fkbean = new FlipkartBean(URL);
			fkbean.ResultDisplay(WebSite,Keyword);
				
		}
		else if(WebSite.equalsIgnoreCase("Crossword")){
			CrossWordBean cwbean = new CrossWordBean(URL);
			cwbean.ResultDisplay(WebSite,Keyword);	
		}
		else if(WebSite.equalsIgnoreCase("Amazon")){
			AmazonBean abean = new AmazonBean(URL);
			abean.ResultDisplay(WebSite, Keyword);
		}
		
	}

	/**Method to initialize URL*/
	private static String URLInitiator(String webSite, String keyword) {
		String url = new String();
		if(webSite.equalsIgnoreCase("flipkart")){
			
			url = "http://www.flipkart.com/search?q="+keyword+"&sid=search.flipkart.com&as=off"
					+ "&as-show=on&otracker=start"
					+ "&ref=a9d6e69b-1167-4baf-9a66-e48f7ddbcf8a";
			
		}else if(webSite.equalsIgnoreCase("crossword")){
			
			url = "http://www.crossword.in/books/search?q="+keyword;
			
		}
		else if(webSite.equalsIgnoreCase("amazon")){
			url = "http://www.amazon.in/"
					+ "s/ref=nb_sb_noss/"
					+ "278-8539326-8555564?"
					+ "url=search-alias%3Dstripbooks"
					+ "&field-keywords="+keyword;
		}
		return url;
	}
	
}
