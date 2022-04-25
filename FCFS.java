import java.util.LinkedList;
import javax.swing.JLabel;

public class FCFS extends Algorithm{

	public FCFS(LinkedList<Process> AlgorithmList, GhanttChartPanel ghanttchartPanel) {
		super(AlgorithmList, ghanttchartPanel);
		// TODO Auto-generated constructor stub
	}
	
	void schedulling() {
		if(!AlgorithmList.isEmpty() && time == AlgorithmList.peekFirst().ArrivalTime) ReadyQueue.add(AlgorithmList.poll());    // AlgorithmList에서 ReadyQueue로 이동(ArrivalTime에 맞으면)
	      if(PresentProcess == null) {                                                            // 현재 FCFS가 비어있을때 
	         if(!ReadyQueue.isEmpty()) {                                                         // ReadyQueue가 비어있지 않으면 현재 FCFS로 poll
	            PresentProcess = ReadyQueue.poll();
	         }
	      }
	      if(PresentProcess==null) ghanttchartPanel.adding(new JLabel("    "));         
	      else ghanttchartPanel.adding(new JLabel(PresentProcess.Name));                                    // GhanttChart 표시
	      if(!(PresentProcess == null)) PresentProcess.BurstTime -= CoreWork;								// 현재 FCFS가 비어있지 않으면 bursttime에서 처리량 빼주기
	                                   
	      if(!(PresentProcess == null) && PresentProcess.BurstTime <= 0) {
	         PresentProcess.TurnaroundTime = (time + 1) - PresentProcess.ArrivalTime;                               // TT 계산
	         PresentProcess.WaitingTime = PresentProcess.TurnaroundTime - PresentProcess.StaticBurstTime;               // WT 계산
	         PresentProcess.NormalizedTime = PresentProcess.TurnaroundTime / PresentProcess.StaticBurstTime;            // NTT 계산
	         ghanttchartPanel.InformationRelay(PresentProcess.TurnaroundTime, PresentProcess.WaitingTime, PresentProcess.NormalizedTime, PresentProcess.Name.substring(1));
	         PresentProcess = null;                     // bursttime이 0 이하가 되면 null로 변화
	      }
	}

}
