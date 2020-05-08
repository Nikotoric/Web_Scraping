package view.command;

import javax.swing.JTextArea;

public class Paste implements IntCommand {
	private JTextArea txtArea;

	public Paste(JTextArea txtArea) {
		this.txtArea = txtArea;
	}

	@Override
	public void execute() {
		txtArea.append(MyClipboard.getContent());
	}

}
