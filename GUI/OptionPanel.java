package GUI;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import Manager.ProjectManager;

public class OptionPanel extends JPanel{
	ProjectManager manager;
	
	AddPanel addpanel;
	RunPanel runpanel;
	AdditionalButtonPanel additionalbuttonpanel;
	InformationPanel informationpanel;
	GhanttChartPanel ghanttchartpanel_1;
	GhanttChartPanel ghanttchartpanel_2;
	GhanttChartPanel ghanttchartpanel_3;
	GhanttChartPanel ghanttchartpanel_4;
	ReadyQueuePanel readyQueue;
	HighReadyQueuePanel highreadyQueue;
	MiddleReadyQueuePanel midreadyQueue;
	LowReadyQueuePanel lowreadyQueue;
	
	public OptionPanel(InformationPanel informationpanel, GhanttChartPanel ghanttchartpanel_1, GhanttChartPanel ghanttchartpanel_2,
			GhanttChartPanel ghanttchartpanel_3,GhanttChartPanel ghanttchartpanel_4, JLabel CoreLabel_1, JLabel CoreLabel_2,
			JLabel CoreLabel_3, JLabel CoreLabel_4,
			ReadyQueuePanel readyQueue, HighReadyQueuePanel highreadyQueue, MiddleReadyQueuePanel midreadyQueue,
			LowReadyQueuePanel lowreadyQueue, MainFrame mainframe) {
		this.informationpanel = informationpanel;
		this.ghanttchartpanel_1 = ghanttchartpanel_1;
		this.ghanttchartpanel_2 = ghanttchartpanel_2;
		this.ghanttchartpanel_3 = ghanttchartpanel_3;
		this.ghanttchartpanel_4 = ghanttchartpanel_4;
		this.readyQueue = readyQueue;
		this.highreadyQueue = highreadyQueue;
		this.midreadyQueue = midreadyQueue;
		this.lowreadyQueue = lowreadyQueue;
		
		Base();
		addpanel = new AddPanel(manager);
		runpanel = new RunPanel(manager);
		additionalbuttonpanel = new AdditionalButtonPanel(manager);
		
		manager.mainFrame = mainframe;
		manager.coreLabel_1 = CoreLabel_1;
		manager.coreLabel_2 = CoreLabel_2;
		manager.coreLabel_3 = CoreLabel_3;
		manager.coreLabel_4 = CoreLabel_4;
		
		add(addpanel);
		add(runpanel);
		add(additionalbuttonpanel);
	}
	
	private void Base() {
		setSize(520, 310);
		setLocation(730, 10);
		setBackground(Color.RED);
		manager = new ProjectManager(informationpanel, readyQueue, ghanttchartpanel_1, 
				ghanttchartpanel_2, ghanttchartpanel_3, ghanttchartpanel_4,  highreadyQueue, midreadyQueue, lowreadyQueue);
		setLayout(null);
	}
}
