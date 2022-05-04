import java.awt.Color;

import javax.swing.JPanel;

public class OptionPanel extends JPanel{
	AddPanel addpanel;
	RunPanel runpanel;
	InformationPanel informationpanel;
	GhanttChartPanel ghanttchartpanel;
	ReadyQueuePanel readyQueue;
	ProjectManager manager;
	HighReadyQueuePanel highreadyQueue;
	MiddleReadyQueuePanel midreadyQueue;
	LowReadyQueuePanel lowreadyQueue;
	
	public OptionPanel(InformationPanel informationpanel, GhanttChartPanel ghanttchartpanel, 
			ReadyQueuePanel readyQueue, HighReadyQueuePanel highreadyQueue, MiddleReadyQueuePanel midreadyQueue,
			LowReadyQueuePanel lowreadyQueue) {
		this.informationpanel = informationpanel;
		this.ghanttchartpanel = ghanttchartpanel;
		this.readyQueue = readyQueue;
		this.highreadyQueue = highreadyQueue;
		this.midreadyQueue = midreadyQueue;
		this.lowreadyQueue = lowreadyQueue;
		
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
		manager = new ProjectManager(informationpanel, readyQueue, ghanttchartpanel, highreadyQueue, midreadyQueue, lowreadyQueue);
		setLayout(null);
	}
}
