import javax.swing.JLabel;
import java.util.LinkedList;


public class RR extends Algorithm{

	int Quantum;
	int ForQuantum = 0;
	
	public RR(LinkedList<Process> AlgorithmList, GhanttChartPanel ghanttchartPanel, int Quanturm) {
		super(AlgorithmList, ghanttchartPanel);
		this.Quantum = Quanturm;
		// TODO Auto-generated constructor stub
	}

	void schedulling() {
		System.out.print("dd");
		ForQuantum++;
		if(!AlgorithmList.isEmpty() && time == AlgorithmList.peekFirst().ArrivalTime) ReadyQueue.add(AlgorithmList.poll()); 	// FCFSList에서 ReadyQueue로 이동(ArrivalTime에 맞으면)
		if(PresentProcess == null) {																				// 현재 FCFS가 비어있을때
			if(!ReadyQueue.isEmpty()) {																			// ReadyQueue가 비어있지 않으면 현재 FCFS로 poll
				PresentProcess = ReadyQueue.poll();
			}
		}
		
		if(PresentProcess==null) ghanttchartPanel.adding(new JLabel("    "));			
		else ghanttchartPanel.adding(new JLabel(PresentProcess.Name));												// GhanttChart 표시
		if(!(PresentProcess == null)) PresentProcess.BurstTime -= CoreWork;											// 현재 FCFS가 비어있지 않으면 bursttime에서 처리량 빼주기	
	
		if(!(PresentProcess == null) && PresentProcess.BurstTime <= 0) {
			PresentProcess.TurnaroundTime = time - PresentProcess.ArrivalTime;
			PresentProcess.WaitingTime = PresentProcess.TurnaroundTime - PresentProcess.StaticBurstTime;
			PresentProcess.NormalizedTime = PresentProcess.TurnaroundTime / PresentProcess.StaticBurstTime;
			ghanttchartPanel.InformationRelay(PresentProcess.TurnaroundTime, PresentProcess.WaitingTime, PresentProcess.NormalizedTime, PresentProcess.Name.substring(1));
			PresentProcess = null;							// bursttime이 0 이하가 되면 null로 변화
			ForQuantum = 0;
		}
		
		if(ForQuantum == Quantum) {
			ReadyQueue.add(PresentProcess);
			PresentProcess = null;
			ForQuantum = 0;
		}
	}
}

