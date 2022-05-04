import javax.swing.JLabel;
import java.util.LinkedList;


public class RR extends Algorithm{

	int Quantum;
	int ForQuantum = 0;
	public RR(ProjectManager manager, int Quanturm) {
		super(manager);
		this.Quantum = Quanturm;
		// TODO Auto-generated constructor stub
	}

	void schedulling() {
		if(!(PresentProcess == null) && PresentProcess.BurstTime <= 0) {
			PresentProcess.TurnaroundTime = time - PresentProcess.ArrivalTime;
			PresentProcess.WaitingTime = PresentProcess.TurnaroundTime - PresentProcess.StaticBurstTime;
			PresentProcess.NormalizedTime = PresentProcess.TurnaroundTime / PresentProcess.StaticBurstTime;
			manager.information.ChangeInformation(PresentProcess.TurnaroundTime, PresentProcess.WaitingTime, PresentProcess.NormalizedTime, PresentProcess.Row);
			PresentProcess = null;							// bursttime이 0 이하가 되면 null로 변화
			ForQuantum = 0;
		}
		if(ForQuantum == Quantum) {
			ReadyQueue.add(PresentProcess);
			manager.ReadyQueue.create_form(ReadyQueue);
			PresentProcess = null;
			ForQuantum = 0;
		}
		ForQuantum++;
		if(!AlgorithmList.isEmpty() && time == AlgorithmList.peekFirst().ArrivalTime) {
			ReadyQueue.add(AlgorithmList.poll()); 	// FCFSList에서 ReadyQueue로 이동(ArrivalTime에 맞으면)
			manager.ReadyQueue.create_form(ReadyQueue);
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

