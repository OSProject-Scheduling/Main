package Manager;

import GUI.*;
import Scheduling.Algorithm;

public class ProjectManager {
	public InformationPanel information;
	public ReadyQueuePanel ReadyQueue;
	public HighReadyQueuePanel HighReadyQueue;
	public MiddleReadyQueuePanel MidReadyQueue;
	public LowReadyQueuePanel lowReadyQueue;
	public GhanttChartPanel GhanttChart;
	public AddPanel addPanel;
	public RunPanel runpanel;
	public MainFrame mainFrame;
	public Algorithm algorithm;
	
	public ProjectManager(InformationPanel information, ReadyQueuePanel ReadyQueue,
			GhanttChartPanel GhanttChart, HighReadyQueuePanel HighReadyQueue, MiddleReadyQueuePanel MidReadyQueue,
			LowReadyQueuePanel lowReadyQueue) {
		this.information = information;
		this.ReadyQueue = ReadyQueue;
		this.GhanttChart = GhanttChart;
		this.HighReadyQueue = HighReadyQueue;
		this.MidReadyQueue = MidReadyQueue;
		this.lowReadyQueue = lowReadyQueue;
	}
}
