
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Queue;

public abstract class Algorithm {

	protected GhanttChartPanel ghanttchartPanel;
	protected LinkedList<Process> AlgorithmList = new LinkedList<>();		
	protected LinkedList<Process> ReadyQueue = new LinkedList<>();
	protected Process PresentProcess = null;
	// FCFSProcess Process; 이거는 hrrn에서 따로 선언 ㄱ
	
	abstract void schedulling();
	
	int time = 0;
	int CoreWork = 1;
	//	int pass = 1;   -- SRTN만
	
	// String SetAlgorithm; -- 알고리즘 선택(이거 GUI에서 해결할것)
	
	public Algorithm(LinkedList<Process> AlgorithmList, GhanttChartPanel ghanttchartPanel) {
		this.AlgorithmList = AlgorithmList;
		this.ghanttchartPanel = ghanttchartPanel;
		start();
	}
	
	private void start() {
		Timer timer = new Timer(); 																				// timer와 timertask를 사용해 카운트를 구현시켰습니다
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
					schedulling();
					if(AlgorithmList.isEmpty() && ReadyQueue.isEmpty() && PresentProcess == null) timer.cancel(); 
					time++; 																					// time변수를 증가시켜줘 초를 표현
				}
			};
			timer.schedule(task, 1000,1000); 																	// 1초마다 실행
	}
	
	
	public void Core() { // 예정
		// -> CoreWork 이거 변경해주는거 
		// 상황에 맞게 CoreWork 변경
	}
}
