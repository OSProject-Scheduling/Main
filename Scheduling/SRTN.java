package Scheduling;

import Manager.ProjectManager;

public class SRTN extends Algorithm{

	public SRTN(ProjectManager manager, int PCoreCount, int ECoreCount) {
		super(manager, PCoreCount, ECoreCount);
		// TODO Auto-generated constructor stub
	}
	
	protected void schedulling() {
		CalculateTime();
		manager.mainPanel.Elec.setText("총 전력: " + Math.round(elec*100)/100.0 + "W");
		
		/*--------------------------종료 조건---------------------------*/
		if(Terminate()) return;
		
		/*------------------------Ready Queue------------------------*/
		while (!AlgorithmList.isEmpty() && time == AlgorithmList.peekFirst().ArrivalTime) {
			for (int i = 0; i <= ReadyQueue.size(); i++) {			// 레디큐의 모든 프로세스를 검사
				if (i == ReadyQueue.size()) {						// i가 레디큐의 마지막 인자까지 검사했으면 레디큐 맨 뒤에 프로세스 추가
					ReadyQueue.add(AlgorithmList.poll());
					break;
				}

				if (ReadyQueue.get(i).BurstTime > AlgorithmList.peekFirst().BurstTime) {	// 프로세스 리스트의 맨 앞 프로세스의 BT가 i번째 프로세스의 BT보다 짧으면
					ReadyQueue.add(i, AlgorithmList.poll());								// 레디큐의 i번째에 프로세스 추가(새치기)
					break;
				}
			}
		}
		
		/*------------------------SRTN 알고리즘------------------------*/
		for(int i=0; i<CoreCount; i++) {
			if(PresentProcess[i] != null && !ReadyQueue.isEmpty())					// 현재 실행 중인 프로세스가 있고, Ready큐에 하나라도 있다면
			{
				if(PresentProcess[i].BurstTime > ReadyQueue.get(0).BurstTime) 		// 현재 진행중인 프로세스의 BT와 레디큐 맨 앞 프로세스의 BT 비교해서 레디큐의 BT가 더 작으면 선점 당하므로
				{
					for (int j = 0; j <= ReadyQueue.size(); j++) {				// 레디큐의 모든 프로세스를 검사
						if (i == ReadyQueue.size()) {							// i가 레디큐의 마지막 인자까지 검사했으면 레디큐 맨 뒤에 현재 프로세스 추가
							ReadyQueue.add(PresentProcess[i]);
							break;
						}

						if (ReadyQueue.get(j).BurstTime > PresentProcess[i].BurstTime) {	// 프로세스 리스트의 맨 앞 프로세스의 BT가 i번째 프로세스의 BT보다 짧으면
							ReadyQueue.add(j, PresentProcess[i]);							// 레디큐의 i번째에 현재 프로세스 추가(새치기)
							break;
						}
					}

					PresentProcess[i] = ReadyQueue.poll();								// 레디큐의 맨 앞이 선점함
				}
			}
		}
		
		ReadyQueue_To_PresentProcess();
		
		manager.ReadyQueue.create_form(ReadyQueue);
		
		for(int i=0; i<ReadyQueue.size(); i++) ReadyQueue.get(i).WaitingTime++;		// WT 계산
		GUISetting();
		
		ElecBurstTImeCalculate();
	}
}