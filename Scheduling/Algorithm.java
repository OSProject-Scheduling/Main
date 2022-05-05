package Scheduling;
import java.util.Timer;
import java.util.TimerTask;

import GUI.GhanttChartPanel;
import Manager.ProjectManager;

import java.util.LinkedList;

public abstract class Algorithm {
	abstract void schedulling();
	protected GhanttChartPanel ghanttchartPanel;
	
	protected LinkedList<Process> AlgorithmList;	
	protected LinkedList<Process> ReadyQueue = new LinkedList<>();
	
	protected Process PresentProcess = null;
	protected ProjectManager manager;
	protected int time = 0;
	protected int CoreWork = 2;
	
	public Timer timer = new Timer();					// 타이머 중지를 위한 public 설정
	
	public Algorithm(ProjectManager manager) {
		this.AlgorithmList = manager.addPanel.AlgorithmList;
		this.ghanttchartPanel = manager.GhanttChart;
		this.manager = manager;
		start();
	}
	
	public void start() {																		// timer와 timertask를 사용해 카운트를 구현시켰습니다
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
					schedulling();
					if(AlgorithmList.isEmpty() && ReadyQueue.isEmpty() && PresentProcess == null) {
						timer.cancel(); 
						manager.algorithm = null;
					}
					time++; // 코어 고려 안되었음																					// time변수를 증가시켜줘 초를 표현
				}
			};
			timer = new Timer();
			timer.schedule(task, 1000,1000); 																	// 1초마다 실행
	}
	
	
	protected void Core() { // 예정
		// -> CoreWork 이거 변경해주는거 
		// 상황에 맞게 CoreWork 변경
	}
}
