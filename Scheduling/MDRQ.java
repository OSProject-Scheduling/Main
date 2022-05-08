package Scheduling;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

import GUI.GhanttChartPanel;
import Manager.ProjectManager;

//import GUI.GhanttChartPanel;
//import Manager.ProjectManager;

import java.util.LinkedList;

public class MDRQ {
	public int MaxQuantum;
	private int ForQuantum[];
	private int Quantum;
	private int remain = 0;
	private int Div;
	
	private GhanttChartPanel[] ghanttchartPanel;

	private Process PresentProcess[];
	
	private int CoreWork[];
	
	private LinkedList<Process> HighAlgorithmList = new LinkedList<>();
	private LinkedList<Process> MiddleAlgorithmList = new LinkedList<>();
	private LinkedList<Process> LowAlgorithmList = new LinkedList<>();

	private LinkedList<Process> HighReadyQueue = new LinkedList<>();
	private LinkedList<Process> MiddleReadyQueue = new LinkedList<>();
	private LinkedList<Process> LowReadyQueue = new LinkedList<>();
	
	private int time = 0;
	
	public int PCoreCount;
	public int ECoreCount;
	private int CoreCount;
	
	private double elec = 0;
	
	private ProjectManager manager;
	
	public Timer timer = new Timer(); // 타이머 중지를 위한 public 설정

	public MDRQ(ProjectManager manager, int MaxQuantum, int Div, int PCoreCount, int ECoreCount) {
		this.manager = manager;
		this.MaxQuantum = MaxQuantum;
		this.Div = Div;
		
		this.HighAlgorithmList = manager.addPanel.MFQHighAlgorithmList;
		this.MiddleAlgorithmList = manager.addPanel.MFQMiddleAlgorithmList;
		this.LowAlgorithmList = manager.addPanel.MFQLowAlorithmList;
	
		this.PCoreCount = PCoreCount;
		this.ECoreCount = ECoreCount;
		CoreCount = PCoreCount + ECoreCount;
		
		ghanttchartPanel = new GhanttChartPanel[CoreCount];							// 배열화
		PresentProcess = new Process[CoreCount];
		CoreWork = new int[CoreCount];
		
		ForQuantum = new int[CoreCount];
		
		this.ghanttchartPanel[0] = manager.GhanttChart_1;
        if(CoreCount>=2) this.ghanttchartPanel[1] = manager.GhanttChart_2;			// 간트차트
        if(CoreCount>=3) this.ghanttchartPanel[2] = manager.GhanttChart_3;
        if(CoreCount==4) this.ghanttchartPanel[3] = manager.GhanttChart_4;
        
		for(int i=0; i<ForQuantum.length; i++) ForQuantum[i] = 0;
		
		for(int i=0; i<PCoreCount; i++) CoreWork[i] = 2;							// CoreWork
        for(int i=0; i<ECoreCount; i++) CoreWork[CoreCount-i-1] = 1;

		start();
	}
	


	public void start() { // timer와 timertask를 사용해 카운트를 구현시켰습니다
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				schedulling();
				if ((HighAlgorithmList.isEmpty() && HighReadyQueue.isEmpty())
						&& (MiddleAlgorithmList.isEmpty() && MiddleReadyQueue.isEmpty())
						&& (LowAlgorithmList.isEmpty() && LowReadyQueue.isEmpty())) {
					boolean Quit = true;
					for(int i=0; i<CoreCount; i++) {
						if(PresentProcess[i] != null) {
							Quit = false;
						}
					}
					if(Quit == true) {
						timer.cancel();
					}
				}
				time++; // time변수를 증가시켜줘 초를 표현
			}
		};
		timer = new Timer();
		timer.schedule(task, 1000, 1000); // 1초마다 실행
	}
	
	private void CalculateTime() {				// TT / WT / NTT 계산
		for(int i=0; i<CoreCount; i++) {
			if(!(PresentProcess[i] == null) && PresentProcess[i].BurstTime <= 0) {
		         PresentProcess[i].TurnaroundTime = time - PresentProcess[i].ArrivalTime;                               // TT 계산             // WT 계산
		         PresentProcess[i].NormalizedTime = Math.round((PresentProcess[i].TurnaroundTime / PresentProcess[i].StaticBurstTime)*100)/100.0;            // NTT 계산
		         manager.information.ChangeInformation(PresentProcess[i].TurnaroundTime, PresentProcess[i].WaitingTime, PresentProcess[i].NormalizedTime, PresentProcess[i].Row);
		         PresentProcess[i] = null;                     							// bursttime이 0 이하가 되면 null로 변화
		      }
		}
	}

	private void schedulling() { // MFQ용 스케쥴링
		CalculateTime();								//프로세스가 BrustTime이 종료되었을경우 프로세스의 WT,TT,NTT 계산
		manager.mainPanel.Elec.setText("총 전력: " + Math.round(elec*100)/100.0 + "W");
		/*--------------------------종료 조건---------------------------*/
		if ((HighAlgorithmList.isEmpty() && HighReadyQueue.isEmpty())
				&& (MiddleAlgorithmList.isEmpty() && MiddleReadyQueue.isEmpty())
				&& (LowAlgorithmList.isEmpty() && LowReadyQueue.isEmpty())) {
			boolean Quit = true;
			for(int i=0; i<CoreCount; i++) {
				if(PresentProcess[i] != null) {
					Quit = false;
				}
			}
			if(Quit == true) {
				for (int i = 0; i < CoreCount; i++) {
					ghanttchartPanel[i].addLastSecond();
				}
				manager.mdrq = null;
				return;
			}
		}
		
		for(int i=0; i<CoreCount; i++) if(PresentProcess[i] == null) ForQuantum[i] = 0; //프로세스 종료 후 ForQuantum을 0으로 초기화
		/* Algorithm ----> ReadyQueue */
		while(!HighAlgorithmList.isEmpty() && time == HighAlgorithmList.peekFirst().ArrivalTime) {
			HighReadyQueue.add(HighAlgorithmList.poll()); 	// FCFSList에서 ReadyQueue로 이동(ArrivalTime에 맞으면)
		}
		while(!MiddleAlgorithmList.isEmpty() && time == MiddleAlgorithmList.peekFirst().ArrivalTime) {
			MiddleReadyQueue.add(MiddleAlgorithmList.poll()); 	// FCFSList에서 ReadyQueue로 이동(ArrivalTime에 맞으면)
		}
		while(!LowAlgorithmList.isEmpty() && time == LowAlgorithmList.peekFirst().ArrivalTime) {
			LowReadyQueue.add(LowAlgorithmList.poll()); 	// FCFSList에서 ReadyQueue로 이동(ArrivalTime에 맞으면)
		}
		//High ReadyQueue
		
		/*자신에게 할당된 시간이 끝나면 자원을 빼았김 (PresentProcess --> ReadyQueue맨뒤에 추가)*/
		for(int i=0; i<CoreCount; i++) {
			if(!(PresentProcess[i] == null) && ForQuantum[i] >= Quantum) {
				if(PresentProcess[i].Priority.equals("High")) HighReadyQueue.add(PresentProcess[i]);
				else if(PresentProcess[i].Priority.equals("Middle")) MiddleReadyQueue.add(PresentProcess[i]);
				else if(PresentProcess[i].Priority.equals("Low")) LowReadyQueue.add(PresentProcess[i]);
				PresentProcess[i] = null;
				ForQuantum[i] = 0;
			}
			if(CoreWork[i]==1) ForQuantum[i]++; 	//현재 프로세스가 진행 시간 +1
			else ForQuantum[i] += 2; 	//현재 프로세스가 진행 시간 +2
		}
		if(!(HighAlgorithmList.isEmpty()) || !(HighReadyQueue.isEmpty())){			
			/*현재 실행중인 프로세스가 없을때 ReadyQueue에 있는 프로세스 실행*/
			for(int i=0; i<CoreCount; i++) {
				if(PresentProcess[i] == null) {																				// 현재 FCFS가 비어있을때
					if(!HighReadyQueue.isEmpty()) {																			// ReadyQueue가 비어있지 않으면 현재 FCFS로 poll
						PresentProcess[i] = HighReadyQueue.poll();
						PresentProcess[i].count++;
						remain = (int)(PresentProcess[i].StaticBurstTime % Div);
						if (PresentProcess[i].StaticBurstTime < Div)	Quantum = 1;
						else {
							if (PresentProcess[i].count <= Div-remain /*1*/) {
								Quantum = (int)(PresentProcess[i].StaticBurstTime/Div);
								if(Quantum > MaxQuantum) Quantum = MaxQuantum;
							}
							else {
								Quantum = (int)(PresentProcess[i].StaticBurstTime/Div) + 1;
								if(Quantum > MaxQuantum) Quantum = MaxQuantum;
							}
							
						}
					}
				}
			}
		}
		
		//Middle ReadyQueue ReadyQueue
		else if(!(MiddleAlgorithmList.isEmpty()) || !(MiddleReadyQueue.isEmpty())){
			/*현재 실행중인 프로세스가 없을때 ReadyQueue에 있는 프로세스 실행*/
			for(int i=0; i<CoreCount; i++) {
				if(PresentProcess[i] == null) {																				// 현재 FCFS가 비어있을때
					if(!MiddleReadyQueue.isEmpty()) {																			// ReadyQueue가 비어있지 않으면 현재 FCFS로 poll
						PresentProcess[i] = MiddleReadyQueue.poll();
						PresentProcess[i].count++;
						remain = (int)(PresentProcess[i].StaticBurstTime % Div);
						if (PresentProcess[i].StaticBurstTime < Div)	Quantum = 1;
						else {
							if (PresentProcess[i].count <= Div-remain /*1*/) {
								Quantum = (int)(PresentProcess[i].StaticBurstTime/Div);
								if(Quantum > MaxQuantum) Quantum = MaxQuantum;
							}
							else {
								Quantum = (int)(PresentProcess[i].StaticBurstTime/Div) + 1;
								if(Quantum > MaxQuantum) Quantum = MaxQuantum;
							}
							
						}
					}
				}
			}
		}
		
		//Low
		else if(!(LowAlgorithmList.isEmpty()) || !(LowReadyQueue.isEmpty())){		
			/*현재 실행중인 프로세스가 없을때 ReadyQueue에 있는 프로세스 실행*/
			for(int i=0; i<CoreCount; i++) {
				if(PresentProcess[i] == null) {																				// 현재 FCFS가 비어있을때
					if(!LowReadyQueue.isEmpty()) {																			// ReadyQueue가 비어있지 않으면 현재 FCFS로 poll
						PresentProcess[i] = LowReadyQueue.poll();
						PresentProcess[i].count++;
						remain = (int)(PresentProcess[i].StaticBurstTime % Div);
						if (PresentProcess[i].StaticBurstTime < Div)	Quantum = 1;
						else {
							if (PresentProcess[i].count <= Div-remain /*1*/) {
								Quantum = (int)(PresentProcess[i].StaticBurstTime/Div);
								if(Quantum > MaxQuantum) Quantum = MaxQuantum;
							}
							else {
								Quantum = (int)(PresentProcess[i].StaticBurstTime/Div) + 1;
								if(Quantum > MaxQuantum) Quantum = MaxQuantum;
							}
						}
					}
				}
			}
		}
		
		//Middle-> High  승급조건
		for (int i = 0; i < MiddleReadyQueue.size(); i++) {														//현재 ReadyQueue에 있는 프로세스들의 TT,WT,ResponseRatio 계산
			MiddleReadyQueue.get(i).TurnaroundTime = time - MiddleReadyQueue.get(i).ArrivalTime;
			MiddleReadyQueue.get(i).ResponseRatio = (MiddleReadyQueue.get(i).WaitingTime + MiddleReadyQueue.get(i).StaticBurstTime) / MiddleReadyQueue.get(i).StaticBurstTime;
			if((MiddleReadyQueue.get(i).ResponseRatio <= 15 && MiddleReadyQueue.get(i).ResponseRatio > 10 && HighReadyQueue.size() <=3) || MiddleReadyQueue.get(i).ResponseRatio > 15) {
				MiddleReadyQueue.get(i).Priority = "High";
				HighReadyQueue.add(MiddleReadyQueue.get(i));
				MiddleReadyQueue.remove(i);
			}
		}
		
		//Low-> Middle  승급조건
		for (int i = 0; i < LowReadyQueue.size(); i++) {														//현재 ReadyQueue에 있는 프로세스들의 TT,WT,ResponseRatio 계산
			LowReadyQueue.get(i).TurnaroundTime = time - LowReadyQueue.get(i).ArrivalTime;
			LowReadyQueue.get(i).ResponseRatio = (LowReadyQueue.get(i).WaitingTime + LowReadyQueue.get(i).StaticBurstTime) / LowReadyQueue.get(i).StaticBurstTime;
			if((LowReadyQueue.get(i).ResponseRatio <= 20 && LowReadyQueue.get(i).ResponseRatio > 15 && MiddleReadyQueue.size() <=5) || LowReadyQueue.get(i).ResponseRatio > 20) {
				LowReadyQueue.get(i).ArrivalTime = time;
				LowReadyQueue.get(i).Priority = "Middle";
				MiddleReadyQueue.add(LowReadyQueue.get(i));
				LowReadyQueue.remove(i);
			}
		}
		manager.MFQreadyQueue.HighReadyQueue.create_form(HighReadyQueue);
		manager.MFQreadyQueue.MiddleReadyQueue.create_form(MiddleReadyQueue);
		manager.MFQreadyQueue.LowReadyQueue.create_form(LowReadyQueue);
		
		for(int i=0; i<HighReadyQueue.size(); i++) HighReadyQueue.get(i).WaitingTime++;
		for(int i=0; i<MiddleReadyQueue.size(); i++) MiddleReadyQueue.get(i).WaitingTime++;
		for(int i=0; i<LowReadyQueue.size(); i++) LowReadyQueue.get(i).WaitingTime++;
		/*-------------------------------GUI---------------------------*/
		for(int i=0; i<CoreCount; i++) {
			if(PresentProcess[i]==null) ghanttchartPanel[i].adding(new JLabel("    "), -1);
			else ghanttchartPanel[i].adding(new JLabel(PresentProcess[i].Name), PresentProcess[i].Row);
		}
		
		for(int i=0; i<CoreCount; i++) {
			if(!(PresentProcess[i] == null)) {
				PresentProcess[i].BurstTime -= CoreWork[i];	// 현재 FCFS가 비어있지 않으면 bursttime에서 처리량 빼주기
				if (CoreWork[i] == 1) elec += 1;		//현재 코어가 E Core인경우 소비전력  +1
				else elec += 3;						//현재 코어가 P Core인경우 소비전력  +1
			}
			else {
				elec += 0.1;
			}
		}
	}
}