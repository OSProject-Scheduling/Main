package Scheduling;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

//import GUI.GhanttChartPanel;
//import Manager.ProjectManager;

import java.util.LinkedList;

public class MFQ {
	int MaxQuantum = 20;
	int ForQuantum = 0;
	int Quantum = 3;
	int Div = 7;
	//protected GhanttChartPanel ghanttchartPanel;

	protected LinkedList<Process> HighAlgorithmList = new LinkedList<>();
	protected LinkedList<Process> MiddleAlgorithmList = new LinkedList<>();
	protected LinkedList<Process> LowAlgorithmList = new LinkedList<>();

	protected LinkedList<Process> HighReadyQueue = new LinkedList<>();
	protected LinkedList<Process> MiddleReadyQueue = new LinkedList<>();
	protected LinkedList<Process> LowReadyQueue = new LinkedList<>();

	protected Process PresentProcess = null;
	protected int time = 0;
	protected int CoreWork = 2;

	public Timer timer = new Timer(); // 타이머 중지를 위한 public 설정
	
	public MFQ() {
		HighAlgorithmList.add(new Process("High","p1",0,15));
		HighAlgorithmList.add(new Process("High","p2",1,6));
		HighAlgorithmList.add(new Process("High","p3",4,7));
		
		MiddleAlgorithmList.add(new Process("Middle","p4",3,5));
		MiddleAlgorithmList.add(new Process("Middle","p5",6,3));
		MiddleAlgorithmList.add(new Process("Middle","p6",5,7));
		
		LowAlgorithmList.add(new Process("Low","p7",3,3));
		LowAlgorithmList.add(new Process("Low","p8",5,4));
		LowAlgorithmList.add(new Process("Low","p9",6,5));
		
		start();
	}
	
	public void start() { // timer와 timertask를 사용해 카운트를 구현시켰습니다
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				schedulling();
				if (PresentProcess == null && (HighAlgorithmList.isEmpty() && HighReadyQueue.isEmpty())
						&& (MiddleAlgorithmList.isEmpty() && MiddleReadyQueue.isEmpty())
						&& (LowAlgorithmList.isEmpty() && LowReadyQueue.isEmpty()))
					timer.cancel(); // MFQ용 종료 조건 선언 해야함
				time++; // time변수를 증가시켜줘 초를 표현

			}
		};
		timer = new Timer();
		timer.schedule(task, 1000, 1000); // 1초마다 실행
	}
	//응답률 : ReadyQueue.get(i).ResponseRatio = (ReadyQueue.get(i).WaitingTime + ReadyQueue.get(i).BurstTime) / ReadyQueue.get(i).BurstTime;
	void schedulling() { // MFQ용 스케쥴링
		System.out.print("ready Q : ");
		for (int i =0; i < HighReadyQueue.size(); i++) {
			System.out.print(HighReadyQueue.get(i).Name);
		}
		System.out.println();
		
		//High
		if(!(HighAlgorithmList.isEmpty()) || !(HighReadyQueue.isEmpty())){			
			if(!(PresentProcess == null) && PresentProcess.BurstTime <= 0) {
				PresentProcess.TurnaroundTime = time - PresentProcess.ArrivalTime;
				PresentProcess.WaitingTime = PresentProcess.TurnaroundTime - PresentProcess.StaticBurstTime;
				PresentProcess.NormalizedTime = PresentProcess.TurnaroundTime / PresentProcess.StaticBurstTime;
				System.out.println(PresentProcess.Name + "나감");
				System.out.println(PresentProcess.Name + "종료됨");
				PresentProcess = null;							// bursttime이 0 이하가 되면 null로 변화
				ForQuantum = 0;
			}
			if(!(PresentProcess == null) && ForQuantum == Quantum) {
				if (PresentProcess.count == Div) {
					HighReadyQueue.addFirst(PresentProcess);
				}
				else {
					System.out.println(PresentProcess.Name + "빼앗김");
					HighReadyQueue.addLast(PresentProcess);
					PresentProcess = null;
					ForQuantum = 0;
				}
			}
			ForQuantum++;
			if(!HighAlgorithmList.isEmpty() && time == HighAlgorithmList.peekFirst().ArrivalTime) {
				System.out.println(HighAlgorithmList.peekFirst().Name + " ReadQueue에 들어옴");
				HighReadyQueue.add(HighAlgorithmList.poll()); 	// FCFSList에서 ReadyQueue로 이동(ArrivalTime에 맞으면)
			}
			if(!MiddleAlgorithmList.isEmpty() && time == MiddleAlgorithmList.peekFirst().ArrivalTime) {
				MiddleReadyQueue.add(MiddleAlgorithmList.poll()); 	// FCFSList에서 ReadyQueue로 이동(ArrivalTime에 맞으면)
			}
			if(!LowAlgorithmList.isEmpty() && time == LowAlgorithmList.peekFirst().ArrivalTime) {
				LowReadyQueue.add(LowAlgorithmList.poll()); 	// FCFSList에서 ReadyQueue로 이동(ArrivalTime에 맞으면)
			}
			if(PresentProcess == null) {																				// 현재 FCFS가 비어있을때
				if(!HighReadyQueue.isEmpty()) {																			// ReadyQueue가 비어있지 않으면 현재 FCFS로 poll
					PresentProcess = HighReadyQueue.poll();
					System.out.println();
					System.out.println(PresentProcess.Name + "들어옴");
					PresentProcess.count++;
					Quantum = (int)(PresentProcess.StaticBurstTime/Div);
					if (Div > PresentProcess.StaticBurstTime) {
						Quantum = 1;
					}
				}
			}
		}
		
		//Middle
		else if(!(MiddleAlgorithmList.isEmpty()) || !(MiddleReadyQueue.isEmpty())){			
			if(!(PresentProcess == null) && PresentProcess.BurstTime <= 0) {
				PresentProcess.TurnaroundTime = time - PresentProcess.ArrivalTime;
				PresentProcess.WaitingTime = PresentProcess.TurnaroundTime - PresentProcess.StaticBurstTime;
				PresentProcess.NormalizedTime = PresentProcess.TurnaroundTime / PresentProcess.StaticBurstTime;
				System.out.println(PresentProcess.Name + "나감");
				System.out.println(PresentProcess.Name + "종료됨");
				PresentProcess = null;							// bursttime이 0 이하가 되면 null로 변화
				ForQuantum = 0;
			}
			if(!(PresentProcess == null) && ForQuantum == Quantum) {
				if (PresentProcess.count == Div) {
					MiddleReadyQueue.addFirst(PresentProcess);
				}
				else {
					System.out.println(PresentProcess.Name + "빼앗김");
					MiddleReadyQueue.addLast(PresentProcess);
					PresentProcess = null;
					ForQuantum = 0;
				}
			}
			ForQuantum++;
			if(!HighAlgorithmList.isEmpty() && time == HighAlgorithmList.peekFirst().ArrivalTime) {
				System.out.println(HighAlgorithmList.peekFirst().Name + " ReadQueue에 들어옴");
				HighReadyQueue.add(HighAlgorithmList.poll()); 	// FCFSList에서 ReadyQueue로 이동(ArrivalTime에 맞으면)
			}
			if(!MiddleAlgorithmList.isEmpty() && time == MiddleAlgorithmList.peekFirst().ArrivalTime) {
				MiddleReadyQueue.add(MiddleAlgorithmList.poll()); 	// FCFSList에서 ReadyQueue로 이동(ArrivalTime에 맞으면)
			}
			if(!LowAlgorithmList.isEmpty() && time == LowAlgorithmList.peekFirst().ArrivalTime) {
				LowReadyQueue.add(LowAlgorithmList.poll()); 	// FCFSList에서 ReadyQueue로 이동(ArrivalTime에 맞으면)
			}
			if(PresentProcess == null) {																				// 현재 FCFS가 비어있을때
				if(!MiddleReadyQueue.isEmpty()) {																			// ReadyQueue가 비어있지 않으면 현재 FCFS로 poll
					PresentProcess = MiddleReadyQueue.poll();
					System.out.println();
					System.out.println(PresentProcess.Name + "들어옴");
					PresentProcess.count++;
					Quantum = (int)(PresentProcess.StaticBurstTime/Div);
					if (Div > PresentProcess.StaticBurstTime) {
						Quantum = 1;
					}
				}
			}
		}
		
		//Low
		else if(!(LowAlgorithmList.isEmpty()) || !(LowReadyQueue.isEmpty())){			
			if(!(PresentProcess == null) && PresentProcess.BurstTime <= 0) {
				PresentProcess.TurnaroundTime = time - PresentProcess.ArrivalTime;
				PresentProcess.WaitingTime = PresentProcess.TurnaroundTime - PresentProcess.StaticBurstTime;
				PresentProcess.NormalizedTime = PresentProcess.TurnaroundTime / PresentProcess.StaticBurstTime;
				System.out.println(PresentProcess.Name + "나감");
				System.out.println(PresentProcess.Name + "종료됨");
				PresentProcess = null;							// bursttime이 0 이하가 되면 null로 변화
				ForQuantum = 0;
			}
			if(!(PresentProcess == null) && ForQuantum == Quantum) {
				if (PresentProcess.count == Div) {
					LowReadyQueue.addFirst(PresentProcess);
				}
				else {
					System.out.println(PresentProcess.Name + "빼앗김");
					LowReadyQueue.addLast(PresentProcess);
					PresentProcess = null;
					ForQuantum = 0;
				}
			}
			ForQuantum++;
			if(!HighAlgorithmList.isEmpty() && time == HighAlgorithmList.peekFirst().ArrivalTime) {
				System.out.println(HighAlgorithmList.peekFirst().Name + " ReadQueue에 들어옴");
				HighReadyQueue.add(HighAlgorithmList.poll()); 	// FCFSList에서 ReadyQueue로 이동(ArrivalTime에 맞으면)
			}
			if(!MiddleAlgorithmList.isEmpty() && time == MiddleAlgorithmList.peekFirst().ArrivalTime) {
				MiddleReadyQueue.add(MiddleAlgorithmList.poll()); 	// FCFSList에서 ReadyQueue로 이동(ArrivalTime에 맞으면)
			}
			if(!LowAlgorithmList.isEmpty() && time == LowAlgorithmList.peekFirst().ArrivalTime) {
				LowReadyQueue.add(LowAlgorithmList.poll()); 	// FCFSList에서 ReadyQueue로 이동(ArrivalTime에 맞으면)
			}
			if(PresentProcess == null) {																				// 현재 FCFS가 비어있을때
				if(!LowReadyQueue.isEmpty()) {																			// ReadyQueue가 비어있지 않으면 현재 FCFS로 poll
					PresentProcess = MiddleReadyQueue.poll();
					System.out.println();
					System.out.println(PresentProcess.Name + "들어옴");
					PresentProcess.count++;
					Quantum = (int)(PresentProcess.StaticBurstTime/Div);
					if (Div > PresentProcess.StaticBurstTime) {
						Quantum = 1;
					}
				}
			}
		}
		for (int i = 0 ; i < MiddleReadyQueue.size(); i++) {
			MiddleReadyQueue.get(i).TurnaroundTime = time - MiddleReadyQueue.get(i).ArrivalTime;
			MiddleReadyQueue.get(i).WaitingTime = MiddleReadyQueue.get(i).TurnaroundTime/MiddleReadyQueue.get(i).StaticBurstTime;
			MiddleReadyQueue.get(i).ResponseRatio = (MiddleReadyQueue.get(i).WaitingTime + MiddleReadyQueue.get(i).BurstTime) / MiddleReadyQueue.get(i).BurstTime;
			
		}
		for (int i = 0 ; i < LowReadyQueue.size(); i++) {
			LowReadyQueue.get(i).TurnaroundTime = time - MiddleReadyQueue.get(i).ArrivalTime;
			LowReadyQueue.get(i).WaitingTime = LowReadyQueue.get(i).TurnaroundTime/LowReadyQueue.get(i).StaticBurstTime;
			LowReadyQueue.get(i).ResponseRatio = (LowReadyQueue.get(i).WaitingTime + LowReadyQueue.get(i).BurstTime) / LowReadyQueue.get(i).BurstTime;
		}
		if(!(PresentProcess == null)) PresentProcess.BurstTime -= CoreWork;
		System.out.print(time);
		if(!(PresentProcess == null)) System.out.println(" " + PresentProcess.Name);
		if (PresentProcess == null && (HighAlgorithmList.isEmpty() && HighReadyQueue.isEmpty())
				&& (MiddleAlgorithmList.isEmpty() && MiddleReadyQueue.isEmpty())
				&& (LowAlgorithmList.isEmpty() && LowReadyQueue.isEmpty()))
			return;
	}

	protected void Core() { // 예정
		// -> CoreWork 이거 변경해주는거
		// 상황에 맞게 CoreWork 변경
	}
}