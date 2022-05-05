package Scheduling;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

import GUI.GhanttChartPanel;
import Manager.ProjectManager;

//import GUI.GhanttChartPanel;
//import Manager.ProjectManager;

import java.util.LinkedList;

public class MFQ {
	int MaxQuantum;
	int ForQuantum = 0;
	int Quantum = 3;
	int remain = 0;
	int Div;
	ProjectManager manager;
	
	GhanttChartPanel ghanttchartPanel;

	public LinkedList<Process> HighAlgorithmList = new LinkedList<>();
	public LinkedList<Process> MiddleAlgorithmList = new LinkedList<>();
	public LinkedList<Process> LowAlgorithmList = new LinkedList<>();

	public LinkedList<Process> HighReadyQueue = new LinkedList<>();
	public LinkedList<Process> MiddleReadyQueue = new LinkedList<>();
	public LinkedList<Process> LowReadyQueue = new LinkedList<>();

	Process PresentProcess = null;
	int time = 0;
	int CoreWork;
	int PCoreCount;
	int ECoreCount;
	double elec = 0;

	public Timer timer = new Timer(); // 타이머 중지를 위한 public 설정

	public MFQ(ProjectManager manager, int MaxQuantum, int Div, int PCoreCount, int ECoreCount) {
		this.manager = manager;
		this.MaxQuantum = MaxQuantum;
		this.Div = Div;
		this.HighAlgorithmList = manager.addPanel.MFQHighAlgorithmList;
		this.MiddleAlgorithmList = manager.addPanel.MFQMiddleAlgorithmList;
		this.LowAlgorithmList = manager.addPanel.MFQLowAlorithmList;
		ghanttchartPanel = manager.GhanttChart;
		
		this.PCoreCount = PCoreCount;
        this.ECoreCount = ECoreCount;
        
        CoreWork = PCoreCount*2 + ECoreCount;
		
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

	void schedulling() { // MFQ용 스케쥴링
		//High
		if(!(PresentProcess == null) && PresentProcess.BurstTime <= 0) {
			PresentProcess.TurnaroundTime = time - PresentProcess.ArrivalTime;
			PresentProcess.WaitingTime = PresentProcess.TurnaroundTime - PresentProcess.StaticBurstTime;
			PresentProcess.NormalizedTime = PresentProcess.TurnaroundTime / PresentProcess.StaticBurstTime;
	        manager.information.ChangeInformation(PresentProcess.TurnaroundTime, PresentProcess.WaitingTime, PresentProcess.NormalizedTime, PresentProcess.Row);
			PresentProcess = null;							// bursttime이 0 이하가 되면 null로 변화
			ForQuantum = 0;
		}
		if(!HighAlgorithmList.isEmpty() && time == HighAlgorithmList.peekFirst().ArrivalTime) {
			HighReadyQueue.add(HighAlgorithmList.poll()); 	// FCFSList에서 ReadyQueue로 이동(ArrivalTime에 맞으면)
			manager.HighReadyQueue.create_form(HighReadyQueue); // HighQueue GUI부분(HighQueue에 add되거나 poll되는 순간에 이거 사용해야함
		}
		if(!MiddleAlgorithmList.isEmpty() && time == MiddleAlgorithmList.peekFirst().ArrivalTime) {
			MiddleReadyQueue.add(MiddleAlgorithmList.poll()); 	// FCFSList에서 ReadyQueue로 이동(ArrivalTime에 맞으면)
			manager.MidReadyQueue.create_form(MiddleReadyQueue); // MiddleQueue GUI부분(MiddleQueue에 add되거나 poll되는 순간에 이거 사용해야함
		}
		if(!LowAlgorithmList.isEmpty() && time == LowAlgorithmList.peekFirst().ArrivalTime) {
			LowReadyQueue.add(LowAlgorithmList.poll()); 	// FCFSList에서 ReadyQueue로 이동(ArrivalTime에 맞으면)
			manager.lowReadyQueue.create_form(LowReadyQueue); // lowQueue GUI부분(lowQueue에 add되거나 poll되는 순간에 이거 사용해야함
		}
		//High
		if(!(HighAlgorithmList.isEmpty()) || !(HighReadyQueue.isEmpty())){			
			if(!(PresentProcess == null) && ForQuantum == Quantum) {
				HighReadyQueue.add(PresentProcess);
	
				PresentProcess = null;
				ForQuantum = 0;
			}
			ForQuantum++;
			if(PresentProcess == null) {																				// 현재 FCFS가 비어있을때
				if(!HighReadyQueue.isEmpty()) {																			// ReadyQueue가 비어있지 않으면 현재 FCFS로 poll
					PresentProcess = HighReadyQueue.poll();
					manager.HighReadyQueue.create_form(HighReadyQueue);    // HighQueue GUI부분(HighQueue에 add되거나 poll되는 순간에 이거 사용해야함
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
				MiddleReadyQueue.addLast(PresentProcess);
				PresentProcess = null;
				ForQuantum = 0;
			}
			ForQuantum++;
			if(PresentProcess == null) {																				// 현재 FCFS가 비어있을때
				if(!MiddleReadyQueue.isEmpty()) {																			// ReadyQueue가 비어있지 않으면 현재 FCFS로 poll
					PresentProcess = MiddleReadyQueue.poll();
					manager.MidReadyQueue.create_form(MiddleReadyQueue); // MiddleQueue GUI부분(MiddleQueue에 add되거나 poll되는 순간에 이거 사용해야함
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
				LowReadyQueue.addLast(PresentProcess);
				PresentProcess = null;
				ForQuantum = 0;
			}
			ForQuantum++;
			if(PresentProcess == null) {																				// 현재 FCFS가 비어있을때
				if(!LowReadyQueue.isEmpty()) {																			// ReadyQueue가 비어있지 않으면 현재 FCFS로 poll
					PresentProcess = LowReadyQueue.poll();		// 오류?? mid로 되어있음
					manager.lowReadyQueue.create_form(LowReadyQueue); // lowQueue GUI부분(lowQueue에 add되거나 poll되는 순간에 이거 사용해야함
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
		for (int i = 0; i < MiddleReadyQueue.size(); i++) { // 현재 ReadyQueue에 있는 프로세스들의 TT,WT,ResponseRatio 계산
			MiddleReadyQueue.get(i).TurnaroundTime = time - MiddleReadyQueue.get(i).ArrivalTime;
			MiddleReadyQueue.get(i).WaitingTime = MiddleReadyQueue.get(i).TurnaroundTime/ MiddleReadyQueue.get(i).StaticBurstTime;
			MiddleReadyQueue.get(i).ResponseRatio = (MiddleReadyQueue.get(i).WaitingTime + MiddleReadyQueue.get(i).StaticBurstTime)/ MiddleReadyQueue.get(i).StaticBurstTime;
			if ((MiddleReadyQueue.get(i).ResponseRatio <= 15 && MiddleReadyQueue.get(i).ResponseRatio > 10&& HighReadyQueue.size() <= 3) || MiddleReadyQueue.get(i).ResponseRatio > 15) {
				MiddleReadyQueue.get(i).Priority = "High";
				HighReadyQueue.add(MiddleReadyQueue.get(i));
				MiddleReadyQueue.remove(i);
				manager.HighReadyQueue.create_form(HighReadyQueue); // HighQueue GUI부분(HighQueue에 add되거나 poll되는 순간에 이거 사용해야함
				manager.MidReadyQueue.create_form(MiddleReadyQueue); // MiddleQueue GUI부분(MiddleQueue에 add되거나 poll되는 순간에 이거 사용해야함
			}
		}

		// Low-> Middle 승급조건
		for (int i = 0; i < LowReadyQueue.size(); i++) { // 현재 ReadyQueue에 있는 프로세스들의 TT,WT,ResponseRatio 계산
			LowReadyQueue.get(i).TurnaroundTime = time - LowReadyQueue.get(i).ArrivalTime;
			LowReadyQueue.get(i).WaitingTime = LowReadyQueue.get(i).TurnaroundTime/ LowReadyQueue.get(i).StaticBurstTime;
			LowReadyQueue.get(i).ResponseRatio = (LowReadyQueue.get(i).WaitingTime + LowReadyQueue.get(i).StaticBurstTime)/ LowReadyQueue.get(i).StaticBurstTime;
			if ((LowReadyQueue.get(i).ResponseRatio <= 20 && LowReadyQueue.get(i).ResponseRatio > 15&& MiddleReadyQueue.size() <= 5) || LowReadyQueue.get(i).ResponseRatio > 20) {
				LowReadyQueue.get(i).ArrivalTime = time;
				LowReadyQueue.get(i).Priority = "Middle";
				MiddleReadyQueue.add(LowReadyQueue.get(i));
				LowReadyQueue.remove(i);
				manager.MidReadyQueue.create_form(MiddleReadyQueue); // MiddleQueue GUI부분(MiddleQueue에 add되거나 poll되는 순간에 이거 사용해야함
				manager.lowReadyQueue.create_form(LowReadyQueue); // lowQueue GUI부분(lowQueue에 add되거나 poll되는 순간에 이거 사용해야함

			}
		}
				
		if (PresentProcess == null && (HighAlgorithmList.isEmpty() && HighReadyQueue.isEmpty())
				&& (MiddleAlgorithmList.isEmpty() && MiddleReadyQueue.isEmpty())
				&& (LowAlgorithmList.isEmpty() && LowReadyQueue.isEmpty())) {
			manager.GhanttChart.addLastSecond();
			return;
		}
		if(PresentProcess==null) {
			ghanttchartPanel.adding(new JLabel("    "),-1);			
			elec += ((PCoreCount + ECoreCount)*0.1);
		}
		else {
			ghanttchartPanel.adding(new JLabel(PresentProcess.Name), PresentProcess.Row);	
			elec += PCoreCount*3 + ECoreCount; // 8
		}										
		System.out.println(elec);// GhanttChart 표시
		
		if(!(PresentProcess == null)) PresentProcess.BurstTime -= CoreWork;
	}
}