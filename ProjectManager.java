
public class ProjectManager {
	InformationPanel information;
	ReadyQueuePanel ReadyQueue;
	HighReadyQueuePanel HighReadyQueue;
	MiddleReadyQueuePanel MiddleReadyQueue;
	LowReadyQueuePanel lowReadyQueue;
	
	GhanttChartPanel GhanttChart;
	AddPanel addPanel;
	RunPanel runpanel;
	public ProjectManager(InformationPanel information, ReadyQueuePanel ReadyQueue,
			GhanttChartPanel GhanttChart, HighReadyQueuePanel HighReadyQueue, MiddleReadyQueuePanel MiddleReadyQueue,
			LowReadyQueuePanel lowReadyQueue) {
		this.information = information;
		this.ReadyQueue = ReadyQueue;
		this.GhanttChart = GhanttChart;
		this.HighReadyQueue = HighReadyQueue;
		this.MiddleReadyQueue = MiddleReadyQueue;
		this.lowReadyQueue = lowReadyQueue;
	}
}
