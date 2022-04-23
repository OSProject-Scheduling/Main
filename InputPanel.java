import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputPanel extends JPanel{
	BaseLabel AlgorithmLabel = new BaseLabel("Algorithm");
	
	String[] algorithmArray = {"FCFS", "RR", "SPN", "SRTN", "HRRN"};
	JComboBox<String> AlgorithmComboBox = new JComboBox<String>(algorithmArray);
	
	BaseLabel ProcessNameLabel = new BaseLabel("Process Name");
	
	JTextField ProcessNameTextField = new JTextField(10);
	
	BaseLabel ArrivalTimeLabel = new BaseLabel("Arrival Time");
	
	JTextField ArrivalTimeTextField = new JTextField(10);
	
	BaseLabel BurstTimeLabel = new BaseLabel("Burst Time");
	
	JTextField BurstTimeTextField = new JTextField(10);

	
	public InputPanel() {
		Base();
		ComponentSetting();
	}
	private void Base(){
		setSize(240, 340);
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
	}
	
	private class ProcessNameKeyListener extends KeyAdapter{		// ���μ��� �̸� ���� ������
		public void keyTyped(KeyEvent e) {	
			JTextField t = (JTextField)e.getSource();
			if(t.getText().length()>=10) e.consume();		// ���� �� ����
		}
	}
	
	private class TimeKeyListener extends KeyAdapter{
		public void keyTyped(KeyEvent e) {				// ���� �ܿ��� �ۼ��� �� ������ ����
			JTextField t = (JTextField)e.getSource();
			if((e.getKeyChar() == '0') ||(e.getKeyChar() == '1') ||(e.getKeyChar() == '2') ||
					(e.getKeyChar() == '3') ||(e.getKeyChar() == '4') ||(e.getKeyChar() == '5') ||
					(e.getKeyChar() == '6') ||(e.getKeyChar() == '7') ||(e.getKeyChar() == '8') ||
					(e.getKeyChar() == '9')) {
			}
			else e.consume();
			if(t.getText().length()>=2) e.consume();	// 3�ڸ� �� �̻� ���� �Ұ�
		}
	}
	
	public FCFSProcess AlgorithmSetting() {
		if(ProcessNameTextField.getText().equals("") || ArrivalTimeTextField.getText().equals("")	// �Է�ĭ�� ��ĭ�� ��� ����޼��� ���
				|| BurstTimeTextField.getText().equals("")) {
			return new FCFSProcess("ERROR", -1, -1, -1, -1, -1);
		}
		
		int ArrivalTime = Integer.parseInt(ArrivalTimeTextField.getText());
		int BurstTime = Integer.parseInt(BurstTimeTextField.getText());

		return new FCFSProcess(ProcessNameTextField.getText(), ArrivalTime, BurstTime, 0, 0, 0);
	}
	
	public void Update() {
		AlgorithmComboBox.disable();
		ProcessNameTextField.setText("");
		ArrivalTimeTextField.setText("");
		BurstTimeTextField.setText("");
	}
}