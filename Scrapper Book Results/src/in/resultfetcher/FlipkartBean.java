package in.resultfetcher;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.beans.FilterBean;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.CompositeTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.util.SimpleNodeIterator;

public class FlipkartBean implements ScrapeHTML{
	
	/**Variable URL to hold the url of the web site.*/
	String URL;
	FilterBean bean = new FilterBean ();

	public FilterBean getBean() {
		return bean;
	}

	public void setURL(String uRL) {
		URL = uRL;
		bean.setURL(URL);

	}
	
	/**Constructor : initializes URL and sets Filters on FilterBean */
	public FlipkartBean(String url){
		setURL(url);
		bean.setFilters (FilterConfig());
	}

	/**Method to configure Filter*/
	public NodeFilter[] FilterConfig(){
		HasAttributeFilter BookNameFilter = new HasAttributeFilter("class", "fk-display-block");
		HasAttributeFilter AuthorFilter = new HasAttributeFilter("class","pu-extra fk-font-11");
		HasAttributeFilter PriceFilter = new HasAttributeFilter("class","fk-font-17 fk-bold");

		NodeFilter[] array1 = new NodeFilter[3];
		array1[0] = BookNameFilter;
		array1[1] = AuthorFilter;
		array1[2] = PriceFilter; 

		OrFilter orfilter = new OrFilter();
		orfilter.setPredicates(array1);

		NodeFilter[] finalnode = new NodeFilter[1];
		finalnode[0] = orfilter;
		

		return finalnode;

	}
	
	/**Method to Display Results*/
	public void ResultDisplay(String WebSite, String keyword) {
		PrintStream out = null;
		try {
			
			out = new PrintStream(new FileOutputStream("C:/FKoutput.txt",true));
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		System.setOut(out);
		
		
		System.out.println(WebSite.toUpperCase()+" Results for "+keyword+" : ");
		System.out.println("Book Title | Author | Price \n");

		try {
			String html = getBean().getNodes().toHtml();
			Parser parser = new Parser(html);
			NodeList nodelist = parser.parse(null);

			SimpleNodeIterator nodeiterator = nodelist.elements();
			int i = 0;
			while(nodeiterator.hasMoreNodes()){
				Node node = nodeiterator.nextNode();
				if(node instanceof CompositeTag){
					CompositeTag CT = (CompositeTag)node;
					System.out.print(CT.getStringText()
							.toString()
							.trim()
							.replaceAll("\\<[^>]*>","").replaceFirst("by", ""));
					if(i != 2){
						System.out.print(" | ");
					}

					i++;
				}
				if(i == 3){
					i = 0;
					System.out.println("\n");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}

		} catch (ParserException e) {
			e.printStackTrace();
		}
	}
	
	
}
