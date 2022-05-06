package Scheduling;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

import GUI.GhanttChartPanel;
import Manager.ProjectManager;

import java.util.LinkedList;

public abstract class Algorithm {
   abstract void schedulling();
   protected GhanttChartPanel ghanttchartPanel_1;
   protected GhanttChartPanel ghanttchartPanel_2;
   protected GhanttChartPanel ghanttchartPanel_3;
   protected GhanttChartPanel ghanttchartPanel_4;
   
   protected LinkedList<Process> AlgorithmList;   
   protected LinkedList<Process> ReadyQueue = new LinkedList<>();
   
   protected Process PresentProcess = null;
   protected Process PresentProcess2 = null;
   protected Process PresentProcess3 = null;
   protected Process PresentProcess4 = null;
   protected ProjectManager manager;
   protected int time = 0;
   protected int CoreWork1 = 1;
   protected int CoreWork2 = 1;
   protected int CoreWork3 = 1;
   protected int CoreWork4 = 1;
   
   int PCoreCount;
   int ECoreCount;
   double elec = 0;
   
   public Timer timer = new Timer();               // 타이머 중지를 위한 public 설정
   
   public Algorithm(ProjectManager manager, int PCoreCount, int ECoreCount) {
      this.AlgorithmList = manager.addPanel.AlgorithmList;
      this.ghanttchartPanel_1 = manager.GhanttChart_1;
      this.ghanttchartPanel_2 = manager.GhanttChart_2;
      this.ghanttchartPanel_3 = manager.GhanttChart_3;
      this.ghanttchartPanel_4 = manager.GhanttChart_4;
      this.manager = manager;
      
      this.PCoreCount = PCoreCount;
        this.ECoreCount = ECoreCount;
        //CoreWork = PCoreCount*2 + ECoreCount;
        
        if(ECoreCount == 3) {
         CoreWork4 = 2;
      }
      
      if(ECoreCount == 2) {
         CoreWork3 = 2;
         CoreWork4 = 2;
      }
      
      if(ECoreCount == 1) {
         CoreWork2 = 2;
         CoreWork3 = 2;
         CoreWork4 = 2;
      }
      
      if(ECoreCount == 0) {
         CoreWork1 = 2;
         CoreWork2 = 2;
         CoreWork3 = 2;
         CoreWork4 = 2;
      }
      
      start();
   }
   
   public void start() {                                                      // timer와 timertask를 사용해 카운트를 구현시켰습니다
      TimerTask task = new TimerTask() {
         @Override
         public void run() {
               schedulling();
               if(AlgorithmList.isEmpty() && ReadyQueue.isEmpty() && PresentProcess == null && PresentProcess2 == null && PresentProcess3 == null && PresentProcess4 == null) {
                  timer.cancel(); 
               }
               time++; // 코어 고려 안되었음                                                               // time변수를 증가시켜줘 초를 표현
            }
         };
         timer = new Timer();
         timer.schedule(task, 1000,1000);                                                    // 1초마다 실행
   }
   
   protected void CalculateTime() {            // TT / WT / NTT 계산
      if(!(PresentProcess == null) && PresentProcess.BurstTime <= 0) {
            PresentProcess.TurnaroundTime = time - PresentProcess.ArrivalTime;                               // TT 계산
            PresentProcess.WaitingTime = PresentProcess.TurnaroundTime - PresentProcess.StaticBurstTime;               // WT 계산
            PresentProcess.NormalizedTime = (((PresentProcess.TurnaroundTime / PresentProcess.StaticBurstTime)*100)/100.0);            // NTT 계산
            manager.information.ChangeInformation(PresentProcess.TurnaroundTime, PresentProcess.WaitingTime, PresentProcess.NormalizedTime, PresentProcess.Row);
            System.out.println(PresentProcess.Name + "나감");//테스트용 지워야됨!!!!!!!!!!!!!!!!!!!
            PresentProcess = null;                                    // bursttime이 0 이하가 되면 null로 변화
            
         }
      
      if(!(PresentProcess2 == null) && PresentProcess2.BurstTime <= 0) {
            PresentProcess2.TurnaroundTime = time - PresentProcess2.ArrivalTime;                               // TT 계산
            PresentProcess2.WaitingTime = PresentProcess2.TurnaroundTime - PresentProcess2.StaticBurstTime;               // WT 계산
            PresentProcess2.NormalizedTime = PresentProcess2.TurnaroundTime / PresentProcess2.StaticBurstTime;            // NTT 계산
            manager.information.ChangeInformation(PresentProcess2.TurnaroundTime, PresentProcess2.WaitingTime, PresentProcess2.NormalizedTime, PresentProcess2.Row);
            System.out.println(PresentProcess2.Name + "나감");//테스트용 지워야됨!!!!!!!!!!!!!!!!!!!
            PresentProcess2 = null;                                    // bursttime이 0 이하가 되면 null로 변화
         }
      
      if(!(PresentProcess3 == null) && PresentProcess3.BurstTime <= 0) {
            PresentProcess3.TurnaroundTime = time - PresentProcess3.ArrivalTime;                               // TT 계산
            PresentProcess3.WaitingTime = PresentProcess3.TurnaroundTime - PresentProcess3.StaticBurstTime;               // WT 계산
            PresentProcess3.NormalizedTime = PresentProcess3.TurnaroundTime / PresentProcess3.StaticBurstTime;            // NTT 계산
            manager.information.ChangeInformation(PresentProcess3.TurnaroundTime, PresentProcess3.WaitingTime, PresentProcess3.NormalizedTime, PresentProcess3.Row);
            System.out.println(PresentProcess3.Name + "나감");//테스트용 지워야됨!!!!!!!!!!!!!!!!!!!
            PresentProcess3 = null;                                    // bursttime이 0 이하가 되면 null로 변화
         }
      
      if(!(PresentProcess4 == null) && PresentProcess4.BurstTime <= 0) {
            PresentProcess4.TurnaroundTime = time - PresentProcess4.ArrivalTime;                               // TT 계산
            PresentProcess4.WaitingTime = PresentProcess4.TurnaroundTime - PresentProcess4.StaticBurstTime;               // WT 계산
            PresentProcess4.NormalizedTime = PresentProcess4.TurnaroundTime / PresentProcess4.StaticBurstTime;            // NTT 계산
            manager.information.ChangeInformation(PresentProcess4.TurnaroundTime, PresentProcess4.WaitingTime, PresentProcess4.NormalizedTime, PresentProcess4.Row);
            System.out.println(PresentProcess4.Name + "나감");//테스트용 지워야됨!!!!!!!!!!!!!!!!!!!
            PresentProcess4 = null;                                    // bursttime이 0 이하가 되면 null로 변화
         }
   }
//   if(PresentProcess==null) {
//      ghanttchartPanel_1.adding(new JLabel("    "),-1);         
//      elec += ((PCoreCount + ECoreCount)*0.1);
//   }
//   else {
//      ghanttchartPanel_1.adding(new JLabel(PresentProcess.Name), PresentProcess.Row);   
//      elec += PCoreCount*3 + ECoreCount; // 8
//   }  
}