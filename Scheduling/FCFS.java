package Scheduling;
import javax.swing.JLabel;

import Manager.ProjectManager;

public class FCFS extends Algorithm{
	public FCFS(ProjectManager manager, int PCoreCount, int ECoreCount) {
		super(manager, PCoreCount, ECoreCount);
	}
	
	void schedulling() {
		CalculateTime(); 	
		
		if(!AlgorithmList.isEmpty() && time == AlgorithmList.peekFirst().ArrivalTime) {
			ReadyQueue.add(AlgorithmList.poll());   						 // AlgorithmList에서 ReadyQueue로 이동(ArrivalTime에 맞으면)
			manager.ReadyQueue.create_form(ReadyQueue);
		}
	    if(PresentProcess == null) {                                                            // 현재 FCFS가 비어있을때 
	       if(!ReadyQueue.isEmpty()) {                                                         // ReadyQueue가 비어있지 않으면 현재 FCFS로 poll
	          PresentProcess = ReadyQueue.poll();
	          manager.ReadyQueue.create_form(ReadyQueue);
	       }
	    }
	    if(PresentProcess == null && ReadyQueue.isEmpty() && AlgorithmList.isEmpty()) {
	    	manager.GhanttChart_1.addLastSecond();
	    	return;
	    }
	    GUIELEC();                       // GhanttChart 표시
	    if(!(PresentProcess == null)) PresentProcess.BurstTime -= CoreWork;								// 현재 FCFS가 비어있지 않으면 bursttime에서 처리량 빼주기                
	    
	}
}
