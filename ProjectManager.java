
public class ProjectManager {
	InformationPanel information;
	ReadyQueuePanel ReadyQueue;
	GhanttChartPanel GhanttChart;
	ActionPanel action;
	InputPanel input;
	public ProjectManager(InformationPanel information, ReadyQueuePanel ReadyQueue,
			GhanttChartPanel GhanttChart, ActionPanel action, InputPanel input) {
		this.information = information;
		this.ReadyQueue = ReadyQueue;
		this.GhanttChart = GhanttChart;
		this.action = action;
		this.input = input;
	}
}
