
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

public class AddPanel extends JPanel{
	ProjectManager manager;
	
	JButton AddButton = new JButton("Add");
	
	Process AddProcess;
	
	MFQProcess AddMFQProcess;

	LinkedList<Process> AlgorithmList = new LinkedList<>();
	
	LinkedList<MFQProcess> MFQAlgorithmList = new LinkedList<>();
	
	BaseLabel AlgorithmLabel = new BaseLabel("Algorithm");
	
	String[] algorithmArray = {"FCFS", "RR", "SPN", "SRTN", "HRRN", "MFQ"};
	JComboBox<String> AlgorithmComboBox = new JComboBox<String>(algorithmArray);
	
	BaseLabel ProcessNameLabel = new BaseLabel("Process Name");
	
	JTextField ProcessNameTextField = new JTextField(10);
	
	BaseLabel ArrivalTimeLabel = new BaseLabel("Arrival Time");
	
	JTextField ArrivalTimeTextField = new JTextField(10);
	
	BaseLabel BurstTimeLabel = new BaseLabel("Burst Time");
	
	JTextField BurstTimeTextField = new JTextField(10);
	
	BaseLabel PriorityReadyQueueLabel = new BaseLabel("PIReadyQueue");
	
	String [] ReadyQueueArray = {"High", "Middle", "Low"};
	JComboBox<String> PriorityReadyQueueComboBox = new JComboBox<String>(ReadyQueueArray);
	
	int Row = 0;

	public String SetPriorityReadyQueue;
	
	public String SetAlgorithm = "";
	
	public AddPanel(ProjectManager manager) {
		this.manager = manager;
		manager.addPanel = this;
		Base();
		ComponentSetting();
		
		AlgorithmList.add(new Process("P1", 0, 15));
		AlgorithmList.add(new Process("P2", 1, 1));
		AlgorithmList.add(new Process("P3", 2, 1));
		AlgorithmList.add(new Process("P4", 3, 1));
		AlgorithmList.add(new Process("P5", 4, 1));
		AlgorithmList.add(new Process("P6", 5, 1));
		AlgorithmList.add(new Process("P7", 6, 1));
		AlgorithmList.add(new Process("P8", 7, 1));
		AlgorithmList.add(new Process("P9", 8, 1));
		AlgorithmList.add(new Process("P10", 9, 1));
		AlgorithmList.add(new Process("P11", 10, 1));
		AlgorithmList.add(new Process("P12", 11, 1));
		AlgorithmList.add(new Process("P13", 12, 1));
	}
	private void Base(){
		setSize(240, 270);
		setLocation(10, 110);
		setBackground(Color.yellow);
		setLayout(null);
	}
	
	private void ComponentSetting() {
		AlgorithmLabel.setLocation(10, 10);									// AlgorithmLabel adding
		add(AlgorithmLabel);
		
		AlgorithmComboBox.setFont(new Font("Dialog", Font.BOLD, 14));		// AlgorithmComboBox adding
		AlgorithmComboBox.setSize(110, 20);
		AlgorithmComboBox.setLocation(120, 15);
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
		
		PriorityReadyQueueLabel.setLocation(10,130);
		add(PriorityReadyQueueLabel);
		
		PriorityReadyQueueComboBox.setFont(new Font("Dialog", Font.BOLD, 14));
		PriorityReadyQueueComboBox.setSize(110,20);
		PriorityReadyQueueComboBox.setLocation(120,135);
		add(PriorityReadyQueueComboBox);
		
		PriorityReadyQueueLabel.setVisible(false);
		PriorityReadyQueueComboBox.setVisible(false);
		
		
		AlgorithmComboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SetAlgorithm = AlgorithmComboBox.getSelectedItem().toString();
				if(SetAlgorithm == "RR") {
					manager.runpanel.QuanturmTimeLabel.setVisible(true);
					manager.runpanel.QuanturmTimeTextField.setVisible(true);
				}
				else {
					manager.runpanel.QuanturmTimeLabel.setVisible(false);
					manager.runpanel.QuanturmTimeTextField.setVisible(false);
					manager.runpanel.QuanturmTimeTextField.setText("");
				}
				if(SetAlgorithm == "MFQ") {
					PriorityReadyQueueComboBox.setVisible(true);
					PriorityReadyQueueLabel.setVisible(true);
					manager.HighReadyQueue.ReadyQueueScrollBar.setVisible(true);
					manager.lowReadyQueue.ReadyQueueScrollBar.setVisible(true);
					manager.MidReadyQueue.ReadyQueueScrollBar.setVisible(true);
					manager.ReadyQueue.ReadyQueueScrollBar.setVisible(false);
					manager.information.model.addColumn("Priority");
					manager.information.table.getColumn("Priority").setPreferredWidth(20);
				}
				else {
					PriorityReadyQueueComboBox.setVisible(false);
					PriorityReadyQueueLabel.setVisible(false);
					manager.HighReadyQueue.ReadyQueueScrollBar.setVisible(false);
					manager.lowReadyQueue.ReadyQueueScrollBar.setVisible(false);
					manager.MidReadyQueue.ReadyQueueScrollBar.setVisible(false);
					manager.ReadyQueue.ReadyQueueScrollBar.setVisible(true);
					String[] TableHeader = {"Process Name", "Arrival time", "Burst time", 
							"Waiting time", "Turnaround time", "Normalized TT"};
					manager.information.model.setColumnIdentifiers(TableHeader);

				}
			}
		});
		
		PriorityReadyQueueComboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SetPriorityReadyQueue = PriorityReadyQueueComboBox.getSelectedItem().toString();
				
			}
		});
		
		AddButton.setSize(105, 20);
		AddButton.setLocation(70, 240);
		AddButton.setOpaque(true);
		AddButton.setBackground(Color.green);
		AddButton.addActionListener(new AddActionListener());
		add(AddButton);
		
	}
	
	private class ProcessNameKeyListener extends KeyAdapter{		// 프로세스 이름 관리 리스너
		public void keyTyped(KeyEvent e) {	
			JTextField t = (JTextField)e.getSource();
			if(t.getText().length()>=10) e.consume();		// 글자 수 제한
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
	
	public Process AlgorithmSetting() {
		if(ProcessNameTextField.getText().equals("") || ArrivalTimeTextField.getText().equals("")	// 입력칸에 빈칸인 경우 경고메세지 출력
				|| BurstTimeTextField.getText().equals("")) {
			return new Process("ERROR", -1, -1,-1);
		}
		int ArrivalTime = Integer.parseInt(ArrivalTimeTextField.getText());
		int BurstTime = Integer.parseInt(BurstTimeTextField.getText());

		return new Process(ProcessNameTextField.getText(), ArrivalTime, BurstTime, Row);
	}
	
	public MFQProcess MFQAlgorithmSetting() {
		if(ProcessNameTextField.getText().equals("") || ArrivalTimeTextField.getText().equals("")	// 입력칸에 빈칸인 경우 경고메세지 출력
				|| BurstTimeTextField.getText().equals("")) {
			return new MFQProcess("ERROR","ERROR", -1, -1,-1);
		}
		int ArrivalTime = Integer.parseInt(ArrivalTimeTextField.getText());
		int BurstTime = Integer.parseInt(BurstTimeTextField.getText());
		
		return new MFQProcess(SetPriorityReadyQueue, ProcessNameTextField.getText(), ArrivalTime, BurstTime, Row);
	}
	
	private class AddActionListener  implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(AlgorithmSetting().ArrivalTime == -1) {
				JOptionPane.showMessageDialog(null,  "Fill in the blanks.", "Error", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(AlgorithmComboBox.getSelectedItem().toString() == "MFQ") {
				AddMFQProcess = MFQAlgorithmSetting();
				MFQAlgorithmList.add(AddMFQProcess);
				AlgorithmList.add((Process)AddMFQProcess);
				manager.information.MFQAddAlgorithm(AddMFQProcess);
				Row++;
				Update();
			}
			else {
				AddProcess = AlgorithmSetting();
				AlgorithmList.add(AddProcess);
				manager.information.AddAlgorithm(AddProcess);
				Row++;
				Update();
			}
		}
	}



	
	public void Update() {
		ProcessNameTextField.setText("");
		ArrivalTimeTextField.setText("");
		BurstTimeTextField.setText("");
		
	}
}
