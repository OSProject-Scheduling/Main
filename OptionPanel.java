import java.awt.Color;

import javax.swing.JPanel;

public class OptionPanel extends JPanel{
	InputPanel inputpanel = new InputPanel();
	ActionPanel actionpanel;
	InformationPanel informationpanel;
	GhanttChartPanel ghanttchartpanel;
	ReadyQueuePanel readyQueue;
	ProjectManager manager;
	public OptionPanel(InformationPanel informationpanel, GhanttChartPanel ghanttchartpanel, ReadyQueuePanel readyQueue) {
		this.informationpanel = informationpanel;
		this.ghanttchartpanel = ghanttchartpanel;
		this.readyQueue = readyQueue;
		Base();
		actionpanel = new ActionPanel(manager);
		add(inputpanel);
		add(actionpanel);
	}
	
	private void Base() {
		setSize(260, 740);
		setLocation(730, 10);
		setBackground(Color.white);
		manager = new ProjectManager(informationpanel, readyQueue, ghanttchartpanel, null, inputpanel);
		setLayout(null);
		
	}
}
