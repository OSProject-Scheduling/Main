import javax.swing.JLabel;
import java.util.LinkedList;




public class MFQ extends Algorithm{
	final static int MaxQuantum = 20;		//Quantum의 최대 시간은 20초이다
	int ProcessQuantum = 0;					//실행중인 Process의 퀀텀시간
	int ForQuantum = 0;
	// 응답률 : ReadyQueue.get(i).ResponseRatio = (ReadyQueue.get(i).WaitingTime + ReadyQueue.get(i).BurstTime) / ReadyQueue.get(i).BurstTime; 
	
	public MFQ(ProjectManager manager, int ProcessQuantum) {
		super(manager);
		this.ProcessQuantum = ProcessQuantum;
	}
	
	void schedulling() {
		if(!(PresentProcess == null)){
			if(PresentProcess.StaticBurstTime % 2 != 0){
				ProcessQuantum = (int)(PresentProcess.StaticBurstTime / 2) + 1;
			}
			else ProcessQuantum = (int)(PresentProcess.StaticBurstTime / 2.0);
			if (ProcessQuantum > 20) {
				ProcessQuantum = MaxQuantum;
			}
		}
		
		if(!(PresentProcess == null) && PresentProcess.BurstTime <= 0) {
			PresentProcess.TurnaroundTime = time - PresentProcess.ArrivalTime;
			PresentProcess.WaitingTime = PresentProcess.TurnaroundTime - PresentProcess.StaticBurstTime;
			PresentProcess.NormalizedTime = PresentProcess.TurnaroundTime / PresentProcess.StaticBurstTime;
			manager.information.ChangeInformation(PresentProcess.TurnaroundTime, PresentProcess.WaitingTime, PresentProcess.NormalizedTime, PresentProcess.Row);
			PresentProcess = null;							// bursttime이 0 이하가 되면 null로 변화
			
			ForQuantum = 0;
		}
		
		if(ForQuantum == ProcessQuantum) {
			ReadyQueue.add(PresentProcess);
			manager.ReadyQueue.create_form(ReadyQueue);
			PresentProcess = null;
			ForQuantum = 0;
		}
		
		ForQuantum++;
		//우선순위에 따른 알고리즘 리스트 추가
		if(!HighAlgorithmList.isEmpty() && time == HighAlgorithmList.peekFirst().ArrivalTime && HighAlgorithmList.peekFirst().Priority == "High") {
			HighReadyQueue.add(HighAlgorithmList.poll()); 	// FCFSList에서 ReadyQueue로 이동(ArrivalTime에 맞으면)
			manager.HighReadyQueue.create_form(HighReadyQueue);
		}
		if(!MiddleAlgorithmList.isEmpty() && time == MiddleAlgorithmList.peekFirst().ArrivalTime && MiddleAlgorithmList.peekFirst().Priority == "Middle") {
			MiddleReadyQueue.add(MiddleAlgorithmList.poll()); 	// FCFSList에서 ReadyQueue로 이동(ArrivalTime에 맞으면)
			manager.MiddleReadyQueue.create_form(MiddleReadyQueue);
		}
		if(!LowAlgorithmList.isEmpty() && time == LowAlgorithmList.peekFirst().ArrivalTime && LowAlgorithmList.peekFirst().Priority == "Low") {
			LowReadyQueue.add(LowAlgorithmList.poll()); 	// FCFSList에서 ReadyQueue로 이동(ArrivalTime에 맞으면)
			manager.lowReadyQueue.create_form(LowReadyQueue);
		}
		
		
		
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
		if(PresentProcess==null) ghanttchartPanel.adding(new JLabel("    "));			
		else ghanttchartPanel.adding(new JLabel(PresentProcess.Name));												// GhanttChart 표시
		if(!(PresentProcess == null)) PresentProcess.BurstTime -= CoreWork;											// 현재 FCFS가 비어있지 않으면 bursttime에서 처리량 빼주기	
	
		
	
	}
}

