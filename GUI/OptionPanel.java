package GUI;
import java.awt.Color;
import javax.swing.JPanel;
import Manager.ProjectManager;

public class OptionPanel extends JPanel{
	ProjectManager manager;
	
	AddPanel addpanel;
	RunPanel runpanel;
	AdditionalButtonPanel additionalbuttonpanel;
	InformationPanel informationpanel;
	GhanttChartPanel ghanttchartpanel;
	ReadyQueuePanel readyQueue;
	HighReadyQueuePanel highreadyQueue;
	MiddleReadyQueuePanel midreadyQueue;
	LowReadyQueuePanel lowreadyQueue;
	
	public OptionPanel(InformationPanel informationpanel, GhanttChartPanel ghanttchartpanel, 
			ReadyQueuePanel readyQueue, HighReadyQueuePanel highreadyQueue, MiddleReadyQueuePanel midreadyQueue,
			LowReadyQueuePanel lowreadyQueue, MainFrame mainframe) {
		this.informationpanel = informationpanel;
		this.ghanttchartpanel = ghanttchartpanel;
		this.readyQueue = readyQueue;
		this.highreadyQueue = highreadyQueue;
		this.midreadyQueue = midreadyQueue;
		this.lowreadyQueue = lowreadyQueue;
		
		Base();
		addpanel = new AddPanel(manager);
		runpanel = new RunPanel(manager);
		additionalbuttonpanel = new AdditionalButtonPanel(manager);
		
		manager.mainFrame = mainframe;
		
		add(addpanel);
		add(runpanel);
		add(additionalbuttonpanel);
	}
	
	private void Base() {
		setSize(520, 310);
		setLocation(730, 10);
		setBackground(Color.RED);
		manager = new ProjectManager(informationpanel, readyQueue, ghanttchartpanel, highreadyQueue, midreadyQueue, lowreadyQueue);
		setLayout(null);
	}
}
