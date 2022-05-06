package Scheduling;
import javax.swing.JLabel;

import Manager.ProjectManager;

public class FCFS extends Algorithm{
   public FCFS(ProjectManager manager, int PCoreCount, int ECoreCount) {
      super(manager, PCoreCount, ECoreCount);
   }
   
   void schedulling() {
      CalculateTime(); // 프로세스 종료 후 시간 계산
      
      // 종료 조건
       if(PresentProcess == null && PresentProcess2 == null && PresentProcess3 == null && PresentProcess4 == null && ReadyQueue.isEmpty() && AlgorithmList.isEmpty()) {
          // 현재 실행중인 프로세스가 없고, 레디큐와 알고리즘 리스트 모두 비어있으면 종료
          //manager.GhanttChart.addLastSecond();                                 // GHantt차트 마지막 초 표ㅛ
    	  System.out.print(elec);
    	  manager.mainPanel.Elec.setText("<총 전력>: " + elec);
          return;
       }
      
      /*------------------------Ready Queue------------------------*/
      while(!AlgorithmList.isEmpty() && time == AlgorithmList.peekFirst().ArrivalTime) {
         //프로세스 리스트가 있고, 프로세스 리스트의 첫 프로세스 AR이 time과 같으면 (프로세스들은 AR기준으로 정렬되어 있음)
         ReadyQueue.add(AlgorithmList.poll());  // AlgorithmList에서 ReadyQueue로 이동
         manager.ReadyQueue.create_form(ReadyQueue);
      }

      /*------------------------FCFS 알고리즘------------------------*/
      if(PresentProcess == null) {             // 현재 실행중인 프로세스가 없을 때 
         if(!ReadyQueue.isEmpty()) {          // ReadyQueue가 비어있지 않으면
            PresentProcess = ReadyQueue.poll(); // ReadyQueue의 첫 프로세스를 실행
            manager.ReadyQueue.create_form(ReadyQueue);
         }
       }
      
      if((PCoreCount + ECoreCount >= 2) && PresentProcess2 == null) { // 코어가 2개 이상이고, 코어 2에서 현재 실행중인 프로세스가 없을 때
         if(!ReadyQueue.isEmpty()) {                         // ReadyQueue가 비어있지 않으면
            PresentProcess2 = ReadyQueue.poll();                // ReadyQueue의 첫 프로세스를 실행
            manager.ReadyQueue.create_form(ReadyQueue);
         }
       }
      
      if((PCoreCount + ECoreCount >= 3) && PresentProcess3 == null) { // 코어가 3개 이상이고, 코어 3에서 현재 실행중인 프로세스가 없을 때
         if(!ReadyQueue.isEmpty()) {                         // ReadyQueue가 비어있지 않으면
            PresentProcess3 = ReadyQueue.poll();                // ReadyQueue의 첫 프로세스를 실행
            manager.ReadyQueue.create_form(ReadyQueue);
         }
       }
      
      if((PCoreCount + ECoreCount >= 4) && PresentProcess4 == null) { // 코어가 4개 이상이고, 코어 4에서 현재 실행중인 프로세스가 없을 때
         if(!ReadyQueue.isEmpty()) {                         // ReadyQueue가 비어있지 않으면
            PresentProcess4 = ReadyQueue.poll();                // ReadyQueue의 첫 프로세스를 실행
            manager.ReadyQueue.create_form(ReadyQueue);
         }
       }
      /*---------------------GUI표시-----------------------------*/
      if(PresentProcess==null) {
         ghanttchartPanel_1.adding(new JLabel("    "),-1);         
         elec += ((PCoreCount + ECoreCount)*0.1);
      }
      else {
         ghanttchartPanel_1.adding(new JLabel(PresentProcess.Name), PresentProcess.Row);   
         elec += PCoreCount*3 + ECoreCount; // 8
      }  
      if(PresentProcess2==null) {
         ghanttchartPanel_2.adding(new JLabel("    "),-1);         
         elec += ((PCoreCount + ECoreCount)*0.1);
      }
      else {
         ghanttchartPanel_2.adding(new JLabel(PresentProcess2.Name), PresentProcess2.Row);   
         elec += PCoreCount*3 + ECoreCount; // 8
      }  
      if(PresentProcess3==null) {
         ghanttchartPanel_3.adding(new JLabel("    "),-1);         
         elec += ((PCoreCount + ECoreCount)*0.1);
      }
      else {
         ghanttchartPanel_3.adding(new JLabel(PresentProcess3.Name), PresentProcess3.Row);   
         elec += PCoreCount*3 + ECoreCount; // 8
      }  
      if(PresentProcess4==null) {
         ghanttchartPanel_4.adding(new JLabel("    "),-1);         
         elec += ((PCoreCount + ECoreCount)*0.1);
      }
      else {
         ghanttchartPanel_4.adding(new JLabel(PresentProcess4.Name), PresentProcess4.Row);   
         elec += PCoreCount*3 + ECoreCount; // 8
      }  
      /*---------------------GUI표시-----------------------------*/
      
       if(!(PresentProcess == null)) PresentProcess.BurstTime -= CoreWork1;   // 현재 FCFS가 비어있지 않으면 Bursttime에서 처리량 빼주기
       if(!(PresentProcess2 == null)) PresentProcess2.BurstTime -= CoreWork2;
       if(!(PresentProcess3 == null)) PresentProcess3.BurstTime -= CoreWork3;
       if(!(PresentProcess4 == null)) PresentProcess4.BurstTime -= CoreWork4;
       
       
       
       
       /////////////////////////////////////////////////////////////////////////
//       if(PresentProcess==null) {
//         ghanttchartPanel.adding(new JLabel("    "),-1);         
//         elec += ((PCoreCount + ECoreCount)*0.1);
//      }
//      else {
//         ghanttchartPanel.adding(new JLabel(PresentProcess.Name), PresentProcess.Row);   
//         elec += PCoreCount*3 + ECoreCount; // 8
//      }
   }
}