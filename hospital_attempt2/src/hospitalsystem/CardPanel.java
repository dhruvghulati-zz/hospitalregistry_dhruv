package hospitalsystem;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;

public class CardPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public CardPanel() {
		setLayout(new BorderLayout(100, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(20, 0, 0, 0));
		
		JButton btnNewButton = new JButton("New button");
		panel.add(btnNewButton);

	}

}
