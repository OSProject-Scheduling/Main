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
		if(PresentProcess == null && PresentProcess2 == null && PresentProcess3 == null && PresentProcess4 == null && ReadyQueue.isEmpty() && AlgorithmList.isEmpty()) {
			// 현재 실행중인 프로세스가 없고, 레디큐와 알고리즘 리스트 모두 비어있으면 종료
			manager.GhanttChart.addLastSecond();
			return;
		}
		
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
		if(PresentProcess != null && !ReadyQueue.isEmpty())					// 현재 실행 중인 프로세스가 있고, Ready큐에 하나라도 있다면
		{
			if(PresentProcess.BurstTime > ReadyQueue.get(0).BurstTime) 		// 현재 진행중인 프로세스의 BT와 레디큐 맨 앞 프로세스의 BT 비교해서 레디큐의 BT가 더 작으면 선점 당하므로
			{
				for (int i = 0; i <= ReadyQueue.size(); i++) {				// 레디큐의 모든 프로세스를 검사
					if (i == ReadyQueue.size()) {							// i가 레디큐의 마지막 인자까지 검사했으면 레디큐 맨 뒤에 현재 프로세스 추가
						ReadyQueue.add(PresentProcess);
						break;
					}

					if (ReadyQueue.get(i).BurstTime > PresentProcess.BurstTime) {	// 프로세스 리스트의 맨 앞 프로세스의 BT가 i번째 프로세스의 BT보다 짧으면
						ReadyQueue.add(i, PresentProcess);							// 레디큐의 i번째에 현재 프로세스 추가(새치기)
						break;
					}
				}

				PresentProcess = ReadyQueue.poll();								// 레디큐의 맨 앞이 선점함
				manager.ReadyQueue.create_form(ReadyQueue);
			}
		}
		
		if(PresentProcess2 != null && !ReadyQueue.isEmpty())					// 현재 실행 중인 프로세스가 있고, Ready큐에 하나라도 있다면
		{
			if(PresentProcess2.BurstTime > ReadyQueue.get(0).BurstTime) 		// 현재 진행중인 프로세스의 BT와 레디큐 맨 앞 프로세스의 BT 비교해서 레디큐의 BT가 더 작으면 선점 당하므로
			{
				for (int i = 0; i <= ReadyQueue.size(); i++) {					// 레디큐의 모든 프로세스를 검사
					if (i == ReadyQueue.size()) {								// i가 레디큐의 마지막 인자까지 검사했으면 레디큐 맨 뒤에 현재 프로세스 추가
						ReadyQueue.add(PresentProcess2);
						break;
					}

					if (ReadyQueue.get(i).BurstTime > PresentProcess2.BurstTime) {	// 프로세스 리스트의 맨 앞 프로세스의 BT가 i번째 프로세스의 BT보다 짧으면
						ReadyQueue.add(i, PresentProcess2);							// 레디큐의 i번째에 현재 프로세스 추가(새치기)
						break;
					}
				}

				PresentProcess2 = ReadyQueue.poll();							// 레디큐의 맨 앞이 선점함
				manager.ReadyQueue.create_form(ReadyQueue);
			}
		}
		
		if(PresentProcess3 != null && !ReadyQueue.isEmpty())					// 현재 실행 중인 프로세스가 있고, Ready큐에 하나라도 있다면
		{
			if(PresentProcess3.BurstTime > ReadyQueue.get(0).BurstTime) 		// 현재 진행중인 프로세스의 BT와 레디큐 맨 앞 프로세스의 BT 비교해서 레디큐의 BT가 더 작으면 선점 당하므로
			{
				for (int i = 0; i <= ReadyQueue.size(); i++) {					// 레디큐의 모든 프로세스를 검사
					if (i == ReadyQueue.size()) {								// i가 레디큐의 마지막 인자까지 검사했으면 레디큐 맨 뒤에 현재 프로세스 추가
						ReadyQueue.add(PresentProcess3);
						break;
					}

					if (ReadyQueue.get(i).BurstTime > PresentProcess3.BurstTime) {	// 프로세스 리스트의 맨 앞 프로세스의 BT가 i번째 프로세스의 BT보다 짧으면
						ReadyQueue.add(i, PresentProcess3);							// 레디큐의 i번째에 현재 프로세스 추가(새치기)
						break;
					}
				}

				PresentProcess3 = ReadyQueue.poll();							// 레디큐의 맨 앞이 선점함
				manager.ReadyQueue.create_form(ReadyQueue);
			}
		}
		
		if(PresentProcess4 != null && !ReadyQueue.isEmpty())					// 현재 실행 중인 프로세스가 있고, Ready큐에 하나라도 있다면
		{
			if(PresentProcess4.BurstTime > ReadyQueue.get(0).BurstTime) 		// 현재 진행중인 프로세스의 BT와 레디큐 맨 앞 프로세스의 BT 비교해서 레디큐의 BT가 더 작으면 선점 당하므로
			{
				for (int i = 0; i <= ReadyQueue.size(); i++) {					// 레디큐의 모든 프로세스를 검사
					if (i == ReadyQueue.size()) {								// i가 레디큐의 마지막 인자까지 검사했으면 레디큐 맨 뒤에 현재 프로세스 추가
						ReadyQueue.add(PresentProcess4);
						break;
					}

					if (ReadyQueue.get(i).BurstTime > PresentProcess4.BurstTime) {	// 프로세스 리스트의 맨 앞 프로세스의 BT가 i번째 프로세스의 BT보다 짧으면
						ReadyQueue.add(i, PresentProcess4);							// 레디큐의 i번째에 현재 프로세스 추가(새치기)
						break;
					}
				}

				PresentProcess4 = ReadyQueue.poll();							// 레디큐의 맨 앞이 선점함
				manager.ReadyQueue.create_form(ReadyQueue);
			}
		}
		
		if (PresentProcess == null) { 									// 현재 진행중인 프로세스가 없고
			if (!ReadyQueue.isEmpty()) { 								// ReadyQueue가 비어있지 않으면
				PresentProcess = ReadyQueue.poll(); 					// ReadyQueue에서 BurstTime이 가장 짦은 프로세스를 진행시킴
				manager.ReadyQueue.create_form(ReadyQueue);
			}
			else {														// 레디큐가 비어있으면 대기전력 0.1+
				elec += 0.1;
			}
		}
		
		if((PCoreCount + ECoreCount >= 2) && PresentProcess2 == null) { // 코어가 2개 이상이고, 코어 2에서 현재 실행중인 프로세스가 없을 때
			if(!ReadyQueue.isEmpty()) { 								// ReadyQueue가 비어있지 않으면
				PresentProcess2 = ReadyQueue.poll(); 					// ReadyQueue의 첫 프로세스를 실행
				manager.ReadyQueue.create_form(ReadyQueue);
			}
			else {														// 레디큐가 비어있으면 대기전력 0.1+
				elec += 0.1;
			}
	    }
		
		if((PCoreCount + ECoreCount >= 3) && PresentProcess3 == null) { // 코어가 3개 이상이고, 코어 3에서 현재 실행중인 프로세스가 없을 때
			if(!ReadyQueue.isEmpty()) { 								// ReadyQueue가 비어있지 않으면
				PresentProcess3 = ReadyQueue.poll(); 					// ReadyQueue의 첫 프로세스를 실행
				manager.ReadyQueue.create_form(ReadyQueue);
			}
			else {														// 레디큐가 비어있으면 대기전력 0.1+
				elec += 0.1;
			}
	    }
		
		if((PCoreCount + ECoreCount >= 4) && PresentProcess4 == null) { // 코어가 4개 이상이고, 코어 4에서 현재 실행중인 프로세스가 없을 때
			if(!ReadyQueue.isEmpty()) { 								// ReadyQueue가 비어있지 않으면
				PresentProcess4 = ReadyQueue.poll(); 					// ReadyQueue의 첫 프로세스를 실행
				manager.ReadyQueue.create_form(ReadyQueue);
			}
			else {														// 레디큐가 비어있으면 대기전력 0.1+
				elec += 0.1;
			}
		
		// GhanttChart 표시
	}
		if(!(PresentProcess == null)) {
	    	PresentProcess.BurstTime -= CoreWork1;	// 현재 실행 중인 프로세스가 있다면 Bursttime에서 처리량 빼주기
	    	if(CoreWork1 == 1)						// e코어이면 전력+1
	    		elec += 1;
	    	else									// p코어이면 전력+3
	    		elec += 3;
	    }
	    if(!(PresentProcess2 == null)) {
	    	PresentProcess2.BurstTime -= CoreWork2;
	    	if(CoreWork2 == 1)
	    		elec += 1;
	    	else
	    		elec += 3;
	    }
	    if(!(PresentProcess3 == null)) {
	    	PresentProcess3.BurstTime -= CoreWork3;
	    	if(CoreWork3 == 1)
	    		elec += 1;
	    	else
	    		elec += 3;
	    }
	    if(!(PresentProcess4 == null)) {
	    	PresentProcess4.BurstTime -= CoreWork4;
	    	if(CoreWork4 == 1)
	    		elec += 1;
	    	else
	    		elec += 3;
	    }
	}
}