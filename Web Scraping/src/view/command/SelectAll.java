package view.command;

import javax.swing.JTextArea;

public class SelectAll implements IntCommand {
	private JTextArea txtArea;

	public SelectAll(JTextArea txtArea) {
		this.txtArea = txtArea;
	}

	@Override
	public void execute() {
		txtArea.selectAll();
		txtArea.requestFocus();
	}

}