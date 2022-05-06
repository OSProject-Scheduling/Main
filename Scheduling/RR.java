package Scheduling;
import javax.swing.JLabel;

import Manager.ProjectManager;

import java.util.LinkedList;


public class RR extends Algorithm{

	int Quantum;			// 퀀텀
	int ForQuantum = 0;		// 현재 프로세스 실행 시간, 퀀텀과 같은 수가 되면 현재 프로세스 내보내고 0으로 초기화
	int ForQuantum2 = 0;
	int ForQuantum3 = 0;
	int ForQuantum4 = 0;
	
	public RR(ProjectManager manager, int Quanturm, int PCoreCount, int ECoreCount) {
		super(manager, PCoreCount, ECoreCount);
		this.Quantum = Quanturm;
		// TODO Auto-generated constructor stub
	}

	void schedulling() {
		CalculateTime(); 								// 프로세스 종료 후 시간 계산

		/*--------------------------종료 조건---------------------------*/
		if(PresentProcess == null && PresentProcess2 == null && PresentProcess3 == null && PresentProcess4 == null && ReadyQueue.isEmpty() && AlgorithmList.isEmpty()) {
			// 현재 실행중인 프로세스가 없고, 레디큐와 알고리즘 리스트 모두 비어있으면 종료
			System.out.println(time);
			System.out.println("종료");
			manager.GhanttChart.addLastSecond();
			return;
		}
		
		if(PresentProcess == null) ForQuantum = 0;		//프로세스 종료 후 ForQuantum을 0으로 초기화
		if(PresentProcess2 == null) ForQuantum2 = 0;
		if(PresentProcess3 == null) ForQuantum3 = 0;
		if(PresentProcess4 == null) ForQuantum4 = 0;
		
		
		
		/*------------------------Ready Queue------------------------*/
		while(!AlgorithmList.isEmpty() && time == AlgorithmList.peekFirst().ArrivalTime) {
			//프로세스 리스트가 있고, 프로세스 리스트의 첫 프로세스 AR이 time과 같으면 (프로세스들은 AR기준으로 정렬되어 있음) 
			ReadyQueue.add(AlgorithmList.poll()); 	// AlgorithmList에서 ReadyQueue로 이동
			manager.ReadyQueue.create_form(ReadyQueue);
		}
		
		
		
		/*------------------------RR 알고리즘--------------------------*/
		if(ForQuantum == Quantum) {										// 퀀텀과 실행시간이 같아지면
			ReadyQueue.add(PresentProcess);								// 현재 실행 중인 프로세스를 레디큐 맨 뒤로 보내고
			manager.ReadyQueue.create_form(ReadyQueue);
			PresentProcess = null;										// 현재 프로세스를 null
			ForQuantum = 0;												// 현재 프로세스 시간 초기화
		}
		
		if((PCoreCount + ECoreCount >= 2) && ForQuantum2 == Quantum) { 	// 코어가 2개 이상이고, 퀀텀과 실행시간이 같아지면
			ReadyQueue.add(PresentProcess2);
			manager.ReadyQueue.create_form(ReadyQueue);
			PresentProcess2 = null;
			ForQuantum2 = 0;
		}
		
		if((PCoreCount + ECoreCount >= 3) && ForQuantum3 == Quantum) { 	// 코어가 3개 이상이고, 퀀텀과 실행시간이 같아지면
			ReadyQueue.add(PresentProcess3);
			manager.ReadyQueue.create_form(ReadyQueue);
			PresentProcess3 = null;
			ForQuantum3 = 0;
		}
		
		if((PCoreCount + ECoreCount >= 4) && ForQuantum4 == Quantum) { 	// 코어가 4개 이상이고, 퀀텀과 실행시간이 같아지면
			ReadyQueue.add(PresentProcess4);
			manager.ReadyQueue.create_form(ReadyQueue);
			PresentProcess4 = null;
			ForQuantum4 = 0;
		}

		ForQuantum++;	// 현재 프로세스 시간 +1
		ForQuantum2++;
		ForQuantum3++;
		ForQuantum4++;
		
		if(PresentProcess == null) {										// 현재 실행 중인 프로세스가 없을 때
			if(!ReadyQueue.isEmpty()) {										// ReadyQueue가 비어있지 않으면 맨 앞 프로세스 실행
				PresentProcess = ReadyQueue.poll();
				manager.ReadyQueue.create_form(ReadyQueue);
			}
			else {															// 레디큐가 비어있으면 대기전력 0.1+
				elec += 0.1;												
			}
		}
		
		if((PCoreCount + ECoreCount >= 2) && PresentProcess2 == null) {		// 코어가 2개 이상이고, 현재 실행 중인 프로세스가 없을 때
			if(!ReadyQueue.isEmpty()) {										// ReadyQueue가 비어있지 않으면 맨 앞 프로세스 실행
				PresentProcess2 = ReadyQueue.poll();
				manager.ReadyQueue.create_form(ReadyQueue);
			}
			else {															// 레디큐가 비어있으면 대기전력 0.1+
				elec += 0.1;
			}
		}
		
		if((PCoreCount + ECoreCount >= 3) && PresentProcess3 == null) {		// 코어가 3개 이상이고, 현재 실행 중인 프로세스가 없을 때
			if(!ReadyQueue.isEmpty()) {										// ReadyQueue가 비어있지 않으면 맨 앞 프로세스 실행
				PresentProcess3 = ReadyQueue.poll();
				manager.ReadyQueue.create_form(ReadyQueue);
			}
			else {															// 레디큐가 비어있으면 대기전력 0.1+
				elec += 0.1;
			}
		}
		
		if((PCoreCount + ECoreCount >= 4) && PresentProcess4 == null) {		// 코어가 4개 이상이고, 현재 실행 중인 프로세스가 없을 때
			if(!ReadyQueue.isEmpty()) {										// ReadyQueue가 비어있지 않으면 맨 앞 프로세스 실행
				PresentProcess4 = ReadyQueue.poll();
				manager.ReadyQueue.create_form(ReadyQueue);
			}
			else {															// 레디큐가 비어있으면 대기전력 0.1+
				elec += 0.1;
			}
		}
									
		if(!(PresentProcess == null)) {
	    	PresentProcess.BurstTime -= CoreWork1;	// 현재 실행 중인 프로세스가 있다면 Bursttime에서 처리량 빼주기
	    	if(CoreWork1 == 1)						// e코어이면 전력+1
	    		elec += 1;
	    	else									// p코어이면 전력+3
	    		elec += 3;
	    }
	    if(!(PresentProcess2 == null)) {
	    	PresentProcess2.BurstTime -= CoreWork2;
	    	if(CoreWork2 == 1)
	    		elec += 1;
	    	else
	    		elec += 3;
	    }
	    if(!(PresentProcess3 == null)) {
	    	PresentProcess3.BurstTime -= CoreWork3;
	    	if(CoreWork3 == 1)
	    		elec += 1;
	    	else
	    		elec += 3;
	    }
	    if(!(PresentProcess4 == null)) {
	    	PresentProcess4.BurstTime -= CoreWork4;
	    	if(CoreWork4 == 1)
	    		elec += 1;
	    	else
	    		elec += 3;
	    }
	}
}

