import javax.swing.JLabel;
import java.util.LinkedList;

//spn
public class SPN extends Algorithm{

	public SPN(LinkedList<Process> AlgorithmList, GhanttChartPanel ghanttchartPanel) {
		super(AlgorithmList, ghanttchartPanel);
		// TODO Auto-generated constructor stub
	}

	void schedulling() {
if(!AlgorithmList.isEmpty() && time == AlgorithmList.peekFirst().ArrivalTime) {
			
			for (int i = 0; i<=ReadyQueue.size(); i++) {
				if(i==ReadyQueue.size()) {
					ReadyQueue.add(AlgorithmList.poll());
					break;
				}
				
				if(ReadyQueue.get(i).BurstTime > AlgorithmList.peekFirst().BurstTime) {
					ReadyQueue.add(i, AlgorithmList.poll());
					break;
				}
			}		
		}
		
		if(PresentProcess == null) {																				// 현재 FCFS가 비어있을때
			if(!ReadyQueue.isEmpty()) {																			// ReadyQueue가 비어있지 않으면 현재 FCFS로 poll
				PresentProcess = ReadyQueue.poll();
			}
		}
		if(PresentProcess==null) ghanttchartPanel.adding(new JLabel("    "));			
		else ghanttchartPanel.adding(new JLabel(PresentProcess.Name));												// GhanttChart 표시
		if(!(PresentProcess == null)) PresentProcess.BurstTime -= CoreWork;											// 현재 FCFS가 비어있지 않으면 bursttime에서 처리량 빼주기
		if(!(PresentProcess == null) && PresentProcess.BurstTime <= 0) {						// bursttime이 0 이하가 되면 null로 변화
			PresentProcess.TurnaroundTime = time - PresentProcess.ArrivalTime;
			PresentProcess.WaitingTime = PresentProcess.TurnaroundTime - PresentProcess.StaticBurstTime;
			PresentProcess.NormalizedTime = PresentProcess.TurnaroundTime / PresentProcess.StaticBurstTime;
			ghanttchartPanel.InformationRelay(PresentProcess.TurnaroundTime, PresentProcess.WaitingTime, PresentProcess.NormalizedTime, PresentProcess.Name.substring(1));
			PresentProcess = null;	
		}
	}
	
}

