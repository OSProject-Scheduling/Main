package Scheduling;
import javax.swing.JLabel;

import Manager.ProjectManager;

import java.util.LinkedList;

//spn
public class SPN extends Algorithm{

	public SPN(ProjectManager manager, int PCoreCount, int ECoreCount) {
		super(manager, PCoreCount, ECoreCount);
		// TODO Auto-generated constructor stub
	}

	void schedulling() {
		CalculateTime();
		
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
		if(PresentProcess == null && ReadyQueue.isEmpty() && AlgorithmList.isEmpty()) {
			manager.GhanttChart.addLastSecond();
			return;
		}
		GUIELEC();																				// GhanttChart 표시
		if(!(PresentProcess == null)) PresentProcess.BurstTime -= CoreWork;											// 현재 FCFS가 비어있지 않으면 bursttime에서 처리량 빼주기
	}
	
}

