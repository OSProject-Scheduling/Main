import java.util.LinkedList;

import javax.swing.JLabel;

public class HRRN extends Algorithm{

	public HRRN(ProjectManager manager) {
		super(manager);
		// TODO Auto-generated constructor stub
	}
	
	void schedulling() {
		if(!(PresentProcess == null) && PresentProcess.BurstTime <= 0) {												// �쁽�옱 吏꾪뻾 以묒씠�뜕 �봽濡쒖꽭�뒪媛� �걹�굹硫�
			System.out.println(PresentProcess.Name + " " + "TT: " + PresentProcess.TurnaroundTime);	
			PresentProcess.TurnaroundTime = (time + 1) - PresentProcess.ArrivalTime;										// �걹�궃 �봽濡쒖꽭�뒪�쓽 TT,WT,NTT 怨꾩궛
			PresentProcess.WaitingTime = PresentProcess.TurnaroundTime - PresentProcess.StaticBurstTime;
			PresentProcess.NormalizedTime = PresentProcess.TurnaroundTime / PresentProcess.StaticBurstTime;
			manager.information.ChangeInformation(PresentProcess.TurnaroundTime, PresentProcess.WaitingTime, PresentProcess.NormalizedTime, Integer.parseInt(PresentProcess.Name.substring(1)));
			PresentProcess = null;
			for (int i = 0; i < ReadyQueue.size(); i++) {														//�쁽�옱 ReadyQueue�뿉 �엳�뒗 �봽濡쒖꽭�뒪�뱾�쓽 TT,WT,ResponseRatio 怨꾩궛
				ReadyQueue.get(i).TurnaroundTime = time - ReadyQueue.get(i).ArrivalTime;
				ReadyQueue.get(i).WaitingTime = ReadyQueue.get(i).TurnaroundTime/ReadyQueue.get(i).StaticBurstTime;
				ReadyQueue.get(i).ResponseRatio = (ReadyQueue.get(i).WaitingTime + ReadyQueue.get(i).BurstTime) / ReadyQueue.get(i).BurstTime;
				System.out.println(ReadyQueue.get(i).Name + " " + "WT: " + ReadyQueue.get(i).WaitingTime + " " + "RS: " + ReadyQueue.get(i).ResponseRatio + "TT: ");
			}
			for(int i = 1;i<ReadyQueue.size();i++) {															// ResponseRadio媛믪쓣 湲곗��쑝濡� �젙�젹
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
			ReadyQueue.add(AlgorithmList.poll()); 	// AlgorithmList�뿉�꽌 ReadyQueue濡� �씠�룞(ArrivalTime�뿉 留욎쑝硫�)
			manager.ReadyQueue.create_form(ReadyQueue);
			System.out.print("김문웅 병신");
		}
		if(PresentProcess == null) {																				// �쁽�옱 FCFS媛� �엳�쑝硫�
			if(!ReadyQueue.isEmpty()) {																			
				PresentProcess = ReadyQueue.poll();
				manager.ReadyQueue.create_form(ReadyQueue);
			}
		}
		if(PresentProcess == null && ReadyQueue.isEmpty() && AlgorithmList.isEmpty()) return;
		if(PresentProcess==null) ghanttchartPanel.adding(new JLabel("    "));			
		else ghanttchartPanel.adding(new JLabel(PresentProcess.Name));												
																											
		if(!(PresentProcess == null)) { 
			for(int i =0; i<ReadyQueue.size(); i++) {
				ReadyQueue.get(i).TurnaroundTime+=1;
			}
			PresentProcess.BurstTime -= CoreWork;
		}											
	}
}