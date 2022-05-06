package Scheduling;
import javax.swing.JLabel;

import Manager.ProjectManager;

public class FCFS extends Algorithm{
	public FCFS(ProjectManager manager, int PCoreCount, int ECoreCount) {
		super(manager, PCoreCount, ECoreCount);
	}
	
	void schedulling() {
		CalculateTime(); // 프로세스 종료 후 시간 계산
		
		/*--------------------------종료 조건---------------------------*/
		if(Terminate()) return;
		
		/*------------------------Ready Queue------------------------*/
		while(!AlgorithmList.isEmpty() && time == AlgorithmList.peekFirst().ArrivalTime) {
			//프로세스 리스트가 있고, 프로세스 리스트의 첫 프로세스 AR이 time과 같으면 (프로세스들은 AR기준으로 정렬되어 있음)
			ReadyQueue.add(AlgorithmList.poll());  // AlgorithmList에서 ReadyQueue로 이동
			manager.ReadyQueue.create_form(ReadyQueue);
		}

		/*------------------------FCFS 알고리즘------------------------*/
		
		for(int i=0; i<CoreCount; i++) {
			if(PresentProcess[i] == null) { 				// 현재 실행중인 프로세스가 없을 때 
				if(!ReadyQueue.isEmpty()) { 			// ReadyQueue가 비어있지 않으면
					PresentProcess[i] = ReadyQueue.poll(); // ReadyQueue의 첫 프로세스를 실행
				}
				else {									// 레디큐가 비어있으면 대기전력 0.1+
					elec += 0.1;
				}
		    }
		}
		manager.ReadyQueue.create_form(ReadyQueue);
		
		GUISetting();
		
		for(int i=0; i<CoreCount; i++) {
			if(!(PresentProcess[i] == null)) {
		    	PresentProcess[i].BurstTime -= CoreWork[i];	// 현재 실행 중인 프로세스가 있다면 Bursttime에서 처리량 빼주기
		    	if(CoreWork[i] == 1)						// e코어이면 전력+1
		    		elec += 1;							
		    	else									// p코어이면 전력+2
		    		elec += 3;
		    }
		}
	    
		manager.mainPanel.Elec.setText("총 전력: " + Math.round(elec*100)/100.0);
	    
	    /////////////////////////////////////////////////////////////////////////
//	    if(PresentProcess==null) {
//			ghanttchartPanel.adding(new JLabel("    "),-1);			
//			elec += ((PCoreCount + ECoreCount)*0.1);
//		}
//		else {
//			ghanttchartPanel.adding(new JLabel(PresentProcess.Name), PresentProcess.Row);	
//			elec += PCoreCount*3 + ECoreCount; // 8
//		}
	}
}