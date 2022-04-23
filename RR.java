
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Queue;

public class RR{

	GhanttChartPanel ghanttchartPanel;
	LinkedList<FCFSProcess> FCFSList = new LinkedList<>();		// ���⿡ FCFS�� ����Ǿ� ���� ���⿡ �ִ°� ������ ��.
	LinkedList<FCFSProcess> ReadyQueue = new LinkedList<>();
	FCFSProcess PresentFCFS = null;
	
	int time = 0;
	int CoreWork = 1;
	int Quantum = 3;
	int ForQuantum = 0;
	
	public RR(LinkedList<FCFSProcess> FCFSprocess, GhanttChartPanel ghanttchartPanel) {
		this.FCFSList = FCFSprocess;
		this.ghanttchartPanel = ghanttchartPanel;
		start();
	}
	
	public void start() {
		Timer timer = new Timer(); 																				// timer�� timertask�� ����� ī��Ʈ�� �������׽��ϴ�
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
					Schedulling(); 																				// 1�� ���� ����
					if(FCFSList.isEmpty() && ReadyQueue.isEmpty() && PresentFCFS == null) timer.cancel(); 
					time++; 																					// time������ ���������� �ʸ� ǥ��
				}
			};
			timer.schedule(task, 1000,1000); 																	// 1�ʸ��� ����
	}
	
	public void Schedulling() {
		ForQuantum++;
		if(!FCFSList.isEmpty() && time == FCFSList.peekFirst().ArrivalTime) ReadyQueue.add(FCFSList.poll()); 	// FCFSList���� ReadyQueue�� �̵�(ArrivalTime�� ������)
		if(PresentFCFS == null) {																				// ���� FCFS�� ��������� 
			if(!ReadyQueue.isEmpty()) {																			// ReadyQueue�� ������� ������ ���� FCFS�� poll
				PresentFCFS = ReadyQueue.poll();
			}
		}
		
		if(PresentFCFS==null) ghanttchartPanel.adding(new JLabel("    "));			
		else ghanttchartPanel.adding(new JLabel(PresentFCFS.Name));												// GhanttChart ǥ��
		if(!(PresentFCFS == null)) PresentFCFS.BurstTime -= CoreWork;											// ���� FCFS�� ������� ������ bursttime���� ó���� ���ֱ�
		if(!(PresentFCFS == null) && PresentFCFS.BurstTime <= 0) {
			PresentFCFS = null;							// bursttime�� 0 ���ϰ� �Ǹ� null�� ��ȭ
			ForQuantum = 0;
		}
		
		if(ForQuantum == Quantum) {
			ReadyQueue.add(PresentFCFS);
			PresentFCFS = null;
			ForQuantum = 0;
		}
	}
	
	public void Core() { // ����
		// -> CoreWork �̰� �������ִ°� 
		// ��Ȳ�� �°� CoreWork ����
	}
}

