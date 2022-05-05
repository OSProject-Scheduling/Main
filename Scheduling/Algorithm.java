package Scheduling;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

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
	protected int CoreWork;
	
	int PCoreCount;
	int ECoreCount;
	double elec = 0;
	
	public Timer timer = new Timer();					// 타이머 중지를 위한 public 설정
	
	public Algorithm(ProjectManager manager, int PCoreCount, int ECoreCount) {
		this.AlgorithmList = manager.addPanel.AlgorithmList;
		this.ghanttchartPanel = manager.GhanttChart;
		this.manager = manager;
		
		this.PCoreCount = PCoreCount;
        this.ECoreCount = ECoreCount;
        
        CoreWork = PCoreCount*2 + ECoreCount;
        
		start();
	}
	
	public void start() {																		// timer와 timertask를 사용해 카운트를 구현시켰습니다
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
					schedulling();
					if(AlgorithmList.isEmpty() && ReadyQueue.isEmpty() && PresentProcess == null) {
						timer.cancel(); 
					}
					time++; // 코어 고려 안되었음																					// time변수를 증가시켜줘 초를 표현
				}
			};
			timer = new Timer();
			timer.schedule(task, 100,100); 																	// 1초마다 실행
	}
	
	protected void CalculateTime() {				// TT / WT / NTT 계산
		if(!(PresentProcess == null) && PresentProcess.BurstTime <= 0) {
	         PresentProcess.TurnaroundTime = (time - PresentProcess.ArrivalTime) * CoreWork;                               // TT 계산
	         PresentProcess.WaitingTime = PresentProcess.TurnaroundTime - PresentProcess.StaticBurstTime;               // WT 계산
	         PresentProcess.NormalizedTime = PresentProcess.TurnaroundTime / PresentProcess.StaticBurstTime;            // NTT 계산
	         manager.information.ChangeInformation(PresentProcess.TurnaroundTime, PresentProcess.WaitingTime, PresentProcess.NormalizedTime, PresentProcess.Row);
	         PresentProcess = null;                     					// bursttime이 0 이하가 되면 null로 변화
	      }
	}
	
	protected void GUIELEC() {
		if(PresentProcess==null) {
			ghanttchartPanel.adding(new JLabel("    "),-1);			
			elec += ((PCoreCount + ECoreCount)*0.1);
		}
		else {
			ghanttchartPanel.adding(new JLabel(PresentProcess.Name), PresentProcess.Row);	
			elec += PCoreCount*3 + ECoreCount; // 8
		}  
		// 전력 표시 여기에 ㄱㄱ
	}
}
