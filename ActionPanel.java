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
	LinkedList<FCFSProcess> FCFSList = new LinkedList<>();
	InformationPanel informationpanel;
	GhanttChartPanel ghanttchartpanel;
	FCFSProcess FCFS;
	
	public ActionPanel(InputPanel inputpanel, InformationPanel informationpanel, 
			GhanttChartPanel ghanttchartpanel) {
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
				FCFS = inputpanel.AlgorithmSetting();
				FCFSList.add(FCFS);
				informationpanel.AddAlgorithm(FCFS);
				inputpanel.Update();
			}
		}
	}
	
	private class RunActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(FCFSList.isEmpty()) {
				JOptionPane.showMessageDialog(null,  "Add Process", "Error", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				new FCFS(FCFSList, ghanttchartpanel);
			}
		}
	}
}
