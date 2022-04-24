
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
	LinkedList<FCFSProcess> FCFSList = new LinkedList<>();		
	LinkedList<FCFSProcess> ReadyQueue = new LinkedList<>();
	FCFSProcess PresentFCFS = null;
	FCFSProcess Process;
		
	int Quantum;
	int ForQuantum = 0;
	
	int time = 0;
	int CoreWork = 1;
	int pass = 1;
	
	String SetAlgorithm;
	
	public FCFS(LinkedList<FCFSProcess> FCFSprocess, GhanttChartPanel ghanttchartPanel, String SetAlgorithm) {
		this.FCFSList = FCFSprocess;
		this.ghanttchartPanel = ghanttchartPanel;
		this.Quantum = FCFSList.get(0).Quanturm;
		this.SetAlgorithm = SetAlgorithm;
		System.out.print("d");
		start();
	}
	
	public void start() {
		Timer timer = new Timer(); 																				// timer와 timertask를 사용해 카운트를 구현시켰습니다
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
					if(SetAlgorithm == "FCFS")
						FCFSSchedulling();
					else if(SetAlgorithm == "RR")
						RRSchedulling();
					else if(SetAlgorithm == "SPN")
						SPNSchedulling();
					else if(SetAlgorithm == "SRTN")
						SRTNSchedulling();
					else if(SetAlgorithm == "HRRN")
						HRRNSchedulling();
					if(FCFSList.isEmpty() && ReadyQueue.isEmpty() && PresentFCFS == null) timer.cancel(); 
					time++; 																					// time변수를 증가시켜줘 초를 표현
				}
			};
			timer.schedule(task, 1000,1000); 																	// 1초마다 실행
	}
	
	public void FCFSSchedulling() {
		if(!FCFSList.isEmpty() && time == FCFSList.peekFirst().ArrivalTime) ReadyQueue.add(FCFSList.poll()); 	// FCFSList에서 ReadyQueue로 이동(ArrivalTime에 맞으면)
		if(PresentFCFS == null) {																				// 현재 FCFS가 비어있을때 
			if(!ReadyQueue.isEmpty()) {																			// ReadyQueue가 비어있지 않으면 현재 FCFS로 poll
				PresentFCFS = ReadyQueue.poll();
			}
		}
		if(PresentFCFS==null) ghanttchartPanel.adding(new JLabel("    "));			
		else ghanttchartPanel.adding(new JLabel(PresentFCFS.Name));												// GhanttChart 표시
		if(!(PresentFCFS == null)) PresentFCFS.BurstTime -= CoreWork;											// 현재 FCFS가 비어있지 않으면 bursttime에서 처리량 빼주기
		if(!(PresentFCFS == null) && PresentFCFS.BurstTime <= 0) {
			PresentFCFS.TurnaroundTime = time - PresentFCFS.ArrivalTime;									 	// TT 계산
			PresentFCFS.WaitingTime = PresentFCFS.TurnaroundTime - PresentFCFS.StaticBurstTime;					// WT 계산
			PresentFCFS.NormalizedTime = PresentFCFS.TurnaroundTime / PresentFCFS.StaticBurstTime;				// NTT 계산
			PresentFCFS = null;							// bursttime이 0 이하가 되면 null로 변화
		}
	}
	
	//--------------------------------------------------------------------------------------------------------------
	
	void SRTNSchedulling() {
		if(!FCFSList.isEmpty() && time == FCFSList.peekFirst().ArrivalTime) ReadyQueue.add(FCFSList.poll()); 	// FCFSList에서 ReadyQueue로 이동(ArrivalTime에 맞으면)
		if (PresentFCFS != null) {																				// 현재 FCFS가 있으며
			if (ReadyQueue.size() > 1) {																		// Ready큐에 1개 이상 들어있다면
				for (int i = ReadyQueue.size() - 2; i >= 0; i--) {												// 맨 뒤에 프로세스를 하나씩 비교를 하며 정렬
					if (ReadyQueue.get(ReadyQueue.size() - pass).BurstTime < ReadyQueue.get(i).BurstTime) {		// PASS의 용도는 만약 바뀐다면 바꾼 인덱스값도 증가가 되므로 그거 맞춰주기 위해 사용
						Process = ReadyQueue.get(ReadyQueue.size() - pass);
						ReadyQueue.set(ReadyQueue.size() - pass, ReadyQueue.get(i));
						ReadyQueue.set(i, Process);
						pass++;
					}
				}
				pass = 1;
			}
		}
		if(PresentFCFS != null && !ReadyQueue.isEmpty())														// 현재 FCFS가 있으며, Ready큐에 하나라도 있다면
		{
			if(PresentFCFS.BurstTime > ReadyQueue.get(0).BurstTime) 											// 현재 진행중인 것과 Ready큐에 있는 BurstTime이 가장 짦은 것과 비교
			{
				ReadyQueue.add(PresentFCFS);
				PresentFCFS = ReadyQueue.poll();
			}
		}
		
		if (PresentFCFS == null) { // 현재 진행중이 FCFS가 끝났고
			if (!ReadyQueue.isEmpty()) { // ReadyQueue가 비어있지 않으면
				PresentFCFS = ReadyQueue.poll(); // ReadyQueue에서 BurstTime이 가장 짦은 프로세스를 진행시킴
			}
		}
		if(PresentFCFS==null) ghanttchartPanel.adding(new JLabel("    "));			
		else ghanttchartPanel.adding(new JLabel(PresentFCFS.Name));												// GhanttChart 표시
		if(!(PresentFCFS == null)) {
			if (time >= 1) {						// time 1초 뒤에 빼줘야 알맞다고 판단	
				PresentFCFS.BurstTime -= CoreWork; // 현재 FCFS가 비어있지 않으면 bursttime에서 처리량 빼주기
			}
				
		}
		if(!(PresentFCFS == null) && PresentFCFS.BurstTime <= 0) {		// 현재 진행 중인 프로세스가 일을 끝냈으면
			PresentFCFS.TurnaroundTime = time - PresentFCFS.ArrivalTime;							// TT 계산
			PresentFCFS.WaitingTime = PresentFCFS.TurnaroundTime - PresentFCFS.StaticBurstTime;		// WT 계산
			PresentFCFS.NormalizedTime = PresentFCFS.TurnaroundTime / PresentFCFS.StaticBurstTime;	// NTT 계산
			PresentFCFS = null;
		}
	}
	
	//----------------------------------------------------------------------------------------------------------------
	
	
	void HRRNSchedulling() {
		if(!FCFSList.isEmpty() && time == FCFSList.peekFirst().ArrivalTime) ReadyQueue.add(FCFSList.poll()); 	// FCFSList에서 ReadyQueue로 이동(ArrivalTime에 맞으면)
		if(PresentFCFS == null) {																				// 현재 FCFS가 있으며
			if(!ReadyQueue.isEmpty()) {																			
				PresentFCFS = ReadyQueue.poll();
			}
		}
		if(PresentFCFS==null) ghanttchartPanel.adding(new JLabel("    "));			
		else ghanttchartPanel.adding(new JLabel(PresentFCFS.Name));												
																											
		if(!(PresentFCFS == null)) { 
			for(int i =0; i<ReadyQueue.size(); i++) {
				ReadyQueue.get(i).TurnaroundTime+=1;
			}
			PresentFCFS.BurstTime -= CoreWork;
		}											
		if(!(PresentFCFS == null) && PresentFCFS.BurstTime <= 0) {												// 현재 진행 중이던 프로세스가 끝나면
			System.out.println(PresentFCFS.Name + " " + "TT: " + PresentFCFS.TurnaroundTime);	
			PresentFCFS.TurnaroundTime = time - PresentFCFS.ArrivalTime;										// 끝난 프로세스의 TT,WT,NTT 계산
			PresentFCFS.WaitingTime = PresentFCFS.TurnaroundTime - PresentFCFS.StaticBurstTime;
			PresentFCFS.NormalizedTime = PresentFCFS.TurnaroundTime / PresentFCFS.StaticBurstTime;
			System.out.println(PresentFCFS.Name + "����" + " " + "Time:" + time + " " + "TT: " + PresentFCFS.TurnaroundTime +" " + "WT: " + PresentFCFS.WaitingTime + " " + "NTT: " +  PresentFCFS.NormalizedTime);
			PresentFCFS = null;
			for (int i = 0; i < ReadyQueue.size(); i++) {														//현재 ReadyQueue에 있는 프로세스들의 TT,WT,ResponseRatio 계산
				ReadyQueue.get(i).TurnaroundTime = time - ReadyQueue.get(i).ArrivalTime;
				ReadyQueue.get(i).WaitingTime = ReadyQueue.get(i).TurnaroundTime/ReadyQueue.get(i).StaticBurstTime;
				ReadyQueue.get(i).ResponseRatio = (ReadyQueue.get(i).WaitingTime + ReadyQueue.get(i).BurstTime) / ReadyQueue.get(i).BurstTime;
				System.out.println(ReadyQueue.get(i).Name + " " + "WT: " + ReadyQueue.get(i).WaitingTime + " " + "RS: " + ReadyQueue.get(i).ResponseRatio + "TT: ");
			}
			for(int i = 1;i<ReadyQueue.size();i++) {															// ResponseRadio값을 기준으로 정렬
				for(int j = 0; j<ReadyQueue.size()-1; j++) {
					if(ReadyQueue.get(j).ResponseRatio< ReadyQueue.get(j+1).ResponseRatio) {
						FCFSProcess temp = ReadyQueue.get(j);
						ReadyQueue.set(j, ReadyQueue.get(j+1));
						ReadyQueue.set(j+1, temp);
					}
				}
			}
		}
	}
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public void RRSchedulling() {
		ForQuantum++;
		if(!FCFSList.isEmpty() && time == FCFSList.peekFirst().ArrivalTime) ReadyQueue.add(FCFSList.poll()); 	// FCFSList에서 ReadyQueue로 이동(ArrivalTime에 맞으면)
		if(PresentFCFS == null) {																				// 현재 FCFS가 비어있을때
			if(!ReadyQueue.isEmpty()) {																			// ReadyQueue가 비어있지 않으면 현재 FCFS로 poll
				PresentFCFS = ReadyQueue.poll();
			}
		}
		
		if(PresentFCFS==null) ghanttchartPanel.adding(new JLabel("    "));			
		else ghanttchartPanel.adding(new JLabel(PresentFCFS.Name));												// GhanttChart 표시
		if(!(PresentFCFS == null)) PresentFCFS.BurstTime -= CoreWork;											// 현재 FCFS가 비어있지 않으면 bursttime에서 처리량 빼주기
		if(!(PresentFCFS == null) && PresentFCFS.BurstTime <= 0) {
			PresentFCFS.TurnaroundTime = time - PresentFCFS.ArrivalTime;
			PresentFCFS.WaitingTime = PresentFCFS.TurnaroundTime - PresentFCFS.StaticBurstTime;
			PresentFCFS.NormalizedTime = PresentFCFS.TurnaroundTime / PresentFCFS.StaticBurstTime;
			PresentFCFS = null;							// bursttime이 0 이하가 되면 null로 변화
			ForQuantum = 0;
		}
		
		if(ForQuantum == Quantum) {
			ReadyQueue.add(PresentFCFS);
			PresentFCFS = null;
			ForQuantum = 0;
		}
	}
	
	//--------------------------------------------------------------------------------------------------------------------------
	
	public void SPNSchedulling() {
		if(!FCFSList.isEmpty() && time == FCFSList.peekFirst().ArrivalTime) {
			
			for (int i = 0; i<=ReadyQueue.size(); i++) {
				if(i==ReadyQueue.size()) {
					ReadyQueue.add(FCFSList.poll());
					break;
				}
				
				if(ReadyQueue.get(i).BurstTime > FCFSList.peekFirst().BurstTime) {
					ReadyQueue.add(i, FCFSList.poll());
					break;
				}
			}		
		}
		
		if(PresentFCFS == null) {																				// 현재 FCFS가 비어있을때
			if(!ReadyQueue.isEmpty()) {																			// ReadyQueue가 비어있지 않으면 현재 FCFS로 poll
				PresentFCFS = ReadyQueue.poll();
			}
		}
		if(PresentFCFS==null) ghanttchartPanel.adding(new JLabel("    "));			
		else ghanttchartPanel.adding(new JLabel(PresentFCFS.Name));												// GhanttChart 표시
		if(!(PresentFCFS == null)) PresentFCFS.BurstTime -= CoreWork;											// 현재 FCFS가 비어있지 않으면 bursttime에서 처리량 빼주기
		if(!(PresentFCFS == null) && PresentFCFS.BurstTime <= 0) {						// bursttime이 0 이하가 되면 null로 변화
			PresentFCFS.TurnaroundTime = time - PresentFCFS.ArrivalTime;
			PresentFCFS.WaitingTime = PresentFCFS.TurnaroundTime - PresentFCFS.StaticBurstTime;
			PresentFCFS.NormalizedTime = PresentFCFS.TurnaroundTime / PresentFCFS.StaticBurstTime;
			PresentFCFS = null;	
		}
	}
	public void Core() { // 예정
		// -> CoreWork 이거 변경해주는거 
		// 상황에 맞게 CoreWork 변경
	}
}

