package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.TableColumnModel;

import Manager.ProjectManager;
import Scheduling.Process;

public class AddPanel extends JPanel{
	ProjectManager manager;	
	
	JButton AddButton = new JButton("Add");						// AddButton
	
	Process AddProcess;											// FCFS, RR, SPN, SRTN, HRRN용 AddingProcess

	public LinkedList<Process> AlgorithmList = new LinkedList<>();		// FCFS, RR, SPN, SRTN, HRRN용 process 리스트
	
	public LinkedList<Process> MFQHighAlgorithmList = new LinkedList<>();	// MFQ용 process 리스트
	public LinkedList<Process> MFQMiddleAlgorithmList = new LinkedList<>();
	public LinkedList<Process> MFQLowAlorithmList = new LinkedList<>();
	
	BaseLabel AlgorithmLabel = new BaseLabel("Algorithm");		// algorithm
	
	String[] algorithmArray = {"FCFS", "RR", "SPN", "SRTN", "HRRN", "MFQ"};	
	JComboBox<String> AlgorithmComboBox = new JComboBox<String>(algorithmArray);
	
	BaseLabel ProcessNameLabel = new BaseLabel("Process Name");	// processName
	JTextField ProcessNameTextField = new JTextField(10);
	
	BaseLabel ArrivalTimeLabel = new BaseLabel("Arrival Time");	// ArivalTime
	JTextField ArrivalTimeTextField = new JTextField(10);
	
	BaseLabel BurstTimeLabel = new BaseLabel("Burst Time");		// BurstTime
	JTextField BurstTimeTextField = new JTextField(10);
	
	BaseLabel PriorityReadyQueueLabel = new BaseLabel("Priority");	// MFQ용 Priority
	String [] ReadyQueueArray = {"High", "Middle", "Low"};
	JComboBox<String> PriorityReadyQueueComboBox = new JComboBox<String>(ReadyQueueArray);
	
	public int Row = 0;												// information table용 row
	
	public String Priority;
	public String SetAlgorithm;
	
	public AddPanel(ProjectManager manager) {					// 생성자
		this.manager = manager;
		manager.addPanel = this;
		Base();													// addpanel 기본 세팅
		ComponentSetting();										// addpanel에 추가할 요소들 세팅
		
//		Process p1 = new Process("p1",1,20, 0);
//		Process p2 = new Process("p2",2,6, 1);
//		Process p3 = new Process("p3",4,8, 2);
//		
//		Process p4 = new Process("p4",3,3, 3);
//		Process p5 = new Process("p5",5,1, 4);
//		Process p6 = new Process("p6",6,7, 5);
//		
//		Process p7 = new Process("p7",3,1, 6);
//		Process p8 = new Process("p8",5,4, 7);
//		Process p9 = new Process("p9",6,5, 8);
//		Process p10 = new Process("p9",7,5, 8);
//		Process p11 = new Process("p9",8,5, 8);
//		Process p12 = new Process("p9",9,5, 8);
//		
//		AlgorithmList.add(p1);
//		AlgorithmList.add(p2);
//		AlgorithmList.add(p3);
//		AlgorithmList.add(p4);
//		AlgorithmList.add(p5);
//		
//		AlgorithmList.add(p6);
//		AlgorithmList.add(p7);
//		AlgorithmList.add(p8);
//		AlgorithmList.add(p9);
//		AlgorithmList.add(p10);
//		AlgorithmList.add(p11);
//		AlgorithmList.add(p12);
		
		
		/*너네구나*/
		Process p1 = new Process("High","p1",1,20, 0);
		Process p2 = new Process("High","p2",2,6, 1);
		Process p3 = new Process("High","p3",4,8, 2);
		
		Process p4 = new Process("High","p4",5,3, 3);
		Process p5 = new Process("High","p5",6,1, 4);
		Process p6 = new Process("Middle","p6",6,7, 5);
		
		Process p7 = new Process("Low","p7",3,1, 6);
		Process p8 = new Process("Low","p8",5,4, 7);
		Process p9 = new Process("Low","p9",6,5, 8);
		
		MFQHighAlgorithmList.add(p1);
		MFQHighAlgorithmList.add(p2);
		MFQHighAlgorithmList.add(p3);
		
		MFQHighAlgorithmList.add(p4);
		MFQHighAlgorithmList.add(p5);
		MFQMiddleAlgorithmList.add(p6);
		
		MFQLowAlorithmList.add(p7);
		MFQLowAlorithmList.add(p8);
		MFQLowAlorithmList.add(p9);
		
		manager.information.AddAlgorithm(p1);
		manager.information.AddAlgorithm(p2);
		manager.information.AddAlgorithm(p3);
		
		manager.information.AddAlgorithm(p4);
		manager.information.AddAlgorithm(p5);
		manager.information.AddAlgorithm(p6);
		
		manager.information.AddAlgorithm(p7);
		manager.information.AddAlgorithm(p8);
		manager.information.AddAlgorithm(p9);
		/*너네구나*/
	}
	private void Base(){		// addPanel 기본 세팅
		setSize(240, 201);
		setLocation(10, 10);
		setBackground(Color.yellow);
		setLayout(null);
	}
	
	private void ComponentSetting() {
		AlgorithmLabel.setLocation(10, 10);									// AlgorithmLabel adding
		add(AlgorithmLabel);
		
		AlgorithmComboBox.setFont(new Font("Dialog", Font.BOLD, 14));		// AlgorithmComboBox adding
		AlgorithmComboBox.setSize(110, 20);
		AlgorithmComboBox.setLocation(120, 15);
		AlgorithmComboBox.setSelectedItem("FCFS");
		add(AlgorithmComboBox);
		
		ProcessNameLabel.setLocation(10, 40);								// ProcessNameLabel adding
		add(ProcessNameLabel);
		
		ProcessNameTextField.setHorizontalAlignment(JTextField.CENTER);		// ProcessNameTextField adding
		ProcessNameTextField.setLocation(120, 45);
		ProcessNameTextField.setSize(110, 20);
		ProcessNameTextField.addKeyListener(new ProcessNameKeyListener());
		add(ProcessNameTextField);
		
		ArrivalTimeLabel.setLocation(10, 70);								// ArrivalTimeLabel adding
		add(ArrivalTimeLabel);
		
		ArrivalTimeTextField.setHorizontalAlignment(JTextField.CENTER);		// ArrivalTimeTextField adding
		ArrivalTimeTextField.setLocation(120, 75);
		ArrivalTimeTextField.setSize(110, 20);
		ArrivalTimeTextField.addKeyListener(new TimeKeyListener());
		add(ArrivalTimeTextField);
		
		BurstTimeLabel.setLocation(10, 100);								// ArrivalTimeLabel adding
		add(BurstTimeLabel);
		
		BurstTimeTextField.setHorizontalAlignment(JTextField.CENTER);		// ArrivalTimeTextField adding
		BurstTimeTextField.setLocation(120, 105);
		BurstTimeTextField.setSize(110, 20);
		BurstTimeTextField.addKeyListener(new TimeKeyListener());
		add(BurstTimeTextField);
		
		PriorityReadyQueueLabel.setLocation(10,130);						// PriorityReadyQueueLabel adding
		add(PriorityReadyQueueLabel);
		
		PriorityReadyQueueComboBox.setFont(new Font("Dialog", Font.BOLD, 14));	// PriorityReadyQueueComboBox adding
		PriorityReadyQueueComboBox.setSize(110,20);
		PriorityReadyQueueComboBox.setLocation(120,135);
		PriorityReadyQueueComboBox.setSelectedItem("Middle");
		add(PriorityReadyQueueComboBox);
		
		PriorityReadyQueueLabel.setVisible(false);							// 일단 안보이게, MFQ사용시 보이게
		PriorityReadyQueueComboBox.setVisible(false);
		
		AlgorithmComboBox.addActionListener(new ActionListener() {			// MFQ선택시 보이게 할 요소, 미선택 시 안보이게 할 요소
			@Override
			public void actionPerformed(ActionEvent e) {
				SetAlgorithm = AlgorithmComboBox.getSelectedItem().toString();
				if(SetAlgorithm == "RR") {									// RR일 경우 Quanturm 표시
					manager.runpanel.QuanturmTimeLabel.setVisible(true);
					manager.runpanel.QuanturmTimeTextField.setVisible(true);
				}
				else {
					manager.runpanel.QuanturmTimeLabel.setVisible(false);
					manager.runpanel.QuanturmTimeTextField.setVisible(false);
				}
				
				if(SetAlgorithm == "MFQ") {									// MFQ일 경우 다양한 요소 표시
					PriorityReadyQueueComboBox.setVisible(true);			// ADD부분 우선순위 추가
					PriorityReadyQueueLabel.setVisible(true);
					
					manager.MFQreadyQueue.setVisible(true);
					
					manager.runpanel.DivLabel.setVisible(true);
					manager.runpanel.DivTextField.setVisible(true);
					
					manager.runpanel.MaxQuanturmLabel.setVisible(true);
					manager.runpanel.MaxQuanturmTextField.setVisible(true);
					
					manager.ReadyQueue.ReadyQueueScrollBar.setVisible(false);		// 기존 Readyquueue 안보이게
					manager.ReadyQueue.ReadyQueueLabel.setVisible(false);
					
					manager.information.model.addColumn("Priority");
					manager.information.table.getColumn("Priority").setPreferredWidth(20);
					manager.information.CenterSetting();
				}
				else {													// MFQ아닐 경우 반대로
					PriorityReadyQueueComboBox.setVisible(false);
					PriorityReadyQueueLabel.setVisible(false);
					
					manager.MFQreadyQueue.setVisible(false);
					
					manager.runpanel.DivLabel.setVisible(false);
					manager.runpanel.DivTextField.setVisible(false);
					
					manager.runpanel.MaxQuanturmLabel.setVisible(false);
					manager.runpanel.MaxQuanturmTextField.setVisible(false);
					
					manager.ReadyQueue.ReadyQueueScrollBar.setVisible(true);
					manager.ReadyQueue.ReadyQueueLabel.setVisible(true);
					
					String[] TableHeader = {"Process Name", "Arrival time", "Burst time", 		// 우선순위 표시
                            "Waiting time", "Turnaround time", "Normalized TT"};

                    manager.information.model.setColumnIdentifiers(TableHeader);
				}
			}
		});
		
		PriorityReadyQueueComboBox.addActionListener(new ActionListener() {		// 우선순위에 맞는 Queue에 저장
			@Override
			public void actionPerformed(ActionEvent e) {
				Priority = PriorityReadyQueueComboBox.getSelectedItem().toString();
				
			}
		});
		
		AddButton.setSize(220, 30);								// addButton adding
		AddButton.setLocation(10, 170);
		AddButton.setOpaque(true);
		AddButton.setBackground(Color.green);
		AddButton.addActionListener(new AddActionListener());	// add 클릭 시 저 리스너 실행(맨 아래)
		add(AddButton);
		
	}
	
	private class ProcessNameKeyListener extends KeyAdapter{		// 프로세스 이름 관리 리스너
		public void keyTyped(KeyEvent e) {	
			JTextField t = (JTextField)e.getSource();
			if(t.getText().length()>=7) e.consume();		// 글자 수 제한
		}
	}
	
	private class TimeKeyListener extends KeyAdapter{
		public void keyTyped(KeyEvent e) {				// 숫자 외에는 작성할 수 없도록 설정
			JTextField t = (JTextField)e.getSource();
			if((e.getKeyChar() == '0') ||(e.getKeyChar() == '1') ||(e.getKeyChar() == '2') ||
					(e.getKeyChar() == '3') ||(e.getKeyChar() == '4') ||(e.getKeyChar() == '5') ||
					(e.getKeyChar() == '6') ||(e.getKeyChar() == '7') ||(e.getKeyChar() == '8') ||
					(e.getKeyChar() == '9')) {
			}
			else e.consume();
			if(t.getText().length()>=2) e.consume();	// 3자리 수 이상 설정 불가
		}
	}
	
	/*현재 텍스트에 입력된 ProcessName, AT, BT Process타입으로 변환해주는 함수 */
	public Process AlgorithmSetting() {
		if(ProcessNameTextField.getText().equals("") || ArrivalTimeTextField.getText().equals("")	// 입력칸에 빈칸인 경우 경고메세지 출력 하기 위한 과정
				|| BurstTimeTextField.getText().equals("")) {
			return new Process("ERROR", -1, -1,-1);
		}
		int ArrivalTime = Integer.parseInt(ArrivalTimeTextField.getText());	
		int BurstTime = Integer.parseInt(BurstTimeTextField.getText());
		if(SetAlgorithm == null) SetAlgorithm = "FCFS";

		return new Process(ProcessNameTextField.getText(), ArrivalTime, BurstTime, Row);			// Process타입으로 리턴(Row 참고)
	}
	
	public Process MFQAlgorithmSetting() {
		if(ProcessNameTextField.getText().equals("") || ArrivalTimeTextField.getText().equals("")	// 입력칸에 빈칸인 경우 경고메세지 출력
				|| BurstTimeTextField.getText().equals("")) {
			return new Process("ERROR","ERROR", -1, -1,-1);
		}
		int ArrivalTime = Integer.parseInt(ArrivalTimeTextField.getText());
		int BurstTime = Integer.parseInt(BurstTimeTextField.getText());
		
		if(Priority == null) Priority = "Middle";
		return new Process(Priority, ProcessNameTextField.getText(), ArrivalTime, BurstTime, Row);
	}
	
	private class AddActionListener  implements ActionListener{				// add 클릭 시 액션 리스너
		public void actionPerformed(ActionEvent e) {
			if((manager.algorithm == null) && (manager.mfq == null)) {	// 현재 실행 중인 알고리즘이 없을 때
				if(AlgorithmSetting().ArrivalTime == -1) {						// 현재 입력된 값들이 빈칸인 경우 에러 메세지 출력
					JOptionPane.showMessageDialog(null,  "Fill in the blanks.", "Error", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(AlgorithmComboBox.getSelectedItem().toString() == "MFQ") {		// MFQ인 경우
					AddProcess = MFQAlgorithmSetting();
					
					if(AddProcess.Priority.equals("High")) MFQHighAlgorithmList.add(AddProcess);
					else if(AddProcess.Priority == "Middle") MFQMiddleAlgorithmList.add(AddProcess);
					else MFQLowAlorithmList.add(AddProcess);
					
					AlgorithmList.add((Process)AddProcess);					// MFQ용 Algorithmlist에 추가, Information에 추가
					manager.information.MFQAddAlgorithm(AddProcess);
					Row++;
					Update();
				}
				else {															// MFQ가 아닐 때
					AddProcess = AlgorithmSetting();							
					AlgorithmList.add(AlgorithmSetting());						// AlgorithmList에 추가
					manager.information.AddAlgorithm(AddProcess);				// Information에 추가
					Row++;
					Update();
				}
			}
		}
	}

	public void Update() {
		ProcessNameTextField.setText("");
		ArrivalTimeTextField.setText("");
		BurstTimeTextField.setText("");
		PriorityReadyQueueComboBox.setSelectedItem("Middle");
	}
	
	public void RunningState() {
		AlgorithmComboBox.disable();
		ProcessNameTextField.setEditable(false);
		ArrivalTimeTextField.setEditable(false);
		BurstTimeTextField.setEditable(false);
		PriorityReadyQueueComboBox.disable();
	}
}
