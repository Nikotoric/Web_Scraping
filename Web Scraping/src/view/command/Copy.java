package view.command;

import javax.swing.JTextArea;

public class Copy implements IntCommand {
	private JTextArea txtArea;

	public Copy(JTextArea txtArea) {
		this.txtArea = txtArea;
	}

	@Override
	public void execute() {
		MyClipboard.clip(txtArea.getSelectedText());
	}

}
