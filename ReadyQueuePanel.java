import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class ReadyQueuePanel extends JPanel{
	GridBagLayout Gbag = new GridBagLayout();
	
	public ReadyQueuePanel() {
		setBackground(Color.green);
		setLayout(Gbag);
		
	}
	
	public void create_form(Component cmpt, int x, int y, int w, int h) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		this.Gbag.setConstraints(cmpt, gbc);
		add(cmpt);
		updateUI();
	}
}
