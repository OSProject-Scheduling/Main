
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Queue;

//spn
public class SPN {

	GhanttChartPanel ghanttchartPanel;
	LinkedList<FCFSProcess> FCFSList = new LinkedList<>();		// ���⿡ FCFS�� ����Ǿ� ���� ���⿡ �ִ°� ������ ��.
	LinkedList<FCFSProcess> ReadyQueue = new LinkedList<>();
	FCFSProcess PresentFCFS = null;
	
	int time = 0;
	int CoreWork = 1;
	
	public SPN(LinkedList<FCFSProcess> FCFSprocess, GhanttChartPanel ghanttchartPanel) {
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
		if(!FCFSList.isEmpty() && time == FCFSList.peekFirst().ArrivalTime) {
			
			for (int i = 0; i<=ReadyQueue.size(); i++) {
				if(i==ReadyQueue.size()) {
					ReadyQueue.add(FCFSList.poll());
					break;
				}
				
				if(ReadyQueue.get(i).BurstTime > FCFSList.peekFirst().BurstTime) {
					ReadyQueue.add(i, FCFSList.poll());
					break;
				}
			}		
		}
		
		if(PresentFCFS == null) {																				// ���� FCFS�� ��������� 
			if(!ReadyQueue.isEmpty()) {																			// ReadyQueue�� ������� ������ ���� FCFS�� poll
				PresentFCFS = ReadyQueue.poll();
			}
		}
		if(PresentFCFS==null) ghanttchartPanel.adding(new JLabel("    "));			
		else ghanttchartPanel.adding(new JLabel(PresentFCFS.Name));												// GhanttChart ǥ��
		if(!(PresentFCFS == null)) PresentFCFS.BurstTime -= CoreWork;											// ���� FCFS�� ������� ������ bursttime���� ó���� ���ֱ�
		if(!(PresentFCFS == null) && PresentFCFS.BurstTime <= 0) PresentFCFS = null;							// bursttime�� 0 ���ϰ� �Ǹ� null�� ��ȭ
	}
	
	public void Core() { // ����
		// -> CoreWork �̰� �������ִ°� 
		// ��Ȳ�� �°� CoreWork ����
		
	}
}

