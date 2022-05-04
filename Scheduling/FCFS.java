package Scheduling;
import javax.swing.JLabel;

import Manager.ProjectManager;

public class FCFS extends Algorithm{
	public FCFS(ProjectManager manager) {
		super(manager);
		// TODO Auto-generated constructor stub
	}
	
	void schedulling() {
		if(!(PresentProcess == null) && PresentProcess.BurstTime <= 0) {
	         PresentProcess.TurnaroundTime = time - PresentProcess.ArrivalTime;                               // TT 계산
	         PresentProcess.WaitingTime = PresentProcess.TurnaroundTime - PresentProcess.StaticBurstTime;               // WT 계산
	         PresentProcess.NormalizedTime = PresentProcess.TurnaroundTime / PresentProcess.StaticBurstTime;            // NTT 계산
	         manager.information.ChangeInformation(PresentProcess.TurnaroundTime, PresentProcess.WaitingTime, PresentProcess.NormalizedTime, PresentProcess.Row);
	         PresentProcess = null;                     // bursttime이 0 이하가 되면 null로 변화
	      }
		if(!AlgorithmList.isEmpty() && time == AlgorithmList.peekFirst().ArrivalTime) {
			ReadyQueue.add(AlgorithmList.poll());    // AlgorithmList에서 ReadyQueue로 이동(ArrivalTime에 맞으면)
			manager.ReadyQueue.create_form(ReadyQueue);
		}
	    if(PresentProcess == null) {                                                            // 현재 FCFS가 비어있을때 
	       if(!ReadyQueue.isEmpty()) {                                                         // ReadyQueue가 비어있지 않으면 현재 FCFS로 poll
	          PresentProcess = ReadyQueue.poll();
	          manager.ReadyQueue.create_form(ReadyQueue);
	       }
	    }
	    if(PresentProcess == null && ReadyQueue.isEmpty() && AlgorithmList.isEmpty()) return;
	    if(PresentProcess==null) ghanttchartPanel.adding(new JLabel("    "), -1);         
	    else ghanttchartPanel.adding(new JLabel(PresentProcess.Name), PresentProcess.Row);                                    // GhanttChart 표시
	    if(!(PresentProcess == null)) PresentProcess.BurstTime -= CoreWork;								// 현재 FCFS가 비어있지 않으면 bursttime에서 처리량 빼주기                             
	}
}
