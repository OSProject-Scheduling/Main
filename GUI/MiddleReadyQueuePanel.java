package GUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Scheduling.Process;

public class MiddleReadyQueuePanel extends JPanel{
	
	JScrollPane ReadyQueueScrollBar;
	JLabel MiddleReadyQueueLabel;
	int PrevProcessList;
	
	public MiddleReadyQueuePanel() {
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		MiddleReadyQueueLabel = new JLabel("<MiddleReadyQueue>");
		MiddleReadyQueueLabel.setLocation(250,10);
		MiddleReadyQueueLabel.setBackground(Color.WHITE);
		MiddleReadyQueueLabel.setSize(100,20);
		add(MiddleReadyQueueLabel);
		MiddleReadyQueueLabel.setVisible(false);
		
	}
	public void ScrollSetting(JScrollPane ReadyQueueScrollbar) {
		this.ReadyQueueScrollBar = ReadyQueueScrollbar;
		ReadyQueueScrollBar.setVisible(false);
	}
	
	public void create_form(LinkedList<Process> ProcessList) {
		removeAll();
		repaint();
		if(ProcessList.size()>8) {
			ReadyQueueScrollBar.setBounds(250,30,220, 100);
			setPreferredSize(new Dimension(621+80*(ProcessList.size()-8), getHeight()));
			ReadyQueueScrollBar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		}
		else {
			ReadyQueueScrollBar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			ReadyQueueScrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
			ReadyQueueScrollBar.setBounds(250,30,220, 83);
		}
		if (ProcessList.size() > 0) {
			for (int i = 0; i < ProcessList.size(); i++) {
				JLabel space = new JLabel();
				JLabel label = new JLabel(ProcessList.get(i).Name);
				label.setHorizontalAlignment(JLabel.CENTER);
				label.setOpaque(true);
				label.setBackground(Color.yellow);
				label.setPreferredSize(new Dimension(60, 80));
				space.setPreferredSize(new Dimension(20,80));
				add(label);
				if(i <= ProcessList.size()-2) add(space);
			}
			
			revalidate();
			if(PrevProcessList > ProcessList.size()) ReadyQueueScrollBar.getHorizontalScrollBar().setValue(0);
			else ReadyQueueScrollBar.getHorizontalScrollBar().setValue(ReadyQueueScrollBar.getHorizontalScrollBar().getMaximum());
		}
		else {
			removeAll();
		}
		
		PrevProcessList = ProcessList.size();
	}
}
