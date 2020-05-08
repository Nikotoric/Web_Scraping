package controller;

import java.util.List;

import javax.swing.JTextArea;

import model.FileOperations;
import model.WebText;

public class Controller {
	private WebText webTxt;

	public void saveFile(String fileAbsPath, String content) {
		FileOperations.writeToFile(fileAbsPath, content);
	}

	public void openAndDisplayFile(String absolutePath, JTextArea txtArea) {
		String text = FileOperations.readFile(absolutePath);
		
		txtArea.setText(text);
	}

	public void scrapeWebFromURL(String urlTxt) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				webTxt = new WebText(urlTxt);
			}
		};

		Thread webScrapingThread = new Thread(runnable);
		webScrapingThread.start();
	}

	public void showScrapedContent(JTextArea txtArea) {
		
		List<String> scrapedTxt = webTxt.getScrapedText();
		
		for (String temp : scrapedTxt) {
			txtArea.append(temp);
			txtArea.append("\n");
		}
	}
	
	
	
}

