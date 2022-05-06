package Scheduling;
import java.util.LinkedList;

import javax.swing.JLabel;

import Manager.ProjectManager;

public class SRTN extends Algorithm{
	Process tmpProcess;
	int pass = 1;

	public SRTN(ProjectManager manager, int PCoreCount, int ECoreCount) {
		super(manager, PCoreCount, ECoreCount);
		// TODO Auto-generated constructor stub
	}
	
	void schedulling() {
		CalculateTime();
		
		/*--------------------------종료 조건---------------------------*/
		if(Terminate()) return;
		
		/*------------------------Ready Queue------------------------*/
		while (!AlgorithmList.isEmpty() && time == AlgorithmList.peekFirst().ArrivalTime) {
			for (int i = 0; i <= ReadyQueue.size(); i++) {			// 레디큐의 모든 프로세스를 검사
				if (i == ReadyQueue.size()) {						// i가 레디큐의 마지막 인자까지 검사했으면 레디큐 맨 뒤에 프로세스 추가
					ReadyQueue.add(AlgorithmList.poll());
					manager.ReadyQueue.create_form(ReadyQueue);
					break;
				}

				if (ReadyQueue.get(i).BurstTime > AlgorithmList.peekFirst().BurstTime) {	// 프로세스 리스트의 맨 앞 프로세스의 BT가 i번째 프로세스의 BT보다 짧으면
					ReadyQueue.add(i, AlgorithmList.poll());								// 레디큐의 i번째에 프로세스 추가(새치기)
					manager.ReadyQueue.create_form(ReadyQueue);
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
		manager.ReadyQueue.create_form(ReadyQueue);
		
		for(int i=0; i<CoreCount; i++) {
			if (PresentProcess[i] == null) { 									// 현재 진행중인 프로세스가 없고
				if (!ReadyQueue.isEmpty()) { 								// ReadyQueue가 비어있지 않으면
					PresentProcess[i] = ReadyQueue.poll(); 					// ReadyQueue에서 BurstTime이 가장 짦은 프로세스를 진행시킴
				}
				else {														// 레디큐가 비어있으면 대기전력 0.1+
					elec += 0.1;
				}
			}
		}
		manager.ReadyQueue.create_form(ReadyQueue);
		
		GUISetting();
		for(int i=0; i<CoreCount; i++) {
			if(!(PresentProcess == null)) {
		    	PresentProcess[i].BurstTime -= CoreWork[i];	// 현재 실행 중인 프로세스가 있다면 Bursttime에서 처리량 빼주기
		    	if(CoreWork[i] == 1)						// e코어이면 전력+1
		    		elec += 1;
		    	else									// p코어이면 전력+3
		    		elec += 3;
		    }
		}
		manager.mainPanel.Elec.setText("총 전력: " + elec);
	}
}