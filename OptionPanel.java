import java.awt.Color;

import javax.swing.JPanel;

public class OptionPanel extends JPanel{
	AddPanel addpanel;
	RunPanel runpanel;
	InformationPanel informationpanel;
	GhanttChartPanel ghanttchartpanel;
	ReadyQueuePanel readyQueue;
	ProjectManager manager;
	public OptionPanel(InformationPanel informationpanel, GhanttChartPanel ghanttchartpanel, ReadyQueuePanel readyQueue) {
		this.informationpanel = informationpanel;
		this.ghanttchartpanel = ghanttchartpanel;
		this.readyQueue = readyQueue;
		Base();
		addpanel = new AddPanel(manager);
		runpanel = new RunPanel(manager);
		add(addpanel);
		add(runpanel);
	}
	
	private void Base() {
		setSize(260, 740);
		setLocation(730, 10);
		setBackground(Color.RED);
		manager = new ProjectManager(informationpanel, readyQueue, ghanttchartpanel);
		setLayout(null);
		
	}
}
