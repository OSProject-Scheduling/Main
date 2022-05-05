package Scheduling;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

//import GUI.GhanttChartPanel;
//import Manager.ProjectManager;

import java.util.LinkedList;

public class MFQ_v1 {
	int MaxQuantum = 20;
	int ForQuantum = 0;
	int Quantum = 3;
	int remain = 0;
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
	protected int CoreWork = 1;

	public Timer timer = new Timer(); // 타이머 중지를 위한 public 설정

	public MFQ_v1() {
		HighAlgorithmList.add(new Process("High","p1",0,20));
		HighAlgorithmList.add(new Process("High","p2",1,6));
		HighAlgorithmList.add(new Process("High","p3",4,8));
		
		MiddleAlgorithmList.add(new Process("Middle","p4",3,3));
		MiddleAlgorithmList.add(new Process("Middle","p5",5,1));
		MiddleAlgorithmList.add(new Process("Middle","p6",6,7));
		
		LowAlgorithmList.add(new Process("Low","p7",3,1));
		LowAlgorithmList.add(new Process("Low","p8",5,4));
		LowAlgorithmList.add(new Process("Low","p9",6,5));
		
		start();
	}
	// 먼저 몫+1을 나머지 만큼 먼저 실행하고, 그다음에 남은 번수를 몫만큼돌린다? 네 맞워요 ㅎㅎ
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
		timer.schedule(task, 100, 100); // 1초마다 실행
	}

	void schedulling() { // MFQ용 스케쥴링
		System.out.print("Highready Q : ");
		for (int i =0; i < HighReadyQueue.size(); i++) {
			System.out.print(HighReadyQueue.get(i).Name);
		}
		System.out.println();
		System.out.print("Middleready Q : ");
		for (int i =0; i < MiddleReadyQueue.size(); i++) {
			System.out.print(MiddleReadyQueue.get(i).Name);
		}
		System.out.println();
		System.out.print("Lowready Q : ");
		for (int i =0; i < LowReadyQueue.size(); i++) {
			System.out.print(LowReadyQueue.get(i).Name);
		}
		System.out.println();
		if(!(PresentProcess == null) && PresentProcess.BurstTime <= 0) {
			PresentProcess.TurnaroundTime = time - PresentProcess.ArrivalTime;
			PresentProcess.WaitingTime = PresentProcess.TurnaroundTime - PresentProcess.StaticBurstTime;
			PresentProcess.NormalizedTime = PresentProcess.TurnaroundTime / PresentProcess.StaticBurstTime;
			System.out.println(PresentProcess.Name + "나감");
			System.out.println(PresentProcess.Name + "종료됨");
			PresentProcess = null;							// bursttime이 0 이하가 되면 null로 변화
			ForQuantum = 0;
		}
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
		//High
		if(!(HighAlgorithmList.isEmpty()) || !(HighReadyQueue.isEmpty())){			
			
			if(!(PresentProcess == null) && ForQuantum == Quantum) {
				System.out.println(PresentProcess.Name + "빼앗김1");
				HighReadyQueue.add(PresentProcess);
				for (int i =0; i < HighReadyQueue.size(); i++) {
					System.out.print(time + ": " + HighReadyQueue.get(i).Name);
				}
				PresentProcess = null;
				ForQuantum = 0;
			}
			ForQuantum++;
			
			if(PresentProcess == null) {																				// 현재 FCFS가 비어있을때
				if(!HighReadyQueue.isEmpty()) {																			// ReadyQueue가 비어있지 않으면 현재 FCFS로 poll
					PresentProcess = HighReadyQueue.poll();
					System.out.println();
					System.out.println(PresentProcess.Name + "들어옴");
					PresentProcess.count++;
					remain = (int)(PresentProcess.StaticBurstTime % Div);
					if (PresentProcess.StaticBurstTime < Div)	Quantum = 1;
					else {
						if (PresentProcess.count <= Div-remain /*1*/) {
							Quantum = (int)(PresentProcess.StaticBurstTime/Div);
						}
						else Quantum = (int)(PresentProcess.StaticBurstTime/Div) + 1;
					}
				}
			}
		}
		
		//Middle
		else if(!(MiddleAlgorithmList.isEmpty()) || !(MiddleReadyQueue.isEmpty())){			
			
			if(!(PresentProcess == null) && ForQuantum == Quantum) {
				System.out.println(PresentProcess.Name + "빼앗김2");
				MiddleReadyQueue.addLast(PresentProcess);
				PresentProcess = null;
				ForQuantum = 0;
			}
			ForQuantum++;
			
			if(PresentProcess == null) {																				// 현재 FCFS가 비어있을때
				if(!MiddleReadyQueue.isEmpty()) {																			// ReadyQueue가 비어있지 않으면 현재 FCFS로 poll
					PresentProcess = MiddleReadyQueue.poll();
					System.out.println();
					System.out.println(PresentProcess.Name + "들어옴");
					PresentProcess.count++;
					if (PresentProcess.StaticBurstTime < Div)	Quantum = 1;
					else {
						if (PresentProcess.count <= Div-remain) {
							Quantum = (int)(PresentProcess.StaticBurstTime/Div);
						}
						else Quantum = (int)(PresentProcess.StaticBurstTime/Div) + 1;
					}
				}
			}
		}
		
		//Low
		else if(!(LowAlgorithmList.isEmpty()) || !(LowReadyQueue.isEmpty())){			
			if(!(PresentProcess == null) && ForQuantum == Quantum) {
				System.out.println(PresentProcess.Name + "빼앗김3");
				LowReadyQueue.addLast(PresentProcess);
				PresentProcess = null;
				ForQuantum = 0;
			}
			ForQuantum++;
			
			if(PresentProcess == null) {																				// 현재 FCFS가 비어있을때
				if(!LowReadyQueue.isEmpty()) {																			// ReadyQueue가 비어있지 않으면 현재 FCFS로 poll
					PresentProcess = LowReadyQueue.poll();
					System.out.println(PresentProcess.Name + "들어옴");
					PresentProcess.count++;
					if (PresentProcess.StaticBurstTime < Div)	Quantum = 1;
					else {
						if (PresentProcess.count <= Div-remain) {
							Quantum = (int)(PresentProcess.StaticBurstTime/Div);
						}
						else Quantum = (int)(PresentProcess.StaticBurstTime/Div) + 1;
					}
				}
			}
		}
		
		
		//Middle-> High  승급조건
		for (int i = 0; i < MiddleReadyQueue.size(); i++) {														//현재 ReadyQueue에 있는 프로세스들의 TT,WT,ResponseRatio 계산
			MiddleReadyQueue.get(i).TurnaroundTime = time - MiddleReadyQueue.get(i).ArrivalTime;
			MiddleReadyQueue.get(i).WaitingTime = MiddleReadyQueue.get(i).TurnaroundTime/MiddleReadyQueue.get(i).StaticBurstTime;
			MiddleReadyQueue.get(i).ResponseRatio = (MiddleReadyQueue.get(i).WaitingTime + MiddleReadyQueue.get(i).StaticBurstTime) / MiddleReadyQueue.get(i).StaticBurstTime;
			if((MiddleReadyQueue.get(i).ResponseRatio <= 15 && MiddleReadyQueue.get(i).ResponseRatio > 10 && HighReadyQueue.size() <=3) || MiddleReadyQueue.get(i).ResponseRatio > 15) {
				System.out.println(time + ": " + MiddleReadyQueue.get(i).Name + "승급함" + MiddleReadyQueue.get(i).ResponseRatio);
				MiddleReadyQueue.get(i).Priority = "High";
				HighReadyQueue.add(MiddleReadyQueue.get(i));
				MiddleReadyQueue.remove(i);
			}
		}
		
		//Low-> Middle  승급조건
		for (int i = 0; i < LowReadyQueue.size(); i++) {														//현재 ReadyQueue에 있는 프로세스들의 TT,WT,ResponseRatio 계산
			LowReadyQueue.get(i).TurnaroundTime = time - LowReadyQueue.get(i).ArrivalTime;
			LowReadyQueue.get(i).WaitingTime = LowReadyQueue.get(i).TurnaroundTime/LowReadyQueue.get(i).StaticBurstTime;
			LowReadyQueue.get(i).ResponseRatio = (LowReadyQueue.get(i).WaitingTime + LowReadyQueue.get(i).StaticBurstTime) / LowReadyQueue.get(i).StaticBurstTime;
			if((LowReadyQueue.get(i).ResponseRatio <= 20 && LowReadyQueue.get(i).ResponseRatio > 15 && MiddleReadyQueue.size() <=5) || LowReadyQueue.get(i).ResponseRatio > 20) {
				System.out.println(time + ": " + LowReadyQueue.get(i).Name + "승급함" + LowReadyQueue.get(i).ResponseRatio);
				LowReadyQueue.get(i).ArrivalTime = time;
				LowReadyQueue.get(i).Priority = "Middle";
				MiddleReadyQueue.add(LowReadyQueue.get(i));
				LowReadyQueue.remove(i);
			}
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