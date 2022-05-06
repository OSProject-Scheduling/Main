package Scheduling;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

import Manager.ProjectManager;

//import GUI.GhanttChartPanel;
//import Manager.ProjectManager;

import java.util.LinkedList;

public class MFQ {
	int MaxQuantum = 20;
	int ForQuantum = 0;
	int ForQuantum2 = 0;
	int ForQuantum3 = 0;
	int ForQuantum4 = 0;
	int Quantum = 3;
	int remain = 0;
	int Div = 7;
	
	ProjectManager manager;

	protected LinkedList<Process> HighAlgorithmList = new LinkedList<>();
	protected LinkedList<Process> MiddleAlgorithmList = new LinkedList<>();
	protected LinkedList<Process> LowAlgorithmList = new LinkedList<>();

	protected LinkedList<Process> HighReadyQueue = new LinkedList<>();
	protected LinkedList<Process> MiddleReadyQueue = new LinkedList<>();
	protected LinkedList<Process> LowReadyQueue = new LinkedList<>();
	
	protected Process PresentProcess = null;
	protected Process PresentProcess2 = null;
	protected Process PresentProcess3 = null;
	protected Process PresentProcess4 = null;
	
	protected int time = 0;
	
	protected int CoreWork1 = 1;
	protected int CoreWork2 = 1;
	protected int CoreWork3 = 1;
	protected int CoreWork4 = 1;
	
	int PCoreCount;
	int ECoreCount;
	
	double elec = 0;
	
	public Timer timer = new Timer(); // 타이머 중지를 위한 public 설정

	public MFQ(ProjectManager manager, int MaxQuantum, int Div, int PCoreCount, int ECoreCount) {
		this.manager = manager;
		this.MaxQuantum = MaxQuantum;
		this.Div = Div;
		//this.HighAlgorithmList = manager.addPanel.MFQHighAlgorithmList;
		//this.MiddleAlgorithmList = manager.addPanel.MFQMiddleAlgorithmList;
		//this.LowAlgorithmList = manager.addPanel.MFQLowAlorithmList;
		this.PCoreCount = PCoreCount;
		this.ECoreCount = ECoreCount;
		//ghanttchartPanel = manager.GhanttChart;
		if (ECoreCount == 3) {
			CoreWork4 = 2;
		}

		if (ECoreCount == 2) {
			CoreWork3 = 2;
			CoreWork4 = 2;
		}

		if (ECoreCount == 1) {
			CoreWork2 = 2;
			CoreWork3 = 2;
			CoreWork4 = 2;
		}

		if (ECoreCount == 0) {
			CoreWork2 = 2;
			CoreWork2 = 2;
			CoreWork3 = 2;
			CoreWork4 = 2;
		}

		start();
	}
	


	public void start() { // timer와 timertask를 사용해 카운트를 구현시켰습니다
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				schedulling();
				if (PresentProcess == null && (HighAlgorithmList.isEmpty() && HighReadyQueue.isEmpty())
						&& (MiddleAlgorithmList.isEmpty() && MiddleReadyQueue.isEmpty())
						&& (LowAlgorithmList.isEmpty() && LowReadyQueue.isEmpty()) 
						&& PresentProcess == null && PresentProcess2 == null 
						&& PresentProcess3 == null && PresentProcess4 == null)
					timer.cancel(); // MFQ용 종료 조건 선언 해야함
				time++; // time변수를 증가시켜줘 초를 표현

			}
		};
		timer = new Timer();
		timer.schedule(task, 100, 100); // 1초마다 실행
	}
	
	protected void CalculateTime() {				// TT / WT / NTT 계산
		if(!(PresentProcess == null) && PresentProcess.BurstTime <= 0) {
	         PresentProcess.TurnaroundTime = time - PresentProcess.ArrivalTime;                               // TT 계산
	         PresentProcess.WaitingTime = PresentProcess.TurnaroundTime - PresentProcess.StaticBurstTime;               // WT 계산
	         PresentProcess.NormalizedTime = PresentProcess.TurnaroundTime / PresentProcess.StaticBurstTime;            // NTT 계산
	         //manager.information.ChangeInformation(PresentProcess.TurnaroundTime, PresentProcess.WaitingTime, PresentProcess.NormalizedTime, PresentProcess.Row);
	         System.out.println(PresentProcess.Name + "나감");//테스트용 지워야됨!!!!!!!!!!!!!!!!!!!
	         PresentProcess = null;                     					// bursttime이 0 이하가 되면 null로 변화
	         
	      }
		
		if(!(PresentProcess2 == null) && PresentProcess2.BurstTime <= 0) {
	         PresentProcess2.TurnaroundTime = time - PresentProcess2.ArrivalTime;                               // TT 계산
	         PresentProcess2.WaitingTime = PresentProcess2.TurnaroundTime - PresentProcess2.StaticBurstTime;               // WT 계산
	         PresentProcess2.NormalizedTime = PresentProcess2.TurnaroundTime / PresentProcess2.StaticBurstTime;            // NTT 계산
	         //manager.information.ChangeInformation(PresentProcess2.TurnaroundTime, PresentProcess2.WaitingTime, PresentProcess2.NormalizedTime, PresentProcess2.Row);
	         System.out.println(PresentProcess2.Name + "나감");//테스트용 지워야됨!!!!!!!!!!!!!!!!!!!
	         PresentProcess2 = null;                     					// bursttime이 0 이하가 되면 null로 변화
	      }
		
		if(!(PresentProcess3 == null) && PresentProcess3.BurstTime <= 0) {
	         PresentProcess3.TurnaroundTime = time - PresentProcess3.ArrivalTime;                               // TT 계산
	         PresentProcess3.WaitingTime = PresentProcess3.TurnaroundTime - PresentProcess3.StaticBurstTime;               // WT 계산
	         PresentProcess3.NormalizedTime = PresentProcess3.TurnaroundTime / PresentProcess3.StaticBurstTime;            // NTT 계산
	         //manager.information.ChangeInformation(PresentProcess3.TurnaroundTime, PresentProcess3.WaitingTime, PresentProcess3.NormalizedTime, PresentProcess3.Row);
	         System.out.println(PresentProcess3.Name + "나감");//테스트용 지워야됨!!!!!!!!!!!!!!!!!!!
	         PresentProcess3 = null;                     					// bursttime이 0 이하가 되면 null로 변화
	      }
		
		if(!(PresentProcess4 == null) && PresentProcess4.BurstTime <= 0) {
	         PresentProcess4.TurnaroundTime = time - PresentProcess4.ArrivalTime;                               // TT 계산
	         PresentProcess4.WaitingTime = PresentProcess4.TurnaroundTime - PresentProcess4.StaticBurstTime;               // WT 계산
	         PresentProcess4.NormalizedTime = PresentProcess4.TurnaroundTime / PresentProcess4.StaticBurstTime;            // NTT 계산
	         //manager.information.ChangeInformation(PresentProcess4.TurnaroundTime, PresentProcess4.WaitingTime, PresentProcess4.NormalizedTime, PresentProcess4.Row);
	         System.out.println(PresentProcess4.Name + "나감");//테스트용 지워야됨!!!!!!!!!!!!!!!!!!!
	         PresentProcess4 = null;                     					// bursttime이 0 이하가 되면 null로 변화
	      }
	}

	void schedulling() { // MFQ용 스케쥴링
		
		// 종료조건
		if(PresentProcess == null && PresentProcess2 == null 
				&& PresentProcess3 == null && PresentProcess4 == null 
				&& (HighAlgorithmList.isEmpty() && HighReadyQueue.isEmpty())
				&& (MiddleAlgorithmList.isEmpty() && MiddleReadyQueue.isEmpty())
				&& (LowAlgorithmList.isEmpty() && LowReadyQueue.isEmpty())) {
			// 현재 실행중인 프로세스가 없고, 레디큐와 알고리즘 리스트 모두 비어있으면 종료
			System.out.println(time);
			System.out.println("종료");
			//manager.GhanttChart.addLastSecond();
			return;
		}
		
		if(PresentProcess == null) ForQuantum = 0;		//프로세스 종료 후 ForQuantum을 0으로 초기화
		if(PresentProcess2 == null) ForQuantum2 = 0;
		if(PresentProcess3 == null) ForQuantum3 = 0;
		if(PresentProcess4 == null) ForQuantum4 = 0;
		
		CalculateTime();								//프로세스가 BrustTime이 종료되었을경우 프로세스의 WT,TT,NTT 계산
		
		/* Algorithm ----> ReadyQueue */
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
		
		//High ReadyQueue
		if(!(HighAlgorithmList.isEmpty()) || !(HighReadyQueue.isEmpty())){			
			/*자신에게 할당된 시간이 끝나면 자원을 빼았김 (PresentProcess --> ReadyQueue맨뒤에 추가)*/
			if(!(PresentProcess == null) && ForQuantum == Quantum) {
				System.out.println(PresentProcess.Name + "빼앗김1");
				HighReadyQueue.add(PresentProcess);
				for (int i =0; i < HighReadyQueue.size(); i++) {
					System.out.print(time + ": " + HighReadyQueue.get(i).Name);
				}
				PresentProcess = null;
				ForQuantum = 0;
			}
			if(!(PresentProcess2 == null) && ForQuantum2 == Quantum) {
				System.out.println(PresentProcess2.Name + "빼앗김1");
				HighReadyQueue.add(PresentProcess2);
				for (int i =0; i < HighReadyQueue.size(); i++) {
					System.out.print(time + ": " + HighReadyQueue.get(i).Name);
				}
				PresentProcess = null;
				ForQuantum2 = 0;
			}
			if(!(PresentProcess3 == null) && ForQuantum3 == Quantum) {
				System.out.println(PresentProcess3.Name + "빼앗김1");
				HighReadyQueue.add(PresentProcess3);
				for (int i =0; i < HighReadyQueue.size(); i++) {
					System.out.print(time + ": " + HighReadyQueue.get(i).Name);
				}
				PresentProcess = null;
				ForQuantum3 = 0;
			}
			if(!(PresentProcess4 == null) && ForQuantum4 == Quantum) {
				System.out.println(PresentProcess4.Name + "빼앗김1");
				HighReadyQueue.add(PresentProcess4);
				for (int i =0; i < HighReadyQueue.size(); i++) {
					System.out.print(time + ": " + HighReadyQueue.get(i).Name);
				}
				PresentProcess = null;
				ForQuantum4 = 0;
			}
			
			//현재 프로세스가 진행 시간 +1
			ForQuantum++;
			ForQuantum2++;
			ForQuantum3++;
			ForQuantum4++;
			
			/*현재 실행중인 프로세스가 없을때 ReadyQueue에 있는 프로세스 실행*/
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
			if((PCoreCount + ECoreCount >= 2) && PresentProcess2 == null) {																				// 현재 FCFS가 비어있을때
				if(!HighReadyQueue.isEmpty()) {																			// ReadyQueue가 비어있지 않으면 현재 FCFS로 poll
					PresentProcess2 = HighReadyQueue.poll();
					System.out.println();
					System.out.println(PresentProcess2.Name + "들어옴");
					PresentProcess2.count++;
					remain = (int)(PresentProcess2.StaticBurstTime % Div);
					if (PresentProcess2.StaticBurstTime < Div)	Quantum = 1;
					else {
						if (PresentProcess.count <= Div-remain /*1*/) {
							Quantum = (int)(PresentProcess2.StaticBurstTime/Div);
						}
						else Quantum = (int)(PresentProcess2.StaticBurstTime/Div) + 1;
					}
				}
			}
			if((PCoreCount + ECoreCount >= 3) && PresentProcess3 == null) {																				// 현재 FCFS가 비어있을때
				if(!HighReadyQueue.isEmpty()) {																			// ReadyQueue가 비어있지 않으면 현재 FCFS로 poll
					PresentProcess3 = HighReadyQueue.poll();
					System.out.println();
					System.out.println(PresentProcess3.Name + "들어옴");
					PresentProcess3.count++;
					remain = (int)(PresentProcess3.StaticBurstTime % Div);
					if (PresentProcess3.StaticBurstTime < Div)	Quantum = 1;
					else {
						if (PresentProcess.count <= Div-remain /*1*/) {
							Quantum = (int)(PresentProcess3.StaticBurstTime/Div);
						}
						else Quantum = (int)(PresentProcess3.StaticBurstTime/Div) + 1;
					}
				}
			}
			if((PCoreCount + ECoreCount >= 4) && PresentProcess4 == null) {																				// 현재 FCFS가 비어있을때
				if(!HighReadyQueue.isEmpty()) {																			// ReadyQueue가 비어있지 않으면 현재 FCFS로 poll
					PresentProcess4 = HighReadyQueue.poll();
					System.out.println();
					System.out.println(PresentProcess4.Name + "들어옴");
					PresentProcess4.count++;
					remain = (int)(PresentProcess4.StaticBurstTime % Div);
					if (PresentProcess4.StaticBurstTime < Div)	Quantum = 1;
					else {
						if (PresentProcess4.count <= Div-remain /*1*/) {
							Quantum = (int)(PresentProcess4.StaticBurstTime/Div);
						}
						else Quantum = (int)(PresentProcess4.StaticBurstTime/Div) + 1;
					}
				}
			}
		}
		
		//Middle ReadyQueue ReadyQueue
		else if(!(MiddleAlgorithmList.isEmpty()) || !(MiddleReadyQueue.isEmpty())){
			/*자신에게 할당된 시간이 끝나면 자원을 빼았김 (PresentProcess --> ReadyQueue맨뒤에 추가)*/
			if(!(PresentProcess == null) && ForQuantum == Quantum) {
				System.out.println(PresentProcess.Name + "빼앗김1");
				MiddleReadyQueue.add(PresentProcess);
				PresentProcess = null;
				ForQuantum = 0;
			}
			if(!(PresentProcess2 == null) && ForQuantum2 == Quantum) {
				System.out.println(PresentProcess2.Name + "빼앗김1");
				MiddleReadyQueue.add(PresentProcess2);
				PresentProcess = null;
				ForQuantum2 = 0;
			}
			if(!(PresentProcess3 == null) && ForQuantum3 == Quantum) {
				System.out.println(PresentProcess3.Name + "빼앗김1");
				MiddleReadyQueue.add(PresentProcess3);
				PresentProcess = null;
				ForQuantum3 = 0;
			}
			if(!(PresentProcess4 == null) && ForQuantum4 == Quantum) {
				System.out.println(PresentProcess4.Name + "빼앗김1");
				MiddleReadyQueue.add(PresentProcess4);
				PresentProcess = null;
				ForQuantum4 = 0;
			}
			
			//현재 프로세스가 진행시간 +1
			ForQuantum++;
			ForQuantum2++;
			ForQuantum3++;
			ForQuantum4++;
			
			/*현재 실행중인 프로세스가 없을때 ReadyQueue에 있는 프로세스 실행*/
			if(PresentProcess == null) {																				// 현재 FCFS가 비어있을때
				if(!MiddleReadyQueue.isEmpty()) {																			// ReadyQueue가 비어있지 않으면 현재 FCFS로 poll
					PresentProcess = MiddleReadyQueue.poll();
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
			if((PCoreCount + ECoreCount >= 2) && PresentProcess2 == null) {																				// 현재 FCFS가 비어있을때
				if(!MiddleReadyQueue.isEmpty()) {																			// ReadyQueue가 비어있지 않으면 현재 FCFS로 poll
					PresentProcess2 = MiddleReadyQueue.poll();
					System.out.println();
					System.out.println(PresentProcess2.Name + "들어옴");
					PresentProcess2.count++;
					remain = (int)(PresentProcess2.StaticBurstTime % Div);
					if (PresentProcess2.StaticBurstTime < Div)	Quantum = 1;
					else {
						if (PresentProcess.count <= Div-remain /*1*/) {
							Quantum = (int)(PresentProcess2.StaticBurstTime/Div);
						}
						else Quantum = (int)(PresentProcess2.StaticBurstTime/Div) + 1;
					}
				}
			}
			if((PCoreCount + ECoreCount >= 3) && PresentProcess3 == null) {																				// 현재 FCFS가 비어있을때
				if(!MiddleReadyQueue.isEmpty()) {																			// ReadyQueue가 비어있지 않으면 현재 FCFS로 poll
					PresentProcess3 = MiddleReadyQueue.poll();
					System.out.println();
					System.out.println(PresentProcess3.Name + "들어옴");
					PresentProcess3.count++;
					remain = (int)(PresentProcess3.StaticBurstTime % Div);
					if (PresentProcess3.StaticBurstTime < Div)	Quantum = 1;
					else {
						if (PresentProcess.count <= Div-remain /*1*/) {
							Quantum = (int)(PresentProcess3.StaticBurstTime/Div);
						}
						else Quantum = (int)(PresentProcess3.StaticBurstTime/Div) + 1;
					}
				}
			}
			if((PCoreCount + ECoreCount >= 4) && PresentProcess4 == null) {																				// 현재 FCFS가 비어있을때
				if(!MiddleReadyQueue.isEmpty()) {																			// ReadyQueue가 비어있지 않으면 현재 FCFS로 poll
					PresentProcess4 = MiddleReadyQueue.poll();
					System.out.println();
					System.out.println(PresentProcess4.Name + "들어옴");
					PresentProcess4.count++;
					remain = (int)(PresentProcess4.StaticBurstTime % Div);
					if (PresentProcess4.StaticBurstTime < Div)	Quantum = 1;
					else {
						if (PresentProcess4.count <= Div-remain /*1*/) {
							Quantum = (int)(PresentProcess4.StaticBurstTime/Div);
						}
						else Quantum = (int)(PresentProcess4.StaticBurstTime/Div) + 1;
					}
				}
			}
		}
		
		//Low
		else if(!(LowAlgorithmList.isEmpty()) || !(LowReadyQueue.isEmpty())){		
			/*자신에게 할당된 시간이 끝나면 자원을 빼았김 (PresentProcess --> ReadyQueue맨뒤에 추가)*/
			if(!(PresentProcess == null) && ForQuantum == Quantum) {
				System.out.println(PresentProcess.Name + "빼앗김1");
				LowReadyQueue.add(PresentProcess);
				PresentProcess = null;
				ForQuantum = 0;
			}
			if(!(PresentProcess2 == null) && ForQuantum2 == Quantum) {
				System.out.println(PresentProcess2.Name + "빼앗김1");
				LowReadyQueue.add(PresentProcess2);
				PresentProcess = null;
				ForQuantum2 = 0;
			}
			if(!(PresentProcess3 == null) && ForQuantum3 == Quantum) {
				System.out.println(PresentProcess3.Name + "빼앗김1");
				LowReadyQueue.add(PresentProcess3);
				PresentProcess = null;
				ForQuantum3 = 0;
			}
			if(!(PresentProcess4 == null) && ForQuantum4 == Quantum) {
				System.out.println(PresentProcess4.Name + "빼앗김1");
				LowReadyQueue.add(PresentProcess4);
				PresentProcess = null;
				ForQuantum4 = 0;
			}
			
			//현재 프로세스가 진행시간 +1
			ForQuantum++;
			ForQuantum2++;
			ForQuantum3++;
			ForQuantum4++;
			
			/*현재 실행중인 프로세스가 없을때 ReadyQueue에 있는 프로세스 실행*/
			if(PresentProcess == null) {																				// 현재 FCFS가 비어있을때
				if(!LowReadyQueue.isEmpty()) {																			// ReadyQueue가 비어있지 않으면 현재 FCFS로 poll
					PresentProcess = LowReadyQueue.poll();
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
			if((PCoreCount + ECoreCount >= 2) && PresentProcess2 == null) {																				// 현재 FCFS가 비어있을때
				if(!LowReadyQueue.isEmpty()) {																			// ReadyQueue가 비어있지 않으면 현재 FCFS로 poll
					PresentProcess2 = LowReadyQueue.poll();
					System.out.println();
					System.out.println(PresentProcess2.Name + "들어옴");
					PresentProcess2.count++;
					remain = (int)(PresentProcess2.StaticBurstTime % Div);
					if (PresentProcess2.StaticBurstTime < Div)	Quantum = 1;
					else {
						if (PresentProcess.count <= Div-remain /*1*/) {
							Quantum = (int)(PresentProcess2.StaticBurstTime/Div);
						}
						else Quantum = (int)(PresentProcess2.StaticBurstTime/Div) + 1;
					}
				}
			}
			if((PCoreCount + ECoreCount >= 3) && PresentProcess3 == null) {																				// 현재 FCFS가 비어있을때
				if(!LowReadyQueue.isEmpty()) {																			// ReadyQueue가 비어있지 않으면 현재 FCFS로 poll
					PresentProcess3 = LowReadyQueue.poll();
					System.out.println();
					System.out.println(PresentProcess3.Name + "들어옴");
					PresentProcess3.count++;
					remain = (int)(PresentProcess3.StaticBurstTime % Div);
					if (PresentProcess3.StaticBurstTime < Div)	Quantum = 1;
					else {
						if (PresentProcess.count <= Div-remain /*1*/) {
							Quantum = (int)(PresentProcess3.StaticBurstTime/Div);
						}
						else Quantum = (int)(PresentProcess3.StaticBurstTime/Div) + 1;
					}
				}
			}
			if((PCoreCount + ECoreCount >= 4) && PresentProcess4 == null) {																				// 현재 FCFS가 비어있을때
				if(!LowReadyQueue.isEmpty()) {																			// ReadyQueue가 비어있지 않으면 현재 FCFS로 poll
					PresentProcess4 = LowReadyQueue.poll();
					System.out.println();
					System.out.println(PresentProcess4.Name + "들어옴");
					PresentProcess4.count++;
					remain = (int)(PresentProcess4.StaticBurstTime % Div);
					if (PresentProcess4.StaticBurstTime < Div)	Quantum = 1;
					else {
						if (PresentProcess4.count <= Div-remain /*1*/) {
							Quantum = (int)(PresentProcess4.StaticBurstTime/Div);
						}
						else Quantum = (int)(PresentProcess4.StaticBurstTime/Div) + 1;
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
		if(!(PresentProcess == null)) PresentProcess.BurstTime -= CoreWork1;	// 현재 RR이 비어있지 않으면 Bursttime에서 처리량 빼주기										// 현재 FCFS가 비어있지 않으면 bursttime에서 처리량 빼주기	
		if(!(PresentProcess2 == null)) PresentProcess2.BurstTime -= CoreWork2;
	    if(!(PresentProcess3 == null)) PresentProcess3.BurstTime -= CoreWork3;
	    if(!(PresentProcess4 == null)) PresentProcess4.BurstTime -= CoreWork4;		
	}

}