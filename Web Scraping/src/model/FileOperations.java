package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileOperations {
	public static void writeToFile(String filePath, String content) {
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(new File(filePath)));
			bw.write(content);
			bw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}

	public static String readFile(String filePath) {
		String txt = "";

		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));

			String line = br.readLine();
			while (line != null) {
				txt += line;
				line = br.readLine();
			}

			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}

		return txt;
	}
}
