package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MousePopUpListener extends MouseAdapter {

	private PopUpMenu popup;

	public MousePopUpListener(PopUpMenu popup) {
		this.popup = popup;
	}

	public void mousePressed(MouseEvent mev) {
		if (mev.isPopupTrigger())
			doSomething(mev);
	}

	public void mouseReleased(MouseEvent mev) {
		if (mev.isPopupTrigger())
			doSomething(mev);
	}

	private void doSomething(MouseEvent mev) {
		if (mev.isPopupTrigger()) {

			// Show popup menu on invoker in which MouseEvent occurred
			popup.show(mev.getComponent(), mev.getX(), mev.getY());
		}

	}
}