
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddPanel extends JPanel{
	ProjectManager manager;
	
	JButton AddButton = new JButton("Add");
	
	Process AddProcess;

	LinkedList<Process> AlgorithmList = new LinkedList<>();
	
	BaseLabel AlgorithmLabel = new BaseLabel("Algorithm");
	
	String[] algorithmArray = {"FCFS", "RR", "SPN", "SRTN", "HRRN", "MFQ"};
	JComboBox<String> AlgorithmComboBox = new JComboBox<String>(algorithmArray);
	
	BaseLabel ProcessNameLabel = new BaseLabel("Process Name");
	
	JTextField ProcessNameTextField = new JTextField(10);
	
	BaseLabel ArrivalTimeLabel = new BaseLabel("Arrival Time");
	
	JTextField ArrivalTimeTextField = new JTextField(10);
	
	BaseLabel BurstTimeLabel = new BaseLabel("Burst Time");
	
	JTextField BurstTimeTextField = new JTextField(10);

	public String SetAlgorithm = "";
	
	public AddPanel(ProjectManager manager) {
		this.manager = manager;
		manager.addPanel = this;
		Base();
		ComponentSetting();
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
			return new Process("ERROR", -1, -1);
		}
		int ArrivalTime = Integer.parseInt(ArrivalTimeTextField.getText());
		int BurstTime = Integer.parseInt(BurstTimeTextField.getText());

		return new Process(ProcessNameTextField.getText(), ArrivalTime, BurstTime);
	}
	
	private class AddActionListener  implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(AlgorithmSetting().ArrivalTime == -1) {
				JOptionPane.showMessageDialog(null,  "Fill in the blanks.", "Error", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				AddProcess = AlgorithmSetting();
				AlgorithmList.add(AddProcess);
				manager.information.AddAlgorithm(AddProcess);
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
