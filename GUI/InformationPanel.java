package GUI;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import Manager.Renderer;
import Scheduling.MFQProcess;
import Scheduling.Process;

public class InformationPanel extends JPanel{
	String[] TableHeader = {"Process Name", "Arrival time", "Burst time", // 행
			"Waiting time", "Turnaround time", "Normalized TT"};

	DefaultTableModel model = new DefaultTableModel(TableHeader, 0);
	JTable table = new JTable(model);
	DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
	
	Renderer renderer = new Renderer();
	JScrollPane scrollpane = new JScrollPane(table);

	ArrayList<Object[]> list = new ArrayList<>();

	int InsertCount = 0;
	
	public InformationPanel() {
		Base();
		ComponentSetting();
	}
	
	private void Base() {
		setSize(700, 500);
		setLocation(10, 310);
		setBackground(Color.WHITE);
		setLayout(null);
	}
	
	private void ComponentSetting() {
		table.setSize(600, 200);
		table.setLocation(10, 20);
		table.setRowHeight(25);
		scrollpane.setBounds(2, 0, 695, 400);
		
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);	// 가운데 정렬
		TableColumnModel tcmSchedule = table.getColumnModel();
		for(int i=0; i<table.getColumnCount(); i++) {
			tcmSchedule.getColumn(i).setCellRenderer(dtcr);
		}
	
		add(scrollpane);
	}
	
	public void AddAlgorithm(Process FCFS) {
			Object[] obj = new Object[] {FCFS.Name, Double.toString(FCFS.ArrivalTime), Double.toString(FCFS.BurstTime),
					Double.toString(FCFS.WaitingTime), Double.toString(FCFS.TurnaroundTime),
					Double.toString(FCFS.NormalizedTime)};
			list.add(obj);
			if(InsertCount>=1)
			{
				for(int i = InsertCount-1; i>=0;i--) {
					((DefaultTableModel)table.getModel()).removeRow(i);
				}
			}

			if(list.size()>1) {
				for(int i =0; i<list.size(); i++) {
					for(int j = 0; j<list.size()-1;j++) {
						if(Double.parseDouble(list.get(j)[1].toString())>Double.parseDouble(list.get(j+1)[1].toString())) {
							Object[] temp = list.get(j);
							list.set(j, list.get(j+1));
							list.set(j+1, temp);
						}
					}
				}
				for(int i = 0; i<list.size(); i++) {
					model.addRow(list.get(i));
				}
				InsertCount++;
			}
			else {
				model.addRow(new Object[] {FCFS.Name, Double.toString(FCFS.ArrivalTime), Double.toString(FCFS.BurstTime),
					Double.toString(FCFS.WaitingTime), Double.toString(FCFS.TurnaroundTime),
					Double.toString(FCFS.NormalizedTime)});
				InsertCount++;
			}
			TableColumn col = table.getColumnModel().getColumn(0);
			col.setCellRenderer(new Renderer());
			

			
	}
	public void MFQAddAlgorithm(MFQProcess Process) {
		model.addRow(new Object[] {Process.Name, Double.toString(Process.ArrivalTime), Double.toString(Process.BurstTime),
				Double.toString(Process.WaitingTime), Double.toString(Process.TurnaroundTime),
				Double.toString(Process.NormalizedTime), Process.PriorityRedayQueue
				});
		
		

			

	}
	public void ChangeInformation(double TT, double WT, double NTT, int Row) {
		model.setValueAt(WT, Row, 3);
		model.setValueAt(TT, Row, 4);
		model.setValueAt(NTT, Row, 5);
	}

}
