package view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingWorker;

import controller.Controller;
import view.command.Copy;
import view.command.Cut;
import view.command.IntCommand;
import view.command.Paste;
import view.command.SelectAll;

public class ScraperFrame extends JFrame implements ActionListener {

	private JPanel scraping, edit;
	private JMenuBar menuBar;
	private JMenu editing;
	private JMenu file;
	private JMenuItem openItem, saveItem, selectItem, copyItem, cutItem, pasteItem;
	private JButton getTextButton, showButton, selectButton, copyButton, cutButton, pasteButton, clearButton;
	private JTextArea txtArea;
	private JLabel url;
	private JTextField urlFld;
	private JProgressBar progress;

	private Controller controller;

	public ScraperFrame() {
		super("Web txtScraper");
		setSize(800, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		controller = new Controller();

		createComps();
		initGui();
		activateButtons();
		activateMnemonics();

	}

	private void createComps() {

		scraping = new JPanel();
		edit = new JPanel();

		// upper Panel
		menuBar = new JMenuBar();

		file = new JMenu("File");
		openItem = new JMenuItem("Open");
		saveItem = new JMenuItem("Save As");

		editing = new JMenu("Edit");
		selectItem = new JMenuItem("Select All");
		cutItem = new JMenuItem("Cut");
		copyItem = new JMenuItem("Copy");
		pasteItem = new JMenuItem("Paste");

		txtArea = new JTextArea();

		// bottom Panel
		url = new JLabel("URL: ");
		urlFld = new JTextField(20);
		getTextButton = new JButton("Get text");

		progress = new JProgressBar();

		showButton = new JButton("Show");
		showButton.setEnabled(false);

		selectButton = new JButton("Select All");
		copyButton = new JButton("Copy");
		cutButton = new JButton("Cut");
		pasteButton = new JButton("Paste");
		clearButton = new JButton("Clear View");
		clearButton.setEnabled(false);

	}

	// use different keyboard shortcuts than usual for COPY, CUT, PASTE, SELECT ALL
	private void activateMnemonics() {
		editing.setMnemonic(KeyEvent.VK_E);
		copyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, ActionEvent.CTRL_MASK));
		pasteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		selectItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
		cutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
	}

	private void activateButtons() {
		txtArea.addMouseListener(new MousePopUpListener(new PopUpMenu(txtArea, clearButton)));

		cutButton.addActionListener(this);
		getTextButton.addActionListener(this);
		copyButton.addActionListener(this);
		selectButton.addActionListener(this);
		clearButton.addActionListener(this);
		pasteButton.addActionListener(this);
		showButton.addActionListener(this);

		openItem.addActionListener(this);
		saveItem.addActionListener(this);
		copyItem.addActionListener(this);
		cutItem.addActionListener(this);
		pasteItem.addActionListener(this);
		selectItem.addActionListener(this);

	}

	private void initGui() {
		GridBagConstraints gb = new GridBagConstraints();

		// textArea
		JScrollPane scrollPane = new JScrollPane(txtArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		add(scrollPane, BorderLayout.CENTER);
		txtArea.setLineWrap(true);

		// menuBar
		file.add(openItem);
		file.add(saveItem);
		editing.add(selectItem);
		editing.add(copyItem);
		editing.add(cutItem);
		editing.addSeparator();
		editing.add(pasteItem);
		menuBar.add(file);
		menuBar.add(editing);
		setJMenuBar(menuBar);

		// editPanel
		edit.setBorder(BorderFactory.createTitledBorder("Edit"));
		edit.setLayout(new GridBagLayout());

		gb.anchor = GridBagConstraints.LINE_START;
		gb.insets = new Insets(10, 10, 10, 20);

		gb.gridx = 0;
		gb.gridy = 0;
		edit.add(selectButton, gb);

		gb.gridx = 1;
		gb.gridy = 0;
		pasteButton.setMargin(new Insets(3, 52, 2, 52));
		edit.add(pasteButton, gb);

		gb.gridx = 0;
		gb.gridy = 1;
		edit.add(copyButton, gb);

		gb.gridx = 1;
		gb.gridy = 1;
		edit.add(clearButton, gb);

		gb.gridx = 0;
		gb.gridy = 2;
		edit.add(cutButton, gb);

		// scrapingPanel
		scraping.setBorder(BorderFactory.createTitledBorder("Web scraping"));
		scraping.setLayout(new GridBagLayout());
		gb.insets = new Insets(10, 0, 10, 0);

		gb.gridx = 0;
		gb.gridy = 0;
		scraping.add(url, gb);

		gb.gridx = 1;
		gb.gridy = 0;
		scraping.add(urlFld, gb);

		gb.gridx = 1;
		gb.gridy = 1;
		scraping.add(getTextButton, gb);

		gb.gridx = 1;
		gb.gridy = 3;
		showButton.setMargin(new Insets(3, 75, 2, 75));
		scraping.add(showButton, gb);

		gb.gridx = 1;
		gb.gridy = 2;
		gb.ipadx = 290;
		gb.ipady = 5;
		gb.fill = GridBagConstraints.HORIZONTAL;
		progress.setStringPainted(true);
		scraping.add(progress, gb);

		// adding panel to frame
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(scraping, BorderLayout.CENTER);
		panel.add(edit, BorderLayout.EAST);
		add(panel, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == openItem) {
			openFile();
		} else if (e.getSource() == saveItem) {
			saveFile();
		} else if (e.getSource() == getTextButton) {
			String urlTxt = urlFld.getText();

			controller.scrapeWebFromURL(urlTxt);

			// progressbar task
			new Task().execute();

		} else if (e.getSource() == showButton) {

			controller.showScrapedContent(txtArea);

			showButton.setEnabled(false);
			urlFld.setText("");
			clearButton.setEnabled(true);

		} else if (e.getSource() == copyButton || e.getSource() == copyItem) {
			IntCommand copy = new Copy(txtArea);
			copy.execute();
			clearButton.setEnabled(true);
		} else if (e.getSource() == cutButton || e.getSource() == cutItem) {
			IntCommand cut = new Cut(txtArea);
			cut.execute();
			clearButton.setEnabled(true);
		} else if (e.getSource() == pasteButton || e.getSource() == pasteItem) {
			IntCommand paste = new Paste(txtArea);
			paste.execute();
		} else if (e.getSource() == selectButton || e.getSource() == selectItem) {
			IntCommand selectAll = new SelectAll(txtArea);
			selectAll.execute();
		} else if (e.getSource() == clearButton) {
			txtArea.selectAll();
			txtArea.replaceSelection("");
		}
	}

	private void saveFile() {
		JFileChooser fChooser = new JFileChooser();

		int returnVal = fChooser.showSaveDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {

			controller.saveFile(fChooser.getSelectedFile().getAbsolutePath(), txtArea.getText());

		}
	}

	private void clearTxtArea() {
		txtArea.selectAll();
		txtArea.replaceSelection("");
	}

	private void openFile() {
		JFileChooser fChooser = new JFileChooser();

		int returnVal = fChooser.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {

			String absPath = fChooser.getSelectedFile().getAbsolutePath();

			clearTxtArea();

			controller.openAndDisplayFile(fChooser.getSelectedFile().getAbsolutePath(), txtArea);

		}

	}

	private class Task extends SwingWorker<Void, Void> {

		@Override
		protected Void doInBackground() throws Exception {
			int pr = 10;
			int val = 0;
			while (val < 100) {
				Thread.sleep(300);
				val += pr;
				progress.setValue(val);
				System.out.println("< -" + val + " ->");
			}
			System.out.println("Done!!!!!!");
			return null;
		}

		// Executed on the Event Dispatch Thread after the doInBackground() method is
		// finished.
		@Override
		protected void done() {
			System.out.println("Done method is executing...");
			Toolkit.getDefaultToolkit().beep();
			showButton.setEnabled(true);
		}

	}

}
