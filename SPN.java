import javax.swing.JLabel;
import java.util.LinkedList;

//spn
public class SPN extends Algorithm{

	public SPN(ProjectManager manager) {
		super(manager);
		// TODO Auto-generated constructor stub
	}

	void schedulling() {
		if(!(PresentProcess == null) && PresentProcess.BurstTime <= 0) {						// bursttime이 0 이하가 되면 null로 변화
			PresentProcess.TurnaroundTime = time - PresentProcess.ArrivalTime;
			PresentProcess.WaitingTime = PresentProcess.TurnaroundTime - PresentProcess.StaticBurstTime;
			PresentProcess.NormalizedTime = PresentProcess.TurnaroundTime / PresentProcess.StaticBurstTime;
			manager.information.ChangeInformation(PresentProcess.TurnaroundTime, PresentProcess.WaitingTime, PresentProcess.NormalizedTime, PresentProcess.Row);
			PresentProcess = null;	
		}
		if (!AlgorithmList.isEmpty() && time == AlgorithmList.peekFirst().ArrivalTime) {

			for (int i = 0; i <= ReadyQueue.size(); i++) {
				if (i == ReadyQueue.size()) {
					ReadyQueue.add(AlgorithmList.poll());
					manager.ReadyQueue.create_form(ReadyQueue);
					break;
				}

				if (ReadyQueue.get(i).BurstTime > AlgorithmList.peekFirst().BurstTime) {
					ReadyQueue.add(i, AlgorithmList.poll());
					manager.ReadyQueue.create_form(ReadyQueue);
					break;
				}
			}
		}
		
		if(PresentProcess == null) {																				// 현재 FCFS가 비어있을때
			if(!ReadyQueue.isEmpty()) {																			// ReadyQueue가 비어있지 않으면 현재 FCFS로 poll
				PresentProcess = ReadyQueue.poll();
				manager.ReadyQueue.create_form(ReadyQueue);
			}
		}
		if(PresentProcess == null && ReadyQueue.isEmpty() && AlgorithmList.isEmpty()) return;
		if(PresentProcess==null) ghanttchartPanel.adding(new JLabel("    "), -1);			
		else ghanttchartPanel.adding(new JLabel(PresentProcess.Name), PresentProcess.Row);												// GhanttChart 표시
		if(!(PresentProcess == null)) PresentProcess.BurstTime -= CoreWork;											// 현재 FCFS가 비어있지 않으면 bursttime에서 처리량 빼주기
	}
	
}

