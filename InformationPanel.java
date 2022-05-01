import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class InformationPanel extends JPanel{
	String[] TableHeader = {"Process Name", "Arrival time(AT)", "Burst time(BT)", 
			"Waiting time(WT)", "Turnaround time(TT)", "Normalized TT(NTT)"};

	DefaultTableModel model = new DefaultTableModel(TableHeader, 0);
	JTable table = new JTable(model);
	DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
	
	JScrollPane scrollpane = new JScrollPane(table);
	
	public InformationPanel() {
		Base();
		ComponentSetting();
	}
	
	private void Base() {
		setSize(700, 500);
		setLocation(10, 280);
		setLayout(null);
	}
	
	private void ComponentSetting() {
		table.setSize(600, 200);
		table.setLocation(10, 20);
		table.setRowHeight(25);
		
		scrollpane.setBounds(2, 0, 695, 400);
		
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);	// ���̺� ��� ����
		TableColumnModel tcmSchedule = table.getColumnModel();
		for(int i=0; i<table.getColumnCount(); i++) {
			tcmSchedule.getColumn(i).setCellRenderer(dtcr);
		}
	
		add(scrollpane);
	}
	
	public void AddAlgorithm(Process FCFS) {
			model.addRow(new Object[] {FCFS.Name, Double.toString(FCFS.ArrivalTime), Double.toString(FCFS.BurstTime),
				Double.toString(FCFS.WaitingTime), Double.toString(FCFS.TurnaroundTime),
				Double.toString(FCFS.NormalizedTime)
				});
	}
	public void ChangeInformation(double TT, double WT, double NTT, int Row) {
		model.setValueAt(WT, Row-1, 3);
		model.setValueAt(TT, Row-1, 4);
		model.setValueAt(NTT, Row-1, 5);
	}	
}
