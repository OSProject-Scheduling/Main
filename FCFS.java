import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Queue;

public class FCFS {

	GhanttChartPanel ghanttchartPanel;
	LinkedList<FCFSProcess> SRTNList = new LinkedList<>();		// 여기에 FCFS가 저장되어 있음 여기에 있는거 빼가야 함.
	LinkedList<FCFSProcess> ReadyQueue = new LinkedList<>();
	FCFSProcess PresentSRTN = null;
	FCFSProcess Process = null;
	int pass = 0;
	
	int time = 0;
	int CoreWork = 1;
	
	public FCFS(LinkedList<FCFSProcess> FCFSprocess, GhanttChartPanel ghanttchartPanel) {
		this.SRTNList = FCFSprocess;
		this.ghanttchartPanel = ghanttchartPanel;
		start();
	}
	
	public void start() {
		Timer timer = new Timer(); 																				// timer와 timertask를 사용해 카운트를 구현시켰습니다
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
					Schedulling(); 																				// 1초 마다 실행
					if(SRTNList.isEmpty() && ReadyQueue.isEmpty() && PresentSRTN == null) timer.cancel(); 
					time++; 																					// time변수를 증가시켜줘 초를 표현
				}
			};
			timer.schedule(task, 1000,1000); 																	// 1초마다 실행
	}
	void Schedulling() {
		if(!SRTNList.isEmpty() && time == SRTNList.peekFirst().ArrivalTime) ReadyQueue.add(SRTNList.poll()); 	// FCFSList에서 ReadyQueue로 이동(ArrivalTime에 맞으면)
		if(PresentSRTN == null) {																				// 현재 FCFS가 비어있을때 
			if(!ReadyQueue.isEmpty()) {																			// ReadyQueue가 비어있지 않으면 현재 FCFS로 poll
				PresentSRTN = ReadyQueue.poll();
			}
		}
		if(PresentSRTN==null) ghanttchartPanel.adding(new JLabel("    "));			
		else ghanttchartPanel.adding(new JLabel(PresentSRTN.Name));												// GhanttChart 표시
		if(!(PresentSRTN == null)) {
			for(int i = SRTNList.size()-2;i>=0;i--) {
				if(SRTNList.get(SRTNList.size()-pass).BurstTime < SRTNList.get(i).BurstTime) {
					Process = SRTNList.get(SRTNList.size()-pass);
					SRTNList.set(SRTNList.size()-pass, SRTNList.get(i));
					SRTNList.set(i, Process);
					pass++;
				}
			}
			PresentSRTN = SRTNList.get(0);
			pass = 1;
			PresentSRTN.BurstTime -= CoreWork;											// 현재 FCFS가 비어있지 않으면 bursttime에서 처리량 빼주기
		}
		if(!(PresentSRTN == null) && PresentSRTN.BurstTime <= 0) PresentSRTN = null;
	}
}

