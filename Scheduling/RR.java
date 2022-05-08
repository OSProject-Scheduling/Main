package Scheduling;

import Manager.ProjectManager;



public class RR extends Algorithm{

	int Quantum;			// 퀀텀
	int ForQuantum[];
	
	public RR(ProjectManager manager, int Quanturm, int PCoreCount, int ECoreCount) {
		super(manager, PCoreCount, ECoreCount);
		this.Quantum = Quanturm;
		ForQuantum = new int[PCoreCount + ECoreCount];
		for(int i=0; i<ForQuantum.length; i++) ForQuantum[i] = 0;
		// TODO Auto-generated constructor stub
	}

	protected void schedulling() {
		CalculateTime(); 								// 프로세스 종료 후 시간 계산
		manager.mainPanel.Elec.setText("총 전력: " + Math.round(elec*100)/100.0 + "W");
		/*--------------------------종료 조건---------------------------*/
		if(Terminate()) return;
		
		for(int i=0; i<CoreCount; i++) if(PresentProcess[i] == null) ForQuantum[i] = 0; //프로세스 종료 후 ForQuantum을 0으로 초기화
		
		/*------------------------Ready Queue------------------------*/
		while(!AlgorithmList.isEmpty() && time == AlgorithmList.peekFirst().ArrivalTime) {
			//프로세스 리스트가 있고, 프로세스 리스트의 첫 프로세스 AR이 time과 같으면 (프로세스들은 AR기준으로 정렬되어 있음) 
			ReadyQueue.add(AlgorithmList.poll()); 	// AlgorithmList에서 ReadyQueue로 이동
		}
	
		
		/*------------------------RR 알고리즘--------------------------*/
		for(int i=0; i<CoreCount; i++) {
			if(ForQuantum[i] == Quantum) {										// 퀀텀과 실행시간이 같아지면
				ReadyQueue.add(PresentProcess[i]);								// 현재 실행 중인 프로세스를 레디큐 맨 뒤로 보내고
				PresentProcess[i] = null;										// 현재 프로세스를 null
				ForQuantum[i] = 0;												// 현재 프로세스 시간 초기화
			}
		}

		for(int i=0; i<CoreCount; i++) ForQuantum[i]++; 						// 현재 프로세스 시간 +1

		ReadyQueue_To_PresentProcess();
		manager.ReadyQueue.create_form(ReadyQueue);
		
		for(int i=0; i<ReadyQueue.size(); i++) ReadyQueue.get(i).WaitingTime++;		// WT 계산
		GUISetting();
		
		ElecBurstTImeCalculate();
	}
}

