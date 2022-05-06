package Scheduling;
import java.util.LinkedList;

import javax.swing.JLabel;

import Manager.ProjectManager;

public class HRRN extends Algorithm{

	public HRRN(ProjectManager manager, int PCoreCount, int ECoreCount) {
		super(manager, PCoreCount, ECoreCount);
		// TODO Auto-generated constructor stub
	}
	
	void schedulling() {
		CalculateTime(); // 프로세스 종료 후 시간 계산
		
		/*--------------------------종료 조건---------------------------*/
		if(Terminate()) return;			
		/*------------------------Ready Queue------------------------*/
		while(!AlgorithmList.isEmpty() && time == AlgorithmList.peekFirst().ArrivalTime) {
			//프로세스 리스트가 있고, 프로세스 리스트의 첫 프로세스 AR이 time과 같으면 (프로세스들은 AR기준으로 정렬되어 있음)
			ReadyQueue.add(AlgorithmList.poll()); 		// AlgorithmList에서 ReadyQueue로 이동
		}
		
		for (int i = 0; i < ReadyQueue.size(); i++) { 	// 현재 ReadyQueue에 있는 프로세스들의 TT,WT,ResponseRatio 계산
			ReadyQueue.get(i).TurnaroundTime = time - ReadyQueue.get(i).ArrivalTime;
			ReadyQueue.get(i).WaitingTime = ReadyQueue.get(i).TurnaroundTime / ReadyQueue.get(i).StaticBurstTime;
			ReadyQueue.get(i).ResponseRatio = (ReadyQueue.get(i).WaitingTime + ReadyQueue.get(i).BurstTime) / ReadyQueue.get(i).BurstTime;
		}

		for (int i = 1; i < ReadyQueue.size(); i++) { 	// 레디큐를 ResponseRadio값을 기준으로 정렬
			for (int j = 0; j < ReadyQueue.size() - i; j++) {
				if (ReadyQueue.get(j).ResponseRatio < ReadyQueue.get(j + 1).ResponseRatio) {
					Process temp = ReadyQueue.get(j);
					ReadyQueue.set(j, ReadyQueue.get(j + 1));
					ReadyQueue.set(j + 1, temp);
				}
			}
		}
		manager.ReadyQueue.create_form(ReadyQueue);

		/*------------------------HRRN 알고리즘------------------------*/
		
		for(int i=0; i<CoreCount; i++) {
			if(PresentProcess[i] == null) {							// 현재 실행 중인 프로세스가 없고
				if(!ReadyQueue.isEmpty()) {							// 레디큐가 비어있지 않으면						
					PresentProcess[i] = ReadyQueue.poll();				// 현재 프로세스 추가
				}
				else {
					elec += 0.1;
				}
			}
		}
		manager.ReadyQueue.create_form(ReadyQueue);
		
		//GUIELEC();

		GUISetting();
		
		for(int i=0; i<CoreCount; i++) {
			if(!(PresentProcess[i] == null)) {
		    	PresentProcess[i].BurstTime -= CoreWork[i];	// 현재 실행 중인 프로세스가 있다면 Bursttime에서 처리량 빼주기
		    	if(CoreWork[i] == 1)						// e코어이면 전력+1
		    		elec += 1;
		    	else									// p코어이면 전력+3
		    		elec += 3;
		    }
		}
		manager.mainPanel.Elec.setText("총 전력: " + elec);
	}
}