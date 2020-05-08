package model;

import java.util.ArrayList;
import java.util.List;

import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

public class WebText {

	private UserAgent ua;
	private List<String> txtWeb;
	private String url;

	// url from JTextField
	public WebText(String url) {
		this.url = url;
		ua = new UserAgent();
		txtWeb = new ArrayList<>();
		scrapText4Web(url);
	}

	// scrap all from <p> html tags
	public void scrapText4Web(String url) {
		try {
			ua.visit(url);
			Elements pAll = ua.doc.findEach("<p>");
			for (Element elm : pAll) {
				txtWeb.add(elm.getTextContent());
				// System.out.println(elm.getTextContent());
			}
		} catch (ResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Scraped content that need to be shown in JTextArea
	public List<String> getScrapedText() {
		return txtWeb;
	}

}

