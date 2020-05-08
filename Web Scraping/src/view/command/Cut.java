package view.command;

import javax.swing.JTextArea;

public class Cut implements IntCommand {
	private JTextArea txtArea;

	public Cut(JTextArea txtArea) {
		this.txtArea = txtArea;
	}

	@Override
	public void execute() {
		MyClipboard.clip(txtArea.getSelectedText());

		txtArea.replaceSelection("");
	}

}