package Scheduling;
import java.util.LinkedList;

import javax.swing.JLabel;

import Manager.ProjectManager;

public class HRRN extends Algorithm{

	public HRRN(ProjectManager manager, int PCoreCount, int ECoreCount) {
		super(manager, PCoreCount, ECoreCount);
		// TODO Auto-generated constructor stub
	}
	
	void schedulling() {
		if(!(PresentProcess == null) && PresentProcess.BurstTime <= 0) {												// 현재 진행 중이던 프로세스가 끝나면
			PresentProcess.TurnaroundTime = time - PresentProcess.ArrivalTime;										// 끝난 프로세스의 TT,WT,NTT 계산
			PresentProcess.WaitingTime = PresentProcess.TurnaroundTime - PresentProcess.StaticBurstTime;
			PresentProcess.NormalizedTime = PresentProcess.TurnaroundTime / PresentProcess.StaticBurstTime;
			manager.information.ChangeInformation(PresentProcess.TurnaroundTime, PresentProcess.WaitingTime, PresentProcess.NormalizedTime, PresentProcess.Row);
			PresentProcess = null;
			for (int i = 0; i < ReadyQueue.size(); i++) {														//현재 ReadyQueue에 있는 프로세스들의 TT,WT,ResponseRatio 계산
				ReadyQueue.get(i).TurnaroundTime = time - ReadyQueue.get(i).ArrivalTime;
				ReadyQueue.get(i).WaitingTime = ReadyQueue.get(i).TurnaroundTime/ReadyQueue.get(i).StaticBurstTime;
				ReadyQueue.get(i).ResponseRatio = (ReadyQueue.get(i).WaitingTime + ReadyQueue.get(i).BurstTime) / ReadyQueue.get(i).BurstTime;
			}
			for(int i = 1;i<ReadyQueue.size();i++) {															// ResponseRadio값을 기준으로 정렬
				for(int j = 0; j<ReadyQueue.size()-1; j++) {
					if(ReadyQueue.get(j).ResponseRatio< ReadyQueue.get(j+1).ResponseRatio) {
						Process temp = ReadyQueue.get(j);
						ReadyQueue.set(j, ReadyQueue.get(j+1));
						ReadyQueue.set(j+1, temp);
					}
				}
			}
			manager.ReadyQueue.create_form(ReadyQueue);
		}
		if(!AlgorithmList.isEmpty() && time == AlgorithmList.peekFirst().ArrivalTime) {
			ReadyQueue.add(AlgorithmList.poll()); 	// AlgorithmList에서 ReadyQueue로 이동(ArrivalTime에 맞으면)
			manager.ReadyQueue.create_form(ReadyQueue);
		}
		if(PresentProcess == null) {																				// 현재 FCFS가 있으며
			if(!ReadyQueue.isEmpty()) {																			
				PresentProcess = ReadyQueue.poll();
				manager.ReadyQueue.create_form(ReadyQueue);
			}
		}
		if(PresentProcess == null && ReadyQueue.isEmpty() && AlgorithmList.isEmpty()) {
			manager.GhanttChart_1.addLastSecond();
			return;
		}
		GUIELEC();
						
		if(!(PresentProcess == null)) { 
			for(int i =0; i<ReadyQueue.size(); i++) {
				ReadyQueue.get(i).TurnaroundTime+=1;
			}
			PresentProcess.BurstTime -= CoreWork;
		}										
		
	}
}