package in.resultfetcher;

import org.htmlparser.NodeFilter;
import org.htmlparser.beans.FilterBean;

public interface ScrapeHTML {
	
	public void ResultDisplay(String WebSite, String keyword);
	public NodeFilter[] FilterConfig();
	public FilterBean getBean();
	public void setURL(String uRL);

}
