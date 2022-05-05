
import java.util.Timer;
import java.util.TimerTask;

import GUI.GhanttChartPanel;
import Manager.ProjectManager;

import java.util.LinkedList;

public abstract class MFQAlgorithm {
	abstract void schedulling();
	
	protected GhanttChartPanel ghanttchartPanel;
		
	protected LinkedList<Process> HighAlgorithmList = new LinkedList<>();
	protected LinkedList<Process> MiddleAlgorithmList = new LinkedList<>();
	protected LinkedList<Process> LowAlgorithmList = new LinkedList<>();
	
	protected LinkedList<Process> HighReadyQueue = new LinkedList<>();
	protected LinkedList<Process> MiddleReadyQueue = new LinkedList<>();
	protected LinkedList<Process> LowReadyQueue = new LinkedList<>();
	
	
	protected Process PresentProcess = null;
	
	protected ProjectManager manager;
	protected int time = 0;
	protected int CoreWork = 1;
	
	public MFQAlgorithm(/*ProjectManager manager*/) {
		/*this.HighAlgorithmList = manager.addPanel.HighAlgorithmList;
		this.MiddleAlgorithmList = manager.addPanel.MidAlgorithmList;
		this.LowAlgorithmList = manager.addPanel.lowAlorithmList;
		
		this.ghanttchartPanel = manager.GhanttChart;
		this.manager = manager;*/
		start();
	}
	
	private void start() {
		Timer timer = new Timer(); 																				// timer와 timertask를 사용해 카운트를 구현시켰습니다
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
					schedulling();
					if((HighAlgorithmList.isEmpty() && HighReadyQueue.isEmpty())&&(MiddleAlgorithmList.isEmpty() && MiddleReadyQueue.isEmpty())
							&&(LowAlgorithmList.isEmpty() && LowReadyQueue.isEmpty()) && PresentProcess == null) timer.cancel();
					time++; 																					// time변수를 증가시켜줘 초를 표현
					
				}
			};
			timer.schedule(task, 1000,1000); 																	// 1초마다 실행
	}
	
	
	protected void Core() { // 예정
		// -> CoreWork 이거 변경해주는거 
		// 상황에 맞게 CoreWork 변경
	}
}
