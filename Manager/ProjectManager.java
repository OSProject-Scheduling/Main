package Manager;

import javax.swing.JLabel;

import GUI.*;
import Scheduling.Algorithm;
import Scheduling.MDRQ;

public class ProjectManager {
	public InformationPanel information;
	public ReadyQueuePanel ReadyQueue;
	public MFQReadyQueue MFQreadyQueue;
	public GhanttChartPanel GhanttChart_1;
	public GhanttChartPanel GhanttChart_2;
	public GhanttChartPanel GhanttChart_3;
	public GhanttChartPanel GhanttChart_4;
	public AddPanel addPanel;
	public RunPanel runpanel;
	public MainFrame mainFrame;
	public MainPanel mainPanel;
	public Algorithm algorithm;
	public MDRQ mfq;
	public JLabel coreLabel_1;
	public JLabel coreLabel_2;
	public JLabel coreLabel_3;
	public JLabel coreLabel_4;
	
	public ProjectManager(InformationPanel information, ReadyQueuePanel ReadyQueue,
			GhanttChartPanel GhanttChart_1,GhanttChartPanel GhanttChart_2,GhanttChartPanel GhanttChart_3,
			GhanttChartPanel GhanttChart_4,MFQReadyQueue MFQreadyQueue) {
		this.information = information;
		this.ReadyQueue = ReadyQueue;
		this.GhanttChart_1 = GhanttChart_1;
		this.GhanttChart_2 = GhanttChart_2;
		this.GhanttChart_3 = GhanttChart_3;
		this.GhanttChart_4 = GhanttChart_4;
		this.MFQreadyQueue = MFQreadyQueue;
	}
}
