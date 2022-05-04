package Scheduling;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

import GUI.GhanttChartPanel;
import Manager.ProjectManager;

import java.util.LinkedList;

public class MFQ {
	ProjectManager manager;
	protected GhanttChartPanel ghanttchartPanel;
	
	protected LinkedList<Process> HighAlgorithmList;
	protected LinkedList<Process> MiddleAlgorithmList;
	protected LinkedList<Process> LowAlgorithmList;
	
	protected LinkedList<Process> HighReadyQueue = new LinkedList<>();
	protected LinkedList<Process> MiddleReadyQueue = new LinkedList<>();
	protected LinkedList<Process> LowReadyQueue = new LinkedList<>();
	
	protected Process PresentProcess = null;
	protected int time = 0;
	protected int CoreWork = 1;
	
	int MaxQuanturm;
	int Div;
	
	public Timer timer = new Timer();					// 타이머 중지를 위한 public 설정
	
	public MFQ(ProjectManager manager, int MaxQuanturm, int Div) {
		this.manager = manager;
		this.MaxQuanturm = MaxQuanturm;
		this.Div = Div;
		start();
	}
	
	public void start() {																		// timer와 timertask를 사용해 카운트를 구현시켰습니다
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
					schedulling();
					// if(AlgorithmList.isEmpty() && ReadyQueue.isEmpty() && PresentProcess == null) timer.cancel(); 	// MFQ용 종료 조건 선언 해야함
					time++; 																					// time변수를 증가시켜줘 초를 표현
					System.out.println("멈춤?");
				}
			};
			timer = new Timer();
			timer.schedule(task, 1000,1000); 																	// 1초마다 실행
	}
	
	void schedulling() {				// MFQ용 스케쥴링
									                           
	}
	
	protected void Core() { // 예정
		// -> CoreWork 이거 변경해주는거 
		// 상황에 맞게 CoreWork 변경
	}
}
