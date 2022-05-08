package Scheduling;

import Manager.ProjectManager;

public class HRRN extends Algorithm{

	public HRRN(ProjectManager manager, int PCoreCount, int ECoreCount) {
		super(manager, PCoreCount, ECoreCount);
		// TODO Auto-generated constructor stub
	}
	
	protected void schedulling() {
		CalculateTime(); // 프로세스 종료 후 시간 계산
		manager.mainPanel.Elec.setText("총 전력: " + Math.round(elec*100)/100.0 + "W");
		/*--------------------------종료 조건---------------------------*/
		if(Terminate()) return;			
		/*------------------------Ready Queue------------------------*/
		while(!AlgorithmList.isEmpty() && time == AlgorithmList.peekFirst().ArrivalTime) {
			//프로세스 리스트가 있고, 프로세스 리스트의 첫 프로세스 AR이 time과 같으면 (프로세스들은 AR기준으로 정렬되어 있음)
			ReadyQueue.add(AlgorithmList.poll()); 		// AlgorithmList에서 ReadyQueue로 이동
		}
		
		for (int i = 0; i < ReadyQueue.size(); i++) { 	// 현재 ReadyQueue에 있는 프로세스들의 TT,WT,ResponseRatio 계산
			ReadyQueue.get(i).TurnaroundTime = time - ReadyQueue.get(i).ArrivalTime;
			ReadyQueue.get(i).ResponseRatio = (ReadyQueue.get(i).WaitingTime + ReadyQueue.get(i).BurstTime) / ReadyQueue.get(i).BurstTime;
		}

		for (int i = 1; i < ReadyQueue.size(); i++) { 	// 레디큐를 ResponseRadio값을 기준으로 정렬
			for (int j = 0; j < ReadyQueue.size() - i; j++) {
				if (ReadyQueue.get(j).ResponseRatio < ReadyQueue.get(j + 1).ResponseRatio) {
					Process temp = ReadyQueue.get(j);
					ReadyQueue.set(j, ReadyQueue.get(j + 1));
					ReadyQueue.set(j + 1, temp);
				}
			}
		}
		manager.ReadyQueue.create_form(ReadyQueue);

		/*------------------------HRRN 알고리즘------------------------*/
		
		ReadyQueue_To_PresentProcess();
		
		manager.ReadyQueue.create_form(ReadyQueue);
		
		for(int i=0; i<ReadyQueue.size(); i++) ReadyQueue.get(i).WaitingTime++;		// WT 계산
		GUISetting();
		
		ElecBurstTImeCalculate();
	}
}