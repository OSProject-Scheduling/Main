import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class ActionPanel extends JPanel{
	JButton AddButton = new JButton("Add");
	JButton RunButton = new JButton("Run");
	InputPanel inputpanel;
	LinkedList<Process> AlgorithmList = new LinkedList<>();
	InformationPanel informationpanel;
	GhanttChartPanel ghanttchartpanel;
	Process AddProcess;
	
	public ActionPanel(InputPanel inputpanel, InformationPanel informationpanel, 
			GhanttChartPanel ghanttchartpanel) {
		AlgorithmList.add(new Process("P1", 0, 3));
		AlgorithmList.add(new Process("P2", 1, 7));
		AlgorithmList.add(new Process("P3", 3, 2));
		AlgorithmList.add(new Process("P4", 5, 5));
		AlgorithmList.add(new Process("P5", 6, 3));
		this.inputpanel = inputpanel;
		this.informationpanel = informationpanel;
		this.ghanttchartpanel = ghanttchartpanel;
		Base();
		ComponentSetting();
	}
	
	private void Base(){
		setSize(240, 200);
		setLocation(10, 450);
		setBackground(Color.blue);
		setLayout(null);
	}
	
	private void ComponentSetting() {
		AddButton.setSize(105, 20);
		AddButton.setLocation(10, 30);
		AddButton.setOpaque(true);
		AddButton.setBackground(Color.green);
		AddButton.addActionListener(new AddActionListener());
		add(AddButton);
		
		RunButton.setSize(105, 20);
		RunButton.setLocation(125, 30);
		RunButton.setOpaque(true);
		RunButton.setBackground(Color.green);
		RunButton.addActionListener(new RunActionListener());
		add(RunButton);
	}
	
	private class AddActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(inputpanel.AlgorithmSetting().ArrivalTime == -1) {
				JOptionPane.showMessageDialog(null,  "Fill in the blanks.", "Error", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				AddProcess = inputpanel.AlgorithmSetting();
				AlgorithmList.add(AddProcess);
				informationpanel.AddAlgorithm(AddProcess);
				inputpanel.Update();
			}
		}
	}
	
	private class RunActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(AlgorithmList.isEmpty()) {
				JOptionPane.showMessageDialog(null,  "Add Process", "Error", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
					if(inputpanel.SetAlgorithm.equals(""))
						inputpanel.SetAlgorithm = "FCFS";
					ghanttchartpanel.information = informationpanel;
					if(inputpanel.SetAlgorithm == "FCFS") new FCFS(AlgorithmList, ghanttchartpanel);
					else if(inputpanel.SetAlgorithm == "SPN") new SPN(AlgorithmList, ghanttchartpanel);
					else if(inputpanel.SetAlgorithm == "SRTN") new SRTN(AlgorithmList, ghanttchartpanel);
					else if(inputpanel.SetAlgorithm == "HRRN") new HRRN(AlgorithmList, ghanttchartpanel);
			}
		}
	}
}
