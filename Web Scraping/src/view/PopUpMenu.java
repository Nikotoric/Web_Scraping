package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

import view.command.Copy;
import view.command.Cut;
import view.command.IntCommand;
import view.command.Paste;
import view.command.SelectAll;

public class PopUpMenu extends JPopupMenu {
	private JMenuItem cut;
	private JMenuItem selectAll;
	private JMenuItem copy;
	private JMenuItem paste;
	private ActionListener menuLstnr;

	// Constructor links popup menu with the Component - invoker
	// Invoker - component in which popup menu appears
	// Invoker in this case is ViewPanel
	public PopUpMenu(JTextArea txtArea, JButton clear) {

		// Add listener for the item --> in your case you will need
		// different to run appropriate command
		menuLstnr = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evnt) {
				System.out.println("PopUp menu item <- " + evnt.getActionCommand() + " -> was pressed :)");

				if (evnt.getSource() == paste) {

					IntCommand paste = new Paste(txtArea);
					paste.execute();

				} else if (evnt.getSource() == selectAll) {

					IntCommand selectAll = new SelectAll(txtArea);
					selectAll.execute();

				} else if (evnt.getSource() == copy) {

					IntCommand copy = new Copy(txtArea);
					copy.execute();
					clear.setEnabled(true);

				} else if (evnt.getSource() == cut) {

					IntCommand cut = new Cut(txtArea);
					cut.execute();
					clear.setEnabled(true);

				}
			}
		};
		initPopMenuItems();
		// you can set dimension for popup menu
		Dimension preferredSize = new Dimension(105, 120);
		setPreferredSize(preferredSize);
	}

	private void initPopMenuItems() {

		add(copy = new JMenuItem("Copy"));
		copy.setHorizontalTextPosition(JMenuItem.RIGHT);
		copy.addActionListener(menuLstnr);
		add(paste = new JMenuItem("Paste"));
		paste.setHorizontalTextPosition(JMenuItem.RIGHT);
		paste.addActionListener(menuLstnr);
		add(cut = new JMenuItem("Cut"));
		cut.setHorizontalTextPosition(JMenuItem.RIGHT);
		cut.addActionListener(menuLstnr);
		addSeparator();
		add(selectAll = new JMenuItem("Select all"));
		selectAll.setHorizontalTextPosition(JMenuItem.RIGHT);
		selectAll.addActionListener(menuLstnr);

		setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

	}

}

