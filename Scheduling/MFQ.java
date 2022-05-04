package Scheduling;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

import GUI.GhanttChartPanel;
import Manager.ProjectManager;

//import GUI.GhanttChartPanel;
//import Manager.ProjectManager;

import java.util.LinkedList;

public class MFQ {
   int MaxQuantum;
   int ForQuantum = 0;
   int Quantum = 3;
   int Div;
   ProjectManager manager;
   
   GhanttChartPanel ghanttchartPanel;

   LinkedList<Process> HighAlgorithmList = new LinkedList<>();
   LinkedList<Process> MiddleAlgorithmList = new LinkedList<>();
   LinkedList<Process> LowAlgorithmList = new LinkedList<>();

   LinkedList<Process> HighReadyQueue = new LinkedList<>();
   LinkedList<Process> MiddleReadyQueue = new LinkedList<>();
   LinkedList<Process> LowReadyQueue = new LinkedList<>();

   Process PresentProcess = null;
   int time = 0;
   int CoreWork = 1;

   public Timer timer = new Timer(); // 타이머 중지를 위한 public 설정

   public MFQ(ProjectManager manager, int MaxQuantum, int Div) {
      this.manager = manager;
      this.MaxQuantum = MaxQuantum;
      this.Div = Div;
      ghanttchartPanel = manager.GhanttChart;
      HighAlgorithmList.add(new Process("High","p1",0,15, 0));
      manager.information.MFQAddAlgorithm(new Process("High","p1",0,15, 0));
      
      HighAlgorithmList.add(new Process("High","p2",1,6, 1));
      HighAlgorithmList.add(new Process("High","p3",4,7, 2));
      
      MiddleAlgorithmList.add(new Process("Middle","p4",3,5, 3));
      MiddleAlgorithmList.add(new Process("Middle","p5",6,3, 4));
      MiddleAlgorithmList.add(new Process("Middle","p6",5,7, 5));
      
      LowAlgorithmList.add(new Process("Low","p7",3,3, 6));
      LowAlgorithmList.add(new Process("Low","p8",5,4, 7));
      LowAlgorithmList.add(new Process("Low","p9",6,5, 8));
      
      start();
   }
   
   public void start() { // timer와 timertask를 사용해 카운트를 구현시켰습니다
      TimerTask task = new TimerTask() {
         @Override
         public void run() {
            schedulling();
            if (PresentProcess == null && (HighAlgorithmList.isEmpty() && HighReadyQueue.isEmpty())
                  && (MiddleAlgorithmList.isEmpty() && MiddleReadyQueue.isEmpty())
                  && (LowAlgorithmList.isEmpty() && LowReadyQueue.isEmpty()))
               timer.cancel(); // MFQ용 종료 조건 선언 해야함
            time++; // time변수를 증가시켜줘 초를 표현

         }
      };
      timer = new Timer();
      timer.schedule(task, 1000, 1000); // 1초마다 실행
   }

   void schedulling() { // MFQ용 스케쥴링
      //High
      if(!(PresentProcess == null) && PresentProcess.BurstTime <= 0) {
         PresentProcess.TurnaroundTime = time - PresentProcess.ArrivalTime;
         PresentProcess.WaitingTime = PresentProcess.TurnaroundTime - PresentProcess.StaticBurstTime;
         PresentProcess.NormalizedTime = PresentProcess.TurnaroundTime / PresentProcess.StaticBurstTime;
           manager.information.ChangeInformation(PresentProcess.TurnaroundTime, PresentProcess.WaitingTime, PresentProcess.NormalizedTime, PresentProcess.Row);
         PresentProcess = null;                     // bursttime이 0 이하가 되면 null로 변화
         ForQuantum = 0;
      }
      
      if(!(HighAlgorithmList.isEmpty()) || !(HighReadyQueue.isEmpty())){         
         if(!(PresentProcess == null) && ForQuantum == Quantum) {      // 퀀텀 처리
            if (PresentProcess.count == Div) {
               HighReadyQueue.addFirst(PresentProcess);
            }
            else {
               HighReadyQueue.addLast(PresentProcess);
               PresentProcess = null;
               ForQuantum = 0;
            }
         }
         ForQuantum++;
         if(!HighAlgorithmList.isEmpty() && time == HighAlgorithmList.peekFirst().ArrivalTime) {
            HighReadyQueue.add(HighAlgorithmList.poll());    // FCFSList에서 ReadyQueue로 이동(ArrivalTime에 맞으면)
            manager.HighReadyQueue.create_form(HighReadyQueue);
         }
         if(!MiddleAlgorithmList.isEmpty() && time == MiddleAlgorithmList.peekFirst().ArrivalTime) {
            MiddleReadyQueue.add(MiddleAlgorithmList.poll());    // FCFSList에서 ReadyQueue로 이동(ArrivalTime에 맞으면)
            manager.MidReadyQueue.create_form(MiddleReadyQueue);
         }
         if(!LowAlgorithmList.isEmpty() && time == LowAlgorithmList.peekFirst().ArrivalTime) {
            LowReadyQueue.add(LowAlgorithmList.poll());    // FCFSList에서 ReadyQueue로 이동(ArrivalTime에 맞으면)
            manager.lowReadyQueue.create_form(LowReadyQueue);
         }
         if(PresentProcess == null) {                                                            // 현재 FCFS가 비어있을때
            if(!HighReadyQueue.isEmpty()) {                                                         // ReadyQueue가 비어있지 않으면 현재 FCFS로 poll
               PresentProcess = HighReadyQueue.poll();
               PresentProcess.count++;
               Quantum = (int)(PresentProcess.StaticBurstTime/Div);
               if (Div > PresentProcess.StaticBurstTime) {
                  Quantum = 1;
               }
            }
         }
      }
      
      //Middle
      else if(!(MiddleAlgorithmList.isEmpty()) || !(MiddleReadyQueue.isEmpty())){         
         if(!(PresentProcess == null) && ForQuantum == Quantum) {
            if (PresentProcess.count == Div) {
               MiddleReadyQueue.addFirst(PresentProcess);
            }
            else {
               MiddleReadyQueue.addLast(PresentProcess);
               PresentProcess = null;
               ForQuantum = 0;
            }
         }
         ForQuantum++;
         if(!HighAlgorithmList.isEmpty() && time == HighAlgorithmList.peekFirst().ArrivalTime) {
            HighReadyQueue.add(HighAlgorithmList.poll());    // FCFSList에서 ReadyQueue로 이동(ArrivalTime에 맞으면)
         }
         if(!MiddleAlgorithmList.isEmpty() && time == MiddleAlgorithmList.peekFirst().ArrivalTime) {
            MiddleReadyQueue.add(MiddleAlgorithmList.poll());    // FCFSList에서 ReadyQueue로 이동(ArrivalTime에 맞으면)
         }
         if(!LowAlgorithmList.isEmpty() && time == LowAlgorithmList.peekFirst().ArrivalTime) {
            LowReadyQueue.add(LowAlgorithmList.poll());    // FCFSList에서 ReadyQueue로 이동(ArrivalTime에 맞으면)
         }
         if(PresentProcess == null) {                                                            // 현재 FCFS가 비어있을때
            if(!MiddleReadyQueue.isEmpty()) {                                                         // ReadyQueue가 비어있지 않으면 현재 FCFS로 poll
               PresentProcess = MiddleReadyQueue.poll();
               PresentProcess.count++;
               Quantum = (int)(PresentProcess.StaticBurstTime/Div);
               if (Div > PresentProcess.StaticBurstTime) {
                  Quantum = 1;
               }
            }
         }
      }
      
      //Low
      else if(!(LowAlgorithmList.isEmpty()) || !(LowReadyQueue.isEmpty())){         
         if(!(PresentProcess == null) && ForQuantum == Quantum) {
            if (PresentProcess.count == Div) {
               LowReadyQueue.addFirst(PresentProcess);
            }
            else {
               LowReadyQueue.addLast(PresentProcess);
               PresentProcess = null;
               ForQuantum = 0;
            }
         }
         ForQuantum++;
         if(!HighAlgorithmList.isEmpty() && time == HighAlgorithmList.peekFirst().ArrivalTime) {
            HighReadyQueue.add(HighAlgorithmList.poll());    // FCFSList에서 ReadyQueue로 이동(ArrivalTime에 맞으면)
         }
         if(!MiddleAlgorithmList.isEmpty() && time == MiddleAlgorithmList.peekFirst().ArrivalTime) {
            MiddleReadyQueue.add(MiddleAlgorithmList.poll());    // FCFSList에서 ReadyQueue로 이동(ArrivalTime에 맞으면)
         }
         if(!LowAlgorithmList.isEmpty() && time == LowAlgorithmList.peekFirst().ArrivalTime) {
            LowReadyQueue.add(LowAlgorithmList.poll());    // FCFSList에서 ReadyQueue로 이동(ArrivalTime에 맞으면)
         }
         if(PresentProcess == null) {                                                            // 현재 FCFS가 비어있을때
            if(!LowReadyQueue.isEmpty()) {                                                         // ReadyQueue가 비어있지 않으면 현재 FCFS로 poll
               PresentProcess = MiddleReadyQueue.poll();
               PresentProcess.count++;
               Quantum = (int)(PresentProcess.StaticBurstTime/Div);
               if (Div > PresentProcess.StaticBurstTime) {
                  Quantum = 1;
               }
            }
         }
      }
      
      if(PresentProcess == null) ghanttchartPanel.adding(new JLabel("    "), -1);         
      else ghanttchartPanel.adding(new JLabel(PresentProcess.Name), PresentProcess.Row);
      
      if(!(PresentProcess == null)) PresentProcess.BurstTime -= CoreWork;
      if (PresentProcess == null && (HighAlgorithmList.isEmpty() && HighReadyQueue.isEmpty())
            && (MiddleAlgorithmList.isEmpty() && MiddleReadyQueue.isEmpty())
            && (LowAlgorithmList.isEmpty() && LowReadyQueue.isEmpty()))
         return;
   }

   protected void Core() { // 예정
      // -> CoreWork 이거 변경해주는거
      // 상황에 맞게 CoreWork 변경
   }
}