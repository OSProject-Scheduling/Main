
public class ProjectManager {
	InformationPanel information;
	ReadyQueuePanel ReadyQueue;
	GhanttChartPanel GhanttChart;
	AddPanel addPanel;
	RunPanel runpanel;
	public ProjectManager(InformationPanel information, ReadyQueuePanel ReadyQueue,
			GhanttChartPanel GhanttChart) {
		this.information = information;
		this.ReadyQueue = ReadyQueue;
		this.GhanttChart = GhanttChart;
	}
}
