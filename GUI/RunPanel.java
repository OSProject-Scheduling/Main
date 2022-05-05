package GUI;
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
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Manager.ProjectManager;
import Scheduling.*;
import Scheduling.Process;

public class RunPanel extends JPanel{

	int QuanturmTime = 0;
	
	ProjectManager manager;	
	
	JButton RunButton = new JButton("Run");								// RunButton

	BaseLabel QuanturmTimeLabel = new BaseLabel("Quenturm");			// Quanturm
	JTextField QuanturmTimeTextField = new JTextField(10);
	
	BaseLabel MaxQuanturmLabel = new BaseLabel("Max Quanturm");			// MFQ용 max Quanturm, Div
	JTextField MaxQuanturmTextField = new JTextField(10);
	
	BaseLabel DivLabel = new BaseLabel("Div");
	JTextField DivTextField = new JTextField(10);
	
	BaseLabel CoreLabel = new BaseLabel("Core");						// Core
	
	JLabel PCoreLabel = new JLabel("P");						
	JSpinner PCoreSpinner;
	JComponent PEditor;
	
	JLabel ECoreLabel = new JLabel("E");
	JSpinner ECoreSpinner;
	JComponent EEditor;
	
	public RunPanel(ProjectManager manager) {
		this.manager = manager;
		manager.runpanel = this;
		Base();															// RunPanel 초기 설정
		ComponentSetting();												// RunPanel에 있는 구성요소 설정
	}
	
	private void Base(){
		setSize(240, 201);
		setLocation(270, 10);
		setBackground(Color.YELLOW);
		setLayout(null);
	}
	
	private void ComponentSetting() {
		
		SpinnerNumberModel Pnumbermodel = new SpinnerNumberModel(0,0,4,1);			// Core Spinner
		SpinnerNumberModel Enumbermodel = new SpinnerNumberModel(1,0,4,1);

		CoreLabel.setLocation(10, 30);												// Core Label adding
		add(CoreLabel);
											
		PCoreLabel.setSize(10, 20);													// PCoreLabel
		PCoreLabel.setLocation(105,35);					
		add(PCoreLabel);
		
		PCoreSpinner = new JSpinner(Pnumbermodel);									// PCoreSpinner
		PEditor = PCoreSpinner.getEditor();
		JSpinner.DefaultEditor PspinnerEditor = (JSpinner.DefaultEditor)PEditor;		// 스피너 가운데 정렬
		PspinnerEditor.getTextField().setHorizontalAlignment(JTextField.CENTER);
		PCoreSpinner.setSize(40,25);
		PCoreSpinner.setLocation(120,35);
		add(PCoreSpinner);
														
		ECoreLabel.setSize(10, 20);													// ECoreLabel
		ECoreLabel.setLocation(175,35);
		add(ECoreLabel);
		
		ECoreSpinner = new JSpinner(Enumbermodel);									// ECoreSpinner
		EEditor = ECoreSpinner.getEditor();
		JSpinner.DefaultEditor EspinnerEditor = (JSpinner.DefaultEditor)EEditor;		// 스피너 가운데 정렬
		EspinnerEditor.getTextField().setHorizontalAlignment(JTextField.CENTER);
		ECoreSpinner.setSize(40,25);
		ECoreSpinner.setLocation(190, 35);
		add(ECoreSpinner);
		
		PCoreSpinner.addChangeListener(new SpinnerChangeListener());
		ECoreSpinner.addChangeListener(new SpinnerChangeListener());
		
		QuanturmTimeLabel.setLocation(10, 80);								// Quanturm adding
		add(QuanturmTimeLabel);
		
		QuanturmTimeTextField.setHorizontalAlignment(JTextField.CENTER);
		QuanturmTimeTextField.setLocation(120, 85);
		QuanturmTimeTextField.setSize(110, 20);
		QuanturmTimeTextField.addKeyListener(new TimeKeyListener());
		add(QuanturmTimeTextField);
		
		QuanturmTimeLabel.setVisible(false);
		QuanturmTimeTextField.setVisible(false);
		
		DivLabel.setLocation(10, 80);										// Div adding(MFQ용)
		add(DivLabel);
		
		DivTextField.setHorizontalAlignment(JTextField.CENTER);
		DivTextField.setLocation(120, 85);
		DivTextField.setSize(110, 20);
		DivTextField.addKeyListener(new TimeKeyListener());
		add(DivTextField);
		
		DivLabel.setVisible(false);
		DivTextField.setVisible(false);
		
		MaxQuanturmLabel.setLocation(10, 130);								// Max Quanturm adding(MFQ용)
		add(MaxQuanturmLabel);
		
		MaxQuanturmTextField.setHorizontalAlignment(JTextField.CENTER);
		MaxQuanturmTextField.setLocation(120, 135);
		MaxQuanturmTextField.setSize(110, 20);
		MaxQuanturmTextField.addKeyListener(new TimeKeyListener());
		add(MaxQuanturmTextField);
		
		MaxQuanturmLabel.setVisible(false);
		MaxQuanturmTextField.setVisible(false);
		
		
		RunButton.setSize(220, 30);											// RunButton adding
		RunButton.setLocation(10, 170);
		RunButton.setOpaque(true);
		RunButton.setBackground(Color.green);
		RunButton.addActionListener(new RunActionListener());
		add(RunButton);
	}
	
	private class SpinnerChangeListener implements ChangeListener{
		public void stateChanged(ChangeEvent e) {
			int PCoreCount = Integer.parseInt(PCoreSpinner.getValue().toString());
			int ECoreCount = Integer.parseInt(ECoreSpinner.getValue().toString());
			if((PCoreCount + ECoreCount) > 4) {								// 1미만, 4초과 못하게
				ECoreSpinner.setValue(Integer.parseInt(ECoreSpinner.getValue().toString())-1);
			}
			else if((PCoreCount + ECoreCount) < 1){
				ECoreSpinner.setValue(Integer.parseInt(ECoreSpinner.getValue().toString())+1);
			}
			
			if((PCoreCount + ECoreCount) == 1) {
				if(PCoreCount == 1) manager.coreLabel_1.setText("<P Core>");
				else manager.coreLabel_1.setText("<E Core>");
				manager.coreLabel_1.setLocation(10, 330);
				manager.coreLabel_2.setVisible(false);
				manager.coreLabel_3.setVisible(false);
				manager.coreLabel_4.setVisible(false);
				manager.GhanttChart_1.GhanttchartScroll.setLocation(10, 350);
				manager.GhanttChart_2.GhanttchartScroll.setVisible(false);
				manager.GhanttChart_3.GhanttchartScroll.setVisible(false);
				manager.GhanttChart_4.GhanttchartScroll.setVisible(false);
			}
			else if((PCoreCount + ECoreCount) == 2) {
				if(PCoreCount==0) {
					manager.coreLabel_1.setText("<E Core>");
					manager.coreLabel_2.setText("<E Core>");
				}
				else if(PCoreCount==1) {
					manager.coreLabel_1.setText("<E Core>");
					manager.coreLabel_2.setText("<P Core>");
				}
				else {
					manager.coreLabel_1.setText("<P Core>");
					manager.coreLabel_2.setText("<P Core>");
				}
				manager.coreLabel_2.setVisible(true);
				manager.coreLabel_3.setVisible(false);
				manager.coreLabel_4.setVisible(false);
				manager.coreLabel_1.setLocation(10, 210);
				manager.coreLabel_2.setLocation(10, 450);
				manager.GhanttChart_1.GhanttchartScroll.setLocation(10, 230);
				manager.GhanttChart_2.GhanttchartScroll.setLocation(10, 470);
				manager.GhanttChart_2.GhanttchartScroll.setVisible(true);
				manager.GhanttChart_3.GhanttchartScroll.setVisible(false);
				manager.GhanttChart_4.GhanttchartScroll.setVisible(false);
			}
			else if((PCoreCount + ECoreCount) == 3) {
				if(PCoreCount==0) {
					manager.coreLabel_1.setText("<E Core>");
					manager.coreLabel_2.setText("<E Core>");
					manager.coreLabel_3.setText("<E Core>");
				}
				else if(PCoreCount==1) {
					manager.coreLabel_1.setText("<E Core>");
					manager.coreLabel_2.setText("<E Core>");
					manager.coreLabel_3.setText("<P Core>");
				}
				else if(PCoreCount==2) {
					manager.coreLabel_1.setText("<E Core>");
					manager.coreLabel_2.setText("<P Core>");
					manager.coreLabel_3.setText("<P Core>");
				}
				else {
					manager.coreLabel_1.setText("<P Core>");
					manager.coreLabel_2.setText("<P Core>");
					manager.coreLabel_3.setText("<P Core>");
				}
				manager.coreLabel_2.setVisible(true);
				manager.coreLabel_3.setVisible(true);
				manager.coreLabel_4.setVisible(false);
				manager.coreLabel_1.setLocation(10, 180);
				manager.coreLabel_2.setLocation(10, 340);
				manager.coreLabel_3.setLocation(10, 500);
				manager.GhanttChart_1.GhanttchartScroll.setLocation(10, 200);
				manager.GhanttChart_2.GhanttchartScroll.setLocation(10, 360);
				manager.GhanttChart_3.GhanttchartScroll.setLocation(10, 520);
				manager.GhanttChart_2.GhanttchartScroll.setVisible(true);
				manager.GhanttChart_3.GhanttchartScroll.setVisible(true);
				manager.GhanttChart_4.GhanttchartScroll.setVisible(false);
			}
			else if((PCoreCount + ECoreCount) == 4) {
				if(PCoreCount==0) {
					manager.coreLabel_1.setText("<E Core>");
					manager.coreLabel_2.setText("<E Core>");
					manager.coreLabel_3.setText("<E Core>");
					manager.coreLabel_4.setText("<E Core>");
				}
				else if(PCoreCount==1) {
					manager.coreLabel_1.setText("<E Core>");
					manager.coreLabel_2.setText("<E Core>");
					manager.coreLabel_3.setText("<E Core>");
					manager.coreLabel_4.setText("<P Core>");
				}
				else if(PCoreCount==2) {
					manager.coreLabel_1.setText("<E Core>");
					manager.coreLabel_2.setText("<E Core>");
					manager.coreLabel_3.setText("<P Core>");
					manager.coreLabel_4.setText("<P Core>");
				}
				else if(PCoreCount==3) {
					manager.coreLabel_1.setText("<E Core>");
					manager.coreLabel_2.setText("<P Core>");
					manager.coreLabel_3.setText("<P Core>");
					manager.coreLabel_4.setText("<P Core>");
				}
				else {
					manager.coreLabel_1.setText("<P Core>");
					manager.coreLabel_2.setText("<P Core>");
					manager.coreLabel_3.setText("<P Core>");
					manager.coreLabel_4.setText("<P Core>");
				}
				manager.coreLabel_2.setVisible(true);
				manager.coreLabel_3.setVisible(true);
				manager.coreLabel_4.setVisible(true);
				manager.coreLabel_1.setLocation(10, 150);
				manager.coreLabel_2.setLocation(10, 300);
				manager.coreLabel_3.setLocation(10, 450);
				manager.coreLabel_4.setLocation(10, 600);
				manager.GhanttChart_1.GhanttchartScroll.setLocation(10, 170);
				manager.GhanttChart_2.GhanttchartScroll.setLocation(10, 320);
				manager.GhanttChart_3.GhanttchartScroll.setLocation(10, 470);
				manager.GhanttChart_4.GhanttchartScroll.setLocation(10, 620);
				manager.GhanttChart_2.GhanttchartScroll.setVisible(true);
				manager.GhanttChart_3.GhanttchartScroll.setVisible(true);
				manager.GhanttChart_4.GhanttchartScroll.setVisible(true);
			}
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
	
	private class RunActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int PCoreCount = Integer.parseInt(PCoreSpinner.getValue().toString());
			int ECoreCount = Integer.parseInt(ECoreSpinner.getValue().toString());
			if((manager.algorithm == null) && (manager.mfq == null)) {
				if(manager.addPanel.AlgorithmList.isEmpty() && manager.addPanel.MFQHighAlgorithmList.isEmpty() &&
						manager.addPanel.MFQMiddleAlgorithmList.isEmpty() && manager.addPanel.MFQLowAlorithmList.isEmpty()) {
					JOptionPane.showMessageDialog(null,  "Add Process", "Error", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if(manager.addPanel.SetAlgorithm == "RR") {
					if(QuanturmTimeTextField.getText().equals("")) {
						JOptionPane.showMessageDialog(null,  "Need Quanturm time", "Error", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					else QuanturmTime = Integer.parseInt(QuanturmTimeTextField.getText());
				}
				if(manager.addPanel.SetAlgorithm == "MFQ") {
					if(DivTextField.getText().equals("")) {
						JOptionPane.showMessageDialog(null,  "Need Div", "Error", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					if(MaxQuanturmTextField.getText().equals("")) {
						JOptionPane.showMessageDialog(null,  "Need Max Quanturm time", "Error", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
				}
				
				for(int i =0; i<manager.addPanel.AlgorithmList.size(); i++) {
					for(int j = 0; j<manager.addPanel.AlgorithmList.size()-1;j++) {
						if(manager.addPanel.AlgorithmList.get(j).ArrivalTime>manager.addPanel.AlgorithmList.get(j+1).ArrivalTime) {
							Process temp = manager.addPanel.AlgorithmList.get(j);
							manager.addPanel.AlgorithmList.set(j, manager.addPanel.AlgorithmList.get(j+1));
							manager.addPanel.AlgorithmList.set(j+1, temp);
						}
					}
				}
				if(manager.addPanel.SetAlgorithm == null) {
					manager.addPanel.SetAlgorithm = "FCFS";
				}
				else if(manager.addPanel.SetAlgorithm == "FCFS") manager.algorithm = new FCFS(manager, PCoreCount, ECoreCount);
				else if(manager.addPanel.SetAlgorithm == "RR") manager.algorithm = new RR(manager, QuanturmTime, PCoreCount, ECoreCount);
				else if(manager.addPanel.SetAlgorithm == "SPN") manager.algorithm = new SPN(manager, PCoreCount, ECoreCount);
				else if(manager.addPanel.SetAlgorithm == "SRTN") manager.algorithm = new SRTN(manager, PCoreCount, ECoreCount);
				else if(manager.addPanel.SetAlgorithm == "HRRN") manager.algorithm = new HRRN(manager, PCoreCount, ECoreCount);
				else if(manager.addPanel.SetAlgorithm == "MFQ") manager.mfq = new MFQ(manager, 
						Integer.parseInt(MaxQuanturmTextField.getText()), Integer.parseInt(DivTextField.getText()), PCoreCount, ECoreCount);
					
				manager.addPanel.RunningState();
				RunningState();
				
			}
		}
	}
	
	private void RunningState() {
		PCoreSpinner.disable();
		ECoreSpinner.disable();
		DivTextField.setEditable(false);
		MaxQuanturmTextField.setEditable(false);
		QuanturmTimeTextField.setEditable(false);
	}

}