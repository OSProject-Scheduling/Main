import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Queue;

public class FCFS {

	GhanttChartPanel ghanttchartPanel;
	LinkedList<FCFSProcess> SRTNList = new LinkedList<>();		// ���⿡ FCFS�� ����Ǿ� ���� ���⿡ �ִ°� ������ ��.
	LinkedList<FCFSProcess> ReadyQueue = new LinkedList<>();
	FCFSProcess PresentSRTN = null;
	FCFSProcess Process = null;
	int pass = 0;
	
	int time = 0;
	int CoreWork = 1;
	
	public FCFS(LinkedList<FCFSProcess> FCFSprocess, GhanttChartPanel ghanttchartPanel) {
		this.SRTNList = FCFSprocess;
		this.ghanttchartPanel = ghanttchartPanel;
		start();
	}
	
	public void start() {
		Timer timer = new Timer(); 																				// timer�� timertask�� ����� ī��Ʈ�� �������׽��ϴ�
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
					Schedulling(); 																				// 1�� ���� ����
					if(SRTNList.isEmpty() && ReadyQueue.isEmpty() && PresentSRTN == null) timer.cancel(); 
					time++; 																					// time������ ���������� �ʸ� ǥ��
				}
			};
			timer.schedule(task, 1000,1000); 																	// 1�ʸ��� ����
	}
	void Schedulling() {
		if(!SRTNList.isEmpty() && time == SRTNList.peekFirst().ArrivalTime) ReadyQueue.add(SRTNList.poll()); 	// FCFSList���� ReadyQueue�� �̵�(ArrivalTime�� ������)
		if(PresentSRTN == null) {																				// ���� FCFS�� ��������� 
			if(!ReadyQueue.isEmpty()) {																			// ReadyQueue�� ������� ������ ���� FCFS�� poll
				PresentSRTN = ReadyQueue.poll();
			}
		}
		if(PresentSRTN==null) ghanttchartPanel.adding(new JLabel("    "));			
		else ghanttchartPanel.adding(new JLabel(PresentSRTN.Name));												// GhanttChart ǥ��
		if(!(PresentSRTN == null)) {
			for(int i = SRTNList.size()-2;i>=0;i--) {
				if(SRTNList.get(SRTNList.size()-pass).BurstTime < SRTNList.get(i).BurstTime) {
					Process = SRTNList.get(SRTNList.size()-pass);
					SRTNList.set(SRTNList.size()-pass, SRTNList.get(i));
					SRTNList.set(i, Process);
					pass++;
				}
			}
			PresentSRTN = SRTNList.get(0);
			pass = 1;
			PresentSRTN.BurstTime -= CoreWork;											// ���� FCFS�� ������� ������ bursttime���� ó���� ���ֱ�
		}
		if(!(PresentSRTN == null) && PresentSRTN.BurstTime <= 0) PresentSRTN = null;
	}
}

