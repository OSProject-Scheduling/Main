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
	LinkedList<Process> AlgorithmList = new LinkedList<>();
	Process AddProcess;
	ProjectManager manager;
	public ActionPanel(ProjectManager manager) {
//		AlgorithmList.add(new Process("P1", 0, 3));
//		AlgorithmList.add(new Process("P2", 1, 7));
//		AlgorithmList.add(new Process("P3", 3, 2));
//		AlgorithmList.add(new Process("P4", 5, 5));
//		AlgorithmList.add(new Process("P5", 6, 3));
		this.manager = manager;
		manager.action = this;
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
	
	private class AddActionListener  implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(manager.input.AlgorithmSetting().ArrivalTime == -1) {
				JOptionPane.showMessageDialog(null,  "Fill in the blanks.", "Error", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				AddProcess = manager.input.AlgorithmSetting();
				AlgorithmList.add(AddProcess);
				manager.information.AddAlgorithm(AddProcess);
				manager.input.Update();
			}
		}
	}
	
	private class RunActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(AlgorithmList.isEmpty()) {
				JOptionPane.showMessageDialog(null,  "Add Process", "Error", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
					if(manager.input.SetAlgorithm.equals(""))
						manager.input.SetAlgorithm = "FCFS";
					if(manager.input.SetAlgorithm == "FCFS") new FCFS(manager);
					else if(manager.input.SetAlgorithm == "RR") new RR(manager,manager.input.QuanturmTime);
					else if(manager.input.SetAlgorithm == "SPN") new SPN(manager);
					else if(manager.input.SetAlgorithm == "SRTN") new SRTN(manager);
					else if(manager.input.SetAlgorithm == "HRRN") new HRRN(manager);
			}
		}
	}
}