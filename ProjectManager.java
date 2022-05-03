
public class ProjectManager {
	InformationPanel information;
	ReadyQueuePanel ReadyQueue;
	HighReadyQueuePanel HighReadyQueue;
	MiddleReadyQueuePanel MidReadyQueue;
	LowReadyQueuePanel lowReadyQueue;
	
	GhanttChartPanel GhanttChart;
	AddPanel addPanel;
	RunPanel runpanel;
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
