package Scheduling;

import Manager.ProjectManager;

public class FCFS extends Algorithm{
	public FCFS(ProjectManager manager, int PCoreCount, int ECoreCount) {
		super(manager, PCoreCount, ECoreCount);
	}
	
	protected void schedulling() {
		CalculateTime(); // 프로세스 종료 후 시간 계산
		manager.mainPanel.Elec.setText("총 전력: " + Math.round(elec*100)/100.0 + "W");
		/*--------------------------종료 조건---------------------------*/
		if(Terminate()) return;
		
		/*------------------------Ready Queue------------------------*/
		while(!AlgorithmList.isEmpty() && time == AlgorithmList.peekFirst().ArrivalTime) {
			//프로세스 리스트가 있고, 프로세스 리스트의 첫 프로세스 AR이 time과 같으면 (프로세스들은 AR기준으로 정렬되어 있음)
			ReadyQueue.add(AlgorithmList.poll());  // AlgorithmList에서 ReadyQueue로 이동
		}

		/*------------------------FCFS 알고리즘------------------------*/
		
		ReadyQueue_To_PresentProcess();							// ReadyQueue에서 PresentProcess로
		
		manager.ReadyQueue.create_form(ReadyQueue);
		for(int i=0; i<ReadyQueue.size(); i++) ReadyQueue.get(i).WaitingTime++;		// WT 계산
		
		GUISetting();
		
		ElecBurstTImeCalculate();						// elect, Burst타임 계산
	}
}