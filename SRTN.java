import java.util.LinkedList;

import javax.swing.JLabel;

public class SRTN extends Algorithm{
	Process tmpProcess;
	int pass = 1;

	public SRTN(ProjectManager manager) {
		super(manager);
		// TODO Auto-generated constructor stub
	}
	
	void schedulling() {
		if(!(PresentProcess == null) && PresentProcess.BurstTime <= 0) {		// 현재 진행 중인 프로세스가 일을 끝냈으면
			PresentProcess.TurnaroundTime = (time + 1) - PresentProcess.ArrivalTime;							// TT 계산
			PresentProcess.WaitingTime = PresentProcess.TurnaroundTime - PresentProcess.StaticBurstTime;		// WT 계산
			PresentProcess.NormalizedTime = PresentProcess.TurnaroundTime / PresentProcess.StaticBurstTime;	// NTT 계산
			manager.information.ChangeInformation(PresentProcess.TurnaroundTime, PresentProcess.WaitingTime, PresentProcess.NormalizedTime, Integer.parseInt(PresentProcess.Name.substring(1)));
			PresentProcess = null;
		}
		if(!AlgorithmList.isEmpty() && time == AlgorithmList.peekFirst().ArrivalTime) {
			ReadyQueue.add(AlgorithmList.poll()); 	// AlgorithmList에서 ReadyQueue로 이동(ArrivalTime에 맞으면)
			manager.ReadyQueue.create_form(ReadyQueue);
		}
		if (PresentProcess != null) {																				// 현재 FCFS가 있으며
			if (ReadyQueue.size() > 1) {																		// Ready큐에 1개 이상 들어있다면
				for (int i = ReadyQueue.size() - 2; i >= 0; i--) {												// 맨 뒤에 프로세스를 하나씩 비교를 하며 정렬
					if (ReadyQueue.get(ReadyQueue.size() - pass).BurstTime < ReadyQueue.get(i).BurstTime) {		// PASS의 용도는 만약 바뀐다면 바꾼 인덱스값도 증가가 되므로 그거 맞춰주기 위해 사용
						tmpProcess = ReadyQueue.get(ReadyQueue.size() - pass);
						ReadyQueue.set(ReadyQueue.size() - pass, ReadyQueue.get(i));
						ReadyQueue.set(i, tmpProcess);
						pass++;
					}
				}
				manager.ReadyQueue.create_form(ReadyQueue);
				pass = 1;
			}
		}
		if(PresentProcess != null && !ReadyQueue.isEmpty())														// 현재 FCFS가 있으며, Ready큐에 하나라도 있다면
		{
			if(PresentProcess.BurstTime > ReadyQueue.get(0).BurstTime) 											// 현재 진행중인 것과 Ready큐에 있는 BurstTime이 가장 짦은 것과 비교
			{
				ReadyQueue.add(PresentProcess);
				PresentProcess = ReadyQueue.poll();
				manager.ReadyQueue.create_form(ReadyQueue);
			}
		}
		
		if (PresentProcess == null) { // 현재 진행중이 FCFS가 끝났고
			if (!ReadyQueue.isEmpty()) { // ReadyQueue가 비어있지 않으면
				PresentProcess = ReadyQueue.poll(); // ReadyQueue에서 BurstTime이 가장 짦은 프로세스를 진행시킴
				manager.ReadyQueue.create_form(ReadyQueue);
			}
		}
		if(PresentProcess == null && ReadyQueue.isEmpty() && AlgorithmList.isEmpty()) return;
		if(PresentProcess==null) ghanttchartPanel.adding(new JLabel("    "));			
		else ghanttchartPanel.adding(new JLabel(PresentProcess.Name));												// GhanttChart 표시
		if(!(PresentProcess == null)) PresentProcess.BurstTime -= CoreWork; // 현재 FCFS가 비어있지 않으면 bursttime에서 처리량 빼주기
	}
}