import java.awt.Color;

import javax.swing.JPanel;

public class OptionPanel extends JPanel{
	InputPanel inputpanel = new InputPanel();
	ActionPanel actionpanel;
	InformationPanel informationpanel;
	GhanttChartPanel ghanttchartpanel;
	public OptionPanel(InformationPanel informationpanel, GhanttChartPanel ghanttchartpanel) {
		this.informationpanel = informationpanel;
		this.ghanttchartpanel = ghanttchartpanel;
		actionpanel = new ActionPanel(inputpanel, informationpanel, ghanttchartpanel);
		Base();
		add(inputpanel);
		add(actionpanel);
	}
	
	private void Base() {
		setSize(260, 740);
		setLocation(730, 10);
		setBackground(Color.white);
		setLayout(null);
	}
}
