package Scheduling;
import javax.swing.JLabel;

import Manager.ProjectManager;

import java.util.LinkedList;


public class RR extends Algorithm{

	int Quantum;			// 퀀텀
	int ForQuantum[];
	
	public RR(ProjectManager manager, int Quanturm, int PCoreCount, int ECoreCount) {
		super(manager, PCoreCount, ECoreCount);
		this.Quantum = Quanturm;
		ForQuantum = new int[PCoreCount + ECoreCount];
		for(int i=0; i<ForQuantum.length; i++) ForQuantum[i] = 0;
		// TODO Auto-generated constructor stub
	}

	void schedulling() {
		CalculateTime(); 								// 프로세스 종료 후 시간 계산

		/*--------------------------종료 조건---------------------------*/
		Terminate();
		
		for(int i=0; i<CoreCount; i++) if(PresentProcess[i] == null) ForQuantum[i] = 0; //프로세스 종료 후 ForQuantum을 0으로 초기화
		
		/*------------------------Ready Queue------------------------*/
		while(!AlgorithmList.isEmpty() && time == AlgorithmList.peekFirst().ArrivalTime) {
			//프로세스 리스트가 있고, 프로세스 리스트의 첫 프로세스 AR이 time과 같으면 (프로세스들은 AR기준으로 정렬되어 있음) 
			ReadyQueue.add(AlgorithmList.poll()); 	// AlgorithmList에서 ReadyQueue로 이동
			manager.ReadyQueue.create_form(ReadyQueue);
		}
	
		
		/*------------------------RR 알고리즘--------------------------*/
		for(int i=0; i<CoreCount; i++) {
			if(ForQuantum[i] == Quantum) {										// 퀀텀과 실행시간이 같아지면
				ReadyQueue.add(PresentProcess[i]);								// 현재 실행 중인 프로세스를 레디큐 맨 뒤로 보내고
				PresentProcess[i] = null;										// 현재 프로세스를 null
				ForQuantum[i] = 0;												// 현재 프로세스 시간 초기화
			}
		}
		manager.ReadyQueue.create_form(ReadyQueue);

		for(int i=0; i<CoreCount; i++) ForQuantum[i]++; 						// 현재 프로세스 시간 +1

		for(int i=0; i<CoreCount; i++) {
			if(PresentProcess[i] == null) {										// 현재 실행 중인 프로세스가 없을 때
				if(!ReadyQueue.isEmpty()) {										// ReadyQueue가 비어있지 않으면 맨 앞 프로세스 실행
					PresentProcess[i] = ReadyQueue.poll();
				}
				else {															// 레디큐가 비어있으면 대기전력 0.1+
					elec += 0.1;												
				}
			}
		}
		manager.ReadyQueue.create_form(ReadyQueue);
		
		GUISetting();
		
		for(int i=0; i<CoreCount; i++) {
			if(!(PresentProcess[i] == null)) {
		    	PresentProcess[i].BurstTime -= CoreWork[i];						// 현재 실행 중인 프로세스가 있다면 Bursttime에서 처리량 빼주기
		    	if(CoreWork[i] == 1)											// e코어이면 전력+1
		    		elec += 1;
		    	else															// p코어이면 전력+3
		    		elec += 3;
		    }
		}
	}
}

