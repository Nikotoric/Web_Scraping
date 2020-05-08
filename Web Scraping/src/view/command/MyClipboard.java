package view.command;

public class MyClipboard {
	private static String content;

	public static void clip(String contnt) {
		content = contnt;
	}

	public static String getContent() {
		return content;

	}
}