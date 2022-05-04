import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class RunPanel extends JPanel{
	int PCoreCount = 0;
	int ECoreCount = 0;
	int Ppass = 0;
	int Ppass1 = 0;
	int Epass = 0;
	int Epass1 = 0;
	boolean isoverCount;
	int QuanturmTime = 0;
	JButton RunButton = new JButton("Run");
	
	ProjectManager manager;	
	BaseLabel QuanturmTimeLabel = new BaseLabel("Quenturm");
	
	JTextField QuanturmTimeTextField = new JTextField(10);
	
	BaseLabel PCoreLabel = new BaseLabel("P Core");
	JSpinner PCore;
	
	BaseLabel ECoreLabel = new BaseLabel("E Core");
	JSpinner ECore;
	
	public RunPanel(ProjectManager manager) {
		this.manager = manager;
		manager.runpanel = this;
		Base();
		ComponentSetting();
	}
	
	private void Base(){
		setSize(240, 270);
		setLocation(10, 390);
		setBackground(Color.YELLOW);
		setLayout(null);
	}
	
	private void ComponentSetting() {
		
		SpinnerNumberModel Pnumbermodel = new SpinnerNumberModel(0,0,5,1);
		SpinnerNumberModel Enumbermodel = new SpinnerNumberModel(0,0,5,1);

		PCore = new JSpinner(Pnumbermodel);
		PCoreLabel.setLocation(10,10);
		add(PCoreLabel);
		
		PCore.setSize(50,25);
		PCore.setLocation(120,15);
		add(PCore);
		
		ECore = new JSpinner(Enumbermodel);
		ECoreLabel.setLocation(10,40);
		add(ECoreLabel);
		
		ECore.setSize(50,25);
		ECore.setLocation(120, 45);
		add(ECore);
		
		
		PCore.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				PCoreCount = Integer.parseInt(PCore.getValue().toString());
				if(PCoreCount + ECoreCount > 4) {
				}
				else if(PCoreCount + ECoreCount < 4){
				}
			}
		});
		
		ECore.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				ECoreCount = Integer.parseInt(ECore.getValue().toString());
				if(PCoreCount + ECoreCount > 4) {
				}
				else if(PCoreCount + ECoreCount < 4){

				}
			}
		});
		
		QuanturmTimeLabel.setLocation(10, 70);
		add(QuanturmTimeLabel);
		
		QuanturmTimeTextField.setHorizontalAlignment(JTextField.CENTER);
		QuanturmTimeTextField.setLocation(120, 75);
		QuanturmTimeTextField.setSize(50, 20);
		QuanturmTimeTextField.addKeyListener(new TimeKeyListener());
		add(QuanturmTimeTextField);
		
		QuanturmTimeLabel.setVisible(false);
		QuanturmTimeTextField.setVisible(false);
		
		
		RunButton.setSize(105, 20);
		RunButton.setLocation(70, 230);
		RunButton.setOpaque(true);
		RunButton.setBackground(Color.green);
		RunButton.addActionListener(new RunActionListener());
		add(RunButton);
		
		
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
	
	
	private class RunActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(manager.addPanel.SetAlgorithm == "RR") {
				QuanturmTime = Integer.parseInt(QuanturmTimeTextField.getText());
			}
			if(manager.addPanel.AlgorithmList.isEmpty()) {
				JOptionPane.showMessageDialog(null,  "Add Process", "Error", JOptionPane.INFORMATION_MESSAGE);
			}
			if(ECoreCount + PCoreCount == 0) {
				JOptionPane.showMessageDialog(null,  "Add Processor", "Error", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				for(int i =0; i<manager.addPanel.AlgorithmList.size(); i++) {
					for(int j = 0; j<manager.addPanel.AlgorithmList.size()-1;j++) {
						if(manager.addPanel.AlgorithmList.get(j).ArrivalTime>manager.addPanel.AlgorithmList.get(j+1).ArrivalTime) {
							Process temp = manager.addPanel.AlgorithmList.get(j);
							manager.addPanel.AlgorithmList.set(j, manager.addPanel.AlgorithmList.get(j+1));
							manager.addPanel.AlgorithmList.set(j+1, temp);
							
						}
					}
				}
				if(manager.addPanel.SetAlgorithm == "FCFS") new FCFS(manager);
				else if(manager.addPanel.SetAlgorithm == "RR") new RR(manager, QuanturmTime);
				else if(manager.addPanel.SetAlgorithm == "SPN") new SPN(manager);
				else if(manager.addPanel.SetAlgorithm == "SRTN") new SRTN(manager);
				else if(manager.addPanel.SetAlgorithm == "HRRN") new HRRN(manager);
			}
		}
	}

}